package com.com.greenacademy.englishlearning.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.com.greenacademy.englishlearning.Activity.LoginActivity;
import com.greenacademy.englishlearning.R;

public class SignUpFragment extends Fragment {
    EditText edtUsername,edtEmail,edtFullname,edtPassword;
    Button btnCreate,btnTerm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup,container,false);
    edtUsername = view.findViewById(R.id.txt_user_signup);
    edtEmail = view.findViewById(R.id.txt_mail_signup);
    edtFullname = view.findViewById(R.id.txt_fullname_signup);
    edtPassword = view.findViewById(R.id.txt_password_signup);
    btnCreate = view.findViewById(R.id.btn_create_account_signup);
    btnTerm = view.findViewById(R.id.btn_term_signup);

    btnCreate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(checkInfo()){
                Toast.makeText(getActivity(),"Create Account successfully !!!",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        }
    });
        return view;
    }
    public boolean checkInfo(){
        String username = edtUsername.getText().toString();
        while(!checkUser(username)){
            return false;
        }
        String email = edtEmail.getText().toString();
        while(!checkEmail(email)){
            return false;
        }
        String fullname = edtFullname.getText().toString();
        while(!checkFullname(fullname)){
            return false;
        }
        String pass = edtPassword.getText().toString();
        while(!checkPass(pass)){
            return false;
        }
        return true;
    }
    private boolean checkUser(String username){
        username = username.trim();
        if(username.isEmpty()){
            Toast.makeText(getActivity(),"Required Username input !!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(username.length() > 30 || username.length() < 5){
            Toast.makeText(getActivity(),"Username input must have length from 5 to 30 !!!",Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }
    private boolean checkFullname(String fullname){
        fullname = fullname.trim();
        if(fullname.isEmpty()){
            Toast.makeText(getActivity(),"Required Fullname input !!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(fullname.length() > 30 || fullname.length() < 5){
            Toast.makeText(getActivity(),"Fullname input must have length from 5 to 30 !!!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private boolean checkPass(String password){
        password = password.trim();
        if(password.isEmpty()){
            Toast.makeText(getActivity(),"Required Password input !!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.length() > 30 || password.length() < 5){
            Toast.makeText(getActivity(),"Password input must have length from 5 to 30 !!!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private boolean checkEmail(String email){
        email = email.trim();
        if(email.isEmpty()){
            Toast.makeText(getActivity(),"Required Email input !!!",Toast.LENGTH_LONG).show();
            return false;
        }
        if(email.length() > 30 || email.length() < 5){
            Toast.makeText(getActivity(),"Email input must have length from 5 to 30 !!!",Toast.LENGTH_LONG).show();
            return false;
        }
        String regex = "^\\w{1,}[@]\\w{1,}([.]\\w{1,}){1,}";
        if(!email.matches(regex)){
            Toast.makeText(getActivity(),"Email wrong format. Email example : (abc@abc.com) !!!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
