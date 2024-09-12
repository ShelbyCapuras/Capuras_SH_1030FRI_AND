package com.capuras.listview

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


class ListAdapter(private val  context: Context, private val items: MutableList<ListItem>) : BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val item = items[position]

        val imageView = view.findViewById<ImageView>(R.id.item_image)
        val textView = view.findViewById<TextView>(R.id.item_text)
        val checkBox = view.findViewById<CheckBox>(R.id.item_checkbox)

        imageView.setImageDrawable(item.imgdrawable)
        textView.text = item.text
        checkBox.isChecked = item.isChecked

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
        }

        view.setOnClickListener(object : View.OnClickListener {
            private var lastClickTime = 0L
            override fun onClick(v: View) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastClickTime < 500) {
                    showEditDeleteDialog(item, position)
                }
                lastClickTime = currentTime
            }
        })

        return view
    }

    private fun showEditDeleteDialog(item: ListItem, position: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit or Delete item")

        builder.setPositiveButton("Edit") { _, _ ->
            showEditItemDialog(item, position)
        }

        builder.setNegativeButton("Delete") { _, _ ->
            builder.setTitle("Are you sure you want to delete this item?")
            builder.setPositiveButton("Yes") { _, _ ->
                items.removeAt(position)
                notifyDataSetChanged()
            }
            builder.setNegativeButton("No", null)
            builder.show()

        }

        builder.show()
    }

    private fun showEditItemDialog(item: ListItem, position: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Edit Item")

        val editText = EditText(context)
        editText.setText(item.text)

        val pickImageButton = Button(context).apply {
            text = context.getString(R.string.change_image)
        }

        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            addView(editText)
            addView(pickImageButton)
        }
        builder.setView(layout)

        pickImageButton.setOnClickListener {
            (context as MainActivity).editItemPosition =
                position
            (context).pickImageFromGallery()
        }

        builder.setPositiveButton("Save") { _, _ ->
            item.text = editText.text.toString()
            notifyDataSetChanged()
        }

        builder.setNegativeButton("Cancel", null)
        builder.show()
    }
}


