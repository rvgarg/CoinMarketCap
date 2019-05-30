package com.example.coinmarketcapclone

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HoldingsAdapter(val context: Context, val list: List<Coins>) :
    RecyclerView.Adapter<HoldingsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_holdings, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(list[position].logoUrl).centerCrop().into(holder.imageView)
        holder.coinId.text = list[position].coinSymbol
        holder.price.text = "$${String.format("%.2f", list[position].coinPrice)}"
        holder.holdings.text = list[position].coinHoldings.toString() + " " + list[position].coinSymbol
        holder.priceTotal.text = "$${String.format("%.2f", (list[position].coinHoldings * list[position].coinPrice))}"
        val change = String.format("%.2f", list[position].priceChange).toDouble()
        holder.change.text = "$$change"
        holder.change.setTextColor(
            if (change < 0.0)
                Color.RED
            else
                Color.GREEN
        )
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.image)!!
        val coinId = itemView.findViewById<TextView>(R.id.coinId)!!
        val price = itemView.findViewById<TextView>(R.id.price)!!
        val change = itemView.findViewById<TextView>(R.id.change)!!
        val priceTotal = itemView.findViewById<TextView>(R.id.priceTotal)!!
        val holdings = itemView.findViewById<TextView>(R.id.holdings)!!

    }
}