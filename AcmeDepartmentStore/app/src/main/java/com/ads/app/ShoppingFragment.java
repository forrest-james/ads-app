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

        // Add action for Sale banner
        TextView saleBanner = view.findViewById(R.id.sale_label);
        saleBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.navigationNotFound(v);
            }
        });

        List<Item> popularItems = DataManager.getInstance().PopularItems();
        final List<Item> popItems = popularItems;       // Required final variable reference for nested method (OnChildClick)

        List<Item> recommendedItems = DataManager.getInstance().RecommendedItems();
        final List<Item> recItems = recommendedItems;   // Required final variable reference for nested method (OnChildClick)

        List<Department> departments = DataManager.getInstance().Departments();
        List<String> departmentNames = DataManager.getInstance().DepartmentNames();

        departmentNames.add(0, "Popular Items");
        departmentNames.add(1, "Recommended Items");

        // Create HashMap for ExpandableListView
        HashMap<String, List<Item>> departmentData = new HashMap<String, List<Item>>();

        // Add meta Item groups
        departmentData.put("Popular Items", popularItems);
        departmentData.put("Recommended Items", recommendedItems);

        // Add standard department Items
        for(int i = 0; i < departments.size(); i++) {
            departmentData.put(departments.get(i).Name(), departments.get(i).Items());
        }

        // Set adapter for ExpandableListView
        ExpandableListView departmentListView = (ExpandableListView) view.findViewById(R.id.department_items);
        ExpandableListAdapter departmentListAdapter = new ExpandableListAdapter(view.getContext(), departmentNames, departmentData);
        departmentListView.setAdapter(departmentListAdapter);

        // Default groups to expanded
        for (int i = 0; i < departmentNames.size(); i++) {
            departmentListView.expandGroup(i);
        }

        departmentListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // Don't allow groups to be collapsed
                return true;
            }
        });

        // Add Click Listener for ExpandableListView children
        departmentListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                MainActivity activity = (MainActivity) getActivity();

                // Needed for "Popular" and "Recommended" Items groups that don't exist in DataManager Departments
                if(groupPosition == 0) {
                    groupPosition = DataManager.getInstance().getItemDepartment(popItems.get(childPosition));
                    childPosition = DataManager.getInstance().Departments().get(groupPosition).findItem(popItems.get(childPosition));
                }
                else if(groupPosition == 1) {
                    groupPosition = DataManager.getInstance().getItemDepartment(recItems.get(childPosition));
                    childPosition = DataManager.getInstance().Departments().get(groupPosition).findItem(recItems.get(childPosition));
                }

                activity.navigateToItem(v, groupPosition, childPosition);
                return true;
            }
        });

        return view;
    }
}