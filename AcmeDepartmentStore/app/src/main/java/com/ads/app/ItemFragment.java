package com.ads.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ItemFragment extends Fragment {
    public static final String FRAGMENT_TAG = BuildConfig.APPLICATION_ID + ".ITEM_FRAGMENT_TAG";

    public ItemFragment() {
        // Required empty public constructor
    }

    public static ItemFragment newInstance(int department, int item) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt("departmentIndex", department);
        args.putInt("itemIndex", item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        Item item = getItem();

        TextView itemName = view.findViewById(R.id.item_name_page);
        itemName.setText(item.Name());

        TextView itemPrice = view.findViewById(R.id.item_price_page);
        itemPrice.setText(String.format("%1$,.2f", item.Price()));

        TextView itemCode = view.findViewById(R.id.item_code);
        itemCode.setText(item.ItemNumber());

        Button addItem = view.findViewById(R.id.btn_add_item);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = getItem();
                ShoppingCart.getInstance().addItem(item, 1);
            }
        });

        return view;
    }

    // Private methods
    private Item getItem() {
        int department = getArguments().getInt("departmentIndex", 0);
        int item = getArguments().getInt("itemIndex", 0);

        return DataManager.getInstance().Departments().get(department).Items().get(item);
    }
}