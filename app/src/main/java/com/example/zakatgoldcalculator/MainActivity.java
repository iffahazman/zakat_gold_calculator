package com.example.zakatgoldcalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private EditText etGoldWeight, etGoldPrice;
    private RadioGroup rgGoldType;
    private RadioButton rbKeep, rbWear;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up Toolbar as ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Optional: Set the title of the ActionBar
        getSupportActionBar().setTitle("Zakat Gold Calculator");

        // Initializing views
        etGoldWeight = findViewById(R.id.etGoldWeight);
        etGoldPrice = findViewById(R.id.etGoldPrice);
        rgGoldType = findViewById(R.id.rgGoldType);
        rbKeep = findViewById(R.id.rbKeep);
        rbWear = findViewById(R.id.rbWear);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        // Set click listener for the calculate button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get inputs from the user
                String weightStr = etGoldWeight.getText().toString().trim();
                String priceStr = etGoldPrice.getText().toString().trim();

                // Validate inputs
                if (weightStr.isEmpty() || priceStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double goldWeight = Double.parseDouble(weightStr);
                double goldPrice = Double.parseDouble(priceStr);

                // Determine type of gold (keep or wear)
                int uruf = 0; // Uruf value for calculation
                if (rgGoldType.getCheckedRadioButtonId() == R.id.rbKeep) {
                    uruf = 85; // Gold Keeping uruf
                } else if (rgGoldType.getCheckedRadioButtonId() == R.id.rbWear) {
                    uruf = 200; // Gold Wearing uruf
                } else {
                    Toast.makeText(MainActivity.this, "Please select the type of gold", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calculate total gold value
                double totalGoldValue = goldWeight * goldPrice;

                // Calculate zakat payable
                double goldMinusX = goldWeight - uruf;
                if (goldMinusX < 0) {
                    goldMinusX = 0; // No zakat payable if the result is less than zero
                }

                double zakatPayable = goldMinusX * goldPrice;
                double totalZakat = zakatPayable * 0.025; // Zakat is 2.5%

                // Display result
                String resultText = "Total Gold Value: RM" + totalGoldValue + "\n" +
                        "Gold Value that is Zakat Payable: RM" + zakatPayable + "\n" +
                        "Total Zakat Payable: RM" + totalZakat;
                tvResult.setText(resultText);
            }
        });
    }

    // Create the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Handle the menu item selection
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {
            // Handle the share action
            shareApp();
            return true;
        } else if (id == R.id.action_about) {
            // Handle the about action
            openAboutPage();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Method to share the app
    private void shareApp() {
        // Prepare the sharing Intent
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Zakat Gold Calculator");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this app: <https://github.com/iffahazman/Zakat-Gold-Calculator.git>");
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    // Method to open the About page (you need to create AboutActivity)
    private void openAboutPage() {
        // Start the About Activity (assuming you have an AboutActivity)
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }
}