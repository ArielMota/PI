package com.example.pi.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.pi.R;
import com.example.pi.manipulacao_api.Busca_categorias;
import com.example.pi.manipulacao_api.Busca_produtos;

public class FragmentRoupasFemininas extends Fragment {

    View view;
    Spinner spinner;


    public FragmentRoupasFemininas() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.roupas_femininas_fragment,container,false);

        spinner = (Spinner) view.findViewById(R.id.menu_categorias);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Busca_categorias busca_categorias = new Busca_categorias();
        busca_categorias.buscarCategorias(getContext(),getActivity(),view);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                //selecteditem =  adapter.getItemAtPosition(i).toString();
                //or this can be also right: selecteditem = level[i];

                Busca_produtos busca_produtos = new Busca_produtos();
                busca_produtos.buscarProdutos(getContext(),getActivity(),view,++i,"F");


            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });



    }

}