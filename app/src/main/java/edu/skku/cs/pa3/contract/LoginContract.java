package edu.skku.cs.pa3.contract;

import android.content.Context;

public interface LoginContract {

    interface View {

    }

    interface Presenter {
        void onLoginButtonClicked(Context context);
    }

}
