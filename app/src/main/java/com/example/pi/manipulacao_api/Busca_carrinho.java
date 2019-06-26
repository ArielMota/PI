package com.example.pi.manipulacao_api;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pi.R;
import com.example.pi.model.Imagem;
import com.example.pi.model.ItemCarrinho;
import com.example.pi.model.Produto;
import com.example.pi.views.CarrinhoListAdapter;
import com.example.pi.views.ProdutoListAdapter;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Busca_carrinho {

    String url ;


    public void buscacarrinho(final Context context, final Activity activity, final View view, final  String token) {



        url = APIconfig.URL;
        String urllocal = "/auth/carrinho";


        RequestQueue requestQueue = Volley.newRequestQueue(context);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+urllocal, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("LOG_RESPONSE", response);

                List<ItemCarrinho> lista_item = new ArrayList<ItemCarrinho>();
                List<Produto> produtos = new ArrayList<Produto>();


                //Tranforma json em lista de objetos
                try {
                    //JSONArray itens_carrinho = new JSONArray(response);
                    JSONObject item_carrinho = new JSONObject(response);


                    JSONArray itens = new JSONArray(item_carrinho.getString("itens"));

                    for (int i = 0; i < itens.length(); i++) {

                        ItemCarrinho itemCarrinho = new ItemCarrinho();
                        Produto produto = new Produto();

                        itemCarrinho.setId(Long.valueOf(itens.getJSONObject(i).getString("id")));
                        itemCarrinho.setQuantidade(Integer.valueOf(itens.getJSONObject(i).getString("quantidade")));
                        lista_item.add(itemCarrinho);

                        JSONObject produto_json = new JSONObject(itens.getJSONObject(i).getString("produto"));

                        //Esse id será utilizado para exclusão do item no carrinho
                        produto.setId(Long.valueOf(produto_json.getString("id")));
                        produto.setNome(produto_json.getString("nome"));
                        produto.setPreco(Double.valueOf(produto_json.getString("preco")));

                        JSONArray js = produto_json.getJSONArray("imagens");

                        List<String> list = new ArrayList<String>();
                        for (int j=0; j<js.length(); j++) {
                            list.add(js.getJSONObject(j).getString("id"));
                        }
                        produto.setLista_id_imagens(list);
                        produtos.add(produto);


                    }






                    //Preenche o recyclerview com produtos
                    RecyclerView myrecyclerview = (RecyclerView) view.findViewById(R.id.my_recycler_view);
                    CarrinhoListAdapter carrinhoListAdapter = new CarrinhoListAdapter(lista_item,produtos,context,activity,view);
                    myrecyclerview.setLayoutManager(new LinearLayoutManager(activity));

                    myrecyclerview.setAdapter(carrinhoListAdapter);



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
        })
        {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", token);
                return headers;
            }
        };

        requestQueue.add(stringRequest);


    }

}
