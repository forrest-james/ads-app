package com.ads.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

public class ShoppingCartFragment extends Fragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".CART_FRAGMENT_TAG";

    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    public static ShoppingCartFragment newInstance() {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shoppingcart, container, false);

        // Set ListView adapter
        ListView listView = view.findViewById(R.id.shopping_cart_items);
        List<ShoppingCartItem> items = ShoppingCart.getInstance().CartItems();
        ArrayAdapter adapter = new ShoppingCartAdapter(getActivity(), this, items);
        listView.setAdapter(adapter);

        // Set values from object
        TextView cartTotal = (TextView)view.findViewById(R.id.cart_total);
        cartTotal.setText(String.format("Cart Total: %1$,.2f", ShoppingCart.getInstance().CartTotal()));

        // Set Checkout action
        Button cartButton = view.findViewById(R.id.btn_checkout);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                activity.navigationNotFound(v);
            }
        });

        return view;
    }
}