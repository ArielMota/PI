package com.example.pi.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pi.R;
import com.example.pi.manipulacao_api.Api_manipulation_authentication;
import com.example.pi.manipulacao_api.Busca_carrinho;
import com.example.pi.model.Cliente;

import customfonts.MyRegulerText;

public class FragmentCarrinho extends Fragment {

    View view;
    EditText  email, senha;
    MyRegulerText btn_logar;
    String Token;


    public FragmentCarrinho() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.carrinho_fragment,container,false);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String token = getActivity().getIntent().getExtras().getString("token");



        Busca_carrinho busca_carrinho = new Busca_carrinho();

        busca_carrinho.buscacarrinho(getContext(),getActivity(),view,token);





    }

}
