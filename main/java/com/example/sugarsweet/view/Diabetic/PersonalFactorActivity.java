package com.example.sugarsweet.view.Diabetic;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sugarsweet.R;
import com.example.sugarsweet.domain.User;


public class PersonalFactorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_factor);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User", User.class);

        EditText personalFactorInput = findViewById(R.id.personalFactorIn);
        Button SubmitButton = findViewById(R.id.submitButton);
        TextView tv = findViewById(R.id.textView5);

        tv.setText(user.getPersonalFactor().toString());

        SubmitButton.setOnClickListener(v -> {
            String personalFactor = personalFactorInput.getText().toString();
            if (Float.parseFloat(personalFactor)==0 || personalFactor.isEmpty()){
                personalFactorInput.setError("Personal factor should be a number greater than 0");
            } else {
                user.setPersonalFactor(Float.valueOf(personalFactor));
                Toast.makeText(this, "The new personal factor is: "+user.getPersonalFactor().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}