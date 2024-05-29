package Model;

public interface Banco {
	ContaModel obterConta(String cpf);
    public Boolean sacar(ContaModel conta, Double valor);
    public Double depositar(ContaModel conta, Double valor);
    public void extrato(ContaModel conta);
    public Boolean transferir(ContaModel contaOrigem, ContaModel contaDestino, Double valor);
}