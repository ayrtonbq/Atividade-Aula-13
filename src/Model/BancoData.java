package Model;

import java.util.List;

public class BancoData {
    private int idBanco;
    private String nome;
    private List<ContaModel> contas;

    public BancoData(int idBanco, String nome, List<ContaModel> contas) {
        this.idBanco = idBanco;
        this.nome = nome;
        this.contas = contas;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ContaModel> getContas() {
        return contas;
    }

    public void setContas(List<ContaModel> contas) {
        this.contas = contas;
    }
}
