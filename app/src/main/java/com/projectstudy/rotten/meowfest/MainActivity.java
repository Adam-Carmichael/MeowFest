package com.projectstudy.rotten.meowfest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    private String endpoint = "https://chex-triplebyte.herokuapp.com/api/cats?page=0";
    private String data;

    // temp variables to keep track of what i need to store in database
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

        // initialize mobile service client for azure backend
        AzureServiceAdapter.Initialize(this);

        // methods
        data = fetchDataFromUrl(endpoint);
        System.out.println(data);

        // to make sure activity is loaded
        Button tempButton = findViewById(R.id.tempExitButton);
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    // Retrieves information from passed-in URL, returning it as a string or null
    public String fetchDataFromUrl(String someUrl) {
        HttpsURLConnection conn = null;
        try {
            URL url = new URL(someUrl);
            conn = (HttpsURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
