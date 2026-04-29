package com.example.sugarsweet.view.Diabetic;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sugarsweet.R;
import com.example.sugarsweet.dao.ExercisesDAO;
import com.example.sugarsweet.domain.Exercise;
import com.example.sugarsweet.domain.ExerciseLog;
import com.example.sugarsweet.domain.InsulinAdministration;
import com.example.sugarsweet.domain.User;
import com.example.sugarsweet.memorydao.MemoryInitializer;

import java.util.ArrayList;
import java.util.List;

public class ExerciseLogActivity extends AppCompatActivity {

    ListView singleChoiceListView;
    private List<Exercise> exerciseList;
    private ExercisesDAO exercisesDAO;
    private Exercise ex;
    private int option;
    private boolean used;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_log);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User", User.class);

        EditText sugarLevelInput = findViewById(R.id.sugarLevelIn);
        EditText durationInput = findViewById(R.id.durationIn);
        Button FinishButton = findViewById(R.id.finishButton);
        TextView tv = findViewById(R.id.advicetextview);
        TextView tv2 = findViewById(R.id.changeTextView);

        exercisesDAO = new MemoryInitializer().getExercisesDAO();

        exerciseList = new ArrayList<>();
        loadExercises();

        singleChoiceListView = findViewById(R.id.checkableListView);

        List<String> exerciseNames = new ArrayList<>();
        for (Exercise exercise : exerciseList) {
            exerciseNames.add(exercise.getExerciseName());  // Using the getter for the name
        }

        exerciseNames.add("OTHER");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,  // Enables single choice
                exerciseNames
        );

        singleChoiceListView.setAdapter(adapter);

        singleChoiceListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        singleChoiceListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedExerciseName = exerciseNames.get(position);
            if (!selectedExerciseName.equals("OTHER")){
                ex = exerciseList.get(position);
                used=false;
            } else {
                used=true;
                showPopupMenu(view);

            }

        });

        FinishButton.setOnClickListener(v -> {
            String sugarLevel = sugarLevelInput.getText().toString();
            String duration = durationInput.getText().toString();
            if (Integer.parseInt(sugarLevel) <= 39 || Integer.parseInt(sugarLevel) >= 501) {
                sugarLevelInput.setError("Sugar level should be between 40 and 500");
            } else if (Integer.parseInt(duration) <= 0){
                durationInput.setError("Duration should be bigger than 0");
            } else {
                InsulinAdministration ia = new InsulinAdministration(new User(user.getPassword(), user.getPersonalFactor(), Integer.parseInt(sugarLevel)));
                if (!used){
                    ExerciseLog el = new ExerciseLog(new User(user.getPassword(), user.getPersonalFactor(), Integer.parseInt(sugarLevel)),ex.getExerciseName(),Integer.parseInt(duration));
                    ia.advice(el,exercisesDAO.findAll(),tv);
                } else {
                    ia.advice(option,Integer.parseInt(duration),tv);
                }

            }
            tv2.setText("Press the button to exit the exercise registration");
            FinishButton.setOnClickListener(v1 -> {
                finish();
            });
        });
    }

    private void loadExercises() {
        exerciseList.addAll(exercisesDAO.findAll().values());
    }

    private void showPopupMenu(View anchorView) {
        PopupMenu popupMenu = new PopupMenu(this, anchorView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        // Handle clicks on popup items
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId()== R.id.option1){
                option=1;
                return true;
            } else if (item.getItemId()== R.id.option2){
                option=2;
                return true;
            } else if (item.getItemId()== R.id.option3) {
                option=3;
                return true;
            } else{
                option=0;
                return false;
            }
        });

        popupMenu.show();
    }

}