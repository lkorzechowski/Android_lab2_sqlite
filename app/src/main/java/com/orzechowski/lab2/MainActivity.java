package com.orzechowski.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.Menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
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
}