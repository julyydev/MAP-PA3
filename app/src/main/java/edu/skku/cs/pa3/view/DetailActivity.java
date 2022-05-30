package edu.skku.cs.pa3.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import edu.skku.cs.pa3.R;
import edu.skku.cs.pa3.StepListAdapter;
import edu.skku.cs.pa3.model.Steps;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DetailActivity extends AppCompatActivity {
    private StepListAdapter listAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        listView = findViewById(R.id.listView);
        listAdapter = new StepListAdapter();
        listView.setAdapter(listAdapter);

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder builder = HttpUrl.parse("https://api.spoonacular.com/recipes/324694/analyzedInstructions").newBuilder();
        builder.addQueryParameter("apiKey", "644a503313de4685ab88325146e8b5e9");
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
                Gson gson = new GsonBuilder().create();
                final Steps[] stepsList = gson.fromJson(res, Steps[].class);
                Log.i("TEST", stepsList[0].getSteps()[0].getStep());

                DetailActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listAdapter.setItems(stepsList[0].getSteps());
                        Log.i("TEST", listAdapter.getItem(3).getStep());
                        listAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }
}