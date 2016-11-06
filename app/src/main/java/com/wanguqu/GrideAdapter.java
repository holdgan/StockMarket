package com.wanguqu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GrideAdapter extends BaseAdapter {
	Context context;

	String[] arr = { "腾讯控股", "阿里巴巴", "中通", "facebook", "腾讯控股", "阿里巴巴", "中通", "facebook", "腾讯控股"};

	public GrideAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.item_search,
					null);
			holder.txt = (TextView) convertView.findViewById(R.id.tv_item_hot);
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder) convertView.getTag();
		}
		holder.txt.setText(arr[position]+"");
		return convertView;
	}

	public final class ViewHolder {
		public TextView txt;
	}

}