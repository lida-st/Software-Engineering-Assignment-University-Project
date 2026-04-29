package com.example.sugarsweet.view.Administrator.Ingredient;

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
import com.example.sugarsweet.dao.IngredientsDAO;
import com.example.sugarsweet.domain.Ingredient;
import com.example.sugarsweet.memorydao.MemoryInitializer;

public class IngredientActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IngredientAdapter adapter;
    private List<Ingredient> ingredientsList;
    private IngredientsDAO ingredientsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ingredient);
        ingredientsDAO = new MemoryInitializer().getIngredientsDAO();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button InsertButton = findViewById(R.id.InsertButton);
        Button CancelButton = findViewById(R.id.CancelButton);

        ingredientsList = new ArrayList<>();
        loadIngredients();

        adapter = new IngredientAdapter(ingredientsList);
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
        ingredientsList = new ArrayList<>();
        loadIngredients();

        adapter = new IngredientAdapter(ingredientsList);
        recyclerView.setAdapter(adapter);
    }
    private void loadIngredients() {
        ingredientsList.addAll(ingredientsDAO.findAll().values());
    }

    public void go(String option) {
        Intent intent;
        if (option.equals("insert")) {
            intent = new Intent(this, Ingredient2Activity.class);
            startActivity(intent);
        } else if (option.equals("cancel")) {
            finish();
        }

    }

}