package com.example.railroads.railroads;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AdventurePlayActivity extends Activity {
    private RecyclerView rv;
    private RecyclerView.Adapter mRecViewAdapter;
    private RecyclerView.LayoutManager mRecViewLayoutManager;

    ArrayList<AdvQuestion> questionDataSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adventure_play);

        rv = (RecyclerView) findViewById(R.id.recycler_view);

        questionDataSet = new ArrayList<>();

        questionDataSet.add(questionDataSet.size(), new AdvQuestion("This is question 1!", 0));

        mRecViewAdapter = new CustomAdapter(questionDataSet);
        mRecViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mRecViewLayoutManager);
        rv.setAdapter(mRecViewAdapter);
        mRecViewAdapter.notifyDataSetChanged();















        
    }
}
