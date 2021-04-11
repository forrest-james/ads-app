package com.ads.app;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MainMenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(R.id.page_container, new MainMenuFragment(), MainMenuFragment.FRAGMENT_TAG);
    }

    // Public Fragment calls
    public void navigationNotFound(View view) {
        replaceFragment(R.id.page_container, new NotImplementedFragment(), NotImplementedFragment.FRAGMENT_TAG, null);
    }

    // Fragment Handlers
    private void addFragment(int containerId, Fragment fragment, String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    private void replaceFragment(int containerId, Fragment fragment, String fragmentTag, String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
}