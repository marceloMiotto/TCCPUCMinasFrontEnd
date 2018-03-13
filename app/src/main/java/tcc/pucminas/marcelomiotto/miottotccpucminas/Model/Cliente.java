package tcc.pucminas.marcelomiotto.miottotccpucminas.Model;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import tcc.pucminas.marcelomiotto.miottotccpucminas.BR;

/*
** Classe Cliente para mapear os dados do cliente. Extends BaseObservable para utilizar
** o padrao MVVM
 */
public class Cliente extends BaseObservable {


    private long   id;
    private String name;
    private String CPF;
    private String endereco;
    private String municipio;
    private String estado;
    private String telefone;
    private String email;
    private String senha;

    public Cliente(){
        this.email = "";
        this.senha = "";
    }

    public Cliente(long id, String name, String CPF, String endereco, String municipio, String estado, String telefone, String email, String senha) {
        this.id = id;
        this.name = name;
        this.CPF = CPF;
        this.endereco = endereco;
        this.municipio = municipio;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
        notifyPropertyChanged(BR.cPF);
    }

    @Bindable
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
        notifyPropertyChanged(BR.endereco);
    }

    @Bindable
    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
        notifyPropertyChanged(BR.municipio);
    }

    @Bindable
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
        notifyPropertyChanged(BR.telefone);
    }

    @Bindable
    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);

    }

    @Bindable
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
        notifyPropertyChanged(BR.senha);

    }

    @Bindable
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        notifyPropertyChanged(BR.estado);
    }
}
