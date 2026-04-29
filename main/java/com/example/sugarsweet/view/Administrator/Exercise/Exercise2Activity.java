package com.example.sugarsweet.view.Administrator.Exercise;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sugarsweet.R;
import com.example.sugarsweet.dao.ExercisesDAO;
import com.example.sugarsweet.dao.UsersDAO;
import com.example.sugarsweet.domain.Exercise;
import com.example.sugarsweet.memorydao.MemoryInitializer;

public class Exercise2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise2);

        ExercisesDAO exercisesDAO = new MemoryInitializer().getExercisesDAO();

        EditText exerciseName = findViewById(R.id.editText);
        EditText consumptionPerMinute = findViewById(R.id.editText2);

        Button insertButton = findViewById(R.id.InsertButton);
        Button cancelButton = findViewById(R.id.CancelButton);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = exerciseName.getText().toString().trim();
                String consumptionStr = consumptionPerMinute.getText().toString().trim();

                if (name.isEmpty() || consumptionStr.isEmpty()) {
                    Toast.makeText(Exercise2Activity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int consumption;
                try {
                    consumption = Integer.parseInt(consumptionStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(Exercise2Activity.this, "Invalid consumption value!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String upperCaseName = name.toUpperCase();
                Exercise exercise = new Exercise(upperCaseName, consumption);

                // Check if exercise already exists (case-insensitive)
                Exercise existingExercise = exercisesDAO.find(name);
                if (existingExercise != null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Exercise2Activity.this);
                    builder.setTitle("Exercise already exists!");
                    builder.setMessage("Clicking OK will update the glucose consumption to the number you provided.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Action for "OK" button
                            existingExercise.setConsumptionPerMinute(consumption);
                            Toast.makeText(Exercise2Activity.this, "Exercise updated successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            finish();}
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog, int which) {
                            // Action for "Cancel" button
                            dialog.dismiss();
                        }
                    });
                    builder.create().show(); // Create the Dialog and display it to the user

                } else {
                    // Add new exercise if it doesn't exist
                    exercisesDAO.save(name, exercise);
                    Toast.makeText(Exercise2Activity.this, "New exercise added successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });


        // Cancel Button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Close the activity
            }
        });
    }
}

