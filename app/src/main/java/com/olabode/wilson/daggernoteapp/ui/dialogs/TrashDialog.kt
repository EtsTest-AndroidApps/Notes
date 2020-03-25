package com.olabode.wilson.daggernoteapp.ui.dialogs

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.olabode.wilson.daggernoteapp.R
import com.olabode.wilson.daggernoteapp.models.Note

/**
 *   Created by OLABODE WILSON on 2020-03-25.
 */
class TrashDialog(val note: Note) : AppCompatDialogFragment() {

    // Use this instance of the interface to deliver action events
    private lateinit var listener: NoteDialogListener

    interface NoteDialogListener {
        fun onNoteOptionClick(note: Note, position: Int)
    }

    fun setNoteDialogClickListener(listener: NoteDialogListener) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = MaterialAlertDialogBuilder(it)
            builder.setTitle(getString(R.string.more))
                .setItems(
                    R.array.trash_options,
                    DialogInterface.OnClickListener { _, which ->
                        listener.onNoteOptionClick(note, which)
                        dismiss()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}