package com.example.pi.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pi.R;
import com.example.pi.manipulacao_api.Api_manipulation_authentication;
import com.example.pi.manipulacao_api.Api_manipulation_register;
import com.example.pi.model.Cliente;

import java.util.ArrayList;
import java.util.List;

import customfonts.MyRegulerText;

public class FragmentLogin extends Fragment {

    View view;
    EditText  email, senha;
    MyRegulerText btn_logar;
    String Token;


    public FragmentLogin() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.login_fragment,container,false);

        email =(EditText) view.findViewById(R.id.email);
        senha =(EditText) view.findViewById(R.id.pswd);

        btn_logar = (MyRegulerText) view.findViewById(R.id.logar);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cliente cli =new Cliente();

                cli.setEmail(email.getText().toString());
                cli.setSenha(senha.getText().toString());

                Api_manipulation_authentication api = new Api_manipulation_authentication();

                api.autenticarCliente(getContext(),getActivity(),view,cli);
            }
        });




    }

}
