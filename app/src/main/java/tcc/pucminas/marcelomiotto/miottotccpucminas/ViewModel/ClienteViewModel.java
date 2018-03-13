package tcc.pucminas.marcelomiotto.miottotccpucminas.ViewModel;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Observable;

import tcc.pucminas.marcelomiotto.miottotccpucminas.Model.Cliente;
import tcc.pucminas.marcelomiotto.miottotccpucminas.Model.ClienteLoader;
import tcc.pucminas.marcelomiotto.miottotccpucminas.Utils.Constant;

import static tcc.pucminas.marcelomiotto.miottotccpucminas.Utils.Constant.EXCLUIR_CLIENTE;
import static tcc.pucminas.marcelomiotto.miottotccpucminas.Utils.Constant.SERVER_API_TCC_PUC_MINAS;


/*
** A classe ClienteViewModel extends Observable para ser usado o padrao MVVM
*/
public class ClienteViewModel extends Observable  {

    Context mContext;

    public ClienteViewModel(Context context) {
        mContext = context;
    }


    /*
    ** O metodo criarCliente eh chamado quando o usuario pressiona o botao 'Criar Conta'
    ** Utiliza a classe ClienteLoader para fazer a requisicao para o servidor com o metodo
    ** jsonRequests
    ** O 'usuario' e 'senha' sao validados antes da requisicao ser feita pelo metodo validarUsuarioSenha
    */
    public void criarCliente(Cliente cliente) {

        validarUsuarioSenha(cliente);

        if(cliente.getId() > 0) {
            jsonRequests(Request.Method.POST, cliente, mContext, SERVER_API_TCC_PUC_MINAS);
        }else{
            notificaObservers(cliente);
        }
    }

    /*
    ** O metodo autenticarCliente eh chamado quando o usuario pressiona o botao 'Acessar'
    ** Utiliza a classe ClienteLoader para fazer a requisicao para o servidor com o metodo
    ** jsonRequests.
    ** O 'usuario' e 'senha' sao validados antes da requisicao ser feita pelo metodo validarUsuarioSenha
    */
    public void autenticarCliente(Cliente cliente) {

        validarUsuarioSenha(cliente);

        if(cliente.getId() > 0) {
            jsonRequests(Request.Method.POST, cliente, mContext, SERVER_API_TCC_PUC_MINAS + Constant.AUTENTICAR_CLIENTE);
        }else{
            notificaObservers(cliente);
        }

    }

    /*
    ** O metodo salvarCliente eh chamado quando o usuario pressiona o botao 'Salvar'
    ** Utiliza a classe ClienteLoader para fazer a requisicao para o servidor com o metodo
    ** jsonRequests
    */
    public void salvarCliente(Cliente cliente) {

        jsonRequests(Request.Method.PUT,cliente,mContext, SERVER_API_TCC_PUC_MINAS);

    }


    /*
    ** O metodo excluirCliente eh chamado quando o usuario pressiona a opcao do Menu suspenso 'Excluir'
    ** Utiliza a classe ClienteLoader para fazer a requisicao para o servidor com o metodo
    ** jsonRequests
    */
    public void excluirCliente(long clienteId){

        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        /*
        ** O metodo utilizado deveria ser o DELETE, mas por alguma razao a biblioteca Volley nao
        ** estava funcionando. A API testada com o Postman funcionava perfeitamente.
        */
        jsonRequests(Request.Method.POST,cliente,mContext,SERVER_API_TCC_PUC_MINAS+EXCLUIR_CLIENTE);

    }

    /*
    ** O metodo buscarCliente eh chamado quando o usuario eh direcionado para a tela dos dados do cliente
    ** Utiliza a classe ClienteLoader para fazer a requisicao para o servidor com o metodo
    ** jsonRequests
    */
    public void buscarCliente( long clienteId) {
        Cliente cliente = new Cliente();
        jsonRequests(Request.Method.GET,cliente,mContext, SERVER_API_TCC_PUC_MINAS + "/"+clienteId);

    }

    /*
    ** O metodo jsonRequests eh chamado pelos metodos da classe que precisam acessar as APIs REST
    ** este metodo encapsula as requisicoes
    ** Utiliza a classe ClienteLoader para fazer a requisicao para o servidor com o metodo
    ** jsonRequests
    */
    private void jsonRequests(int httpMethod,Cliente cliente,Context context, String serverLink){
        try {
            final JSONObject jsonCliente = new JSONObject(new Gson().toJson(cliente));
            final ClienteLoader clienteLoader = new ClienteLoader(context);

            clienteLoader.jsonRequest(httpMethod, serverLink, jsonCliente, this);

        }catch (JSONException e){
            Log.e("TCC PUC MINAS",e.getMessage());
        }
    }


    /*
    ** O metodo notificaObservers eh chamado no retorno da requisicao. Como a resposta eh assincrona um
    ** um objeto da Classe ClienteViewModel eh criado na classe ClienteLoader.
    */
    public void notificaObservers(Object object) {

        setChanged();
        notifyObservers(object);
    }


    /*
    ** O metodo validarUsuarioSenha eh chamado para validar 'usuario' e 'senha' em ambos os metodos
    ** criarCliente e autenticarCliente
    */
    public void validarUsuarioSenha(Cliente cliente){

        if(cliente != null){


            if (cliente.getEmail().equals("") || cliente.getEmail().isEmpty() ||
                cliente.getSenha().equals("") ||cliente.getSenha().isEmpty()
               ) {
                cliente.setId(-1);
            }else{
                cliente.setId(9999L);
            }
        }else {
            cliente = new Cliente();
            cliente.setId(-1);

        }

    }
}
