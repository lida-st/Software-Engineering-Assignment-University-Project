package com.example.sugarsweet.view.Diabetic;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sugarsweet.R;
import com.example.sugarsweet.dao.IngredientsDAO;
import com.example.sugarsweet.domain.Ingredient;
import com.example.sugarsweet.domain.InsulinAdministration;
import com.example.sugarsweet.domain.MealLog;
import com.example.sugarsweet.domain.User;
import com.example.sugarsweet.memorydao.MemoryInitializer;
import com.example.sugarsweet.util.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class MealLogActivity extends AppCompatActivity {

    ListView multiChoiceListView;
    private List<Ingredient> ingredientList;
    private IngredientsDAO ingredientsDAO;
    private ArrayList<Tuple2> selected = new ArrayList<>();
    private boolean error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_meal_log);

        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("User", User.class);

        EditText sugarLevelInput = findViewById(R.id.sugarLevelIn);
        Button FinishButton = findViewById(R.id.finishButton);
        TextView tv = findViewById(R.id.advicetextview);
        TextView tv2 = findViewById(R.id.changeTextView);

        ingredientsDAO = new MemoryInitializer().getIngredientsDAO();

        ingredientList = new ArrayList<>();
        loadIngredients();

        multiChoiceListView = findViewById(R.id.checkableListView);
        IngredientAdapter adapter = new IngredientAdapter(ingredientList);
        multiChoiceListView.setAdapter(adapter);
        multiChoiceListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        FinishButton.setOnClickListener(v -> {
            String sugarLevel = sugarLevelInput.getText().toString();
            if (sugarLevel.isEmpty() || Integer.parseInt(sugarLevel) <= 39 || Integer.parseInt(sugarLevel) >= 501) {
                sugarLevelInput.setError("Sugar level should be between 40 and 500");
            } else {
                selected.clear();
                for (int i = 0; i < ingredientList.size(); i++) {
                    View view = multiChoiceListView.getChildAt(i);  // Get the view for the current item
                    if (view != null) {
                        // Find the CheckBox for this item
                        CheckBox checkBox = view.findViewById(R.id.checkbox);
                        EditText gramsInput = view.findViewById(R.id.gramsInput);
                        String grams = gramsInput.getText().toString();

                        // If the CheckBox is checked, add the ingredient name to the list
                        if (checkBox != null && checkBox.isChecked()) {
                            if (grams.isEmpty() || Integer.parseInt(grams) <=0){
                                gramsInput.setError("Grams should be a number greater than 0");
                                error=true;
                            } else {
                                Ingredient ingredient = ingredientList.get(i);  // Get the current ingredient
                                Tuple2<String,Ingredient,Integer> t= new Tuple2<String,Ingredient,Integer>(ingredient.getIngredientName(),ingredient,Integer.parseInt(grams));
                                selected.add(t);
                            }
                        }
                    }
                }
                if (!error){
                    MealLog ml = new MealLog(user,selected);
                    InsulinAdministration ia = new InsulinAdministration(new User(user.getPassword(), user.getPersonalFactor(), Integer.parseInt(sugarLevel)));
                    ia.advice(ml,tv);
                }

            }
            tv2.setText("Press the button to exit the meal registration");
            FinishButton.setOnClickListener(v1 -> {
                finish();
            });
        });

    }

    private void loadIngredients() {
        ingredientList.addAll(ingredientsDAO.findAll().values());
    }

    private class IngredientAdapter extends ArrayAdapter<Ingredient> {

        public IngredientAdapter(List<Ingredient> ingredients) {
            super(MealLogActivity.this, R.layout.item_meal_ingredient, ingredients);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.item_meal_ingredient, parent, false);
            }

            Ingredient ingredient = getItem(position);

            CheckBox checkBox = view.findViewById(R.id.checkbox);
            TextView ingredientNameTextView = view.findViewById(R.id.ingredientNameTextView);
            EditText gramsInput = view.findViewById(R.id.gramsInput);

            ingredientNameTextView.setText(ingredient.getIngredientName());
            gramsInput.setText("");

            return view;
        }
    }
}
