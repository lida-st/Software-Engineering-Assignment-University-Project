package com.example.sugarsweet.view.Administrator.Ingredient;

/**
 * @author      Alexandra-Maria Mazi || p3220111
 * @author      Lida Statherou       || p3220193
 * @author      Christina Perifana   || p3220160
 **/

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.example.sugarsweet.R;
import com.example.sugarsweet.domain.Ingredient;

public class IngredientAdapter extends RecyclerView.Adapter<com.example.sugarsweet.view.Administrator.Ingredient.IngredientAdapter.IngredientViewHolder> {
    private List<Ingredient> ingredientList;

    public IngredientAdapter(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public com.example.sugarsweet.view.Administrator.Ingredient.IngredientAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingredient, parent, false);
        return new com.example.sugarsweet.view.Administrator.Ingredient.IngredientAdapter.IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.sugarsweet.view.Administrator.Ingredient.IngredientAdapter.IngredientViewHolder holder, int position) {
        Ingredient exercise = ingredientList.get(position);
        holder.ingredientNameTextView.setText(exercise.getIngredientName());
        holder.carbohydratesTextView.setText("Carbohydrates Per Gram: " + exercise.getCarbohydratesPerGram());
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientNameTextView;
        TextView carbohydratesTextView;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientNameTextView = itemView.findViewById(R.id.ingredientNameTextView);
            carbohydratesTextView = itemView.findViewById(R.id.carbohydratesTextView);
        }
    }
}


