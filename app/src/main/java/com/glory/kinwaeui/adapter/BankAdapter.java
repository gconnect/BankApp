package com.glory.kinwaeui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.glory.kinwaeui.R;
import com.glory.kinwaeui.dataModels.Banks;
import com.glory.kinwaeui.dataModels.StateModel;

import java.util.ArrayList;

public class BankAdapter extends ArrayAdapter<Banks> {


    public BankAdapter(Context context, int resouceId, int textviewId,
                       ArrayList<Banks> banks) {
        super(context, resouceId, textviewId, banks);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Banks banks = getItem(position);


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.spinner_item_layout, parent, false);
        }

        TextView textView = listItemView.findViewById(R.id.SpinnertextView);
        if (banks != null) {
            textView.setText(banks.getName());
        }
        return listItemView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getDropDownView(position, convertView, parent);
        Banks banks = getItem(position);


        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.spinner_item_layout, parent, false);
        }

        TextView textView = listItemView.findViewById(R.id.SpinnertextView);
        if (banks != null) {
            textView.setText(banks.getName());
        }
        return listItemView;
    }

}