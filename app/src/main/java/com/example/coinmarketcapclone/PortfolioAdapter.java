package com.example.coinmarketcapclone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.util.List;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.ViewHolder> {
    private List<Coins> list;
    private Context context;

    public PortfolioAdapter(List<Coins> mList, Context context) {
        list = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layuot, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.coinName.setText(list.get(position).getCoinName());
        holder.coinId.setText(list.get(position).getCoinId());
        holder.add.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddActivity.class);
            intent.putExtra("CoinHoldings",list.get(position).getCoinHoldings());
            intent.putExtra("CoinId",list.get(position).getCoinId());
            intent.putExtra("CoinName",list.get(position).getCoinName());
            intent.putExtra("CoinPrice",list.get(position).getCoinPrice());
            intent.putExtra("CoinSymbol",list.get(position).getCoinSymbol());
            intent.putExtra("LogoUrl",list.get(position).getLogoUrl());
            context.startActivity(intent);
        });
        Glide.with(context).load(list.get(position).getLogoUrl()).centerCrop().into(holder.logo);
    }

    public void setList(List<Coins> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<Coins> getList() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView add, logo;
        TextView coinName,coinId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            add = itemView.findViewById(R.id.add);
            logo = itemView.findViewById(R.id.image);
            coinName = itemView.findViewById(R.id.coinName);
            coinId = itemView.findViewById(R.id.coinId);
        }
    }
}
