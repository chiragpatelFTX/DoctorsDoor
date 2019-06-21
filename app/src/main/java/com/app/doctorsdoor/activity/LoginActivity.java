package com.app.doctorsdoor.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.doctorsdoor.R;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    // UI references.
    private EditText edtPhno;
    private EditText mPasswordView;
    private Button mSignInButton;
    private RadioGroup rgUserType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initViews();

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


    }

    private void initViews() {
        // Set up the login form.
        edtPhno =  findViewById(R.id.edt_phno);
        // populateAutoComplete();

        mPasswordView =  findViewById(R.id.password);
        mSignInButton = findViewById(R.id.email_sign_in_button);
        rgUserType = findViewById(R.id.rg_usertype);
        // Uncheck or reset the radio buttons initially
        rgUserType.clearCheck();


    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid contact number, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // When submit button is clicked,
        // Ge the Radio Button which is set
        // If no Radio Button is set, -1 will be returned
        int selectedId = rgUserType.getCheckedRadioButtonId();
        if (selectedId == -1) {
           /* Toast.makeText(LoginActivity.this,
                    "No answer has been selected",
                    Toast.LENGTH_SHORT)
                    .show();*/
        }
        else {

            RadioButton radioButton
                    = (RadioButton)rgUserType
                    .findViewById(selectedId);

            // Now display the value of selected item
            // by the Toast message
            /*Toast.makeText(LoginActivity.this,
                    radioButton.getText(),
                    Toast.LENGTH_SHORT)
                    .show();*/
        }


    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }




}

