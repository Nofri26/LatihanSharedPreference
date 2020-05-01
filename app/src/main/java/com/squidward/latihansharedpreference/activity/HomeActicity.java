package com.squidward.latihansharedpreference.activity;

/* Tanggal Pengerjaan : 30 April - 1 Mei 2020
    Deksripsi         : Buat Home
    Nama              : Nofrizal
    NIM               : 10117139
    Kelas             : IF-4
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.squidward.latihansharedpreference.R;
import com.squidward.latihansharedpreference.utils.Preference;

public class HomeActicity extends AppCompatActivity {

    private TextView txtKeluar;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        declareView();
        txtKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Preference.setLogout(getBaseContext());

                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });

    }

    private void declareView() {
        txtKeluar = findViewById(R.id.txt_logout);
        txtName = findViewById(R.id.txtName);

        txtName.setText(Preference.getRegisteredUser(getBaseContext()));
    }
}
