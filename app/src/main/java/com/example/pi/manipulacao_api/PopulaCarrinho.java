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
import com.example.pi.model.Cliente;
import com.example.pi.model.Produto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PopulaCarrinho {

    String url;

    public void populacarrinho(final Context context, final Activity activity, final View view, final Produto produto,final String token) {

        url = APIconfig.URL;
        String urllocal = "/auth/carrinho";


        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id", produto.getId());
            jsonBody.put("quantidade", produto.getQnt());
            final String mRequestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url+urllocal, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("LOG_RESPONSE", response);
                    if(response.equals("201")){
                        Toast.makeText(context,
                                "Produto adcionado no carrinho",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_RESPONSE", error.toString());

                    Toast.makeText(context,
                            "Verifique os Dados e Tente Novamente!",
                            Toast.LENGTH_LONG).show();
                }
            })
            {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
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
                    return headers;
                }
            };

            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
