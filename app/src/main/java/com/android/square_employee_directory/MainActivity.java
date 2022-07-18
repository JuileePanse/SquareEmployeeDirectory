package com.android.square_employee_directory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.square_employee_directory.adapter.EmployeeListAdapter;
import com.android.square_employee_directory.model.EmployeeModel;
import com.android.square_employee_directory.network.NetworkConnection;
import com.android.square_employee_directory.viewmodel.EmployeeListViewModel;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EmployeeListAdapter adapter;
    private List<EmployeeModel> employeeList;
    private TextView noResult;
    private EmployeeListAdapter.RecyclerViewClickListener listener;
    private SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        noResult = findViewById(R.id.noResultTV);

        //Set on click Listener
        setOnClickListener();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new EmployeeListAdapter(this, employeeList, listener);
        recyclerView.setAdapter(adapter);


        //Divider
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        checkConnCallApi();

        //Refresh list on refresh
        swipeRefresh();

    }

    //Recycler Item click
    private void setOnClickListener() {
        listener = (view, position) -> {
            Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
            intent.putExtra("emp", (Serializable) employeeList.get(position));
            startActivity(intent);
        };
    }

    private void apiCall(){
        noResult.setVisibility(View.GONE);
        EmployeeListViewModel viewModel = ViewModelProviders.of(this).get(EmployeeListViewModel.class);
        viewModel.getEmployeeListObserver().observe(this, employeeModels -> {
            if (employeeModels.size() > 0)
            {
                employeeList = employeeModels;
                Collections.sort(employeeList, EmployeeModel.EmpNameComp);
                adapter.setEmployeeList(employeeModels);
            }else {
                noResult.setVisibility(View.VISIBLE);
            }
        });

        viewModel.apiCall();
    }

    private void swipeRefresh(){
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkConnCallApi();
                swipeContainer.setRefreshing(false);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void checkConnCallApi(){
        //Check internet connection and make an API call
        if (NetworkConnection.isNetworkAvailable(getApplicationContext()))
            apiCall();
        else {
            noResult.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), R.string.checkInternetConn, Toast.LENGTH_SHORT).show();
        }
    }
}