package com.android.square_employee_directory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.square_employee_directory.R;
import com.android.square_employee_directory.model.EmployeeModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.MyViewHolder> {

    private Context context;
    private List<EmployeeModel> employeeList;
    private RecyclerViewClickListener listener;

    public EmployeeListAdapter(Context  context, List<EmployeeModel> employeeList, RecyclerViewClickListener recyclerViewClickListener){
        this.context = context;
        this.employeeList = employeeList;
        this.listener = recyclerViewClickListener;
    }

    public void setEmployeeList(List<EmployeeModel> employeeList){
        this.employeeList = employeeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployeeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeListAdapter.MyViewHolder holder, int position) {
        EmployeeModel employeeModel = this.employeeList.get(position);
        if (employeeModel.getFull_name() != null)
            holder.employeeName.setText(employeeModel.getFull_name());
        else
            holder.employeeName.setText(R.string.noDataFound);

        if (employeeModel.getTeam() != null)
            holder.employeeTeam.setText(employeeModel.getTeam());
        else
            holder.employeeTeam.setText(R.string.noDataFound);

        Glide.with(context)
                .load(this.employeeList.get(position).getPhoto_url_small())
                .placeholder(R.drawable.placeholder)
                .apply(RequestOptions.centerInsideTransform()).into(holder.employeeImage);
    }

    @Override
    public int getItemCount() {
        if (this.employeeList != null)
            return this.employeeList.size();
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView employeeName, employeeTeam;
        ImageView employeeImage;

        public MyViewHolder(View itemView){
            super(itemView);

            employeeImage  = itemView.findViewById(R.id.employeeImage);
            employeeName = itemView.findViewById(R.id.employeeName);
            employeeTeam = itemView.findViewById(R.id.employeeTeam);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View view, int position);
    }
}
