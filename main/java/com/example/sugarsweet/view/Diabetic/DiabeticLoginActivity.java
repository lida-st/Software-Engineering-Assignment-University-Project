package com.example.sugarsweet.view.Diabetic;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sugarsweet.R;
import com.example.sugarsweet.dao.*;
import com.example.sugarsweet.domain.Administrator;
import com.example.sugarsweet.domain.Diabetic;
import com.example.sugarsweet.domain.User;
import com.example.sugarsweet.memorydao.*;

public class DiabeticLoginActivity extends AppCompatActivity {

    private UsersDAO usersDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_diabetic_login);

        EditText passwordInput = findViewById(R.id.passwordInput);
        EditText personalFactorInput = findViewById(R.id.personalFactorInput);
        Button submitButton = findViewById(R.id.submitButton);

        usersDAO = new MemoryInitializer().getUsersDAO();

        submitButton.setOnClickListener(v -> {
            String password = passwordInput.getText().toString();
            String personalFactor = personalFactorInput.getText().toString();
            Log.d("InputPassword", "Entered password: " + password);
            if (password.length() < 8) {
                passwordInput.setError("Password must be exactly 8 characters long");
            } else if (personalFactor.isEmpty() || Float.parseFloat(personalFactor) ==0){
                personalFactorInput.setError("Personal factor should be a number greater than 0");
            } else {
                User user = usersDAO.find(password);  // Fetch user based on password
                if (user != null) {
                    if (user instanceof Diabetic) {
                        Toast.makeText(this, "Diabetic login successful!", Toast.LENGTH_SHORT).show();
                        user.setPersonalFactor(Float.valueOf(personalFactor));
                        openDiabeticMenu(user);
                    } else if (user instanceof Administrator) {
                        Toast.makeText(this, "Error! Password belongs to an Administrator.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Invalid password or login
                    Toast.makeText(this, "Invalid password. Try again.", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void openDiabeticMenu(User user) {
        Intent i = new Intent(this, DiabeticMenuActivity.class);
        i.putExtra("User", user);
        startActivity(i);
    }

}