package com.example.inventoryincidencias.ui.base;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    /**
     * Este método muestra un error en el fragment.
     * Le paso el ID del string pasado a String con getSring(R.id.errorLogin)
     * @param error
     */
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
    /**
     * Le paso el ID del string con R.string.errorLogin*/
    public void showError(int error) {
        Toast.makeText(getContext(), getString(error), Toast.LENGTH_SHORT).show();
    }
}
