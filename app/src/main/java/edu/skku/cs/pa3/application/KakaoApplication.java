package edu.skku.cs.pa3.application;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this, "844d3dd264bd5cee029a1f798a2fabd7");
    }
}
