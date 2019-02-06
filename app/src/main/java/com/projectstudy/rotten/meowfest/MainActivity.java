package com.projectstudy.rotten.meowfest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private String endpoint;
    private String titleArr[];
    private String descArr[];
    private String dateArr[];
    private String imgUrlArr[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AzureServiceAdapter.Initialize(this);

        endpoint = "https://chex-triplebyte.herokuapp.com/api/cats?page=0";

    }

}
