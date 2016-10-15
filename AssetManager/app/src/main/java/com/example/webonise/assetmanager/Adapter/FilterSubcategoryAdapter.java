package com.example.webonise.assetmanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.webonise.assetmanager.Model.Category;
import com.example.webonise.assetmanager.Model.Filter;
import com.example.webonise.assetmanager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by webonise on 13/10/16.
 */

public class FilterSubcategoryAdapter extends RecyclerView.Adapter<FilterSubcategoryAdapter.MySubViewHolder> {
    Context context;
    public String selectedString = null;
    private List<Filter> filterSubCategoryList;

    public FilterSubcategoryAdapter(List<Filter> filterSubCategoryList, Context context) {
        this.filterSubCategoryList = filterSubCategoryList;
        this.context = context;
    }

    @Override
    public MySubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_subcategory_list, parent, false);
        return new MySubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MySubViewHolder holder, final int position) {
        holder.checkBox.setText(filterSubCategoryList.get(position).getName());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Filter filter = new Filter();
//                filter.setSelected(!filter.setSelected());
                    if (filterSubCategoryList.get(position).isSelected()) {
                        holder.checkBox.setChecked(true);
                    } else {
                        holder.checkBox.setChecked(false);
                    }

                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        filterSubCategoryList.get(position).setSelected(isChecked);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterSubCategoryList.size();
    }

    public class MySubViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;

        public MySubViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}
