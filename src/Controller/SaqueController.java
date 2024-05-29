package Controller;

import Model.Banco;
import Model.ContaModel;

public class SaqueController {
    private Banco banco;

    public SaqueController(Banco banco) {
        this.banco = banco;
    }

    public Boolean sacar(ContaModel conta, Double valor) {
        return banco.sacar(conta, valor);
    }
}
