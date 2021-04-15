package com.ads.app;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

public class DepartmentItemsFragment extends Fragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".DEPARTMENT_ITEMS_FRAGMENT_TAG";

    public DepartmentItemsFragment() {
        // Required empty public constructor
    }

    public static DepartmentItemsFragment newInstance(int index) {
        DepartmentItemsFragment fragment = new DepartmentItemsFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_department_items, container, false);
        int index = getArguments().getInt("index", 0);

        List<Item> items = DataManager.getInstance().getDepartmentItems(DataManager.getInstance().Departments().get(index));

        GridView itemGridView = (GridView)view.findViewById(R.id.department_items_grid);
        ItemGridAdapter itemGridAdapter = new ItemGridAdapter(view.getContext(), items);
        itemGridView.setAdapter(itemGridAdapter);

        return view;
    }
}