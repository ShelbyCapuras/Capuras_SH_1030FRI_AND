package com.capuras.listview

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.Manifest
import android.app.AlertDialog
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toDrawable
import com.capuras.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listView: ListView
    private lateinit var listAdapter: ListAdapter
    private lateinit var addBtn: ImageButton
    private val listItems = mutableListOf<ListItem>()
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    var editItemPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listView = binding.studentList
        addBtn = binding.addBtn



        listAdapter = ListAdapter(this, listItems)
        listView.adapter = listAdapter

        pickImageLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val imageUri = data?.data

                if (imageUri != null) {
                    if (editItemPosition != -1) {
                        updateItemImage(imageUri)
                    } else {
                        showNameDialog(imageUri)
                    }
                }
            }
        }


        addBtn.setOnClickListener {
            editItemPosition = -1
            pickImageFromGallery()
        }
    }
    private fun updateItemImage(imageUri: Uri) {
        val drawable = MediaStore.Images.Media.getBitmap(contentResolver, imageUri).toDrawable(resources)
        val item = listItems[editItemPosition]
        item.imgdrawable = drawable
        listAdapter.notifyDataSetChanged()
        editItemPosition = -1
    }

    fun pickImageFromGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                pickImageLauncher.launch(intent)
            } else {
                requestPermissions(arrayOf(Manifest.permission.READ_MEDIA_IMAGES), 1)
            }
        } else {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(intent)
        }
    }


    private fun showNameDialog(imageUri: Uri) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Name")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK") { dialog, _ ->
            val name = input.text.toString()
            val drawable = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            val listItem = ListItem(drawable.toDrawable(resources), name, false)
            listItems.add(listItem)
            listAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImageFromGallery()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }


}



