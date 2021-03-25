package com.example.kotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(val datalist: List<Data>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.text_name)
        val link: TextView = view.findViewById(R.id.text_link)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_data,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = datalist.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=datalist[position]
        holder.name.setText(data.name)
        holder.link.setText(data.link)
    }
}