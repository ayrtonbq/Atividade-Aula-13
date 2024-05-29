package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.BancoDAO;
import Model.Banco;
import Model.BancoA;
import Model.BancoB;
import Model.ContaModel;

public class ExtratoView {
    private Banco banco;
    private BancoDAO bancoDAO;
    private JTextField txtConta;

    public ExtratoView(Banco banco, BancoDAO bancoDAO) {
        this.banco = banco;
        this.bancoDAO = bancoDAO;
    }

    public void exibirInterfaceExtrato() {
        JFrame frame = new JFrame("Extrato");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        lugarComponentes(panel);

        frame.setVisible(true);
    }

    private void lugarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel labelConta = new JLabel("Conta:");
        labelConta.setBounds(10, 20, 120, 25);
        panel.add(labelConta);

        txtConta = new JTextField(20);
        txtConta.setBounds(140, 20, 120, 25);
        panel.add(txtConta);

        JButton btnExibirExtrato = new JButton("Exibir Extrato");
        btnExibirExtrato.setBounds(10, 50, 120, 25);
        panel.add(btnExibirExtrato);

        btnExibirExtrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroConta = txtConta.getText();
                ContaModel conta = obterConta(numeroConta);

                if (conta != null) {
                    banco.extrato(conta);
                    JOptionPane.showMessageDialog(panel, "Extrato exibido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(panel, "Conta não encontrada.");
                }
            }
        });
    }

    private ContaModel obterConta(String cpf) {
        // Verifica se o banco é uma instância de BancoA ou BancoB
        if (banco instanceof BancoA) {
            // Se sim, usa o método obterConta de BancoA
            return ((BancoA) banco).obterConta(cpf);
        } else if (banco instanceof BancoB) {
            // Se sim, usa o método obterConta de BancoB
            return ((BancoB) banco).obterConta(cpf);
        }
        return null;
    }
}
