package com.capuras.bottomnav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capuras.bottomnav.model.Student

class StudentViewModel: ViewModel() {
    private val _students = MutableLiveData<MutableList<Student>>(mutableListOf())
    val students: LiveData<MutableList<Student>> get() = _students

    fun addStudent(student: Student) {
        _students.value?.add(student)
        _students.postValue(_students.value)
    }}