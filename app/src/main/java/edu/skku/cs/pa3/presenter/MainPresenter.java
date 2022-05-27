package edu.skku.cs.pa3.presenter;

import android.content.Context;
import android.content.Intent;

import edu.skku.cs.pa3.contract.MainContract;
import edu.skku.cs.pa3.model.User;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    User user;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.user = new User();
    }

    public void loadUserInfo(Context context) {
    }

}
