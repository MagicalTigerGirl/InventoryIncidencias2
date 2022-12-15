package com.example.inventoryincidencias.ui.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class BaseFragmentDialog extends DialogFragment {

    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_BUNDLE = "result";
    public static final String KEY_REQUEST = "request";
    public static final String TAG_FRAGMENT = "fragment";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            // Me han pasado información para crear el Diálogo
            String title = getArguments().getString(KEY_TITLE);
            String message = getArguments().getString(KEY_MESSAGE);

            // Se crea así mismo a través del patrón Builder del tipo de diálogo a crear
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                Bundle result = new Bundle();
                result.putBoolean(KEY_BUNDLE, true);
                // Fragment parent = getActivity().getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT); buscar el fragment a través del TAG
                getActivity().getSupportFragmentManager().setFragmentResult(KEY_REQUEST, result);
            });
            builder.setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> {
                dismiss();
            });

            // Se devuelve una instancia de Dialog
            return builder.create();
        }
        return null;
    }
}
