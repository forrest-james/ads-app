package com.ads.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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

        final GridView popularItemView = (GridView) view.findViewById(R.id.popular_list);
        final GridView recommendedItemView = (GridView) view.findViewById(R.id.recommended_list);

        List<Item> popularItems = DataManager.getInstance().PopularItems();
        if(popularItems.size() > 5) {
            popularItems = popularItems.subList(0, 4);
        }
        final ItemListAdapter popularAdapter = new ItemListAdapter(getActivity(), popularItems);
        popularItemView.setAdapter(popularAdapter);

        List<Item> recommendedItems = DataManager.getInstance().RecommendedItems();
        if(recommendedItems.size() > 5) {
            recommendedItems = recommendedItems.subList(0, 4);
        }

        final ItemListAdapter recommendedAdapter = new ItemListAdapter(getActivity(), recommendedItems);
        recommendedItemView.setAdapter(recommendedAdapter);

        return view;
    }
}