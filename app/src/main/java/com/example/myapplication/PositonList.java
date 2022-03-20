package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PositonList extends BaseAdapter {
    ArrayList<String> listPosition = new ArrayList<>();
    public PositonList(ArrayList<String> listPosition) {
        this.listPosition = listPosition;
    }
    @Override
    public int getCount() {
        return listPosition.size();
    }
    @Override
    public Object getItem(int i) {
        return listPosition.get(i);
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View positionList = view.inflate(viewGroup.getContext(), R.layout.positon_list, null);
        TextView position = positionList.findViewById(R.id.positonItem);
        position.setText(listPosition.get(i));
        return positionList;
    }
}
