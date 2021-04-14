package com.ads.app;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listHeaders;
    private HashMap<String, List<Item>> _listChildren;

    public ExpandableListAdapter(Context context, List<String> listHeaders, HashMap<String, List<Item>> listChildren) {
        _context = context;
        _listHeaders = listHeaders;
        _listChildren = listChildren;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return _listChildren.get(_listHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final Item child = (Item) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater childInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childInflater.inflate(R.layout.item_list, null);
        }

        TextView name = (TextView)convertView.findViewById(R.id.item_name);
        name.setText(child.Name());

        TextView price = (TextView)convertView.findViewById(R.id.item_price);
        price.setText(String.format("%1$,.2f", child.Price()));

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return _listChildren.get(_listHeaders.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return _listHeaders.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return _listHeaders.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String header = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater groupInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = groupInflater.inflate(R.layout.list_group, null);
        }

        TextView listHeader = (TextView) convertView.findViewById(R.id.list_header);
        listHeader.setText(header);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}