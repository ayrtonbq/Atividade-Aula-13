package Model;

public class ContaModel {
    private String cpf;
    private String banco;
    private double saldo;

    public ContaModel(String cpf, String banco, double saldo) {
        this.cpf = cpf;
        this.banco = banco;
        this.saldo = saldo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
