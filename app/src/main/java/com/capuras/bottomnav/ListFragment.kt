package com.capuras.bottomnav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capuras.bottomnav.adapter.StudentAdapter

class ListFragment : Fragment() {

        private lateinit var studentViewModel : StudentViewModel
        private lateinit var recyclerView: RecyclerView
        private lateinit var adapter: StudentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = StudentAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        studentViewModel = ViewModelProvider(requireActivity())[StudentViewModel::class.java]
        studentViewModel.students.observe(viewLifecycleOwner) { students ->
            adapter.submitList(students)
        }

        return view
    }

}


