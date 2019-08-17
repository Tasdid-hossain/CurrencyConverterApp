package com.example.user.task2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String KEY = "IMAGE";
    private ArrayList<countryPic> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countries=new ArrayList<countryPic>();
        populateList();

        GridView gridView = (GridView) findViewById(R.id.gridLayout);
        final GridAdapter gridAdapter=new GridAdapter(countries,this);
        gridView.setAdapter(gridAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra(KEY, countries.get(position).getUrl());
                startActivity(i);
            }
        });
    }

    private void populateList(){
        countries.add(new countryPic("http://172.16.62.200/images/germany.png"));
        countries.add(new countryPic("http://172.16.62.200/images/indonesia.png"));
        countries.add(new countryPic("http://172.16.62.200/images/japan.png"));
        countries.add(new countryPic("http://172.16.62.200/images/sarawak.png"));
        countries.add(new countryPic("http://172.16.62.200/images/singapore.png"));
        countries.add(new countryPic("http://172.16.62.200/images/switzerland.png"));
    }

}
