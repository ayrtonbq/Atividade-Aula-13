package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.BancoData;
import Model.ContaModel;
import java.util.HashMap;
import java.util.Map;

public class BancoDAO {

    private Connection conexao;

    public BancoDAO(Connection conexao) {
        this.conexao = conexao;
    }
    
    public void registrarMovimentacao(String cpfOrigem, String cpfDestino, double valor, int idBanco, double saldo, String tipoTransacao) throws SQLException {
        String sql = "INSERT INTO Movimentacao (cpf_origem, cpf_destino, valor, id_banco, saldo, tipo_transacao, data) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, cpfOrigem);
            ps.setString(2, cpfDestino);
            ps.setDouble(3, valor);
            ps.setInt(4, idBanco);
            ps.setDouble(5, saldo);
            ps.setString(6, tipoTransacao);
            ps.setDate(7, new Date(System.currentTimeMillis()));

            ps.executeUpdate();
        }
    }
    
    public Map<String, ContaModel> obterTodasContas() {
        Map<String, ContaModel> contas = new HashMap<>();

        String sql = "SELECT cpf, banco, saldo FROM Conta";

        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String cpf = rs.getString("cpf");
                String banco = rs.getString("banco");
                double saldo = rs.getDouble("saldo");
                ContaModel conta = new ContaModel(cpf, banco, saldo);
                contas.put(cpf, conta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contas;
    }
    
    public ContaModel obterConta(String cpf) throws SQLException {
        String sql = "SELECT * FROM Conta WHERE cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ContaModel(
                        rs.getString("cpf"),
                        rs.getString("id_banco"),
                        rs.getDouble("saldo")
                    );
                }
            }
        }
        return null;
    }
    
    public void atualizarSaldo(ContaModel conta) throws SQLException {
        String sql = "UPDATE conta SET saldo = ? WHERE cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo());
            stmt.setString(2, conta.getCpf());
            stmt.executeUpdate();
        }
    }

    // Método para inserir um novo banco na tabela Banco
    public void inserirBanco(BancoData banco) throws SQLException {
        String sql = "INSERT INTO Banco (nome) VALUES (?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, banco.getNome());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar o nome de um banco na tabela Banco
    public void atualizarNomeBanco(int idBanco, String novoNome) throws SQLException {
        String sql = "UPDATE Banco SET nome = ? WHERE id_banco = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, idBanco);
            stmt.executeUpdate();
        }
    }

    // Método para excluir um banco da tabela Banco
    public void excluirBanco(int idBanco) throws SQLException {
        String sql = "DELETE FROM Banco WHERE id_banco = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idBanco);
            stmt.executeUpdate();
        }
    }

    // Método para buscar um banco pelo ID na tabela Banco
    public BancoData buscarPorId(int idBanco) throws SQLException {
        String sql = "SELECT * FROM Banco WHERE id_banco = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idBanco);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Supondo que você tenha um método listarContasPorBanco() que retorna uma lista de ContaModel
                    List<ContaModel> contas = listarContasPorBanco(idBanco);
                    return new BancoData(rs.getInt("id_banco"), rs.getString("nome"), contas);
                }
            }
        }
        return null;
    }

    // Método para listar todos os bancos da tabela Banco
    public List<BancoData> listarBancos() throws SQLException {
        List<BancoData> bancos = new ArrayList<>();
        String sql = "SELECT * FROM Banco";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Supondo que você tenha um método listarContasPorBanco() que retorna uma lista de ContaModel
                List<ContaModel> contas = listarContasPorBanco(rs.getInt("id_banco"));
                bancos.add(new BancoData(rs.getInt("id_banco"), rs.getString("nome"), contas));
            }
        }
        return bancos;
    }

    // Método para listar todas as contas associadas a um banco
    private List<ContaModel> listarContasPorBanco(int idBanco) throws SQLException {
        List<ContaModel> contas = new ArrayList<>();
        String sql = "SELECT * FROM Conta WHERE id_banco = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idBanco);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contas.add(new ContaModel(rs.getString("cpf"), rs.getString("banco"), rs.getDouble("saldo")));
                }
            }
        }
        return contas;
    }
}
