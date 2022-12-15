package com.example.inventoryincidencias.ui.section;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.adapter.SectionAdapter;
import com.example.inventoryincidencias.data.model.Section;
import com.example.inventoryincidencias.databinding.FragmentSectionBinding;

import java.util.ArrayList;

public class SectionFragment extends Fragment implements SectionAdapter.OnManageSectionManager {

    private FragmentSectionBinding binding;
    private SectionAdapter adapter;
    private SectionViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSectionBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 1. Se inicializa la clase ViewModel que gestionará la vista
        viewModel = new ViewModelProvider(this).get(SectionViewModel.class);
        // 2. Se inicializa los observadores
        // 2.1 Observador de los datos
        viewModel.getData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Section>>() {
            @Override
            public void onChanged(ArrayList<Section> list) {
                adapter.update(list);
            }
        });
        // 2.2 Observador de las alternativas del caso de uso
        viewModel.getState().observe(getViewLifecycleOwner(), new Observer<SectionResult>() {
            @Override
            public void onChanged(SectionResult sectionResult) {
                switch (sectionResult)
                {
                    case LOADING:
                        // Se mostraría el progressBar
                        break;
                    case NODATA:
                        showNoData();
                        hideRecycler();
                        break;
                    case SUCCESS:
                        hideNoData();
                        showRecycler();
                        break;
                    case COMPLETE:
                        // Se ocultaría el progressBar
                        break;
                }
            }
        });
        // 3. Pido al modelo que me de los datos a mostrar
        viewModel.getList();
        initRecyclerView();
    }

    private void showNoData() {
        binding.animationGif.setVisibility(View.VISIBLE);
    }

    private void hideRecycler() {
        binding.rvSection.setVisibility(View.INVISIBLE);
    }

    private void showRecycler() {
        binding.rvSection.setVisibility(View.VISIBLE);
    }

    private void hideNoData() {
        binding.animationGif.setVisibility(View.INVISIBLE);
    }

    private void initRecyclerView() {
        adapter = new SectionAdapter(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        binding.rvSection.setLayoutManager(layoutManager);
        binding.rvSection.setAdapter(adapter);
    }

    @Override
    public void onEditSection(Section section) {

    }

    @Override
    public void onDeleteSection(Section section) {

    }
}