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

import com.svalero.resettrain.R;
import com.svalero.resettrain.database.AppDatabase;
import com.svalero.resettrain.domain.Rutina;
import com.svalero.resettrain.view.RutinaDetailsView;

import java.util.List;

public class RutinaAdapter extends RecyclerView.Adapter<RutinaAdapter.RutinaViewHolder> {

    private Context context;
    private List<Rutina> rutinaList;

    public RutinaAdapter(Context context, List<Rutina> dataList) {
        this.context = context;
        this.rutinaList = dataList;
    }


    @Override
    public RutinaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rutina_item, parent, false);
        return new RutinaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RutinaViewHolder holder, int position) {
        holder.rutinaModalidad.setText(rutinaList.get(position).getModalidad());
        holder.rutinaSeries.setText(rutinaList.get(position).getSeries());
        holder.rutinaRepeticiones.setText(rutinaList.get(position).getRepeticiones());
        holder.rutinaMaterial.setChecked(rutinaList.get(position).isMaterial());
    }

    @Override
    public int getItemCount() {
        return rutinaList.size();
    }

    public class RutinaViewHolder extends RecyclerView.ViewHolder {
        public TextView rutinaModalidad;
        public TextView rutinaSeries;
        public TextView rutinaRepeticiones;
        public CheckBox rutinaMaterial;
        public Button deleteRutinaButton;
        public Button modifyRutinaButton;
        public Button modifyFavoritoButton;
        public Button detailRutinaButton;
        public View parentView;

        public RutinaViewHolder(View view) {
            super(view);
            parentView = view;

            rutinaModalidad = view.findViewById(R.id.rutina_modalidad);
            rutinaSeries = view.findViewById(R.id.rutina_series);
            rutinaRepeticiones = view.findViewById(R.id.rutina_repes);
            rutinaMaterial = view.findViewById(R.id.materialCB);
            deleteRutinaButton = view.findViewById(R.id.delete_rutina_button);
            modifyRutinaButton = view.findViewById(R.id.modify_rutina_button);
            modifyFavoritoButton = view.findViewById(R.id.modify_favorito_button);
            detailRutinaButton = view.findViewById(R.id.details_rutina_button);

            // Eliminar usuario
            deleteRutinaButton.setOnClickListener(v -> deleteRutina(getAdapterPosition()));
            // Ver detalles del usuario
            detailRutinaButton.setOnClickListener(v -> detailRutina(getAdapterPosition()));
            // Modificar o Editar el usuario
            modifyRutinaButton.setOnClickListener(v -> modifyRutina(getAdapterPosition()));
            // Favorito
            modifyFavoritoButton.setOnClickListener(v -> modifyFav(getAdapterPosition()));
        }
    }
    private void modifyRutina(int position) {
        Rutina rutina = rutinaList.get(position);
        rutina.setMaterial(!rutina.isMaterial());

        final AppDatabase database = Room.databaseBuilder(context, AppDatabase.class, "rutina")
                .allowMainThreadQueries().build();
        database.rutinaDao().update(rutina);

        notifyItemChanged(position);
    }
    private void modifyFav(int position) {
        Rutina rutina = rutinaList.get(position);
        rutina.setFavorito(!rutina.isFavorito());

        final AppDatabase database = Room.databaseBuilder(context, AppDatabase.class, "rutina")
                .allowMainThreadQueries().build();
        database.rutinaDao().update(rutina);

        notifyItemChanged(position);
    }
    private void detailRutina(int position) {
        Rutina rutina = rutinaList.get(position);

        Intent intent = new Intent(context, RutinaDetailsView.class);
        intent.putExtra("modalidad", rutina.getModalidad());
        context.startActivity(intent);
    }
    private void deleteRutina(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.are_you_sure_message)
                .setTitle(R.string.remove_task_message)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    final AppDatabase database = Room.databaseBuilder(context, AppDatabase.class,"rutina")
                            .allowMainThreadQueries().build();
                    Rutina rutina = rutinaList.get(position);
                    database.rutinaDao().delete(rutina);

                    rutinaList.remove(position);
                    notifyItemRemoved(position);
                })
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
