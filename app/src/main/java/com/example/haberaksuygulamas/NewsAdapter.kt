package com.example.haberaksuygulamas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class NewsAdapter(context: Context, dataArrayList: ArrayList<NewsInfo>) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>(){

    var pContext= context
    var data_array_list  = dataArrayList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {

        var inflater = LayoutInflater.from(parent.context)
        var one_list_item_view = inflater.inflate(R.layout.list_row_main,parent.findViewById(R.id.recycler_view),false)

        return MyViewHolder(one_list_item_view)
    }

    override fun getItemCount(): Int {

        return data_array_list.size

    }

    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {

        var currentNews = data_array_list[position]

        Glide.with(pContext).load(currentNews.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imageView)

        holder.textView.setText(currentNews.title)

    }

    inner class MyViewHolder (itemView : View) :   RecyclerView.ViewHolder(itemView){

        var one_item_holder = this.itemView as CardView

        var imageView = one_item_holder.findViewById<ImageView>(R.id.image_view)
        var textView = one_item_holder.findViewById<TextView>(R.id.text_view)


    }
}