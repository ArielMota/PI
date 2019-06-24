package com.example.pi.manipulacao_api;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pi.R;
import com.example.pi.model.Produto;
import com.example.pi.views.ProdutoListAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Busca_imagens {

    String url ;


    public void buscarImagens(final Context context, final Dialog dialog, final long produto_id) {

        url = APIconfig.URL;
        String urllocal = "/imagem/";


        RequestQueue requestQueue = Volley.newRequestQueue(context);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+urllocal+produto_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("LOG_RESPONSE", response);

                ImageView img = (ImageView) dialog.findViewById(R.id.img_produto);






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
