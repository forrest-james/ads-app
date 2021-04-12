package com.ads.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainMenuFragment extends Fragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".MAIN_MENU_FRAGMENT_TAG";

    public MainMenuFragment() {
        // Required empty public constructor
    }

    public static MainMenuFragment newInstance() {
        MainMenuFragment fragment = new MainMenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        MainActivity activity = (MainActivity)getActivity();

        View departmentButton = view.findViewById(R.id.departments_menu_nav);
        departmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.navigateToDepartments(v);
            }
        });
        View shoppingButton = view.findViewById(R.id.shopping_menu_nav);
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.navigateToShopping(v);
            }
        });
        return view;
    }
}