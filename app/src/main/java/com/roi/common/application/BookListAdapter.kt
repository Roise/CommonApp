package com.roi.common.application

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    data class ImageItem(var imageUrl:String, var documentUrl:String)
    private val imageItemList = ArrayList<ImageItem>()

    class BookHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.activity_intro, parent, false)
    ) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookHolder(parent)
    }

    override fun getItemCount() = imageItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }
}