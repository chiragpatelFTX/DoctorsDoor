package com.app.doctorsdoor.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.doctorsdoor.R;
import com.app.doctorsdoor.common.CustomToast;
import com.app.doctorsdoor.model.User;
import com.app.doctorsdoor.rest.LoginRequest;
import com.app.doctorsdoor.storage.Constants;
import com.app.doctorsdoor.storage.LocalStorage;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText edtPhno;
    private EditText mPasswordView;
    private Button mSignInButton;
    private RadioGroup rgUserType;
    private TextView txtSignUP;
    private Context context = LoginActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initViews();

     /*   if (LocalStorage.read(Constants.storage.USER_ID, null) != null) {
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        }*/
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

        txtSignUP.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edtPhno != null && mPasswordView != null) {

                    Intent intent = new Intent(context, PatientRegisterActivity.class);
                    intent.putExtra("USERNAME", edtPhno.getText().toString().trim());
                    startActivity(intent);

                } else {
                    CustomToast.SingleToastShortContext(context, "Enter Empty Details");
                }


            }
        });


    }

    private void initViews() {
        // Set up the login form.
        edtPhno = findViewById(R.id.edt_phno);
        // populateAutoComplete();

        mPasswordView = findViewById(R.id.password);
        mSignInButton = findViewById(R.id.email_sign_in_button);
        rgUserType = findViewById(R.id.rg_usertype);
        txtSignUP = findViewById(R.id.txt_signup);
        // Uncheck or reset the radio buttons initially
        rgUserType.clearCheck();

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid contact number, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {


        if (isPhnoValid(edtPhno.getText().toString()) &&
                        isPasswordValid(mPasswordView.getText().toString().trim())) {
            //make login request
            User user = new User();
            user.setUsername(edtPhno.getText().toString().trim());
            user.setPassword(mPasswordView.getText().toString().trim());

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", edtPhno.getText().toString().trim());
                jsonObject.put("password", mPasswordView.getText().toString().trim());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.login(jsonObject.toString(), context, "login");
        }


    }

    private boolean isPhnoValid(String phno) {

        if (phno != null && phno.length() < 10) {
            edtPhno.setError(getApplicationContext().getResources().getString(R.string.error_invalid_email));
            return false;
        }
        return true;
    }

    private boolean isPasswordValid(String password) {

        return password.length() > 2;
    }


}

