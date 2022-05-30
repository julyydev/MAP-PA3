package edu.skku.cs.pa3.presenter;

import android.util.Log;

import edu.skku.cs.pa3.contract.RecipeGridAdapterContract;
import edu.skku.cs.pa3.contract.SearchContract;
import edu.skku.cs.pa3.model.Recipe;
import edu.skku.cs.pa3.model.RecipesByName;

public class SearchPresenter implements SearchContract.Presenter {
    SearchContract.View view;
    RecipesByName recipes;
    RecipeGridAdapterContract.View adapterView;
    RecipeGridAdapterContract.Model adapterModel;
    String name;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
        recipes = new RecipesByName();
    }

    @Override
    public void loadName(String name) {
        this.name = name;
    }

    @Override
    public void searchRecipes() {
        recipes.sendRequest(name, response -> loadRecipes(response));
    }

    @Override
    public void setAdapterModel(RecipeGridAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void setAdapterView(RecipeGridAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    public void loadRecipes(RecipesByName response) {
        adapterModel.setRecipes(response.getResults());
    }
}
