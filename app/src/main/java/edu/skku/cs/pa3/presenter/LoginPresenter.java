package edu.skku.cs.pa3.presenter;

import android.content.Context;
import android.content.Intent;

import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;

import edu.skku.cs.pa3.contract.LoginContract;
import edu.skku.cs.pa3.model.User;
import edu.skku.cs.pa3.view.MainActivity;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginPresenter implements LoginContract.Presenter {
    LoginContract.View view;
    User user;
    Context context;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.view = view;
        this.user = new User();
        this.context = context;
    }

    @Override
    public void onLoginButtonClicked(Context context) {
        loginWithKakao(context);
        user.getUserInformation();
    }

    Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
        @Override
        public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
//            if (oAuthToken != null) {
//
//            }
//            if (throwable != null) {
//
//            }
//            user.getUserInformation();
            loadActivity(context);
            return null;
        }
    };

    public void loginWithKakao(Context context) {
        if (UserApiClient.getInstance().isKakaoTalkLoginAvailable(context)) {
            UserApiClient.getInstance().loginWithKakaoTalk(context, callback);
        } else {
            UserApiClient.getInstance().loginWithKakaoAccount(context, callback);
        }
    }

    public void loadActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("email", user.getEmail());
        intent.putExtra("nickname", user.getNickname());
        intent.putExtra("profileImage", user.getProfileImage());
        context.startActivity(intent);
    }
}
