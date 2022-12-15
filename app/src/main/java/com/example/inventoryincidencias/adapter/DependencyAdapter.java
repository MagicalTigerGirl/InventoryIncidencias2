package com.example.inventoryincidencias.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.data.repository.DependencyRepository;
import com.example.inventoryincidencias.data.model.Dependency;
import com.example.inventoryincidencias.databinding.FragmentDependencyListBinding;
import com.example.inventoryincidencias.databinding.ItemDependencyBinding;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.Collections;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {

    private ArrayList<Dependency> list;
    private FragmentDependencyListBinding binding;
    private OnManageDependencyListener listener;

    /**
     * Se declara una interfaz o contrato entre el Adapter-UI Controller
     */
    public interface OnManageDependencyListener{
        // 1. Si se hace click se editará la dependencia (onClickListener)
        void onEditDependency(Dependency dependency);


        // 2. Si se hace longClick entendemos que se quiere eliminar la dependencia (onLongClickListener)
        void onDeleteDependency(Dependency dependency);
    }

    public DependencyAdapter(OnManageDependencyListener listener) {
        //this.list = DependencyRepository.getInstance().getList();         el adapter NO puede depender del repositorio
        this.list = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public DependencyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // SOLUCION1: Tengo que conseguir el layoutInflater, e inflar la vista del XML item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dependency, parent, false);

        //SOLUCION2: Utilizo la clase Binding
        //ItemDependencyBinding binding = ItemDependencyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        // SOLUCION1 :
        return new ViewHolder(view);
        //SOLUCION2:
        //return new ViewHolder(binding.getRoot());

    }

    @Override
    public void onBindViewHolder(@NonNull DependencyAdapter.ViewHolder holder, int position) {
        // SOLUCION 1:
        holder.tvName.setText(list.get(position).getName());
        holder.bind(listener, list.get(position));

        // SOLUCIÓN 2:
        //holder.binding.tvName.setText(list.get(position).getName());
        //holder.bind(listener, list.get(position));
    }

    /**
     * Cuidado que este método no puede devoler el valor por defecto 0. Nunca llamaría a onCreateViewHolder () ni
     * onBindViewHolder()
     * */
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircularImageView imgIcon;
        TextView tvName;
        ItemDependencyBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // SOLUCION1: Tengo que utilizar el findById
            imgIcon = itemView.findViewById(R.id.imgIcon);
            tvName = itemView.findViewById(R.id.tvName);

            // SOLUCION2: Tengo que ver para qué sirve el método
            binding = ItemDependencyBinding.bind(itemView);

        }

        // Solución 1 y 2: en este método se inicializa el listener y su dependencia a cada elemento itemView
        public void bind(OnManageDependencyListener listener, Dependency dependency){
            itemView.setOnClickListener(view -> listener.onEditDependency(dependency));
            itemView.setOnLongClickListener(view -> {
                listener.onDeleteDependency(dependency);
                return true;
            });
        }
    }

    /**
     * Este método actualiza los datos del ArrayList interna del adapter
     * @param data
     */
    public void update(ArrayList<Dependency> data) {
        list.clear();
        list.addAll(data);
        Collections.sort(list);

        // ¡¡ IMPORTANTE !! Si no se llama a este método no se actualiza el RECYCLERVIEW
        notifyDataSetChanged();
    }

    /**
     * Este método actualiza los datos del ArrayList interno del adapter ocn los datos ordenados por el ID
     */
    public void orderById(ArrayList<Dependency> data) {
        list.clear();
        list.addAll(data);

        notifyDataSetChanged();
    }

    public void delete(Dependency dependency) {
        list.remove(dependency);
        notifyDataSetChanged();
    }

    public void undo(Dependency deleteDependency) {
        list.add(deleteDependency);
        notifyDataSetChanged();
    }

}
