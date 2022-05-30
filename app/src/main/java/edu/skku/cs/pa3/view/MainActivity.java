package edu.skku.cs.pa3.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.skku.cs.pa3.PagerAdapter;
import edu.skku.cs.pa3.R;
import edu.skku.cs.pa3.contract.MainContract;
import edu.skku.cs.pa3.model.AddUser;
import edu.skku.cs.pa3.presenter.MainPresenter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private String nickname;
    private String email;
    private String profileImage;

    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;
    private SearchFragment searchFragment;
    private LikeFragment likeFragment;
    private MyFragment myFragment;
    private TabLayout tabLayout;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        nickname = intent.getStringExtra("nickname");
        profileImage = intent.getStringExtra("profileImage");
        Log.i("TEST", email + " " + nickname + " " + profileImage);

        searchFragment = new SearchFragment();
        likeFragment = new LikeFragment();
        myFragment = new MyFragment();

        viewPager = (ViewPager2)findViewById(R.id.viewPager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle());
        pagerAdapter.addFragment(searchFragment);
        pagerAdapter.addFragment(likeFragment);
        pagerAdapter.addFragment(myFragment);

        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        pagerAdapter.setArguments(0, bundle);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setUserInputEnabled(false); // 이 줄을 주석처리 하면 슬라이딩으로 탭을 바꿀 수 있다.


        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                switch (pos) {
                    case 0:
//                        Bundle bundle = new Bundle();
//                        bundle.putString("email", email);
//                        searchFragment.setArguments(bundle);
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}