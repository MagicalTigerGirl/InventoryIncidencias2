package com.example.inventoryincidencias.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.data.model.Dependency;
import com.example.inventoryincidencias.databinding.FragmentCreateDependencyBinding;
import com.example.inventoryincidencias.ui.base.BaseFragment;
import com.example.inventoryincidencias.ui.dependency.DependencyListViewModel;
import com.example.inventoryincidencias.ui.dependency.DependencyManagerViewModel;

public class CreateDependencyFragment extends BaseFragment {

    FragmentCreateDependencyBinding binding;
    DependencyManagerViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateDependencyBinding.inflate(inflater, container, false);
        binding.setDependency(new Dependency());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() == null) {
            binding.tvTitulo.setText("Crear dependencia");
            binding.btnCreateDependency.setOnClickListener(view1 -> {
                //viewModel.add(binding.getDependency());
                viewModel.add(new Dependency(binding.tilCreateDependencyName.getEditText().getText().toString(), binding.tilCreateDependencyShortName.getEditText().getText().toString(), binding.tilCreateDependencyDescription.getEditText().getText().toString(), binding.tilCreateDependencyImage.getEditText().getText().toString()));
            });
        }else {
            binding.tvTitulo.setText("Editar dependencia");
            binding.btnCreateDependency.setText("EDITAR DEPENDENCIA");
            binding.tilCreateDependencyShortName.getEditText().setEnabled(false);
            binding.setDependency(getArguments().getParcelable("dependency"));
            binding.btnCreateDependency.setOnClickListener(view1 -> {
                //viewModel.edit(binding.getDependency());
                viewModel.edit(new Dependency(binding.tilCreateDependencyName.getEditText().getText().toString(), binding.tilCreateDependencyShortName.getEditText().getText().toString(), binding.tilCreateDependencyDescription.getEditText().getText().toString(), binding.tilCreateDependencyImage.getEditText().getText().toString()));
            });
        }

        initViewModel();
    }

    /**
     * Método que inicializa el ViewModel
     */
    private void initViewModel() {
        viewModel= new ViewModelProvider(this).get(DependencyManagerViewModel.class);
        viewModel.getResult().observe(getViewLifecycleOwner(), dependencyManagerResult -> {
            switch (dependencyManagerResult){
                case NAMEEMPTY:
                    showError(R.string.errorNameEmpty);
                    break;
                case SHORTNAMEEMPTY:
                    showError(R.string.errorShortNameEmpty);
                    break;
                case SHORTNAMEFORMAT:
                    showError(R.string.errorShortNameFormat);
                    break;
                case FAILURE:
                    showError(R.string.errorDuplicate);
                    break;
                case SUCCESS:
                    NavHostFragment.findNavController(this).navigateUp();
                    break;
            }
        });
    }
}