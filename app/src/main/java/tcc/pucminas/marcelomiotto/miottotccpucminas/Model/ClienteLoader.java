package tcc.pucminas.marcelomiotto.miottotccpucminas.Model;


import android.content.Context;
import android.util.Log;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import org.json.JSONObject;
import tcc.pucminas.marcelomiotto.miottotccpucminas.Network.NetworkSingleton;
import tcc.pucminas.marcelomiotto.miottotccpucminas.ViewModel.ClienteViewModel;

/*
** Classe ClienteLoader para fazer as requisicoes as APIs Rest
*/
public class ClienteLoader {

    private Context mContext;
    private ClienteViewModel mClienteViewModel = null;

    public ClienteLoader(){}

    public ClienteLoader(Context context){
        mContext = context;
    }

    /*
    ** Metodo jsonRequest realiza as requisicoes para o servidor REST conforme o parametro requestMethod
    ** Apos o retorno o objeto mClienteViewModel notifica os observadores
     */
    public void jsonRequest(int requestMethod, String restApiURL, JSONObject jsonBody,ClienteViewModel clienteViewModel) {

        mClienteViewModel = clienteViewModel;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (requestMethod, restApiURL, jsonBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        Cliente cliente = new Gson().fromJson(response.toString(),Cliente.class);
                        mClienteViewModel.notificaObservers(cliente);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TCC","Erro de comunicacao " + error.getMessage());

                    }
                });

        // Acesso atraves da classe singleton
        NetworkSingleton.getInstance(mContext).addJSONToRequestQueue(jsObjRequest);


    }


}
