package com.example.pi.manipulacao_api;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pi.R;
import com.example.pi.model.Categoria;
import com.example.pi.model.Cliente;
import com.example.pi.model.Imagem;
import com.example.pi.model.Produto;
import com.example.pi.views.ProdutoListAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInput;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Busca_produtos {

    String url ;



    //Busca produtos por categoria
    public void buscarProdutos(final Context context, final Activity activity, final View view, final int categoria, final String sexo) {

        url = APIconfig.URL;
        String urllocal = "/categoria/";


        RequestQueue requestQueue = Volley.newRequestQueue(context);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+urllocal+categoria, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("LOG_RESPONSE", response);

                List<Produto> produtos = new ArrayList<Produto>();


                //Tranforma json em lista de objetos
                try {
                    JSONArray produtosJson = new JSONArray(response);
                    JSONObject produto;

                    JsonArray id_imagens;
                    List<Imagem> list_img = new ArrayList<>();

                    for (int i = 0; i < produtosJson.length(); i++) {
                        produto = new JSONObject(produtosJson.getString(i));
                        // Log.i("PESSOA ENCONTRADA: ", "id=" + produto.getString("id"));

                        Produto objetoProduto = new Produto();
                        objetoProduto.setId(produto.getLong("id"));
                        objetoProduto.setNome(produto.getString("nome"));
                        objetoProduto.setPreco(Double.valueOf(produto.getString("preco")));
                        objetoProduto.setQnt(produto.getInt("quantidade"));
                        objetoProduto.setSexo(produto.getString("sexo"));

                        JSONArray js = produto.getJSONArray("imagens");


                        List<String> list = new ArrayList<String>();
                        for (int j=0; j<js.length(); j++) {
                            list.add(js.getJSONObject(j).getString("id"));
                        }

                        objetoProduto.setLista_id_imagens(list);


                        //System.out.println(objetoProduto.getLista_id_imagens().size());


                        if (produto.getString("sexo").equals(sexo)) {

                                produtos.add(objetoProduto);


                            }

                    }


                //Preenche o recyclerview com produtos
                RecyclerView myrecyclerview = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                ProdutoListAdapter produtoListAdapter = new ProdutoListAdapter(produtos,context,activity);
                myrecyclerview.setLayoutManager(new LinearLayoutManager(activity));
                //myrecyclerview.setLayoutManager(new GridLayoutManager(activity(),2));

                myrecyclerview.setAdapter(produtoListAdapter);
                } catch (JSONException e) {
                    Log.e("Erro", "kkkkkkkkkkkkkkkkkkkkkkkkkkkkk", e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG_RESPONSE", error.toString());

                Toast.makeText(context,
                        "Verifique a internet e Tente Novamente!",
                        Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);


    }

}
