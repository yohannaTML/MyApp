package com.example.broadway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.broadway.model.Musical;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar loader;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        loader = findViewById(R.id.loader_main_activity);

        controller = new MainController(this);
        controller.onCreate();
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
    }

    public void showLoader(){
        loader.setVisibility(View.VISIBLE);
    }

    public void hideLoader(){
        loader.setVisibility(View.GONE);
    }

    public void showList(List<Musical> list){
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new MyAdapter(MainActivity.this, list, new OnItemClickListener() {
            @Override
            public void onItemClick(Musical item) {
                Toast.makeText(getApplicationContext(),item.getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("theatre", item.getTheatre());
                intent.putExtra("story", item.getStory());
                intent.putExtra("card", item.getImagecard_url());


                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);

    }
}
