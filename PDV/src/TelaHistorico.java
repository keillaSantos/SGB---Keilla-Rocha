import javax.swing.*;
import java.awt.*;

public class TelaHistorico extends JFrame {

    private JTextArea areaHistorico;
    private JButton btnVoltar;


    public TelaHistorico() {
        // Configuração da janela
        setTitle("Histórico de Vendas");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // DISPOSE SERVE PARA FECHAR SOMENTE ESSE JANELA.
        setLayout(new BorderLayout(10, 10));

        // DESIGN.
        Color azul = new Color(0, 102, 204);
        Color fundo = new Color(245, 245, 245);
        Font fonte = new Font("Segoe UI", Font.BOLD, 13);

        // CAMPO PARA A LISTAGEM DAS VENDAS.
        areaHistorico = new JTextArea();
        areaHistorico.setEditable(false);
        areaHistorico.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(areaHistorico);
        scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(azul), "Registro de Vendas Concluídas", 0, 0, fonte, azul));

        // Botão para voltar.
        btnVoltar = new JButton("Voltar para o PDV");
        btnVoltar.setBackground(azul);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(fonte);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Evento do botão Voltar.
        btnVoltar.addActionListener(e -> {
            new PDV().setVisible(true);
            dispose();
        });

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setBackground(fundo);
        painelBotao.add(btnVoltar);

        // Adiciona os componentes na tela.
        add(scroll, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);

        getContentPane().setBackground(fundo);

        // Carrega as vendas do banco de dados na tela.
        carregarHistorico();

        setVisible(true);
    }

    // Puxa as informações do banco.
    private void carregarHistorico() {
        areaHistorico.setText(""); // Limpa antes de carregar

        if (Banco.historicoVendas.isEmpty()) {
            areaHistorico.setText("\n   Nenhuma venda foi realizada ainda.");
            return;
        }

        // Percorre a lista do banco e adiciona na tela.
        for (String venda : Banco.historicoVendas) {
            areaHistorico.append(venda + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaHistorico());
    }
}