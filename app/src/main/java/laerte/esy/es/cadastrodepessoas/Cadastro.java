package laerte.esy.es.cadastrodepessoas;

import java.io.Serializable;

/**
 * Created by Laerte on 15/12/2016.
 */

public class Cadastro implements Serializable {
    private String nomeDivida;
    private String valorDivida;


    public Cadastro() {
    }

    public String getNomeDivida() {
        return nomeDivida;
    }

    public void setNomeDivida(String nomeDivida) {
        this.nomeDivida = nomeDivida;
    }

    public String getValorDivida() {
        return valorDivida;
    }

    public void setValorDivida(String valorDivida) {
        this.valorDivida = valorDivida;
    }

    @Override
    public String toString() {
        return nomeDivida +"\n "+valorDivida;
    }
}
