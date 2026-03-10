package dev.fixfis.ecolim.stuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.fixfis.ecolim.R;
import dev.fixfis.ecolim.server.entities.DesperdicioDto;

public class DesperdicoAdapter extends RecyclerView.Adapter<DesperdicoAdapter.ViewHolder>{

    List<DesperdicioDto> lista;
    private final Context c;

    public DesperdicoAdapter(List<DesperdicioDto> lista, Context c){
        this.lista = lista;
        this.c = c;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView cantidad;
        Button eliminar;

        public ViewHolder(View itemView){
            super(itemView);

            nombre = itemView.findViewById(R.id.txtNombre);
            cantidad = itemView.findViewById(R.id.txtcantidad);
            eliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lugar, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        DesperdicioDto lugar = lista.get(position);

        holder.nombre.setText(lugar.getNombre());
        holder.cantidad.setText(String.valueOf(lugar.getCantidad()));

        holder.eliminar.setOnClickListener(v -> {

            Toast.makeText(c,"Funcion no implementada!!",Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public int getItemCount(){
        return lista.size();
    }
}