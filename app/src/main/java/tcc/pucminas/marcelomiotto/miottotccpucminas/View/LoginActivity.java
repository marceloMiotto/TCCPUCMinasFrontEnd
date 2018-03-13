package tcc.pucminas.marcelomiotto.miottotccpucminas.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import tcc.pucminas.marcelomiotto.miottotccpucminas.Model.Cliente;
import tcc.pucminas.marcelomiotto.miottotccpucminas.R;
import tcc.pucminas.marcelomiotto.miottotccpucminas.ViewModel.ClienteViewModel;
import tcc.pucminas.marcelomiotto.miottotccpucminas.databinding.ActivityLoginBinding;

/*
** Classe ClienteActivity eh a interface com o usuario (cliente)
** A classe implementa a interface Observer para ser utilizado o padrao MVVM
** Tambem eh utilizada a biblioteca DataBinding o que torna o padrao MVVM mais eficaz
 */
public class LoginActivity extends AppCompatActivity implements Observer {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        ClienteViewModel clienteViewModel = new ClienteViewModel(this);
        activityLoginBinding.setClienteViewModel(clienteViewModel);
        /*
        ** o objeto mClienteViewModel esta adicionando a classe como observadora
        */
        clienteViewModel.addObserver(this);
        Cliente cliente = new Cliente();
        activityLoginBinding.setCliente(cliente);

    }

    /*
    ** O metodo update sera chamado no momento que o ViewModel notificar os observadores
    ** Neste momento a tela eh atualizada com as mensagens de sucesso ou erro das operacoes
    */
    @Override
    public void update(Observable observable, Object o) {

        if (o instanceof Cliente) {
            if (((Cliente) o).getId()> 0) {
                Intent intent = new Intent(this, ClienteActivity.class);
                intent.putExtra("ClienteId", ((Cliente) o).getId());
                startActivity(intent);

            } else {
                if(((Cliente) o).getId() == -1) {
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.login_layout_id),
                            R.string.email_ou_senha, Snackbar.LENGTH_SHORT);
                    mySnackbar.show();
                }else{
                    Snackbar mySnackbar = Snackbar.make(findViewById(R.id.login_layout_id),
                            R.string.usuario_invalido, Snackbar.LENGTH_SHORT);
                    mySnackbar.show();
                }
            }
        }


    }


}
