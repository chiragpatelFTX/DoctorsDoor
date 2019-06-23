package com.app.doctorsdoor.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.doctorsdoor.R;

public class PatientRegisterActivity extends AppCompatActivity {
    private EditText edtName, edtPhNo, edtEmail, edtAddress, edtPincode, edtCity, edtState;
    private TextView tvBirthDate;
    private Spinner spnbloodgrp;
    private RadioGroup rgGender;
    private Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        initUI();
    }

    private void initUI() {
        edtName = findViewById(R.id.edt_register_patient_name);
        edtPhNo = findViewById(R.id.edt_register_patient_phone);
        tvBirthDate = findViewById(R.id.edt_register_patient_bday);
        edtEmail = findViewById(R.id.edt_register_patient_email);
        spnbloodgrp = findViewById(R.id.spinner_register_patient_bloodgrp);
        rgGender = findViewById(R.id.radioGroup_register_gender);
        edtAddress = findViewById(R.id.edt_register_patient_address);
        edtPincode = findViewById(R.id.edt_register_patient_pincode);
        edtCity = findViewById(R.id.edt_register_patient_city);
        edtState = findViewById(R.id.edt_register_patient_state);
        btnSignUp = findViewById(R.id.btn_register_patient_submit);
    }
}
