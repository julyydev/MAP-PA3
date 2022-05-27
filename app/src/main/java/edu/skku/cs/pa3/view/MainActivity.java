package edu.skku.cs.pa3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import edu.skku.cs.pa3.R;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private String nickname;
    private String email;
    private String profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        nickname = intent.getStringExtra("nickname");
        profileImage = intent.getStringExtra("profileImage");

        textView = findViewById(R.id.textView);
        textView.setText(email + "\n" + nickname + "\n" + profileImage);
    }
}