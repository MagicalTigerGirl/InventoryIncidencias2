package com.example.inventoryincidencias.ui.dependency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.adapter.DependencyAdapter;
import com.example.inventoryincidencias.data.model.Dependency;
import com.example.inventoryincidencias.databinding.FragmentDependencyListBinding;
import com.example.inventoryincidencias.ui.base.BaseFragmentDialog;
import com.example.inventoryincidencias.viewmodel.StateDataList;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


/**
 * A fragment representing a list of Items.
 */
public class DependencyListFragment extends Fragment implements DependencyAdapter.OnManageDependencyListener {

    private static final String TAG = "DependencyListFragment";

    private FragmentDependencyListBinding binding;
    private DependencyAdapter adapter;
    private DependencyListViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_dependency_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_dependency_orderById:
                viewModel.orderById();
                return true;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDependencyListBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerDependency();
        initViewModel();

        binding.fab.setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(R.id.action_dependencyListFragment_to_createDependencyFragment));
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

    /**
     * Este método inicializar el componente ViewModel de esta vista
     */
    private void initViewModel() {
        // 1. Instanciar la clase ViewModel a través de su Provider
        viewModel = new ViewModelProvider(this).get(DependencyListViewModel.class);

        // 2. Crear el observador del objeto MutableLiveData
        viewModel.getLiveDataList().observe(getViewLifecycleOwner(), new Observer<StateDataList<ArrayList<Dependency>>>() {
            @Override
            public void onChanged(StateDataList<ArrayList<Dependency>> stateDataList) {
                switch (stateDataList.getState()) {
                    case LOADING:
                        // Se mostraría un progressBar al usuario
                        break;
                    case NODATA:
                        showNoData();
                        hideRecyclerDependency();
                        stateDataList.complete();    // ESTA LÍNEA se añade aquí para que se ejecute si o si el estado NODATA
                        break;
                    case SUCCESS:
                        hideNoData();
                        showRecyclerDependency();
                        adapter.update(stateDataList.getData());
                        stateDataList.complete();    // ESTA LÍNEA ES CONSECUENCIA para que se ejecute si o si el estado SUCCESS
                        break;
                    case ORDER_BY_ID:
                        adapter.orderById(stateDataList.getData());
                        break;
                    case COMPLETE:
                        // Se ocultaría le progressBar al usuario
                        break;
                }
            }
        });

        // 3. Crear el observador del objeto
        viewModel.getDeleteDependency().observe(getViewLifecycleOwner(), new Observer<Dependency>() {
            @Override
            public void onChanged(Dependency deleteDependency) {
                if (deleteDependency != null) { // Solo se ejecuta cuando se elimina
                // Código con efecto colateral
                Snackbar.make(getView(), "Deshacer eliminar"+deleteDependency.getShortName(), Snackbar.LENGTH_SHORT).setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.undo();       // Se debe añadir de nuevo la dependencia en el repositorio remoto
                        adapter.undo(deleteDependency);        // Se debe añadir de nuevo la dependencia en el repositorio local
                    }
                }).show();
            }
            }
        });


        // 3. Pedir los datos al ViewModel, que a su vez llamará al Repository
        viewModel.getDataList();
    }

    private void showNoData() {
        binding.animationGif.setVisibility(View.VISIBLE);
    }

    /**
     * Este método cambia la visibilidad del RecyclerView
     */
    private void hideRecyclerDependency() {
        binding.rvDependency.setVisibility(View.INVISIBLE);
    }

    private void hideNoData() {
        binding.animationGif.setVisibility(View.INVISIBLE);
    }

    private void showRecyclerDependency() {
        binding.rvDependency.setVisibility(View.VISIBLE);
    }



    //ESTOS MÉTODOS SE ORIGINAN POR EL CONTRATO ESTABLECIDO ENTRE EL ADAPTER Y SU UI CONTROLLER
    @Override
    public void onEditDependency(Dependency dependency) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("dependency", dependency);
        NavHostFragment.findNavController(this).navigate(R.id.action_dependencyListFragment_to_createDependencyFragment, bundle);
    }

    @Override
    public void onDeleteDependency(Dependency dependency) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragmentDialog.KEY_TITLE, getString(R.string.title_delete_dependency));
        bundle.putString(BaseFragmentDialog.KEY_MESSAGE, getString(R.string.message_delete, dependency.getShortName()));
        //bundle.putString(BaseFragmentDialog.TAG_FRAGMENT, TAG);
        NavHostFragment.findNavController(this).navigate(R.id.action_dependencyListFragment_to_baseFragmentDialog, bundle);
        getActivity().getSupportFragmentManager().setFragmentResultListener(BaseFragmentDialog.KEY_REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (result.getBoolean(BaseFragmentDialog.KEY_BUNDLE)){
                    viewModel.delete(dependency);   // Mi petición a la BD (copia remota)
                    adapter.delete(dependency);     // Y tengo que actualizar  mi Lista (copia local)
                }
            }
        });
    }
}