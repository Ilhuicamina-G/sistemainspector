package artbot.com.sistemainspector.view.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;

import artbot.com.sistemainspector.R;

import artbot.com.sistemainspector.adapter.HorasAdapter;
import artbot.com.sistemainspector.adapter.ReportAdapterRecyclerView;
import artbot.com.sistemainspector.model.Horas;

import artbot.com.sistemainspector.model.Report;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComercianteFragment extends Fragment  {

    private int idResult;
    private String userResult = "";
    private String user2Result = "";
    private String curpResult = "";
    private String emailResult = "";
    private String telResult = "";
    private String celResult = "";
    private String generoResult = "";
    private String giroResult = "";
    private String tagsResult = "";
    private String tipoResult = "";
    private String tarjetonResult = "";
    private int horarioResult;
    private String descripcionResult = "";
    private String zonaResult = "";
    private String direccionResult = "";
    private String coordenadasResult = "";
    private int metrosResult;
    private String estructuraResult = "";
    private String comentarioResult = "";
    private String estadoResult = "";
    private int vigenciaResult;
    private String imgResult = "";
    private String pngResult = "";
    private String jpegResult = "";
    private String pdfResult = "";


    private String fecha;
    private String desde;
    private String hasta;



    public ComercianteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            idResult = getArguments().getInt("id",0);
            userResult = getArguments().getString("user","");
            user2Result = getArguments().getString("nombre_segundo","");
            curpResult = getArguments().getString("curp","");
            emailResult = getArguments().getString("email","");
            telResult = getArguments().getString("tel","");
            celResult = getArguments().getString("cel","");
            generoResult = getArguments().getString("genero","");
            giroResult = getArguments().getString("giro","");
            tagsResult = getArguments().getString("tags","");
            tipoResult = getArguments().getString("tipo","");
            tarjetonResult = getArguments().getString("tarjetonsindicato","");
            horarioResult = getArguments().getInt("horario",0);
            descripcionResult = getArguments().getString("descripcion","");
            zonaResult = getArguments().getString("zona","");
            direccionResult = getArguments().getString("direccion","");
            coordenadasResult = getArguments().getString("coordenadas","");
            metrosResult = getArguments().getInt("metros",0);
            estructuraResult = getArguments().getString("estructura","");
            comentarioResult = getArguments().getString("comentario","");
            estadoResult = getArguments().getString("estado","");
            vigenciaResult = getArguments().getInt("vigencia",0);
            imgResult = getArguments().getString("img","");
            pngResult = getArguments().getString("png","");
            jpegResult = getArguments().getString("jpeg","");
            pdfResult = getArguments().getString("pdf","");

            //Toast.makeText(getContext(),coordenadasResult,Toast.LENGTH_SHORT).show();


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_comerciante, container, false);
        showToolbar("",true, view);

        CircleImageView imageViewFotoComerciante = (CircleImageView) view.findViewById(R.id.imageViewFotoComerciante);
        Picasso.get().load("http://yobusco.org/uploads/"+vigenciaResult+"/foto_dueno/"+imgResult+"").into(imageViewFotoComerciante);

        ImageView imagenestructura = (ImageView) view.findViewById(R.id.imagen_estructura);
        Picasso.get().load("http://yobusco.org/uploads/"+vigenciaResult+"/estructura/"+imgResult+"").into(imagenestructura);



        TextView textViewuserNameComerciante = (TextView) view.findViewById(R.id.userNameComerciante);
        textViewuserNameComerciante.setText(userResult);

        EditText giro_comerciante = (EditText) view.findViewById(R.id.giro_comerciante);
        EditText descripcion_comerciante = (EditText) view.findViewById(R.id.descripcion_comerciante);
        EditText permiso_comerciante = (EditText) view.findViewById(R.id.permiso_comerciante);
        EditText direccion_comerciante = (EditText) view.findViewById(R.id.direccion_comerciante);
        EditText zona_comerciante = (EditText) view.findViewById(R.id.zona_comerciante);
        TextView estructura_comerciante = (TextView) view.findViewById(R.id.estructura_comerciante);


        giro_comerciante.setText(giroResult);
        descripcion_comerciante.setText(descripcionResult);
        permiso_comerciante.setText(tipoResult);
        direccion_comerciante.setText(direccionResult);
        zona_comerciante.setText(zonaResult);
        estructura_comerciante.setText(estructuraResult);




        final ArrayList<Horas> Reports2 = new ArrayList<>();
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        String idC = String.valueOf(idResult);
        String URL = "http://yobusco.org/android/horario.php?id="+idC;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,


                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray jsonArray = response.getJSONArray("horario");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);

                                fecha=object.getString("fecha");
                                desde=object.getString("desde");
                                hasta=object.getString("hasta");

                                Reports2.add(new Horas(fecha,desde,hasta));
                            }

                            RecyclerView reportRecycler = (RecyclerView) view.findViewById(R.id.recycle_horas);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                            reportRecycler.setLayoutManager(linearLayoutManager);

                            HorasAdapter horasAdapter= new HorasAdapter(Reports2,R.layout.item_horas,getActivity());

                            reportRecycler.setAdapter(horasAdapter);


                        }catch (JSONException e){
                            e.printStackTrace();
                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });



        mQueue.add(request);








        return view;
    }

    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }



}
