package tcc.pucminas.marcelomiotto.miottotccpucminas.View;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Observable;
import java.util.Observer;

import tcc.pucminas.marcelomiotto.miottotccpucminas.Model.Cliente;
import tcc.pucminas.marcelomiotto.miottotccpucminas.R;
import tcc.pucminas.marcelomiotto.miottotccpucminas.ViewModel.ClienteViewModel;
import tcc.pucminas.marcelomiotto.miottotccpucminas.databinding.ActivityMainBinding;

/*
** Classe ClienteActivity eh a interface com o usuario (cliente)
** A classe implementa a interface Observer para ser utilizado o padrao MVVM
** Tambem eh utilizada a biblioteca DataBinding o que torna o padrao MVVM mais eficaz
 */
public class ClienteActivity extends AppCompatActivity implements Observer {

    ActivityMainBinding mContentMainBinding;
    ClienteViewModel    mClienteViewModel;
    Cliente mCliente;
    long mClienteId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        Intent intent = getIntent();
        mClienteId = intent.getLongExtra("ClienteId",0);
        mClienteViewModel = new ClienteViewModel(this);
        mContentMainBinding.setClienteViewModel(mClienteViewModel);
        /*
        ** o objeto mClienteViewModel esta adicionando a classe como observadora
        */
        mClienteViewModel.addObserver(this);
        mCliente = new Cliente();
        mContentMainBinding.setCliente(mCliente);
        mClienteViewModel.buscarCliente(mClienteId);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            mClienteViewModel.excluirCliente(mClienteId);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    ** O metodo update sera chamado no momento que o ViewModel notificar os observadores
    ** Neste momento a tela eh atualizada com as mensagens de sucesso ou erro das operacoes
    */
    @Override
    public void update(Observable observable, Object o) {


        if(((Cliente) o).getId() > 0) {
            mContentMainBinding.setCliente((Cliente) o);

            Snackbar mySnackbar = Snackbar.make(findViewById(R.id.cliente_layout_id),
                    R.string.cliente_atualizado, Snackbar.LENGTH_SHORT);
            mySnackbar.show();

        }else {

            String errorMsg = "Erro";
            if(((Cliente) o).getId() == -92){
                errorMsg = "Erro ao atualizar!";

                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.cliente_layout_id),
                        errorMsg, Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }

            if(((Cliente) o).getId() == -93){
                errorMsg = "Erro ao buscar o cliente !";
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.cliente_layout_id),
                        errorMsg, Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }

            if(((Cliente) o).getId() == -94){
                errorMsg = "Erro ao excluir o cliente !";
                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.cliente_layout_id),
                        errorMsg, Snackbar.LENGTH_SHORT);
                mySnackbar.show();
            }

            if(((Cliente) o).getId() == -99){
                moveTaskToBack(true);
                finish();
            }



        }

    }
}
