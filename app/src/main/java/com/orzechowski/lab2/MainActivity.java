package com.orzechowski.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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
    private Intent add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = new Intent(MainActivity.this, AddPhone.class);

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
                mModel.delete(mModel.getAllElements().getValue().remove(viewHolder.getAdapterPosition()));
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
            Phones obtainedPhone = new Phones(data.getString("manufacturer"),
                    data.getString("model"), data.getString("version"),
                    data.getString("website"));

            if(!data.getBoolean("editing")) {
                mModel.insert(obtainedPhone);
            } else {
                obtainedPhone.setId(data.getLong("id"));
                mModel.update(obtainedPhone);
            }
        }
    }

    @Override
    public void onItemClickListener(Phones phone) {
        Bundle bundle = new Bundle();
        bundle.putLong("id", phone.getId());
        bundle.putString("manufacturer", phone.getProducent());
        bundle.putString("model", phone.getModel());
        bundle.putString("version", phone.getWersja());
        bundle.putString("website", phone.getStrona());
        add.putExtras(bundle);
        startActivity(add);
    }
}