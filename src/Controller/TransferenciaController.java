package Controller;

import Model.Banco;
import Model.ContaModel;

public class TransferenciaController {
    private Banco banco;

    public TransferenciaController(Banco banco) {
        this.banco = banco;
    }
    
    public Boolean transferir(ContaModel contaOrigem, ContaModel contaDestino, Double valor) {
        // Verificar se a transferência é possível (por exemplo, saldo suficiente na conta de origem)
        // Se possível, realizar a transferência e retornar true, caso contrário, retornar false
        // Aqui você deve implementar a lógica de transferência entre as contas
        return banco.transferir(contaOrigem, contaDestino, valor);
    }
}
