package com.orzechowski.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPhone extends AppCompatActivity {

    Intent back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);

        back = new Intent(this, MainActivity.class);

        EditText InputManufacturer = findViewById(R.id.manufactorer_input);
        EditText InputModel = findViewById(R.id.model_input);
        EditText InputVersion = findViewById(R.id.version_input);
        EditText InputWebsite = findViewById(R.id.website_input);

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
                back.putExtras(toMain);
                startActivity(back);
            }
        });

        Button cancel = findViewById(R.id.cancel_button);
        cancel.setOnClickListener(v -> startActivity(back));
    }
}