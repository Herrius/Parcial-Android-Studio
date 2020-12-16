package ubaldo_EvalPar.org.ui.buscar;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ubaldo_EvalPar.org.R;
import ubaldo_EvalPar.org.entidad.producto;

public class buscar extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>, View.OnClickListener {
    EditText campIdProd;
    TextView txtNom,txtDescriP,txtPrecioP,txtidProducto;
    ImageView imagen;
    Button btnBuscarN,btnBuscarT;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest,jsonObjectRequest2;
    private BuscarViewModel mViewModel;

    public static buscar newInstance() {
        return new buscar();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.buscar_fragment,container,false);
        campIdProd=vista.findViewById(R.id.IdProducto);
        txtNom=vista.findViewById(R.id.txtNombre);
        txtDescriP=vista.findViewById(R.id.txtDescripcion);
        txtPrecioP=vista.findViewById(R.id.txtPrecio) ;
        txtidProducto=vista.findViewById(R.id.txtIdProducto);
        imagen=vista.findViewById(R.id.Imagen);
        btnBuscarN=vista.findViewById(R.id.btnMostrarN);
        btnBuscarT=vista.findViewById(R.id.btnMostrarT);
        request= Volley.newRequestQueue(getContext());
        btnBuscarT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebServiceT();

            }
        });
        btnBuscarN.setOnClickListener(this);
        return vista;
    }

    private void cargarWebServiceN(){
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Consulta de datos");
        progreso.show();
        String nombre="http://192.168.1.4/examen%20parcial/consulta.php?nombre="+campIdProd.getText().toString();
        nombre=nombre.replace(" ", "%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,nombre,null,this,this);
        request.add(jsonObjectRequest);
    }

    private void cargarWebServiceT(){
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Consulta de datos");
        progreso.show();
        String tipo="http://192.168.1.4/examen%20parcial/consulta.php?tipo="+campIdProd.getText().toString();
        tipo=tipo.replace(" ", "%20");
        jsonObjectRequest2=new JsonObjectRequest(Request.Method.GET,tipo,null,this,this);
        request.add(jsonObjectRequest2);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(),"No se pudo consultar" + error.toString(),Toast.LENGTH_SHORT).show();
        Log.i("Error",error.toString());
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Mensaje" + response,Toast.LENGTH_SHORT).show();
        progreso.hide();
        producto mercaderia=new producto();
        JSONArray json=response.optJSONArray("producto");
        JSONObject jsonObject=null;
        try{
            jsonObject=json.getJSONObject(0);
            mercaderia.setIdProducto(jsonObject.optInt("idProducto"));
            mercaderia.setNombre(jsonObject.optString("nombre"));
            mercaderia.setDescripcion(jsonObject.optString("descripcion"));
            mercaderia.setTipo(jsonObject.optString("tipo"));
            mercaderia.setPrecio(jsonObject.optDouble("precio"));
            mercaderia.setLink(jsonObject.optString("link"));

        }catch (JSONException e){
            e.printStackTrace();
        }
        txtidProducto.setText(Integer.toString(mercaderia.getIdProducto()));
        if (mercaderia.getTipo()=="")
            txtNom.setText(mercaderia.getNombre());
        else
            txtNom.setText(mercaderia.getTipo());
        txtDescriP.setText(mercaderia.getDescripcion());
        txtPrecioP.setText(Double.toString(mercaderia.getPrecio()));
        Picasso.get().load(mercaderia.getLink()).into(imagen);
    }

    @Override
    public void onClick(View view) {
        cargarWebServiceN();
    }
}
