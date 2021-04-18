package com.ads.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DepartmentsFragment extends Fragment {

    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".DEPARTMENTS_FRAGMENT_TAG";

    public DepartmentsFragment() {
        // Required empty public constructor
    }

    public static DepartmentsFragment newInstance() {
        DepartmentsFragment fragment = new DepartmentsFragment();
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
        View view = inflater.inflate(R.layout.fragment_departments, container, false);

        // Set adapter for ListView
        ListView listView = view.findViewById(R.id.department_listview);
        List<String> departmentsList = DataManager.getInstance().DepartmentNames();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.department_list, R.id.department_label, departmentsList);
        listView.setAdapter(adapter);

        // Set Click Listener for ListView children
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity activity = (MainActivity)getActivity();
                activity.navigateToDepartmentItems(view, position);
            }
        });

        return view;
    }
}