package com.app.doctorsdoor.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.doctorsdoor.R;
import com.app.doctorsdoor.model.DoctorProfile;

public class DoctorRegistrationActivity extends AppCompatActivity {
    private EditText edtDegree, edtExp, edtRgNo, edtNewFee,
            edtOldFee, edtConsultant, edtBankAccountNo, edtIFSC;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);
        initView();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtDegree.getText().toString().length() > 0
                        && edtExp.getText().toString().length() > 0
                        && edtRgNo.getText().toString().length() > 0
                        && edtNewFee.getText().toString().length() > 0
                        && edtOldFee.getText().toString().length() > 0
                        && edtConsultant.getText().toString().length() > 0
                        && edtBankAccountNo.getText().toString().length() > 0
                        && edtIFSC.getText().toString().length() > 0
                ) {
                    DoctorProfile doctorProfile = new DoctorProfile();
                    doctorProfile.setDegree(edtDegree.getText().toString());
                    doctorProfile.setExperience(edtExp.getText().toString());
                    doctorProfile.setRegistration_no(edtRgNo.getText().toString());
                    doctorProfile.setNew_case_fee(Long.parseLong(edtNewFee.getText().toString()));
                    doctorProfile.setOld_case_fee(Long.parseLong(edtOldFee.getText().toString()));
                    doctorProfile.setConsultant(edtConsultant.getText().toString());
                    doctorProfile.setBank_account_no(Long.parseLong(edtBankAccountNo.getText().toString()));
                    doctorProfile.setIfsc_code(edtIFSC.getText().toString());
                }
            }
        });
    }

    private void initView() {
        edtDegree = findViewById(R.id.edt_degree);
        edtExp = findViewById(R.id.edt_experience);
        edtRgNo = findViewById(R.id.edt_registration_no);
        edtNewFee = findViewById(R.id.edt_new_fee);
        edtOldFee = findViewById(R.id.edt_old_fee);
        edtConsultant = findViewById(R.id.edt_consultant);
        edtBankAccountNo = findViewById(R.id.edt_bank_no);
        edtIFSC = findViewById(R.id.edt_ifsc_code);
        btnSubmit = findViewById(R.id.btn_submit);
    }
}
