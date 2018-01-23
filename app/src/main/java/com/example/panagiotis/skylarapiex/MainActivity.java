package com.example.panagiotis.skylarapiex;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.panagiotis.skylarapiex.fragments.FavoritesFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentTransaction(new HomeFragment());

    }
    public void fragmentTransaction(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();
    }

    public void fragmentTransaction(Fragment fragment,String uuid) {

        Bundle bundle = new Bundle();
        bundle.putString("uuid", uuid);
        fragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();
    }

    @OnClick({R.id.btn_favorities,R.id.btn_home})
    public void onToolBarButtonClick(View view){
        if(view.getId()==R.id.btn_favorities){
            fragmentTransaction(new FavoritesFragment());
        }

        if(view.getId()==R.id.btn_home){
            fragmentTransaction(new HomeFragment());
        }
    }

}
