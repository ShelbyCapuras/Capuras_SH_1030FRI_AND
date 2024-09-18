package com.capuras.listfragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class NewsHeadlineFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private var isTwoPane :  Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val  view = inflater.inflate(R.layout.fragment_news_headline, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        isTwoPane  = view.findViewById<View>(R.id.detail_container) != null

        val newsList =  listOf(
            News(getString(R.string.news_headline_1), getString(R.string.news_detail_1), "https://news-image-api.abs-cbn.com/Prod/editorImage/1726538343741track-gener.jpg"),
            News(getString(R.string.news_headline_2),  getString(R.string.news_detail_2), "https://images.gmanews.tv/webpics/2022/10/senate-building_2022_10_10_20_52_45.jpg"),
            News(getString(R.string.news_headline_3), getString(R.string.news_detail_3), "https://globalnation.inquirer.net/files/2024/09/IMG-d3837eb969933aa800cae907e14bd855-V-1200x864.jpg"),
            News(getString(R.string.news_headline_4), getString(R.string.news_detail_4), "https://media.philstar.com/photos/2024/09/17/apollo-quiboloy-court-3_2024-09-17_11-32-50.jpg"),
            News(getString(R.string.news_headline_5), getString(R.string.news_detail_5), "https://media.philstar.com/photos/2024/09/16/1_2024-09-16_19-20-32.jpg"),
            News(getString(R.string.news_headline_6), getString(R.string.news_detail_6), "https://media.philstar.com/photos/2024/09/16/c_2024-09-16_19-09-18.jpg"),
            News(getString(R.string.news_headline_7), getString(R.string.news_detail_7), "https://media.philstar.com/photos/2024/09/17/kent-pastrana-kevin-quiambao-uaap_2024-09-17_12-16-44.jpg"),
            News(getString(R.string.news_headline_8), getString(R.string.news_detail_8), "https://media.philstar.com/photos/2024/09/17/marcio-lassiter-san-miguel-pba_2024-09-17_10-26-58.jpg"),
            News(getString(R.string.news_headline_9), getString(R.string.news_detail_9), "https://media.philstar.com/photos/2024/09/17/hello-love-again-kathryn-bernardo-alden-richards_2024-09-17_11-43-04.jpg"),
            News(getString(R.string.news_headline_10), getString(R.string.news_detail_10), "https://media.philstar.com/photos/2024/09/04/lead_2024-09-04_14-41-18.jpg")
        )

        newsAdapter = NewsAdapter(newsList){ newsItem ->

            if(isTwoPane){
                val fragment = NewsDetailFragment.newInstance(newsItem)
                childFragmentManager.beginTransaction()
                    .replace(R.id.detail_container, fragment)
                    .commit()
            }else{
                val  action  =  NewsHeadlineFragmentDirections.actionToNewsDetailFragment(newsItem)
                findNavController().navigate(action)
            }
        }


        recyclerView.adapter  = newsAdapter

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                showExitConfirmationDialog()
            }
        })
        return view
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("Are you want to exit the app?")
            .setPositiveButton("Yes"){ _,  _ ->
                requireActivity().finish()
                exitProcess(0)
            }
            .setNegativeButton("No",null)
            .show()
    }
}