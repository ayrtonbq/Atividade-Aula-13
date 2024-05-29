package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import Controller.Conexao;
import DAO.BancoDAO;
import Model.Banco;
import Model.BancoA;
import Model.BancoB;

public class PrincipalView {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Selecionar Banco");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        lugarComponentes(panel);

        frame.setVisible(true);
    }

    private static void lugarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel labelEscolha = new JLabel("Escolha o banco:");
        labelEscolha.setBounds(10, 20, 120, 25);
        panel.add(labelEscolha);

        JButton btnBancoA = new JButton("Banco A");
        btnBancoA.setBounds(10, 50, 120, 25);
        panel.add(btnBancoA);

        JButton btnBancoB = new JButton("Banco B");
        btnBancoB.setBounds(140, 50, 120, 25);
        panel.add(btnBancoB);

        // Adicionando ação aos botões de escolha de banco
        btnBancoA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testarBanco("BancoA");
            }
        });

        btnBancoB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testarBanco("BancoB");
            }
        });
    }

    private static void testarBanco(String bancoNome) {
        Connection conexao = Conexao.obterConexao();
        if (conexao == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.");
            return;
        }
        BancoDAO bancoDAO = new BancoDAO(conexao);

        Banco banco;
        if (bancoNome.equals("BancoA")) {
            banco = new BancoA();
        } else {
            banco = new BancoB();
        }

        JFrame operacoesFrame = new JFrame("Operações Bancárias");
        operacoesFrame.setSize(300, 200);
        operacoesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel operacoesPanel = new JPanel();
        operacoesFrame.add(operacoesPanel);
        operacoesPanel.setLayout(null);

        JButton btnSaque = new JButton("Saque");
        btnSaque.setBounds(10, 20, 120, 25);
        operacoesPanel.add(btnSaque);

        JButton btnDeposito = new JButton("Depósito");
        btnDeposito.setBounds(140, 20, 120, 25);
        operacoesPanel.add(btnDeposito);

        JButton btnTransferencia = new JButton("Transferência");
        btnTransferencia.setBounds(10, 50, 120, 25);
        operacoesPanel.add(btnTransferencia);

        JButton btnExtrato = new JButton("Extrato");
        btnExtrato.setBounds(140, 50, 120, 25);
        operacoesPanel.add(btnExtrato);

        btnSaque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaqueView saqueView = new SaqueView(banco, bancoDAO);
                saqueView.exibirInterfaceSaque();
            }
        });

        btnDeposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepositoView depositoView = new DepositoView(banco, bancoDAO);
                depositoView.exibirInterfaceDeposito();
            }
        });

        btnTransferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferenciaView transferenciaView = new TransferenciaView(banco, bancoDAO);
                transferenciaView.exibirInterfaceTransferencia();
            }
        });

        btnExtrato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExtratoView extratoView = new ExtratoView(banco, bancoDAO);
                extratoView.exibirInterfaceExtrato();
            }
        });

        operacoesFrame.setVisible(true);
    }
}
