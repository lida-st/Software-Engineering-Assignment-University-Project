package com.example.sugarsweet.view.Diabetic;

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
import com.example.sugarsweet.dao.MeasurementsDAO;
import com.example.sugarsweet.domain.BloodSugarLog;
import com.example.sugarsweet.domain.User;
import com.example.sugarsweet.memorydao.MemoryInitializer;
import com.example.sugarsweet.util.Tuple;
import com.example.sugarsweet.view.Diabetic.Statistics.StatisticsActivity;

import java.util.ArrayList;

public class DiabeticMenuActivity extends AppCompatActivity {

    ListView singleChoiceListView;
    String[] options = {"Change personal factor", "Register new sugar measurement", "Register new meal", "Register new exercise", "View statistics"};

    private MeasurementsDAO measurementsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_diabetic_menu);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User", User.class);

        singleChoiceListView = findViewById(R.id.checkableListView);
        Button ContinueButton = findViewById(R.id.ContinueButton);

        // Using a built-in layout for single-choice items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,
                options
        );

        singleChoiceListView.setAdapter(adapter);

        measurementsDAO = new MemoryInitializer().getMeasurementsDAO();
        ArrayList<Tuple<String, BloodSugarLog>> measurements = measurementsDAO.findAll(user.getPassword());

        singleChoiceListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = options[position];
            Toast.makeText(getApplicationContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            ContinueButton.setOnClickListener(v -> {

                //Moving on to the appropriate activity based on the users choice
                switch (selectedItem) {
                    case "Change personal factor":
                        go("personal factor",user);
                        break;
                    case "Register new sugar measurement":
                        go("measurement",user);
                        break;
                    case "Register new meal":
                        go("meal",user);
                        break;
                    case "Register new exercise":
                        go("exercise",user);
                        break;
                    case "View statistics":
                        if (measurements.isEmpty()){
                            ContinueButton.setError("There are no registered measurements");
                        } else {
                            go("statistics",user);
                        }
                        break;
                }
            });
        });

    }

    public void go(String option,User user) {
        Intent intent;
        switch (option) {
            case "personal factor":
                intent = new Intent(this, PersonalFactorActivity.class);
                intent.putExtra("User", user);
                break;
            case "measurement":
                intent = new Intent(this, SugarMeasurementActivity.class);
                intent.putExtra("User", user);
                break;
            case "statistics":
                intent = new Intent(this, StatisticsActivity.class);
                intent.putExtra("User", user);
                break;
            case "meal":
                intent = new Intent(this, MealLogActivity.class);
                intent.putExtra("User", user);
                break;
            case "exercise":
                intent = new Intent(this, ExerciseLogActivity.class);
                intent.putExtra("User", user);
                break;
            default:
                return;
        }
        startActivity(intent);
    }

}