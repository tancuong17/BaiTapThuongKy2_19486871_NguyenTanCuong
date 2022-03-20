package com.example.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListStaffAdapter extends BaseAdapter {
    ArrayList<Staff> listStaff = new ArrayList<>();
    public ListStaffAdapter(ArrayList<Staff> listStaff) {
        this.listStaff = listStaff;
    }
    @Override
    public int getCount() {
        return listStaff.size();
    }
    @Override
    public Object getItem(int i) {
        return listStaff.get(i);
    }
    @Override
    public long getItemId(int i) {
        return listStaff.get(i).getIdStaff();
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewListStaff;
        if (view == null)
            viewListStaff = View.inflate(viewGroup.getContext(), R.layout.list_view, null);
        else
            viewListStaff = view;
        Staff staffId = (Staff) getItem(i);
        ImageView imageStaffSub = viewListStaff.findViewById(R.id.imageStaffSub);
        TextView infoStaff = viewListStaff.findViewById(R.id.infoStaff);
        imageStaffSub.setImageBitmap(staffId.getLinkImage());
        infoStaff.setText("Mã nhân viên: " + Integer.toString((int) staffId.getIdStaff()) + "\nHọ và tên: " + staffId.getNameStaff() +"\nGiới tính: " + staffId.getSexStaff() + "\nChức vụ: " + staffId.getPositionStaff());
        return viewListStaff;
    }
}