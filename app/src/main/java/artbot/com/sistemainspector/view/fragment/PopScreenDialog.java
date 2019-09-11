package artbot.com.sistemainspector.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import artbot.com.sistemainspector.R;

public class PopScreenDialog extends DialogFragment {

    private Toolbar toolbar;
    private View view;
    private long backPressedTime;
    private Spinner opciones;
    private EditText editText;

    private  String NameUser;
    private int idUser;
    private int idResult;

    private String text;
    private String comentario;

    public PopScreenDialog() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            idResult = getArguments().getInt("id",0);
            idUser = getArguments().getInt("idUser",0);
            NameUser = getArguments().getString("nameUser","");

        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pop_report, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbarPop);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Nuevo Reporte");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        //showToolbar1("Nuevo Reporte",true,view);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel);
        }


        opciones = (Spinner) view.findViewById(R.id.spselect);
        editText = (EditText) view.findViewById(R.id.comentarioReportEdittext);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.opciones, R.layout.textview_spinner);
        opciones.setAdapter(adapter);


        setHasOptionsMenu(true);
        //Toast.makeText(getContext(),String.valueOf(idResult),Toast.LENGTH_SHORT).show();
        return view;
    }





    @Override
    public void onCreateOptionsMenu(Menu menu1, MenuInflater inflater1) {
        inflater1.inflate(R.menu.popscreen_dialog, menu1);
        MenuItem item = menu1.findItem(R.id.reportItem);
        item.setVisible(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save){
            //Spinner spinner = (Spinner) view.findViewById(R.id.spselect);
            text = opciones.getSelectedItem().toString();
            //EditText editText = (EditText) view.findViewById(R.id.comentarioReportEdittext);
            comentario = editText.getText().toString();

            if (!comentario.isEmpty()){
                String URL = "http://www.carmen.gob.mx/sistema-ambulante/estilos/android/generateReport.php";
                ejecutarServicio(URL);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Exito")
                        .setMessage("El reporte se ha generado correctamente")
                        .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Fragment target = getTargetFragment();
                                if (target == null){ return;}
                                String mensaje = "HOLA";
                                Intent data = new Intent();
                                data.putExtra("hola",mensaje);
                                target.onActivityResult(getTargetRequestCode(),getActivity().RESULT_OK,data);
                                dismiss();
                            }
                        });
                builder.create();
                builder.show();

            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Hola")
                        .setMessage("Ingresa un comentario")
                        .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.create();
                builder.show();
            }




            return true;
        }else if (id == android.R.id.home){
            dismiss();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    private void ejecutarServicio(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getContext(),"Reporte enviado",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString()+ text + comentario,Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("id_master",String.valueOf(idUser));
                parametros.put("nombre_master",NameUser);
                parametros.put("id_comerciante",String.valueOf(idResult));
                parametros.put("comentario",comentario);
                parametros.put("reporte",text);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        


    }


}
