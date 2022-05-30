package edu.skku.cs.pa3.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kakao.sdk.user.UserApiClient;

import java.io.IOException;

import edu.skku.cs.pa3.R;
import edu.skku.cs.pa3.model.AddUser;
import edu.skku.cs.pa3.model.DeleteUser;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyFragment extends Fragment {
    ImageView profileImageView;
    TextView nicknameTextView, emailTextView;
    Button logoutButton, withdrawalButton;
    private String email;
    private String nickname;
    private String profileImage;

    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my, container, false);

        email = getArguments().getString("email");
        nickname = getArguments().getString("nickname");
        profileImage = getArguments().getString("profileImage");

        Log.i("test", email + nickname + profileImage);

        profileImageView = rootView.findViewById(R.id.profileImageView);
        nicknameTextView = rootView.findViewById(R.id.nicknameTextView);
        emailTextView = rootView.findViewById(R.id.emailTextView);

        Glide.with(getActivity()).load(profileImage).into(profileImageView);
        nicknameTextView.setText("HI, " + nickname);
        emailTextView.setText("email: " + email);

        logoutButton = rootView.findViewById(R.id.logoutButton);
        withdrawalButton = rootView.findViewById(R.id.withdrawalButton);

        logoutButton.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Logout Success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });

        withdrawalButton.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Withdrawal Success", Toast.LENGTH_SHORT).show();

            OkHttpClient client = new OkHttpClient();

            DeleteUser data = new DeleteUser();
            data.setEmail(email);

            Gson gson = new Gson();
            String json = gson.toJson(data, DeleteUser.class);

            HttpUrl.Builder builder = HttpUrl.parse("https://8mjpn0w8ob.execute-api.ap-northeast-2.amazonaws.com/map/deleteuser").newBuilder();
            String url = builder.build().toString();

            Request request = new Request.Builder().url(url).post(RequestBody.create(json, MediaType.parse("application/json"))).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                }
            });

            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });

        return rootView;
    }
}