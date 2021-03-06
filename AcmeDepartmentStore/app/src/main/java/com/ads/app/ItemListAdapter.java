package com.ads.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends ArrayAdapter<Item> {

    private Context _context;
    private List<Item> _items = new ArrayList<>();

    // Constructor
    public ItemListAdapter(Context context, List<Item> items) {
        super(context, 0, items);
        _context = context;
        _items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(_context).inflate(R.layout.item_list, parent, false);
        }

        Item currentItem = _items.get(position);

        // Set values from Object
        TextView name = (TextView)listItem.findViewById(R.id.item_name);
        name.setText(currentItem.Name());
        TextView price = (TextView)listItem.findViewById(R.id.item_price);
        price.setText(String.format("%1$,.2f", currentItem.Price()));

        return listItem;
    }
}