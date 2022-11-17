package com.example.inventoryincidencias.ui.dependency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.adapter.DependencyAdapter;
import com.example.inventoryincidencias.data.model.Dependency;
import com.example.inventoryincidencias.databinding.FragmentDependencyListBinding;
import com.google.android.material.snackbar.Snackbar;


/**
 * A fragment representing a list of Items.
 */
public class DependencyListFragment extends Fragment implements DependencyAdapter.OnManageDependencyListener {

    private FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_dependency_list, container, false);
        binding = FragmentDependencyListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerDependency();
    }

    /**
     * Método que inicializa el componente recyclerView
     * */
    private void initRecyclerDependency() {
        // 1. Se debe inicializar el Adapter del RecyclerView
        adapter = new DependencyAdapter(this);

        // 2. OBLIGATORIAMENTE se debe asignar el diseño (layout) que tendrá el componente recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        // 3. Se asigna el layout al recyclerview
        binding.rvDependency.setLayoutManager(layoutManager);

        // 4. Asignar el adapter al reccyclerview
        binding.rvDependency.setAdapter(adapter);

    }

    //ESTOS MÉTODOS SE ORIGINAN POR EL CONTRATO ESTABLECIDO ENTRE EL ADAPTER Y SU UI CONTROLLER
    @Override
    public void onEditDependency(Dependency dependency) {
        Snackbar.make(getView(), "Has pulsado sobre: "+dependency.getName(), Snackbar.LENGTH_SHORT).show();     //getView() -> coordinatorLayout
    }

    @Override
    public void onDeleteDependency(Dependency dependency) {

    }
}