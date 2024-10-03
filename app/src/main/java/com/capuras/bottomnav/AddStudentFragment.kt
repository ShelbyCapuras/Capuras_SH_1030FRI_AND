package com.capuras.bottomnav

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.capuras.bottomnav.model.Student
import de.hdodenhof.circleimageview.CircleImageView

class AddStudentFragment : Fragment() {

    private lateinit var studentViewModel: StudentViewModel
    private lateinit var circleImageView: CircleImageView
    private var selectedImageUri: Uri? = null
    private lateinit var idEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var givenNameEditText: EditText
    private lateinit var middleNameEditText: EditText
    private lateinit var courseSpinner: Spinner
    private lateinit var yearSpinner: Spinner
    private lateinit var semRadioGroup: RadioGroup
    private lateinit var submitButton: Button

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    selectedImageUri = uri
                    circleImageView.setImageURI(uri)
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_student, container, false)
        studentViewModel = ViewModelProvider(requireActivity())[StudentViewModel::class.java]
        circleImageView = view.findViewById(R.id.studentIV)
        submitButton = view.findViewById(R.id.btnSubmit)
        idEditText = view.findViewById(R.id.etStudentID)
        lastNameEditText = view.findViewById(R.id.etLastName)
        givenNameEditText = view.findViewById(R.id.etGivenName)
        middleNameEditText = view.findViewById(R.id.etMiddleName)
        courseSpinner = view.findViewById(R.id.spinnerCourse)
        yearSpinner = view.findViewById(R.id.spinnerYear)
        semRadioGroup = view.findViewById(R.id.radioGroupSemester)


        circleImageView.setOnClickListener {
            checkStoragePermissionAndPickImage()
        }
        submitButton.setOnClickListener {
            submitData()

        }

        return view
    }

    private fun submitData() {
        val lastName = lastNameEditText.text.toString()
        val givenName = givenNameEditText.text.toString()
        val middleName = middleNameEditText.text.toString()
        val idNumber = idEditText.text.toString()
        val course = courseSpinner.selectedItem.toString()
        val year = yearSpinner.selectedItem.toString()
        val selectedRadioButtonId = semRadioGroup.checkedRadioButtonId
        val selectedRadioButton = view?.findViewById<RadioButton>(selectedRadioButtonId)
        val selectedSemester = selectedRadioButton?.text.toString()



        val student = Student(
            id = idNumber,
            lastName = lastName,
            givenName = givenName,
            middleName = middleName,
            imageUri = selectedImageUri?.toString()?: "",
            course = course,
            year = year,
            sem = selectedSemester
            )
        studentViewModel.addStudent(student)
        findNavController().navigate(R.id.listFragment)

    }

    private fun checkStoragePermissionAndPickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_IMAGES)
                == PackageManager.PERMISSION_GRANTED
            ) {
                openImagePicker()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
            }
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        if (isGranted) {
            openImagePicker()
        } else {
            Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}
