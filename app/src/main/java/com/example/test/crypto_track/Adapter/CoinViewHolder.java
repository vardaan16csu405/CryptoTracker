package com.example.test.crypto_track.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test.crypto_track.R;

public class CoinViewHolder extends RecyclerView.ViewHolder {
    ImageView icon_icon;
    TextView icon_symbol,icon_price,icon_name,one_hour_change,twenty_hours_change,seven_days_change;
    public CoinViewHolder(View itemView) {
        super(itemView);

        icon_icon=(ImageView)itemView.findViewById(R.id.coin_icon);
        icon_symbol=(TextView)itemView.findViewById(R.id.coin_symbol);
        icon_name=(TextView)itemView.findViewById(R.id.coin_name);
        icon_price=(TextView)itemView.findViewById(R.id.priceUsdText);
        one_hour_change=(TextView)itemView.findViewById(R.id.oneHourText);
        twenty_hours_change=(TextView)itemView.findViewById(R.id.twentyFourHourText);
        seven_days_change=(TextView)itemView.findViewById(R.id.sevenDayText);

    }
}
