package com.capuras.listfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(
    private val newsList: List<News>,
    private val  onItemClick: (News) -> Unit
): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val  titleTV :  TextView = itemView.findViewById(R.id.news_title)
        val  newsImage:  ImageView = itemView.findViewById(R.id.news_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news_headline, parent, false)
        return NewsViewHolder(view)
    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.titleTV.text = newsItem.title

        // Load the image using Glide
        Glide.with(holder.itemView.context)
            .load(newsItem.imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.newsImage)

        holder.itemView.setOnClickListener {
            onItemClick(newsItem)
        }
    }

    override fun getItemCount(): Int = newsList.size
}