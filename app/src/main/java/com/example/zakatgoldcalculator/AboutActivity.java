package com.example.zakatgoldcalculator;

import android.content.Intent;
import android.net.Uri; // Import this for URI handling
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View; // Import this for the View parameter

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Initialize the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the title to "About"
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("About");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the "back" button
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    // Handle back button press on the toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Handle back button click to return to the previous activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Add this method to handle the GitHub link click
    public void openGitHub(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/iffahazman/zakat_gold_calculator.git"));
        startActivity(browserIntent);
    }
}
