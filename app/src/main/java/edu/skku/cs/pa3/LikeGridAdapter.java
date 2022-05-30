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

import edu.skku.cs.pa3.model.Recipe;

public class LikeGridAdapter extends BaseAdapter {
    ArrayList<Recipe> likes = new ArrayList<>();

    public LikeGridAdapter() {

    }

    @Override
    public int getCount() {
        return likes.size();
    }

    @Override
    public Recipe getItem(int i) {
        return likes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        Recipe recipe = likes.get(i);

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

    public void setLikes(String[] likes) {
        this.likes = new ArrayList<>();
        Log.i("test", "length: " + likes.length);
        for (int i = 0; i < likes.length; i++) {
            Recipe newRecipe = new Recipe();
            String[] split = likes[i].split("#");
            newRecipe.setId(Integer.parseInt(split[0]));
            newRecipe.setTitle(split[1]);
            newRecipe.setImage(split[2]);
            this.likes.add(newRecipe);
        }
    }
}
