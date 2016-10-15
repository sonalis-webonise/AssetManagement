package com.example.webonise.assetmanager.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webonise.assetmanager.Activity.AssignActivity;
import com.example.webonise.assetmanager.Activity.DeviceSpecificationActivity;
import com.example.webonise.assetmanager.Model.DeviceDetail;
import com.example.webonise.assetmanager.R;

import java.util.List;

/**
 * Created by webonise on 6/10/16.
 */

public class DeviceDetailAdapter extends RecyclerView.Adapter<DeviceDetailAdapter.MyViewHolder> {

    Context context;
    private List<DeviceDetail> deviceDetailList;

    public DeviceDetailAdapter(List<DeviceDetail> deviceDetailList, Context context) {
        this.deviceDetailList = deviceDetailList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_detail_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DeviceDetail deviceDetail = deviceDetailList.get(position);
        holder.txtBrand.setText(deviceDetail.getBrand());
        holder.txtTag.setText(deviceDetail.getTag());
        holder.txtAssignedTo.setText(deviceDetail.getEmailId());
        holder.txtDate.setText(deviceDetail.getDate());
        holder.btnAssign.setText(deviceDetail.getStatus());
    }

    @Override
    public int getItemCount() {
        return deviceDetailList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtBrand, txtTag, txtAssignedTo, txtDate;
        public Button btnAssign;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtBrand = (TextView) itemView.findViewById(R.id.txtBrand);
            txtTag = (TextView) itemView.findViewById(R.id.txtTag);
            txtAssignedTo = (TextView) itemView.findViewById(R.id.txtAssignedTo);
            txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            btnAssign = (Button) itemView.findViewById(R.id.btnAssign);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

            btnAssign.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAssign:
                    if (btnAssign.getText().equals("ASSIGNED")) {
                        Toast.makeText(context.getApplicationContext(), "Already Assigned", Toast.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(context, AssignActivity.class);
                        context.startActivity(intent);
                    }
                    break;
                case R.id.cardView:
                    Intent intentcard = new Intent(context, DeviceSpecificationActivity.class);
                    context.startActivity(intentcard);
                    break;
            }
        }
    }
}
