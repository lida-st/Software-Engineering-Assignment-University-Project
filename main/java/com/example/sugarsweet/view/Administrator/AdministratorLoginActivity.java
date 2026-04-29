package com.example.sugarsweet.view.Administrator;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sugarsweet.R;
import com.example.sugarsweet.dao.UsersDAO;
import com.example.sugarsweet.domain.Administrator;
import com.example.sugarsweet.domain.Diabetic;
import com.example.sugarsweet.domain.User;
import com.example.sugarsweet.memorydao.MemoryInitializer;

public class AdministratorLoginActivity extends AppCompatActivity {

    private UsersDAO usersDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_administrator_login);

        EditText passwordInput = findViewById(R.id.passwordInput);
        Button submitButton = findViewById(R.id.submitButton);

        usersDAO = new MemoryInitializer().getUsersDAO();

        submitButton.setOnClickListener(v -> {
            String password = passwordInput.getText().toString();
            if (password.length() < 8) {
                passwordInput.setError("Password must be exactly 8 characters long");
            }
            User user = usersDAO.find(password);  // Fetch user based on password

            if (user != null) {
                if (user instanceof Administrator) {
                    Toast.makeText(this, "Administrator login successful!", Toast.LENGTH_SHORT).show();
                    openAdministratorMenu();
                } else if (user instanceof Diabetic) {
                    Toast.makeText(this, "Error! Password belongs to a Diabetic.", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Invalid password or login
                Toast.makeText(this, "Invalid password. Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openAdministratorMenu() {
        Intent i = new Intent(this, AdministratorMenuActivity.class);
        startActivity(i);
    }
    
}