package edu.skku.cs.pa3.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;

import java.io.IOException;

import edu.skku.cs.pa3.contract.LoginContract;
import edu.skku.cs.pa3.model.AddUser;
import edu.skku.cs.pa3.model.User;
import edu.skku.cs.pa3.view.MainActivity;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        ///////
        OkHttpClient client = new OkHttpClient();

        AddUser data = new AddUser();
        data.setEmail(user.getEmail());
        data.setLikes(new String[0]);

        Gson gson = new Gson();
        String json = gson.toJson(data, AddUser.class);

        HttpUrl.Builder builder = HttpUrl.parse("https://8mjpn0w8ob.execute-api.ap-northeast-2.amazonaws.com/map/adduser").newBuilder();
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

        ///////

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("email", user.getEmail());
        intent.putExtra("nickname", user.getNickname());
        intent.putExtra("profileImage", user.getProfileImage());
        context.startActivity(intent);
    }
}
