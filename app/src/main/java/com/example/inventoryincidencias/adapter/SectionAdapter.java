package com.example.inventoryincidencias.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryincidencias.R;
import com.example.inventoryincidencias.data.model.Dependency;
import com.example.inventoryincidencias.data.model.Section;
import com.example.inventoryincidencias.data.repository.SectionRepository;
import com.example.inventoryincidencias.databinding.FragmentSectionBinding;

import java.util.ArrayList;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {

    private ArrayList<Section> list;
    private FragmentSectionBinding binding;
    private OnManageSectionManager listener;

    // Declarar interfaz
    public interface OnManageSectionManager{
        void onEditSection(Section section);

        void onDeleteSection(Section section);
    }

    public SectionAdapter(OnManageSectionManager listener){
        this.list = new ArrayList<>();
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvShortName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvShortName = itemView.findViewById(R.id.tvShortName);
        }

        public void bind(OnManageSectionManager listener, Section section){
            itemView.setOnClickListener(view -> listener.onEditSection(section));
            itemView.setOnLongClickListener(view -> {
                listener.onDeleteSection(section);
                return true;
            });
        }
    }

    @NonNull
    @Override
    public SectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvShortName.setText(list.get(position).getShortName());
        holder.bind(listener, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * Este método actualiza los datos del ArrayList interna del adapter
     * @param data
     */
    public void update(ArrayList<Section> data) {
        list.clear();
        list.addAll(data);

        // ¡¡ IMPORTANTE !! Si no se llama a este método no se actualiza el RECYCLERVIEW
        notifyDataSetChanged();
    }
}
