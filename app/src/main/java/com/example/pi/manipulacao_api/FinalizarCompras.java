package com.example.pi.manipulacao_api;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pi.model.Produto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class FinalizarCompras {

    String url;

    public void finalizacompra(final Context context, final Activity activity, final View view,final String token) {

        url = APIconfig.URL;
        String urllocal = "/venda";


            RequestQueue requestQueue = Volley.newRequestQueue(context);


            StringRequest stringRequest = new StringRequest(Request.Method.POST, url+urllocal, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("LOG_RESPONSE", response);
                    if(response.equals("201")){
                        Toast.makeText(context,
                                "Compra finalizada com sucesso!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_RESPONSE", error.toString());

                    Toast.makeText(context,
                            "Erro: tente novamente mais tarde!",
                            Toast.LENGTH_LONG).show();
                }
            })
            {



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
