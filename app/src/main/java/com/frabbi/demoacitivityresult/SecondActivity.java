package com.frabbi.demoacitivityresult;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.frabbi.demoacitivityresult.databinding.ActivityMainBinding;
import com.frabbi.demoacitivityresult.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    private String name,email,city,profession,phone,gender;
    private Uri imageLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            name = bundle.getString("name");
            email = bundle.getString("email");
            city = bundle.getString("city");
            profession = bundle.getString("profession");
            phone = bundle.getString("phone");
            imageLink = bundle.getParcelable("img");
            gender = bundle.getString("gender");

        }

        binding.nameId.setText(name);
        binding.emailId.setText(email);
        binding.cityId.setText(city);
        binding.professionId.setText(profession);
        binding.phoneId.setText(phone);
        binding.saveImgProfileId.setImageURI(imageLink);
        binding.genderId.setText(gender);

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });

    }

    private void dialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(SecondActivity.this);
        builder.setMessage("Your data has been send!");
    //    builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}