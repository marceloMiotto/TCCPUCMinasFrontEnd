package tcc.pucminas.marcelomiotto.miottotccpucminas.Network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class NetworkSingleton {
    private static NetworkSingleton ourInstance;
    private static RequestQueue requestQueue;


    /*
    ** A classe NetworkSingleton eh utilizada para gerenciar a fila de requisicoes
     */
    public static NetworkSingleton getInstance(Context context) {

        if(ourInstance == null){
            ourInstance = new NetworkSingleton();
            requestQueue = Volley.newRequestQueue(context);
        }

        return ourInstance;
    }

    private NetworkSingleton() {
    }

    public static void addJSONToRequestQueue(JsonObjectRequest jsObjRequest){
        requestQueue.add(jsObjRequest);
    }


}