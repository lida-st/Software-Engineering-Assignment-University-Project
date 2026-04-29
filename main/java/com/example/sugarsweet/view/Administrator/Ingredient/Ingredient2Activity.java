package com.example.sugarsweet.view.Administrator.Ingredient;

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
import com.example.sugarsweet.dao.IngredientsDAO;
import com.example.sugarsweet.domain.Ingredient;
import com.example.sugarsweet.memorydao.MemoryInitializer;

public class Ingredient2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingredient2);

        IngredientsDAO ingredientsDAO = new MemoryInitializer().getIngredientsDAO();

        EditText ingredientName = findViewById(R.id.editText);
        EditText carbsPerGram = findViewById(R.id.editText2);

        Button insertButton = findViewById(R.id.InsertButton);
        Button cancelButton = findViewById(R.id.CancelButton);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ingredientName.getText().toString().trim();
                String carbsStr = carbsPerGram.getText().toString().trim();

                if (name.isEmpty() || carbsStr.isEmpty()) {
                    Toast.makeText(Ingredient2Activity.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                    return;
                }
                int carbs = 0;
                try {
                    carbs = Integer.parseInt(carbsStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(Ingredient2Activity.this, "Invalid carbohydrates value!", Toast.LENGTH_SHORT).show();
                }

                String upperCaseName = name.toUpperCase();
                Ingredient ingredient = new Ingredient(upperCaseName, carbs);

                //Check if ingredient exists (case-insensitive)
                Ingredient existingIngredient = ingredientsDAO.find(name);
                if (existingIngredient != null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Ingredient2Activity.this);
                    builder.setTitle("Ingredient already exists!");
                    builder.setMessage("Clicking OK will update the carbohydrates to the number you provided.");
                    int finalCarbs = carbs;
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Action for "OK" button
                            existingIngredient.setCarbohydratesPerGram(finalCarbs);
                            Toast.makeText(Ingredient2Activity.this, "Ingredient updated successfully!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            finish();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Action for "Cancel" button
                            dialog.dismiss();
                        }
                    });
                    builder.create().show(); // Create the Dialog and display it to the user

                } else {
                    ingredientsDAO.save(name, ingredient);
                    Toast.makeText(Ingredient2Activity.this, "New ingredient added successfully!", Toast.LENGTH_SHORT).show();
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

