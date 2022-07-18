package com.android.square_employee_directory;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.square_employee_directory.model.EmployeeModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class EmployeeActivity extends AppCompatActivity {

    ImageView employeePhoto;
    TextView txtName, txtEmail, txtPhone, txtBio, txtTeam, txtType;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_employee);

        employeePhoto = findViewById(R.id.imageView);
        txtName = findViewById(R.id.name);
        txtEmail = findViewById(R.id.email);
        txtPhone = findViewById(R.id.phoneNumber);
        txtBio = findViewById(R.id.biography);
        txtTeam = findViewById(R.id.team);
        txtType = findViewById(R.id.type);

        EmployeeModel employeeModel = (EmployeeModel) getIntent().getSerializableExtra("emp");


        Glide.with(getApplicationContext())
                .load(employeeModel.getPhoto_url_large())
                .placeholder(R.drawable.placeholder)
                .apply(RequestOptions.centerInsideTransform()).into(employeePhoto);

        setValue("Name: ",employeeModel.getFull_name(), txtName);
        setValue("Email: ",employeeModel.getEmail_address(), txtEmail);
        setValue("Phone: ",employeeModel.getPhone_number(), txtPhone);
        setValue("Biography: \n",employeeModel.getBiography(), txtBio);
        setValue("Team: ",employeeModel.getTeam(), txtTeam);
        setValue("Type: ",employeeModel.getEmployee_type(), txtType);

    }

    private void setValue(String title, String value, TextView textView){
        if (value != null)
            textView.setText(title + value);
        else
            textView.setText(title + getApplicationContext().getString(R.string.noDataFound));
    }
}
