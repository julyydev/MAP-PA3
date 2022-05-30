package edu.skku.cs.pa3.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import edu.skku.cs.pa3.contract.SearchContract;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipesByName implements SearchContract.Model {
    private Recipe[] results;
    private int offset;
    private int number;
    private int totalResults;

    public Recipe[] getResults() {
        return results;
    }

    public void setResults(Recipe[] results) {
        this.results = results;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @Override
    public void sendRequest(String name, final onFinishedListener onFinishedListener) {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder builder = HttpUrl.parse("https://api.spoonacular.com/recipes/complexSearch").newBuilder();
        builder.addQueryParameter("apiKey", "644a503313de4685ab88325146e8b5e9");
        builder.addQueryParameter("query", name);
        builder.addQueryParameter("number", "100");
        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String res = response.body().string();
                Log.i("TEST", "res: " + res);
                Gson gson = new GsonBuilder().create();
                final RecipesByName recipes = gson.fromJson(res, RecipesByName.class);
                onFinishedListener.onFinished(recipes);
            }
        });
    }
}
