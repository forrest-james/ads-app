package com.ads.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends ArrayAdapter<ShoppingCartItem> {
    private Context _context;
    private ShoppingCartFragment _fragment;
    private List<ShoppingCartItem> _items = new ArrayList<>();

    // Constructor
    public ShoppingCartAdapter(Context context, ShoppingCartFragment fragment, List<ShoppingCartItem> items) {
        super(context, 0, items);
        _context = context;
        _fragment = fragment;
        _items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(_context).inflate(R.layout.shoppingcart_item_list, parent, false);
        }

        ShoppingCartItem currentItem = _items.get(position);

        TextView name = (TextView)listItem.findViewById(R.id.cart_item_name);
        name.setText(currentItem.Item().Name());

        TextView price = (TextView)listItem.findViewById(R.id.cart_item_price);
        price.setText(String.format("%1$,.2f",currentItem.Item().Price()));

        TextView quantity = (TextView)listItem.findViewById(R.id.cart_item_qty);
        quantity.setText(String.format("Qty: %d", currentItem.Quantity()));

        TextView total = (TextView)listItem.findViewById(R.id.cart_item_total);
        total.setText(String.format("Item Total: %1$,.2f", currentItem.TotalPrice()));

        TextView increment = (TextView)listItem.findViewById(R.id.incrementItem);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingCart.getInstance().incrementItem(currentItem);
                refreshShoppingCart();
            }
        });

        TextView decrement = (TextView)listItem.findViewById(R.id.decrementItem);
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingCart.getInstance().decrementItem(currentItem);
                refreshShoppingCart();
            }
        });

        TextView remove = (TextView)listItem.findViewById(R.id.removeItem);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingCart.getInstance().removeItem(currentItem);
                refreshShoppingCart();
            }
        });

        return listItem;
    }

    // Private Methods
    private void refreshShoppingCart() {
        _fragment.getFragmentManager().beginTransaction().detach(_fragment).attach(_fragment).commit();
    }
}