package com.capuras.bottomnav.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capuras.bottomnav.R
import com.capuras.bottomnav.model.Student

class StudentAdapter : ListAdapter<Student, StudentAdapter.StudentViewHolder>(StudentDiffCallback()) {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.studentImage)
        val idNumberTextView: TextView = itemView.findViewById(R.id.studentId)
        val lastNameTextView: TextView = itemView.findViewById(R.id.studentLastName)
        val givenNameTextView: TextView = itemView.findViewById(R.id.studentGivenName)
        val middleNameTextView: TextView = itemView.findViewById(R.id.studentMiddleName)
        val courseNameTextView: TextView = itemView.findViewById(R.id.studentCourse)
        val yearTextView: TextView = itemView.findViewById(R.id.studentYear)
        val semTextView: TextView = itemView.findViewById(R.id.studentSem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = getItem(position)
        holder.imageView.setImageURI(Uri.parse(student.imageUri))
        holder.idNumberTextView.text = student.id
        holder.lastNameTextView.text = student.lastName
        holder.givenNameTextView.text = student.givenName
        holder.middleNameTextView.text = student.middleName
        holder.courseNameTextView.text = student.course
        holder.yearTextView.text = student.year
        holder.semTextView.text = student.sem
    }
}

class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem == newItem
    }
}
