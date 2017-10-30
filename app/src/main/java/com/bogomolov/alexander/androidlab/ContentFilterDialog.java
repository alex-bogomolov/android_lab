package com.bogomolov.alexander.androidlab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by admin on 30.10.2017.
 */

public class ContentFilterDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final NotesActivity activity = (NotesActivity) getActivity();

        builder.setView(inflater.inflate(R.layout.content_filter, null))
                .setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog d = ContentFilterDialog.this.getDialog();

                        TextView textView = (TextView) d.findViewById(R.id.filter_content_text_edit);
                        activity.filterByContent(textView.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ContentFilterDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
