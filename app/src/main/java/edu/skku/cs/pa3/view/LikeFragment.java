package edu.skku.cs.pa3.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import edu.skku.cs.pa3.LikeGridAdapter;
import edu.skku.cs.pa3.R;
import edu.skku.cs.pa3.RecipeGridAdapter;
import edu.skku.cs.pa3.model.AddUser;
import edu.skku.cs.pa3.model.GetUser;
import edu.skku.cs.pa3.model.Likes;
import edu.skku.cs.pa3.model.Steps;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LikeFragment extends Fragment {
    private GridView likeGridView;
    private LikeGridAdapter likeGridAdapter;

    public LikeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_like, container, false);

        likeGridView = rootView.findViewById(R.id.likeGridView);
        likeGridAdapter = new LikeGridAdapter();
        likeGridView.setAdapter(likeGridAdapter);

        String email = getArguments().getString("email");

        OkHttpClient client = new OkHttpClient();

        GetUser data = new GetUser();
        data.setEmail(email);

        Gson gson = new Gson();
        String json = gson.toJson(data, GetUser.class);

        HttpUrl.Builder builder = HttpUrl.parse("https://8mjpn0w8ob.execute-api.ap-northeast-2.amazonaws.com/map/getuser").newBuilder();
        String url = builder.build().toString();

        Request request = new Request.Builder().url(url).post(RequestBody.create(json, MediaType.parse("application/json"))).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String res = response.body().string();
                Gson gson = new GsonBuilder().create();
                final Likes likes = gson.fromJson(res, Likes.class);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        likeGridAdapter.setLikes(likes.getLikes());
                        likeGridAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        return rootView;
    }
}