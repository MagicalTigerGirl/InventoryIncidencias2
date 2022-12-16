package com.example.inventoryincidencias.ui;

import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_DashBoardFragment;
import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_SignUpFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.inventoryincidencias.databinding.FragmentDashBoardBinding;
import com.example.inventoryincidencias.databinding.FragmentLoginBinding;
import com.example.inventoryincidencias.databinding.FragmentSettingInventoryBinding;
import com.example.inventoryincidencias.ui.base.BaseDatePickerDialog;

public class SettingInventoryFragment extends Fragment {

    private FragmentSettingInventoryBinding binding;

    public SettingInventoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSettingInventoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imbCreateCalendar.setOnClickListener(view1 -> {
            showDatePickerDialog();
        });

        binding.fabSettingInventory.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigateUp());    }

    /**
     * Este método muestra un fragment con un DataPickerDialog
     */
    private void showDatePickerDialog() {
        BaseDatePickerDialog newFragment = BaseDatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                binding.etFechaCreacion.setText(selectedDate);
                binding.etFechaCreacion.setEnabled(false);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}