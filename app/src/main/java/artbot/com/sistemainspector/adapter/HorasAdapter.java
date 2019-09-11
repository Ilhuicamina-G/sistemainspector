package artbot.com.sistemainspector.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import artbot.com.sistemainspector.R;
import artbot.com.sistemainspector.model.Horas;
import artbot.com.sistemainspector.model.Report;

public class HorasAdapter extends RecyclerView.Adapter<HorasAdapter.HorasViewHolder>{

    private ArrayList<Horas> reports2;
    private int resource2;
    private Activity activity;

    public HorasAdapter(ArrayList<Horas>reports2,int resource2,Activity activity){
        this.reports2 = reports2;
        this.resource2 = resource2;
        this.activity = activity;

    }

    @NonNull
    @Override
    public HorasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource2, viewGroup,false);
        return new HorasViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HorasViewHolder horasViewHolder, int i) {
        Horas horas = reports2.get(i);
        horasViewHolder.txtfecha.setText(horas.getFecha());
        horasViewHolder.txtdesde.setText(horas.getDesde());
        horasViewHolder.txthasta.setText(horas.getHasta());

    }

    @Override
    public int getItemCount() {
        return reports2.size();
    }
    public class HorasViewHolder extends RecyclerView.ViewHolder {

        private TextView txtfecha;
        private TextView txtdesde;
        private TextView txthasta;

        public HorasViewHolder(@NonNull View itemView) {
            super(itemView);

            txtfecha = (TextView) itemView.findViewById(R.id.text_fecha);
            txtdesde = (TextView) itemView.findViewById(R.id.text_desde);
            txthasta = (TextView) itemView.findViewById(R.id.text_hasta);

        }
    }



}
