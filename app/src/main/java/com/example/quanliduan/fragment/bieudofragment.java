package com.example.quanliduan.fragment;

import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import com.example.quanliduan.R;
import com.example.quanliduan.model.SharedViewModel;
import com.example.quanliduan.model.chiTieu;

public class bieudofragment extends Fragment {

    private PieChart pieChart;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bieudo, container, false);
        pieChart = view.findViewById(R.id.pieChartExpenses);

        // Nhận dữ liệu từ SharedViewModel
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getExpenses().observe(getViewLifecycleOwner(), this::updatePieChart);

        return view;
    }

    private void updatePieChart(ArrayList<chiTieu> data) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (chiTieu item : data) {
            entries.add(new PieEntry((float) item.getPrice(), item.getName()));
        }

        PieDataSet dataSet = new PieDataSet(entries,"");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(12f);

        pieChart.setData(pieData);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12f);
        pieChart.getDescription().setEnabled(false);
        pieChart.animateY(1000);
    }

}