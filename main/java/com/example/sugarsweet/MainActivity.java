package com.example.sugarsweet;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sugarsweet.memorydao.MemoryInitializer;
import com.example.sugarsweet.view.Administrator.AdministratorLoginActivity;
import com.example.sugarsweet.view.Diabetic.DiabeticLoginActivity;

public class MainActivity extends AppCompatActivity {
    private static boolean initialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!initialized) {
            new MemoryInitializer().prepareData();
            initialized = true;
        }

        Button administratorButton = findViewById(R.id.AdministratorLogin);
        Button diabeticButton = findViewById(R.id.DiabeticLogin);

        administratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("administrator");
            }
        });

        diabeticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login("diabetic");
            }
        });
    }

    private void initializeData() {
        new MemoryInitializer().prepareData();
    }

    public void login(String userType) {
        Intent intent;
        if (userType.equals("administrator")) {
            intent = new Intent(this, AdministratorLoginActivity.class);
        } else if (userType.equals("diabetic")) {
            intent = new Intent(this, DiabeticLoginActivity.class);
        } else {
            return;
        }
        startActivity(intent);
    }

}