package com.example.sugarsweet.view.Administrator.Exercise;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import com.example.sugarsweet.R;
import com.example.sugarsweet.dao.ExercisesDAO;
import com.example.sugarsweet.domain.Exercise;
import com.example.sugarsweet.memorydao.MemoryInitializer;
import com.example.sugarsweet.view.Administrator.AdministratorMenuActivity;


public class ExerciseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExerciseAdapter adapter;
    private List<Exercise> exerciseList;
    private ExercisesDAO exercisesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise);

        exercisesDAO = new MemoryInitializer().getExercisesDAO();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button InsertButton = findViewById(R.id.InsertButton);
        Button CancelButton = findViewById(R.id.CancelButton);

        exerciseList = new ArrayList<>();
        loadExercises();

        adapter = new ExerciseAdapter(exerciseList);
        recyclerView.setAdapter(adapter);

        InsertButton.setOnClickListener(v -> {
            go("insert");
        });

        CancelButton.setOnClickListener(v -> {
            go("cancel");
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        exerciseList = new ArrayList<>();
        loadExercises();

        adapter = new ExerciseAdapter(exerciseList);
        recyclerView.setAdapter(adapter);
    }

    private void loadExercises() {
        exerciseList.addAll(exercisesDAO.findAll().values());
    }

    public void go(String option) {
        Intent intent;
        if (option.equals("insert")) {
            intent = new Intent(this, Exercise2Activity.class);
            startActivity(intent);
        } else if (option.equals("cancel")) {
            finish();

        }

    }

}