package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import androidx.fragment.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.model.Login;
import com.example.myapplication.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileLogin extends Fragment  implements View.OnClickListener {


    EditText Edreg_username;
    EditText Edreg_password;
    EditText Edreg_email;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.profile_login, container, false);


        Button logBtn = (Button) rootView.findViewById(R.id.go);
        Button to_reg_Btn = (Button) rootView.findViewById(R.id.createAccount);


        Edreg_username = (EditText) rootView.findViewById(R.id.nickname);
        Edreg_password = (EditText) rootView.findViewById(R.id.password);
        Edreg_email = (EditText) rootView.findViewById(R.id.email);




        logBtn.setOnClickListener(this);
        to_reg_Btn.setOnClickListener(this);

        return rootView;


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go:
                LogButtonClick();
                break;
            case R.id.createAccount:
                RegButtonClick();
                break;
        }
    }



    public void replaceFragment(Fragment someFragment) {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    public void RegButtonClick()
    {

        Fragment fragment = null;
        fragment = new ProfileRegister();
        replaceFragment(fragment);

    }


    public void LogButtonClick()
    {

        if (!IsEmptyEditTextLogin()){

            if ( InternetUtil.isInternetOnline(getActivity()) ){
                login();
            }
        }
    }


    private void login(){


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(PostApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        PostApi postApi = retrofit.create(PostApi.class);




        String add1      =  Edreg_email.getText().toString();
        String add2      =  Edreg_password.getText().toString();

        Login login = new Login(add1, add2);

        Call<User> call = postApi.login(login);
        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){


                    if (response.body() != null) {

                        String token = response.body().getToken();


                        SharedPreferences preferences = getActivity().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefLoginEdit = preferences.edit();
                        prefLoginEdit.putBoolean("loggedin", true);
                        prefLoginEdit.putString("token", token);
                        prefLoginEdit.commit();




                        Fragment fragment = null;
                        fragment = new MainMenu();
                        replaceFragment(fragment);

                    }

                }else {
                    Toast.makeText(getContext(), "login no correct :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "error :(", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private Boolean IsEmptyEditTextLogin(){

        if(Edreg_password.getText().toString().isEmpty() || Edreg_email.getText().toString().isEmpty()){

            Toast toast = Toast.makeText(getActivity(),"Empty EditText", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();


            return true;
        }
        else{
            return false;
        }

    }

}