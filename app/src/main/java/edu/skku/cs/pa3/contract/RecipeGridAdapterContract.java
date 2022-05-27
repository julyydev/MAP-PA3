package edu.skku.cs.pa3.contract;

import java.util.ArrayList;

import edu.skku.cs.pa3.model.Recipe;

public interface RecipeGridAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        void setRecipes(Recipe[] recipes);
    }
}
