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
import com.svalero.resettrain.domain.Usuario;
import com.svalero.resettrain.presenter.UsuarioDetailsPresenter;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private Context context;
    private List<Usuario> usuarioList;

    public UsuarioAdapter(Context context, List<Usuario> dataList) {
        this.context = context;
        this.usuarioList = dataList;
    }

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.usuario_item, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder holder, int position) {
        holder.usuarioName.setText(usuarioList.get(position).getName());
        holder.usuarioObjetive.setText(usuarioList.get(position).getObjective());
        holder.usuarioPhone.setText(usuarioList.get(position).getPhone());
        holder.lesionDone.setChecked(usuarioList.get(position).isLesion());
    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {
        public TextView usuarioName;
        public TextView usuarioObjetive;
        public TextView usuarioPhone;
        public CheckBox lesionDone;
        public Button deleteUsuarioButton;
        public Button modifyUsuarioButton;
        public Button detailUsuarioButton;
        public View parentView;

        public UsuarioViewHolder(View view) {
            super(view);
            parentView = view;

            usuarioName = view.findViewById(R.id.usuario_name);
            usuarioObjetive = view.findViewById(R.id.usuario_objetive);
            usuarioPhone = view.findViewById(R.id.usuario_phone);
            lesionDone = view.findViewById(R.id.lesionadoCB);
            deleteUsuarioButton = view.findViewById(R.id.delete_usuarios_button);
            modifyUsuarioButton = view.findViewById(R.id.modify_usuarios_button);
            detailUsuarioButton = view.findViewById(R.id.details_usuario_button);

            // Eliminar usuario
            deleteUsuarioButton.setOnClickListener(v -> deleteUsuario(getAdapterPosition()));
            // Ver detalles del usuario
            detailUsuarioButton.setOnClickListener(v -> detailUsuario(getAdapterPosition()));
            // Modificar o Editar el usuario
            modifyUsuarioButton.setOnClickListener(v -> modifyUsuario(getAdapterPosition()));
        }
    }
    private void modifyUsuario(int position) {
        Usuario usuario = usuarioList.get(position);
        usuario.setLesion(true);

        final AppDatabase database = Room.databaseBuilder(context, AppDatabase.class, "usuario")
                .allowMainThreadQueries().build();
        database.usuarioDao().update(usuario);

        notifyItemChanged(position);
    }
    private void detailUsuario(int position) {
        Usuario usuario = usuarioList.get(position);

        Intent intent = new Intent(context, UsuarioDetailsActivity.class);
        intent.putExtra("name", usuario.getName());
        context.startActivity(intent);
    }
    private void deleteUsuario(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.are_you_sure_message)
                .setTitle(R.string.remove_task_message)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    final AppDatabase database = Room.databaseBuilder(context, AppDatabase.class,"usuario")
                            .allowMainThreadQueries().build();
                    Usuario usuario = usuarioList.get(position);
                    database.usuarioDao().delete(usuario);

                    usuarioList.remove(position);
                    notifyItemRemoved(position);
                })
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
