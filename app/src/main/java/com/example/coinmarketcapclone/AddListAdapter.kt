package com.example.coinmarketcapclone

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton

class AddListAdapter(val context: Context, private var list: List<Coins>) :
    RecyclerView.Adapter<AddListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layuot, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.coinId.text = list[position].coinSymbol.toUpperCase()
        holder.coinName.text = list[position].coinName
        Glide.with(context).load(list[position].logoUrl).into(holder.Logo)
        if (list[position].coinHoldings == 0.0) {
            holder.Edit.visibility = View.GONE
            holder.Add.visibility = View.VISIBLE
        } else {
            holder.Edit.visibility = View.VISIBLE
            holder.Add.visibility = View.GONE
        }
        holder.Edit.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("CoinHoldings", list[position].coinHoldings)
            intent.putExtra("CoinId", list[position].coinId)
            intent.putExtra("CoinName", list[position].coinName)
            intent.putExtra("CoinPrice", list[position].coinPrice)
            intent.putExtra("CoinSymbol", list[position].coinSymbol)
            intent.putExtra("LogoUrl", list[position].logoUrl)
            context.startActivity(intent)
        }
        holder.Add.setOnClickListener {
            val intent = Intent(context, AddActivity::class.java)
            intent.putExtra("CoinHoldings", list[position].coinHoldings)
            intent.putExtra("CoinId", list[position].coinId)
            intent.putExtra("CoinName", list[position].coinName)
            intent.putExtra("CoinPrice", list[position].coinPrice)
            intent.putExtra("CoinSymbol", list[position].coinSymbol)
            intent.putExtra("LogoUrl", list[position].logoUrl)
            context.startActivity(intent)
        }
    }

    fun setList(list: List<Coins>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Logo = itemView.findViewById<ImageView>(R.id.image)!!
        val coinId = itemView.findViewById<TextView>(R.id.coinId)!!
        val Add = itemView.findViewById<ImageView>(R.id.add)!!
        val Edit = itemView.findViewById<MaterialButton>(R.id.edit)!!
        val coinName = itemView.findViewById<TextView>(R.id.coinName)!!
    }
}