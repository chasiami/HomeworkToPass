package com.example.homeworktopass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallDialog extends DialogFragment {
    private OnCallDialogInteractionListener mListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CallDialog.OnCallDialogInteractionListener) {
            mListener = (CallDialog.OnCallDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDeleteDialogInteractionListener");
        }
    }

    public CallDialog() {
        // Required empty public constructor
    }

    static CallDialog newInstance() {
        return new CallDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setMessage(getString(R.string.callQuestion));

        builder.setPositiveButton(getString(R.string.callConfirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onCallPositiveClick(CallDialog.this);
            }
        });

        builder.setNegativeButton(getString(R.string.callDeclain), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onCallNegativeClick(CallDialog.this);
            }
        });
        return builder.create();
    }

    public interface OnCallDialogInteractionListener {
        void onCallPositiveClick(DialogFragment dialog);

        void onCallNegativeClick(DialogFragment dialog);
    }
}