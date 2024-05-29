package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAO.BancoDAO;
import Model.Banco;
import Model.BancoA;
import Model.BancoB;
import Model.ContaModel;

public class TransferenciaView {
    private Banco banco;
    private BancoDAO bancoDAO;
    private JTextField txtContaOrigem;
    private JTextField txtContaDestino;
    private JTextField txtValor;

    public TransferenciaView(Banco banco, BancoDAO bancoDAO) {
        this.banco = banco;
        this.bancoDAO = bancoDAO;
    }

    public void exibirInterfaceTransferencia() {
        JFrame frame = new JFrame("Transferência");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        lugarComponentes(panel);

        frame.setVisible(true);
    }

    private void lugarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel labelContaOrigem = new JLabel("Conta de origem:");
        labelContaOrigem.setBounds(10, 20, 120, 25);
        panel.add(labelContaOrigem);

        txtContaOrigem = new JTextField(20);
        txtContaOrigem.setBounds(140, 20, 120, 25);
        panel.add(txtContaOrigem);

        JLabel labelContaDestino = new JLabel("Conta de destino:");
        labelContaDestino.setBounds(10, 50, 120, 25);
        panel.add(labelContaDestino);

        txtContaDestino = new JTextField(20);
        txtContaDestino.setBounds(140, 50, 120, 25);
        panel.add(txtContaDestino);

        JLabel labelValor = new JLabel("Valor:");
        labelValor.setBounds(10, 80, 120, 25);
        panel.add(labelValor);

        txtValor = new JTextField(20);
        txtValor.setBounds(140, 80, 120, 25);
        panel.add(txtValor);

        JButton btnTransferir = new JButton("Transferir");
        btnTransferir.setBounds(10, 110, 120, 25);
        panel.add(btnTransferir);

        btnTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroContaOrigem = txtContaOrigem.getText();
                String numeroContaDestino = txtContaDestino.getText();
                double valor = Double.parseDouble(txtValor.getText());

                ContaModel contaOrigem = obterConta(numeroContaOrigem);
                ContaModel contaDestino = obterConta(numeroContaDestino);

                if (contaOrigem != null && contaDestino != null) {
                    boolean sucesso = banco.transferir(contaOrigem, contaDestino, valor);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(panel, "Transferência realizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Falha ao realizar a transferência. Verifique os dados informados.");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Conta de origem ou conta de destino não encontrada.");
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
