package com.wanguqu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MarketArrayAdapter extends ArrayAdapter<MarketArrayData> {
    private LayoutInflater mInflater;

    public MarketArrayAdapter(Context context, MarketArrayData[] values) {
        super(context, R.layout.item_market_main, values);
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            // Inflate the view since it does not exist
            convertView = mInflater.inflate(R.layout.item_market_main, parent, false);

            // Create and save off the holder in the tag so we get quick access to inner fields
            // This must be done for performance reasons
            holder = new Holder();
            holder.rl_item_background = (RelativeLayout) convertView.findViewById(R.id.rl_item_background);
            holder.tv_item_name = (TextView) convertView.findViewById(R.id.tv_item_name);
            holder.tv_item_price = (TextView) convertView.findViewById(R.id.tv_item_price);
            holder.tv_item_wave = (TextView) convertView.findViewById(R.id.tv_item_wave);
            holder.tv_item_proportion = (TextView) convertView.findViewById(R.id.tv_item_proportion);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.rl_item_background.setBackgroundColor(getItem(position).getBackgroundColor());
        holder.tv_item_name.setText(getItem(position).getName());
        holder.tv_item_price.setText(getItem(position).getPrice());
        holder.tv_item_wave.setText(getItem(position).getWave());
        holder.tv_item_proportion.setText(getItem(position).getProportion());

        if (getItem(position).getState()) {
            holder.tv_item_price.setTextColor(getItem(position).getUpColor());
            holder.tv_item_wave.setTextColor(getItem(position).getUpColor());
            holder.tv_item_proportion.setTextColor(getItem(position).getUpColor());

        }

        return convertView;
    }

    /**
     * View holder for the views we need access to
     */
    private static class Holder {
        public RelativeLayout rl_item_background;
        public TextView tv_item_name;
        public TextView tv_item_price;
        public TextView tv_item_wave;
        public TextView tv_item_proportion;
    }
}
