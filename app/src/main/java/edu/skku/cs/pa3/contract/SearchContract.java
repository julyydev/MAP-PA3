package edu.skku.cs.pa3.contract;

import edu.skku.cs.pa3.RecipeGridAdapter;
import edu.skku.cs.pa3.model.RecipesByName;

public interface SearchContract {

    interface Model {
        interface onFinishedListener {
            void onFinished(RecipesByName response);
        }
        void sendRequest(String name, onFinishedListener onFinishedListener);
    }

    interface View {

    }

    interface Presenter {

        void loadName(String name);

        void searchRecipes();

        void setAdapterModel(RecipeGridAdapterContract.Model adapterModel);

        void setAdapterView(RecipeGridAdapterContract.View adapterView);
    }
}
