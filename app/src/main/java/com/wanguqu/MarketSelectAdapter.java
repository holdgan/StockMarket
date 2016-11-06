package com.wanguqu;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MarketSelectAdapter extends BaseAdapter implements Filterable {
    private ArrayFilter mFilter;
    private List<MarketSelectData> mList;
    private Context context;
    private ArrayList<MarketSelectData> mUnfilteredData;

    public MarketSelectAdapter(List<MarketSelectData> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {

        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if (convertView == null) {
            view = View.inflate(context, R.layout.item_select_data, null);

            holder = new ViewHolder();
            holder.tv_item_name = (TextView) view.findViewById(R.id.tv_item_name);
            holder.tv_item_phone = (TextView) view.findViewById(R.id.tv_item_phone);
            holder.tv_item_email = (TextView) view.findViewById(R.id.tv_item_email);
            holder.rl_item_background = (RelativeLayout) view.findViewById(R.id.rl_item_background);

            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        final MarketSelectData pc = mList.get(position);

        holder.tv_item_name.setText("name：" + pc.getName());
        holder.tv_item_phone.setText("Phone：" + pc.getPhone());
        holder.tv_item_email.setText("Email：" + pc.getEmail());


        holder.rl_item_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return view;
    }

    static class ViewHolder {
        public RelativeLayout rl_item_background;
        public TextView tv_item_name;
        public TextView tv_item_phone;
        public TextView tv_item_email;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    private class ArrayFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (mUnfilteredData == null) {
                mUnfilteredData = new ArrayList<MarketSelectData>(mList);
            }

            if (prefix == null || prefix.length() == 0) {
                ArrayList<MarketSelectData> list = mUnfilteredData;
                results.values = list;
                results.count = list.size();
            } else {
                String prefixString = prefix.toString().toLowerCase();

                ArrayList<MarketSelectData> unfilteredValues = mUnfilteredData;
                int count = unfilteredValues.size();

                ArrayList<MarketSelectData> newValues = new ArrayList<MarketSelectData>(count);

                for (int i = 0; i < count; i++) {
                    MarketSelectData pc = unfilteredValues.get(i);
                    if (pc != null) {

                        if (pc.getName() != null && pc.getName().startsWith(prefixString)) {

                            newValues.add(pc);
                        } else if (pc.getEmail() != null && pc.getEmail().startsWith(prefixString)) {

                            newValues.add(pc);
                        }
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            //noinspection unchecked
            mList = (List<MarketSelectData>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }
}
