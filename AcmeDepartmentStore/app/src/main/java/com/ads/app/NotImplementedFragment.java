package com.ads.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NotImplementedFragment extends Fragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".NOT_IMPLEMENTED_FRAGMENT_TAG";

    public NotImplementedFragment() {
        // Required empty public constructor
    }

    public static NotImplementedFragment newInstance() {
        NotImplementedFragment fragment = new NotImplementedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nyi, container, false);
    }
}