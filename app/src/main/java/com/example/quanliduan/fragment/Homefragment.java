package com.example.quanliduan.fragment;



import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.quanliduan.R;
import com.example.quanliduan.adapter.chiTieuAdapter;
import com.example.quanliduan.dao.chiTieuDAO;
//import com.example.quanliduan.model.SharedViewModel;
import com.example.quanliduan.model.chiTieu;

public class Homefragment extends Fragment {
    RecyclerView recyclerView;
    TextView tongTienTextView ,dateText;
    ArrayList<chiTieu> list;
    chiTieuDAO chiTieuDAO;

  //  SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycler_viewChitieu);
        tongTienTextView = view.findViewById(R.id.textchitieu);
        dateText = view.findViewById(R.id.date_text);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        dateText.setText(currentDate);

        // Khởi tạo ViewModel
       // sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Khởi tạo RecyclerView và hiển thị danh sách chi tiêu
        chiTieuDAO = new chiTieuDAO(getContext());
        loadData();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.floadAdd);
        floatingActionButton.setOnClickListener(v -> showdialogadd());

        return view;
    }

    private void loadData() {
        list = (ArrayList<chiTieu>) chiTieuDAO.getAllItems();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chiTieuAdapter chiTieuAdapter = new chiTieuAdapter(getContext(), list, chiTieuDAO);
        recyclerView.setAdapter(chiTieuAdapter);

        // Tính tổng tiền và hiển thị
        double tongTien = 0;
        for (chiTieu chiTieu : list) {
            tongTien += chiTieu.getPrice();
        }
        tongTienTextView.setText(String.format("Tổng chi tiêu: %.2f VNĐ", tongTien));

        // Cập nhật dữ liệu vào SharedViewModel
    //    sharedViewModel.setExpenses(list);
    }

  //2
  //1

}

