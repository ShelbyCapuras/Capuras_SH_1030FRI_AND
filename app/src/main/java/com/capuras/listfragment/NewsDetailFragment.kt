package com.capuras.listfragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class NewsDetailFragment : Fragment() {

    private lateinit var newsItem: News

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)



        arguments?.let {
            newsItem = NewsDetailFragmentArgs.fromBundle(it).newsItem!!
        }

        view.findViewById<TextView>(R.id.news_detail_text).text = newsItem.details
        val imageView = view.findViewById<ImageView>(R.id.news_detail_image)

        Glide.with(this)
            .load(newsItem.imageUrl)
            .into(imageView)


        return view

    }

    companion object {
        private const val ARG_NEWS = "news"

        fun newInstance(news: News): NewsDetailFragment {
            val fragment = NewsDetailFragment()
            val args = Bundle().apply {
                putParcelable("newsItem", news)
            }
            fragment.arguments = args
            return fragment
        }
    }
}


