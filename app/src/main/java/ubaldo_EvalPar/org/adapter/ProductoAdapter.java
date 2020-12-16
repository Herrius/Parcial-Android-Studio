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

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoHolder> {
    List<producto> listaProducto;
    public ProductoAdapter(List<producto>listaProducto){
        this.listaProducto=listaProducto;
    }

    @NonNull
    @Override
    public ProductoAdapter.ProductoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.productolist,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new ProductoHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoAdapter.ProductoHolder holder, int position) {
        holder.txtnombre.setText(String.valueOf(listaProducto.get(position).getNombre()));
        holder.txtdescripcion.setText(String.valueOf(listaProducto.get(position).getDescripcion()));
        holder.txttipo.setText(String.valueOf(listaProducto.get(position).getTipo()));
        holder.txtprecio.setText(String.valueOf(listaProducto.get(position).getPrecio()));
//        holder.txtimagen.setText(String.valueOf(listaProducto.get(position).getLink()));
        Picasso.get().load(String.valueOf(listaProducto.get(position).getLink())).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return listaProducto.size();
    }

    public class ProductoHolder extends RecyclerView.ViewHolder {
        TextView txtnombre, txtdescripcion,txttipo,txtprecio;
        ImageView imagen;
        public ProductoHolder(@NonNull View vista){
            super(vista);
            txtnombre=vista.findViewById(R.id.ultxtNombre);
            txtdescripcion=vista.findViewById(R.id.ultxtDescripcion);
            txttipo=vista.findViewById(R.id.ultxTipo);
            txtprecio=vista.findViewById(R.id.ultxtPrecio);
            imagen=vista.findViewById(R.id.ulImagen);
        }
    }
}
