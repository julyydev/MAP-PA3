package edu.skku.cs.pa3.model;

import com.kakao.sdk.user.UserApiClient;

public class User {
    private String email;
    private String nickname;
    private String profileImage;

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    private void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    private void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void getUserInformation() {
        UserApiClient.getInstance().me((user, throwable) -> {
            if (user != null) {
                setEmail(user.getKakaoAccount().getEmail());
                setNickname(user.getKakaoAccount().getProfile().getNickname());
                setProfileImage(user.getKakaoAccount().getProfile().getProfileImageUrl());
            }
            return null;
        });
    }

}
