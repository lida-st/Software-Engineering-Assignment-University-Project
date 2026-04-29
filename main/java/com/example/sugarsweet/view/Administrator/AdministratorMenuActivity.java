package com.example.sugarsweet.view.Administrator;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sugarsweet.R;
import com.example.sugarsweet.view.Administrator.Exercise.ExerciseActivity;
import com.example.sugarsweet.view.Administrator.Ingredient.IngredientActivity;


public class AdministratorMenuActivity extends AppCompatActivity {

    ListView singleChoiceListView;
    String[] options = {"Insert new exercise", "Insert new ingredient"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_administrator_menu);

        singleChoiceListView = findViewById(R.id.checkableListView);
        Button ContinueButton = findViewById(R.id.ContinueButton);

        // Using a built-in layout for single-choice items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,
                options
        );

        singleChoiceListView.setAdapter(adapter);

        singleChoiceListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = options[position];
            Toast.makeText(getApplicationContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            ContinueButton.setOnClickListener(v -> {

                //Moving on to the appropriate activity based on the users choice
                if (selectedItem.equals("Insert new exercise")) {
                    go("exercise");
                } else if (selectedItem.equals("Insert new ingredient")) {
                    go("ingredient");
                }

            });
        });

    }

    public void go(String option) {
        Intent intent;
        if (option.equals("exercise")) {
            intent = new Intent(this, ExerciseActivity.class);
        } else if (option.equals("ingredient")) {
            intent = new Intent(this, IngredientActivity.class);  //
        } else {
            return;
        }
        startActivity(intent);
    }

}