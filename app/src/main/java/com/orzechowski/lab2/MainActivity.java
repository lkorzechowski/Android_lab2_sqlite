package com.orzechowski.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.Menu;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity
        implements ElementListAdapter.OnItemClickListener
{
    private ElementViewModel mModel;
    private ElementListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler);
        mAdapter = new ElementListAdapter(this);
        recycler.setAdapter(mAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        mModel = new ViewModelProvider(this).get(ElementViewModel.class);
        mModel.getAllElements().observe(this, elements -> {
            mAdapter.setElementList(elements);
        });

        FloatingActionButton linkToAdd = findViewById(R.id.fabmain);
        linkToAdd.setOnClickListener(v -> {
            Intent add = new Intent(MainActivity.this, AddPhone.class);
            startActivity(add);
        });

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper
                .SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //delete
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recycler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        int id = item.getItemId();

        if(id == R.id.clear){
            mModel.deleteAll();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle data = getIntent().getExtras();
        if(data!=null) {
            mModel.insert(new Phones(data.getString("manufacturer"), data.getString("model"),
                    data.getString("version"), data.getString("website")));
        }
    }

    @Override
    public void onItemClickListener(Phones phone) {

    }
}