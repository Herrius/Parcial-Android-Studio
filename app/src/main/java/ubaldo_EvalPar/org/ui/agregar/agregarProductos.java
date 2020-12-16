package ubaldo_EvalPar.org.ui.agregar;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import ubaldo_EvalPar.org.R;

public class agregarProductos extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject> {
    EditText campIdProd, campNom,camTipoP,campDescriP,campPrecioP,campImagenP;
    Button btnGuardar;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    private AgregarProductosViewModel mViewModel;

    public static agregarProductos newInstance() {
        return new agregarProductos();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista =inflater.inflate(R.layout.agregar_productos_fragment,container,false);
        campIdProd=vista.findViewById(R.id.campIdProducto);
        campNom =vista.findViewById(R.id.campNombre);
        campDescriP=vista.findViewById(R.id.campDescripcion);
        camTipoP=vista.findViewById(R.id.campTipo);
        campPrecioP=vista.findViewById(R.id.campPrecio);
        campImagenP=vista.findViewById(R.id.campImagen);
        btnGuardar=vista.findViewById(R.id.btnGuardar);
        request= Volley.newRequestQueue(getContext());
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebservice();
            }
        });
        return vista;
    }
    private void cargarWebservice(){
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("cargando datos");
        progreso.show();
        String url="http://192.168.1.4/examen%20parcial/agregar.php?idProducto="+campIdProd.getText().toString()+"&nombre="+campNom.getText().toString()+
                "&descripcion="+campDescriP.getText().toString()+"&tipo="+camTipoP.getText().toString()+"&precio="+campPrecioP.getText().toString()+"&link="
                +campImagenP.getText().toString();
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(),"no se pudo registrar",Toast.LENGTH_SHORT).show();
        Log.i("Error",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Registro exitoso",Toast.LENGTH_SHORT).show();
        progreso.hide();
        campIdProd.setText("");
        campNom.setText("");
        campDescriP.setText("");
        camTipoP.setText("");
        campPrecioP.setText("");
        campImagenP.setText("");
    }
}