package com.ads.app;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainMenuFragment menuFragment;
    private TextView _pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the in memory data
        DataManager.getInstance();

        setContentView(R.layout.activity_main);
        _pageTitle = (TextView)findViewById(R.id.pageTitle);
        _pageTitle.setText(getString(R.string.app_name));
        addFragment(R.id.page_container, new MainMenuFragment(), MainMenuFragment.FRAGMENT_TAG);
    }

    // Public Fragment calls
    public void navigationNotFound(View view) {
        replaceFragment(R.id.page_container, new NotImplementedFragment(), NotImplementedFragment.FRAGMENT_TAG, null);
    }

    public void navigateToDepartments(View view) {
        _pageTitle.setText("Departments");
        replaceFragment(R.id.page_container, new DepartmentsFragment(), DepartmentsFragment.FRAGMENT_TAG, null);
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