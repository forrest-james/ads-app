package com.ads.app;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainMenuFragment menuFragment;
    private TextView _pageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the in memory data
        DataManager.getInstance();

        // Set initial content
        setContentView(R.layout.activity_main);
        _pageTitle = (TextView)findViewById(R.id.pageTitle);
        _pageTitle.setText(getString(R.string.app_name));
        addFragment(R.id.page_container, new MainMenuFragment(), MainMenuFragment.FRAGMENT_TAG);

        // Add Shopping Cart action
        ImageView cart = (ImageView)findViewById(R.id.shopping_cart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCart(v);
            }
        });
    }

    // Public Fragment calls
    public void navigationNotFound(View view) {
        replaceFragment(R.id.page_container, new NotImplementedFragment(), NotImplementedFragment.FRAGMENT_TAG, null);
    }

    public void navigateToShopping(View view) {
        _pageTitle.setText("Shopping");
        replaceFragment(R.id.page_container, new ShoppingFragment(), ShoppingFragment.FRAGMENT_TAG, null);
    }

    public void navigateToDepartments(View view) {
        _pageTitle.setText("Departments");
        replaceFragment(R.id.page_container, new DepartmentsFragment(), DepartmentsFragment.FRAGMENT_TAG, null);
    }

    public void navigateToDepartmentItems(View view, int index) {
        _pageTitle.setText(DataManager.getInstance().DepartmentNames().get(index));

        DepartmentItemsFragment fragment = new DepartmentItemsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("index", index);
        fragment.setArguments(arguments);

        replaceFragment(R.id.page_container, fragment, DepartmentItemsFragment.FRAGMENT_TAG, null);
    }

    public void navigateToItem(View view, int department, int item) {
        _pageTitle.setText(DataManager.getInstance().DepartmentNames().get(department));

        ItemFragment fragment = new ItemFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("departmentIndex", department);
        arguments.putInt("itemIndex", item);
        fragment.setArguments(arguments);

        replaceFragment(R.id.page_container, fragment, ItemFragment.FRAGMENT_TAG, null);
    }

    public void navigateToCart(View view) {
        _pageTitle.setText("Shopping Cart");
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        replaceFragment(R.id.page_container, fragment, ShoppingCartFragment.FRAGMENT_TAG, null);
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