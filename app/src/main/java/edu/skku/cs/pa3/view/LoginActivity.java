package edu.skku.cs.pa3.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kakao.sdk.auth.AuthApiClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;

import edu.skku.cs.pa3.R;
import edu.skku.cs.pa3.contract.LoginContract;
import edu.skku.cs.pa3.presenter.LoginPresenter;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private View kakaoLoginButton;

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this, LoginActivity.this);

        kakaoLoginButton = findViewById(R.id.kakaoLoginButton);

        kakaoLoginButton.setOnClickListener(view -> {
            presenter.onLoginButtonClicked(LoginActivity.this);
        });
    }
}