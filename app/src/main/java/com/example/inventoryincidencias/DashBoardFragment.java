package com.example.inventoryincidencias;

import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_SignUpFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventoryincidencias.databinding.FragmentDashBoardBinding;
import com.example.inventoryincidencias.databinding.FragmentSignUpBinding;


public class DashBoardFragment extends Fragment {

    public DashBoardFragment() {
    }

    private FragmentDashBoardBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDashBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imbInventario.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(R.id.action_dashBoardFragment_to_settingInventoryFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}