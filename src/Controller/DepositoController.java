package Controller;

import Model.Banco;
import Model.ContaModel;

public class DepositoController {
    private Banco banco;

    public DepositoController(Banco banco) {
        this.banco = banco;
    }

    public Double depositar(ContaModel conta, Double valor) {
        return banco.depositar(conta, valor);
    }
}
