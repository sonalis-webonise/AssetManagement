package com.example.webonise.assetmanager.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webonise.assetmanager.Model.DeviceTypeListData;
import com.example.webonise.assetmanager.R;

import java.util.ArrayList;

/**
 * Created by webonise on 4/10/16.
 */

public class DeviceListBaseAdapter extends BaseAdapter {

    ArrayList deviceList = new ArrayList();
    LayoutInflater inflater;
    Context context;

    public DeviceListBaseAdapter(Context context, ArrayList list) {
        this.context = context;
        this.deviceList = list;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return deviceList.size();
    }

    @Override
    public DeviceTypeListData getItem(int position) {
        return (DeviceTypeListData) deviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.device_list_row, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        DeviceTypeListData deviceTypeListData = getItem(position);
        myViewHolder.txtDevice.setText(deviceTypeListData.getDeviceName());
        myViewHolder.imageDevice.setImageResource(deviceTypeListData.getImageName());
        return convertView;
    }

    public class MyViewHolder {
        TextView txtDevice;
        ImageView imageDevice;

        public MyViewHolder(View view) {
            txtDevice = (TextView) view.findViewById(R.id.deviceList);
            imageDevice = (ImageView) view.findViewById(R.id.imgDevice);
        }
    }
}
