package Model;

import java.util.Map;

public class BancoB implements Banco {
    private Map<String, ContaModel> contas;

    public ContaModel obterConta(String cpf) {
        return contas.get(cpf);
    }

    @Override
    public Boolean sacar(ContaModel conta, Double valor) {
        if (conta.getSaldo() - valor >= -500.0) {
            conta.setSaldo(conta.getSaldo() - valor);
            return true;
        }
        return false;
    }

    @Override
    public Double depositar(ContaModel conta, Double valor) {
        conta.setSaldo(conta.getSaldo() + valor);
        return conta.getSaldo();
    }

    @Override
    public void extrato(ContaModel conta) {
        if (contas.containsKey(conta.getCpf())) {
            ContaModel contaAtual = contas.get(conta.getCpf());

            System.out.println("Extrato da Conta:");
            System.out.println("CPF: " + contaAtual.getCpf());
            System.out.println("Saldo: " + contaAtual.getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    @Override
    public Boolean transferir(ContaModel contaOrigem, ContaModel contaDestino, Double valor) {
        if (sacar(contaOrigem, valor)) {
            depositar(contaDestino, valor);
            return true;
        }
        return false;
    }
}
