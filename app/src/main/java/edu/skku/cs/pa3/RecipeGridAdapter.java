package edu.skku.cs.pa3;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.skku.cs.pa3.contract.RecipeGridAdapterContract;
import edu.skku.cs.pa3.model.Recipe;

public class RecipeGridAdapter extends BaseAdapter implements RecipeGridAdapterContract.View, RecipeGridAdapterContract.Model {
    ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeGridAdapter() {
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Recipe getItem(int i) {
        return recipes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        Recipe recipe = recipes.get(i);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.cell_recipe, viewGroup, false);
        }

        ImageView imageView = view.findViewById(R.id.cellImageView);
        Glide.with(view).load(recipe.getImage()).into(imageView);
        imageView.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);

        TextView textView = view.findViewById(R.id.cellTextView);
        textView.setText(recipe.getTitle());

        return view;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setRecipes(Recipe[] recipes) {
        this.recipes = new ArrayList<>();
        for (int i = 0; i < recipes.length; i++) {
            this.recipes.add(recipes[i]);
            Log.i("TEST", "!!!");
            Log.i("TEST", recipes[i].getTitle());
        }
    }
}
