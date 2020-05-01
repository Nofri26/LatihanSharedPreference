package com.squidward.latihansharedpreference.activity;

/* Tanggal Pengerjaan : 30 April - 1 Mei 2020
    Deksripsi         : Buat Login
    Nama              : Nofrizal
    NIM               : 10117139
    Kelas             : IF-4
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.squidward.latihansharedpreference.R;
import com.squidward.latihansharedpreference.utils.Preference;
import com.squidward.latihansharedpreference.model.UserModel;

public class LoginActivity extends AppCompatActivity {

    private TextView txtMasuk;
    private TextView txtRegister;
    private EditText edtUsername;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        declareView();

        txtMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiLogin();
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preference.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), HomeActicity.class));
            finish();
        }
    }


    private void declareView() {

        txtRegister = findViewById(R.id.txt_login_register);
        txtMasuk = findViewById(R.id.txt_login_masuk);
        edtUsername = findViewById(R.id.edt_login_username);
        edtPassword = findViewById(R.id.edt_login_password);

    }

    private void validasiLogin() {

        edtUsername.setError(null);
        edtPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        String userName = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if (TextUtils.isEmpty(userName)) {
            edtUsername.setError("Harus diisi");
            fokus = edtUsername;
            cancel = true;
        } else if (!cekUser(userName)) {
            edtUsername.setError("Username Tidak Ditemukan");
            fokus = edtUsername;
            cancel = true;
        }


        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Harus Diisi");
            fokus = edtPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            edtPassword.setError("Data yang dimasukkan tidak sesuai");
            fokus = edtPassword;
            cancel = true;
        }


        if (cancel) {
            fokus.requestFocus();
        } else {

            UserModel userModel = new UserModel();
            userModel.setUsername(userName);
            userModel.setPassword(password);

            Preference.setUserPreferences(getBaseContext(), userModel);
            Preference.setLoggedInStatus(getBaseContext(), true);

            startActivity(new Intent(getBaseContext(), HomeActicity.class));
            finish();
        }

    }

    private boolean cekUser(String user) {
        return user.equals(Preference.getRegisteredUser(getBaseContext()));
    }

    private boolean cekPassword(String password) {
        return password.equals(Preference.getRegisteredPassword(getBaseContext()));
    }
}
