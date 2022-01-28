package com.example.tr.adapters

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tr.R
import com.example.tr.model.itemModel

class itemadapter(private val context: Context,
                private val dataset: List<itemModel>):
    RecyclerView.Adapter<itemadapter.ItemViewHolder>(){

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.itemlist_imageview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterlayout= LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false)

        return ItemViewHolder(adapterlayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(context,item.ImageResourceID))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }


}