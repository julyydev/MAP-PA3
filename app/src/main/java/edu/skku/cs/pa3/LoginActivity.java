package edu.skku.cs.pa3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private View kakaoLoginButton, logoutButton;
    private TextView nickname;
    private ImageView profileImgae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        kakaoLoginButton = findViewById(R.id.kakaoLoginButton);
        logoutButton = findViewById(R.id.logoutButton);
        nickname = findViewById(R.id.nickname);
        profileImgae = findViewById(R.id.profile);

        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {

                }
                if (throwable != null) {

                }
                updateKakaoUi();
                return null;
            }
        };

        kakaoLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)) {
                    UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                }
                else {
                    UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this, callback);
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        updateKakaoUi();
                        return null;
                    }
                });
            }
        });

        updateKakaoUi();
    }

    private void updateKakaoUi() {
        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
            @Override
            public Unit invoke(User user, Throwable throwable) {
                if (user != null) {
                    nickname.setText(user.getKakaoAccount().getProfile().getNickname());
                    Glide.with(profileImgae).load(user.getKakaoAccount().getProfile().getThumbnailImageUrl()).circleCrop().into(profileImgae);

                    kakaoLoginButton.setVisibility(View.GONE);
                    logoutButton.setVisibility(View.VISIBLE);
                }
                else {
                    nickname.setText(null);
                    profileImgae.setImageBitmap(null);

                    kakaoLoginButton.setVisibility(View.VISIBLE);
                    logoutButton.setVisibility(View.GONE);
                }
                return null;
            }
        });
    }
}