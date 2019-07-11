package com.app.doctorsdoor.activity;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.doctorsdoor.R;
import com.app.doctorsdoor.common.CustomToast;
import com.app.doctorsdoor.model.User;
import com.app.doctorsdoor.rest.SignuUpRequest;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PatientRegisterActivity extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();
    String userType, userName;
    private EditText edtName, edtPhNo, edtEmail, edtAddress, edtPincode, edtCity, edtState,
            edtCountry, edtLastName, edtPwd;
    private TextView tvBirthDate;
    private Spinner spnbloodgrp;
    private RadioGroup rgGender, rgUserType;
    private Toolbar toolbar;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        initUI();

        setSpinnerForBloodGroup();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        if (getIntent() != null) {
            userName = getIntent().getStringExtra("USERNAME");

        }


        if (userName != null && userName.length() > 0) {
            edtPhNo.setText(userName);
        }


        tvBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                new DatePickerDialog(PatientRegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedIdUserType = rgUserType.getCheckedRadioButtonId();
                if (selectedIdUserType == -1) {
                    CustomToast.SingleToastShortContext(getApplicationContext(),
                            getApplicationContext().getResources().getString(R.string.user_not_selected));
                    return;

                } else {
                    if (edtName.getText().toString().length() > 0
                            && edtPhNo.getText().toString().length() > 0 && edtLastName.getText().toString().length() > 0
                            && edtPwd.getText().toString().length() > 0) {

                        User user = new User();
                        user.setUsername(edtPhNo.getText().toString().trim());
                        user.setPassword(edtPwd.getText().toString().trim());
                        user.setFirstname(edtName.getText().toString().trim());
                        user.setLastname(edtLastName.getText().toString().trim());
                        user.setMobile_no(edtPhNo.getText().toString().trim());
                        RadioButton radioButtonUserType
                                = rgUserType
                                .findViewById(selectedIdUserType);
                        user.setUser_type(radioButtonUserType.getText().toString().trim());

                        int selectedId = rgGender.getCheckedRadioButtonId();


                        RadioButton radioButton
                                = rgGender
                                .findViewById(selectedId);
                        if (selectedId != -1 && radioButton != null && radioButton.getText().length() > 0) {
                            user.setGender(radioButton.getText().toString().trim());
                        }

                        if (tvBirthDate != null && tvBirthDate.getText().toString().length() > 0) {
                            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date date = (Date) formatter.parse(tvBirthDate.getText().toString());
                                user.setBirthdate(String.valueOf(date.getTime()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }

                        if (edtEmail.getText().toString().length() > 0) {
                            user.setEmail_id(edtEmail.getText().toString());
                        }

                        if (spnbloodgrp != null && spnbloodgrp.getSelectedItem() != null
                                && spnbloodgrp.getSelectedItem().toString().length() > 0) {
                            user.setBloodgroup(spnbloodgrp.getSelectedItem().toString());
                        }

                        if (edtCity.getText().toString().length() > 0) {
                            user.setAdd_city(edtCity.getText().toString());
                        }

                        if (edtAddress.getText().toString().length() > 0) {
                            user.setAdd_street(edtAddress.getText().toString());
                        }

                        if (edtPincode.getText().toString().length() > 0 && !edtPincode.getText().toString().equalsIgnoreCase("0")) {
                            user.setAdd_pincode(Integer.parseInt(edtPincode.getText().toString().trim()));
                        }

                        if (edtState.getText().toString().length() > 0) {
                            user.setAdd_state(edtState.getText().toString());
                        }

                        if (edtCountry.getText().toString().length() > 0) {
                            user.setAdd_country(edtCountry.getText().toString());
                        }
                        String androidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                        user.setDevice_id(androidID);
                        Gson gson = new Gson();
                        String json = gson.toJson(user);
                        Log.i("TestJson", json);
                        SignuUpRequest signuprequest = new SignuUpRequest();
                        signuprequest.signup(json, PatientRegisterActivity.this);
                    }
                }
            }
        });
    }

    private void setSpinnerForBloodGroup() {
        List<String> bloodgrpcategories = new ArrayList<String>();
        bloodgrpcategories.add("");
        bloodgrpcategories.add("A+");
        bloodgrpcategories.add("A-");
        bloodgrpcategories.add("B+");
        bloodgrpcategories.add("B-");
        bloodgrpcategories.add("O+");
        bloodgrpcategories.add("O-");
        bloodgrpcategories.add("AB+");
        bloodgrpcategories.add("AB-");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloodgrpcategories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);

        // attaching data adapter to spinner
        spnbloodgrp.setAdapter(dataAdapter);
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tvBirthDate.setText(sdf.format(myCalendar.getTime()));
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
        toolbar = findViewById(R.id.toolbar_patient_register);
        edtCountry = findViewById(R.id.edt_register_patient_country);
        edtLastName = findViewById(R.id.edt_register_patient_lname);
        edtPwd = findViewById(R.id.edt_register_patient_pwd);
        rgUserType = findViewById(R.id.rg_usertype);
        setSupportActionBar(toolbar);
    }


}
