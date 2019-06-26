package com.example.pi.manipulacao_api;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pi.R;
import com.example.pi.model.Imagem;
import com.example.pi.model.Produto;
import com.example.pi.views.ProdutoListAdapter;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Excluir_item_carrinho {

    String url;

    public void excluirItemCarrinho(final Context context,final  long id, final String token) {

        url = APIconfig.URL;
        String urllocal = "/auth/carrinho/";


        RequestQueue requestQueue = Volley.newRequestQueue(context);



        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url+urllocal+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("LOG_RESPONSE", response);

                if(response.equals("200")){
                    Toast.makeText(context,
                            "Item excluido do carrinho!",
                            Toast.LENGTH_SHORT).show();
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
        }){



            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);

                }




                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", token);
                headers.put("Content-Type", "application/json");

                return headers;
            }
        };

        requestQueue.add(stringRequest);


    }
}
