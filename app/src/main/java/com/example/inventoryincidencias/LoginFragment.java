package com.example.inventoryincidencias;

import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_DashBoardFragment;
import static com.example.inventoryincidencias.R.id.action_LoginFragment_to_SignUpFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventoryincidencias.databinding.FragmentLoginBinding;
import com.example.inventoryincidencias.databinding.FragmentSplashBinding;

public class LoginFragment extends Fragment {

    public LoginFragment() {
    }

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnRegistrar.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(action_LoginFragment_to_SignUpFragment));
        binding.btnLogin.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(action_LoginFragment_to_DashBoardFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}