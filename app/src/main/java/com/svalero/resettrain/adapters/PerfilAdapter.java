package com.svalero.resettrain.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.resettrain.PerfilDetailsActivity;
import com.svalero.resettrain.R;
import com.svalero.resettrain.database.AppDatabase;
import com.svalero.resettrain.domain.Perfil;

import java.util.List;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder> {
    private Context context;
    private List<Perfil> perfilList;

    public PerfilAdapter(Context context, List<Perfil> dataList) {
        this.context = context;
        this.perfilList = dataList;
    }

    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.perfil_item, parent, false);
        return new PerfilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PerfilViewHolder holder, int position) {
        holder.perfilRitmo.setText(perfilList.get(position).getRitmo());
        holder.perfilMedidas.setText(perfilList.get(position).getMedidas());
        holder.perfilPeso.setText(perfilList.get(position).getPeso());
        holder.perfilObesidad.setChecked(perfilList.get(position).isObesidad());
    }

    @Override
    public int getItemCount() {
        return perfilList.size();
    }

    public class PerfilViewHolder extends RecyclerView.ViewHolder {
        public TextView perfilRitmo;
        public TextView perfilMedidas;
        public TextView perfilPeso;
        public CheckBox perfilObesidad;
        public Button deletePerfilButton;
        public Button modifyPerfilButton;
        public Button detailPerfilButton;
        public View parentView;

        public PerfilViewHolder(View view) {
            super(view);
            parentView = view;

            perfilRitmo = view.findViewById(R.id.perfil_ritmo);
            perfilMedidas = view.findViewById(R.id.perfil_medidas);
            perfilPeso = view.findViewById(R.id.perfil_peso);
            perfilObesidad = view.findViewById(R.id.obesidadCB);
            deletePerfilButton = view.findViewById(R.id.delete_perfil_button);
            modifyPerfilButton = view.findViewById(R.id.modify_perfil_button);
            detailPerfilButton = view.findViewById(R.id.details_perfil_button);

            // Eliminar usuario
            deletePerfilButton.setOnClickListener(v -> deletePerfil(getAdapterPosition()));
            // Ver detalles del usuario
            detailPerfilButton.setOnClickListener(v -> detailPerfil(getAdapterPosition()));
            // Modificar o Editar el usuario
            modifyPerfilButton.setOnClickListener(v -> modifyPerfil(getAdapterPosition()));
        }
    }
    private void modifyPerfil(int position) {
        Perfil perfil = perfilList.get(position);
        perfil.setObesidad(true);

        final AppDatabase database = Room.databaseBuilder(context, AppDatabase.class, "perfil")
                .allowMainThreadQueries().build();
        database.perfilDao().update(perfil);

        notifyItemChanged(position);
    }
    private void detailPerfil(int position) {
        Perfil perfil = perfilList.get(position);

        Intent intent = new Intent(context, PerfilDetailsActivity.class);
        intent.putExtra("ritmo", perfil.getRitmo());
        context.startActivity(intent);
    }
    private void deletePerfil(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.are_you_sure_message)
                .setTitle(R.string.remove_task_message)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    final AppDatabase database = Room.databaseBuilder(context, AppDatabase.class,"perfil")
                            .allowMainThreadQueries().build();
                    Perfil perfil = perfilList.get(position);
                    database.perfilDao().delete(perfil);

                    perfilList.remove(position);
                    notifyItemRemoved(position);
                })
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
