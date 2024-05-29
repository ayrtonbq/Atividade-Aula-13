package Controller;

import Model.Banco;
import Model.ContaModel;

public class ExtratoController {
    private Banco banco;

    public ExtratoController(Banco banco) {
        this.banco = banco;
    }

    public void extrato(ContaModel conta) {
        banco.extrato(conta);
    }
}
