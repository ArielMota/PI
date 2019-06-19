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
import android.widget.TextView;

import com.example.pi.R;
import com.example.pi.manipulacao_api.Api_manipulation_register;
import com.example.pi.model.Cliente;

import customfonts.MyRegulerText;

public class FragmentCadastrar extends Fragment {

    View view;
    EditText nome, email, senha;
    Button btn_cadastrar;

    public FragmentCadastrar() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.cadastrar_fragment,container,false);

        nome =(EditText) view.findViewById(R.id.nome);
        email =(EditText) view.findViewById(R.id.email);
        senha =(EditText) view.findViewById(R.id.pswd);

        btn_cadastrar = (Button) view.findViewById(R.id.cadastrar);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cliente cli =new Cliente();

                cli.setNome(nome.getText().toString());
                cli.setEmail(email.getText().toString());
                cli.setSenha(senha.getText().toString());

                Api_manipulation_register api = new Api_manipulation_register();

                api.registrarCliente(getContext(),getActivity(),view,cli);
            }
        });




    }


}
