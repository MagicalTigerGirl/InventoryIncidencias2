package com.example.inventoryincidencias.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.databinding.FragmentLoginBinding;
import com.example.inventoryincidencias.databinding.FragmentSplashBinding;
import com.example.inventoryincidencias.ui.preferences.UserPrefManager;

public class SplashFragment extends Fragment {

    private static final long WAIT_TIME = 2000;
    private FragmentSplashBinding binding;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Handler es un manejador de procesos, .postDelayed() -> luego de ejecutar esto haz...
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NavHostFragment.findNavController(SplashFragment.this).navigate(R.id.LoginFragment);
            }
        },WAIT_TIME);*/

        // Lambda
        new Handler().postDelayed(() -> {
            if (new UserPrefManager(getContext()).isUserLogin())
                NavHostFragment.findNavController(SplashFragment.this).navigate(R.id.action_SplashFragment_to_dashBoardFragment);
            else
                NavHostFragment.findNavController(SplashFragment.this).navigate(R.id.action_SplashFragment_to_LoginFragment);
        },WAIT_TIME);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}