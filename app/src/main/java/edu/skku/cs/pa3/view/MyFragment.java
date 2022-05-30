package edu.skku.cs.pa3.view;

import android.content.Intent;
import android.os.Bundle;

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
import com.kakao.sdk.user.UserApiClient;

import edu.skku.cs.pa3.R;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

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
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });

        return rootView;
    }
}