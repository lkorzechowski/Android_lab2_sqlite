package com.orzechowski.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPhone extends AppCompatActivity {

    private Intent back;
    private EditText InputManufacturer, InputModel, InputVersion, InputWebsite;
    private boolean editing;
    private Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        back = new Intent(this, MainActivity.class);
        editing = false;

        InputManufacturer = findViewById(R.id.manufactorer_input);
        InputModel = findViewById(R.id.model_input);
        InputVersion = findViewById(R.id.version_input);
        InputWebsite = findViewById(R.id.website_input);

        Button submit = findViewById(R.id.save_button);
        submit.setOnClickListener(v -> {
            String manufacturer = InputManufacturer.getText().toString();
            String model = InputModel.getText().toString();
            String version = InputVersion.getText().toString();
            String website = InputWebsite.getText().toString();

            if(manufacturer.length()<1){
                Toast.makeText(this, "Insert a manufacturer", Toast.LENGTH_LONG).show();
            }
            else if(model.length()<1){
                Toast.makeText(this, "Insert a model", Toast.LENGTH_LONG).show();
            }
            else if(version.length()<1){
                Toast.makeText(this, "Insert Android version", Toast.LENGTH_LONG).show();
            }
            else if(website.length()<1){
                Toast.makeText(this, "Insert a website", Toast.LENGTH_LONG).show();
            }
            else{
                Bundle toMain = new Bundle();
                toMain.putString("manufacturer", manufacturer);
                toMain.putString("model", model);
                toMain.putString("version", version);
                toMain.putString("website", website);
                toMain.putBoolean("editing", editing);

                if(editing){
                    getIntent().getExtras();
                    long id = data.getLong("id");
                    toMain.putLong("id", id);
                }

                back.putExtras(toMain);
                startActivity(back);
            }
        });

        Button cancel = findViewById(R.id.cancel_button);
        cancel.setOnClickListener(v -> startActivity(back));

        Button web = findViewById(R.id.website_button);

        web.setOnClickListener(v -> {
            String url = InputWebsite.getText().toString();
            if (!url.startsWith("https://") && !url.startsWith("http://")) url = "http://" + url;
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        data = getIntent().getExtras();
        if(data != null) {
            InputManufacturer.setText(data.getString("manufacturer"));
            InputModel.setText(data.getString("model"));
            InputVersion.setText(data.getString("version"));
            InputWebsite.setText(data.getString("website"));
            editing = true;
        } else editing = false;
    }
}