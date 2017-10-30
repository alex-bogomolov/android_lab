package com.bogomolov.alexander.androidlab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by admin on 30.10.2017.
 */

public class PriorityFilterDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final NotesActivity activity = (NotesActivity) getActivity();

        builder.setView(inflater.inflate(R.layout.priority_filter, null))
                .setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog d = PriorityFilterDialog.this.getDialog();

                        RadioGroup radioGroup = (RadioGroup) d.findViewById(R.id.filter_priority_group);
                        RadioButton radioButton = (RadioButton) d.findViewById(radioGroup.getCheckedRadioButtonId());
                        int priority = Integer.parseInt((String) radioButton.getTag());

                        activity.filterByPriority(priority);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PriorityFilterDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
