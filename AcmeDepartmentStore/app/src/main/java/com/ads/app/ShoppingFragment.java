package com.ads.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ShoppingFragment extends Fragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".SHOPPING_FRAGMENT_TAG";

    public ShoppingFragment() {
        // Required empty public constructor
    }

    public static ShoppingFragment newInstance() {
        ShoppingFragment fragment = new ShoppingFragment();
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
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        TextView saleBanner = view.findViewById(R.id.sale_label);
        saleBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.navigationNotFound(v);
            }
        });

        List<Item> popularItems = DataManager.getInstance().PopularItems();
        if(popularItems.size() > 5) {
            popularItems = popularItems.subList(0, 4);
        }

        List<Item> recommendedItems = DataManager.getInstance().RecommendedItems();
        if(recommendedItems.size() > 5) {
            recommendedItems = recommendedItems.subList(0, 4);
        }

        List<Department> departments = DataManager.getInstance().Departments();
        List<String> departmentNames = DataManager.getInstance().DepartmentNames();

        departmentNames.add(0, "Popular Items");
        departmentNames.add(1, "Recommended Items");

        HashMap<String, List<Item>> departmentData = new HashMap<String, List<Item>>();

        departmentData.put("Popular Items", popularItems);
        departmentData.put("Recommended Items", recommendedItems);

        for(int i = 0; i < departments.size(); i++) {
            departmentData.put(departments.get(i).Name(), departments.get(i).Items());
        }

        ExpandableListView departmentListView = (ExpandableListView) view.findViewById(R.id.department_items);
        ExpandableListAdapter departmentListAdapter = new ExpandableListAdapter(view.getContext(), departmentNames, departmentData);
        departmentListView.setAdapter(departmentListAdapter);
        for (int i = 0; i < departmentNames.size(); i++) {
            departmentListView.expandGroup(i);
        }

        departmentListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });

        departmentListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                MainActivity activity = (MainActivity) getActivity();
                activity.navigateToItem(v, groupPosition, childPosition);
                return true;
            }
        });

        return view;
    }
}