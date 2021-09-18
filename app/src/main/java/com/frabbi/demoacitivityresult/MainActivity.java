package com.frabbi.demoacitivityresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.frabbi.demoacitivityresult.databinding.ActivityMainBinding;
import com.github.drjacky.imagepicker.ImagePicker;


public class MainActivity extends AppCompatActivity {
private ActivityMainBinding binding;
private Uri imgLink;
private String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"working",Toast.LENGTH_LONG).show();
                ImagePicker.Companion.with(MainActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .cropOval()	    		//Allow dimmed layer to have a circle inside
                        .compress(1024)
                        .maxResultSize(1080,1080)
                        .start();         //We have to define what image provider we want to use
                      	//Final image resolution will be less than 1080 x 1080(Optional)


            }
        });

        binding.radioGroupId.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == binding.maleRadioBtnId.getId()) {
                    gender = binding.maleRadioBtnId.getText().toString();
                }
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                    intent.putExtra("name",binding.textInputNameId.getText().toString());
                    intent.putExtra("email",binding.textInputEmailId.getText().toString());
                    intent.putExtra("city",binding.textInputCityId.getText().toString());
                    intent.putExtra("profession",binding.textInputProfessionId.getText().toString());
                    intent.putExtra("phone",binding.textInputPhoneNoId.getText().toString());



                    if(imgLink != null){
                        intent.putExtra("img",imgLink);
                    }
                    if(gender != null){
                        intent.putExtra("gender",gender);
                    }
                    startActivity(intent);

            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri uri = data.getData();
            binding.imgProfileId.setImageURI(uri);
            imgLink = data.getData();
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}