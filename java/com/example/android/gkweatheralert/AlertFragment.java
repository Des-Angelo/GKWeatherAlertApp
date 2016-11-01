package com.example.android.gkweatheralert;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import Adapter.RecyclerAdapter;
import Model.Database.DBHandler;
import Model.Database.DBHelper;
import Model.Employee;
import Model.Weather;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlertFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerAdapter adapter;
    private DBHelper dbHelper;
    private String forecastData;
    private DBHandler myDb;
    private boolean isWithinHour;

    private ArrayList<Weather> weather;

    public AlertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_alert, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);


        dbHelper = new DBHelper(this.getContext());

        // Adding Employee
        dbHelper.addEmployee(new Employee("Des-Angelo Pennant", "Mona", "Kingston", 5009000, "IT", "penairdes@gmail.com"));
        dbHelper.addEmployee(new Employee("Romario", "1st Street", "Montego Bay", 4302030, "Admin,", "penairdes@hotmail.com"));

        ArrayList<Employee> employees = dbHelper.getAllEmployee();
        adapter = new RecyclerAdapter(employees);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return v;
    }
}
