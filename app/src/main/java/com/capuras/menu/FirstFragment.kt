package com.capuras.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private lateinit var titleTV : TextView
    private lateinit var messageTV : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_first_fragment, container, false)

        titleTV = view.findViewById(R.id.textViewTitle)
        messageTV = view.findViewById(R.id.textViewMessage)

        return view
    }
    fun updateList(title: String, message: String){
        titleTV.text = title
        messageTV.text = message
    }
}