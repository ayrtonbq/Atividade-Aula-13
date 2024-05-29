package View;

import DAO.BancoDAO;
import Model.Banco;
import Model.ContaModel;

import javax.swing.*;
import java.sql.SQLException;

public class DepositoView {
    private Banco banco;
    private BancoDAO bancoDAO;
    private JTextField txtConta;
    private JTextField txtValor;

    public DepositoView(Banco banco, BancoDAO bancoDAO) {
        this.banco = banco;
        this.bancoDAO = bancoDAO;
    }

    public void exibirInterfaceDeposito() {
        JFrame frame = new JFrame("Depósito");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        lugarComponentes(panel);

        frame.setVisible(true);
    }

    private void lugarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel labelConta = new JLabel("Conta:");
        labelConta.setBounds(10, 20, 80, 25);
        panel.add(labelConta);

        txtConta = new JTextField(20);
        txtConta.setBounds(100, 20, 165, 25);
        panel.add(txtConta);

        JLabel labelValor = new JLabel("Valor:");
        labelValor.setBounds(10, 50, 80, 25);
        panel.add(labelValor);

        txtValor = new JTextField(20);
        txtValor.setBounds(100, 50, 165, 25);
        panel.add(txtValor);

        JButton btnDeposito = new JButton("Depositar");
        btnDeposito.setBounds(10, 80, 120, 25);
        panel.add(btnDeposito);

        btnDeposito.addActionListener(e -> {
            String numeroConta = txtConta.getText();
            double valor;
            try {
                valor = Double.parseDouble(txtValor.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Valor inválido.");
                return;
            }

            try {
                ContaModel conta = bancoDAO.obterConta(numeroConta);

                if (conta != null) {
                    banco.depositar(conta, valor);
                    bancoDAO.atualizarSaldo(conta);
                    JOptionPane.showMessageDialog(panel, "Depósito realizado com sucesso! Novo saldo: " + conta.getSaldo());
                } else {
                    JOptionPane.showMessageDialog(panel, "Conta não encontrada.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao buscar conta: " + ex.getMessage());
            }
        });
    }
}
