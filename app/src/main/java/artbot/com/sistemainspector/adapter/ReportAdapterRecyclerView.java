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
import artbot.com.sistemainspector.model.Report;

public class ReportAdapterRecyclerView extends RecyclerView.Adapter<ReportAdapterRecyclerView.ReportViewHolder>{



    private ArrayList<Report> reports;
    private int resource;
    private Activity activity;

    public ReportAdapterRecyclerView(ArrayList<Report> reports, int resource, Activity activity) {
        this.reports = reports;
        this.resource = resource;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup,false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder reportViewHolder, int i) {
        Report report = reports.get(i);
        reportViewHolder.typeReportCard.setText(report.getReport());
        reportViewHolder.nameEditorCard.setText(report.getName());
        reportViewHolder.dateCard.setText(report.getDate());
        reportViewHolder.commentCard.setText(report.getComment());

    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder{

        private TextView typeReportCard;
        private TextView nameEditorCard;
        private TextView commentCard;
        private TextView dateCard;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);

            typeReportCard = (TextView) itemView.findViewById(R.id.typeReportText);
            nameEditorCard = (TextView) itemView.findViewById(R.id.nameEditorText);
            commentCard = (TextView) itemView.findViewById(R.id.commentReportText);
            dateCard = (TextView) itemView.findViewById(R.id.dateText);

        }
    }

}
