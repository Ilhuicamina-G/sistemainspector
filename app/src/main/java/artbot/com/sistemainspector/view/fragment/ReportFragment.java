package artbot.com.sistemainspector.view.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import artbot.com.sistemainspector.HomeActivity;
import artbot.com.sistemainspector.LoginActivity;
import artbot.com.sistemainspector.R;
import artbot.com.sistemainspector.ReferenciaActivity;
import artbot.com.sistemainspector.adapter.ReportAdapterRecyclerView;
import artbot.com.sistemainspector.model.Report;
import artbot.com.sistemainspector.model.ReportRequest;
import artbot.com.sistemainspector.view.PopReport;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment{

    private static final int MY_REQUEST_CODE = 0;
    private Toolbar toolbar;
    private View view;
    private  Bundle sse;
    private int idResult;


    private String name;
    private String reporte;
    private String comentario;
    private String date;

    private  String NameUser;
    private int idUser;
    //RequestQueue mQueue = Volley.newRequestQueue(getContext());



    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sse = savedInstanceState;

        if (getArguments() != null){
            idResult = getArguments().getInt("id",0);
            idUser = getArguments().getInt("idUser",0);
            NameUser = getArguments().getString("nameUser","");


        }



        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_report, container, false);
        showToolbar("Reportes",false,view);

        buidreports();
        //Toast.makeText(getContext(),NameUser,Toast.LENGTH_SHORT).show();

        // Inflate the layout for this fragment
        return view;
    }




    public void buidreports(){
        final ArrayList<Report> Reports = new ArrayList<>();
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        String idC = String.valueOf(idResult);
        String URL = "http://www.carmen.gob.mx/sistema-ambulante/estilos/android/report.php?id="+idC;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray jsonArray = response.getJSONArray("comentarios");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);

                                name = object.getString("nombre_master");
                                reporte = object.getString("reporte");
                                comentario = object.getString("comentario");
                                date = object.getString("fecha");

                                String[] parts = date.split(" ");
                                String part1 = parts[0];
                                String part2 = parts[1];

                                String[] fecha = part1.split("-");
                                String anio = fecha[0].substring(2,4);
                                String mes = fecha[1];
                                String dia = fecha[2];

                                String fecha_result = dia+"/"+mes+"/"+anio;

                                Reports.add(new Report(name,comentario,reporte,fecha_result));


                            }

                            RecyclerView reportRecycler = (RecyclerView) view.findViewById(R.id.reportRecycler);

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                            reportRecycler.setLayoutManager(linearLayoutManager);

                            ReportAdapterRecyclerView reportAdapterRecyclerView = new ReportAdapterRecyclerView(Reports,R.layout.cardview_reporte,getActivity());

                            reportRecycler.setAdapter(reportAdapterRecyclerView);


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

        return;
    }





    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_reporte,menu);
    }



    public void showToolbar(String tittle, boolean upButton, View view){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_report);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(tittle);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.reportItem){

            //startActivity(new Intent(getActivity(), PopReport.class));

            if (sse == null) {
                crearFullScreenDialog();
            }
        }


        return  super.onOptionsItemSelected(item);
    }

    private void crearFullScreenDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        PopScreenDialog newFragment = new PopScreenDialog();

        Bundle bundle = new Bundle();
        bundle.putInt("id", idResult);
        bundle.putInt("idUser", idUser);
        bundle.putString("nameUser", NameUser);

        newFragment.setArguments(bundle);

        newFragment.setTargetFragment(this,MY_REQUEST_CODE);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment,"FullScreenFragment")
                .commit();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_OK) { return; }

        String qwe;
        qwe = data.getDataString();
        //Toast.makeText(getContext(),qwe,Toast.LENGTH_SHORT).show();


        super.onActivityResult(requestCode, resultCode, data);

    }
}
