package Model;

public class MovimentacaoModel {
    private int idMovimentacao;
    private String descricao;
    private double valor;

    public MovimentacaoModel(int idMovimentacao, String descricao, double valor) {
        this.idMovimentacao = idMovimentacao;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
