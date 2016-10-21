package com.example.webonise.assetmanager.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webonise.assetmanager.Model.CategoryModel;
import com.example.webonise.assetmanager.R;

import java.util.List;

/**
 * Created by webonise on 13/10/16.
 */

public class FilterCategoryAdapter extends RecyclerView.Adapter<FilterCategoryAdapter.MyViewHolder> {

    private List<CategoryModel> filterCategoryList;

    public FilterCategoryAdapter(List<CategoryModel> filterCategoryList, Context context) {
        this.filterCategoryList = filterCategoryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_category_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtCategory.setText(filterCategoryList.get(position).getCategoryName());
    }

    @Override
    public int getItemCount() {
        return filterCategoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCategory;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtCategory=(TextView)itemView.findViewById(R.id.txtCategory);
        }
    }
}
