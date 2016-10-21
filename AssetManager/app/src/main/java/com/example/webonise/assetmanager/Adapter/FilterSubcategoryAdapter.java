package com.example.webonise.assetmanager.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.webonise.assetmanager.Model.FilterModel;
import com.example.webonise.assetmanager.R;

import java.util.List;

/**
 * Created by webonise on 13/10/16.
 */

public class FilterSubcategoryAdapter extends RecyclerView.Adapter<FilterSubcategoryAdapter.MySubViewHolder> {
    Context context;
    public String selectedString = null;
    private List<FilterModel> filterSubCategoryList;

    public FilterSubcategoryAdapter(List<FilterModel> filterSubCategoryList, Context context) {
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
        holder.checkBox.setChecked(filterSubCategoryList.get(position).isSelected());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filterSubCategoryList.get(position).setSelected(isChecked);
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
