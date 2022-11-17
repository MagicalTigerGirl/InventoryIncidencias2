package com.example.inventoryincidencias.ui;

import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_DashBoardFragment;
import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_SignUpFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventoryincidencias.databinding.FragmentDashBoardBinding;
import com.example.inventoryincidencias.databinding.FragmentLoginBinding;
import com.example.inventoryincidencias.databinding.FragmentSettingInventoryBinding;

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

        binding.fabSettingInventory.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigateUp());    }
}