package com.capuras.menu

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.ListFragment

class CustomDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_custom, null)

        val titleEditText  = dialogView.findViewById<EditText>(R.id.titleET)
        val messageEditText  = dialogView.findViewById<EditText>(R.id.messageET)

        builder.setView(dialogView)
            .setTitle("Input title and message")
            .setPositiveButton("Submit"){_, _ ->
                val title = titleEditText.text.toString()
                val message = messageEditText.text.toString()

                val fragment  = parentFragmentManager.findFragmentById(R.id.fragment_container)
                if (fragment is FirstFragment){
                    fragment.updateList(title, message)
                }
            }
            .setNegativeButton("Cancel"){ dialog, _ ->
                dialog.dismiss()
            }
        return builder.create()
    }
}

