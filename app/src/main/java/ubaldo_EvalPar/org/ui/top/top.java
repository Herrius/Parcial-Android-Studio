package ubaldo_EvalPar.org.ui.top;

import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ubaldo_EvalPar.org.R;
import ubaldo_EvalPar.org.adapter.topAdapter;
import ubaldo_EvalPar.org.entidad.producto;

public class top extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    RecyclerView recyclerUsuarios;
    ArrayList<producto> listaTop;
    ProgressDialog progress;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    private TopViewModel mViewModel;

    public static top newInstance() {
        return new top();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.top_fragment,container,false);
        listaTop=new ArrayList<>();
        recyclerUsuarios= vista.findViewById(R.id.idRecyclerImage);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager((this.getContext())));
        recyclerUsuarios.setHasFixedSize(true);
        request= Volley.newRequestQueue(getContext());
        cargarWebService();
        return vista;
    }

    private void cargarWebService(){
        progress=new ProgressDialog(getContext());
        progress.setMessage("Listando");
        progress.show();
        String url="http://192.168.1.4/examen%20parcial/top.php";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
        Toast.makeText(getContext(),"consultando",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        producto top=null;
        JSONArray json =response.optJSONArray("producto");
        try {
            for (int i=0;i<json.length();i++){
                top=new producto();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);
                top.setIdProducto(jsonObject.optInt("idProducto"));
                top.setLink(jsonObject.optString("link"));
                listaTop.add(top);
            }
            progress.hide();
            topAdapter adapter = new topAdapter(listaTop);
            recyclerUsuarios.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getContext(),"No conexion con el server" + response,Toast.LENGTH_SHORT).show();
            progress.hide();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"no se pudo conectar"+error.toString(),Toast.LENGTH_SHORT).show();

        Log.i("ERROR",toString());
        progress.hide();
    }
}