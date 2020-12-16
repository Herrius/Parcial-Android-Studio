package ubaldo_EvalPar.org.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ubaldo_EvalPar.org.R;
import ubaldo_EvalPar.org.entidad.producto;

public class topAdapter extends RecyclerView.Adapter<topAdapter.topHolder> {
    List<producto> listaTop;

    public topAdapter(List<producto> listaTop) {
        this.listaTop = listaTop;
    }

    @NonNull
    @Override
    public topAdapter.topHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.toplist, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new topHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull topHolder holder, int position) {
        int temporal=1;
        holder.txtidproducto.setText(String.valueOf(listaTop.get(position).getIdProducto()));
        Picasso.get().load(String.valueOf(listaTop.get(position).getLink())).into(holder.imagenTop);
        temporal++;
    }

    @Override
    public int getItemCount() {
        return listaTop.size();
    }

    public class topHolder extends RecyclerView.ViewHolder {
        TextView txtidproducto;
        ImageView imagenTop;

        public topHolder(@NonNull View vista) {
            super(vista);
            txtidproducto = vista.findViewById(R.id.txtTopId);
            imagenTop = vista.findViewById(R.id.image);
        }
    }
}