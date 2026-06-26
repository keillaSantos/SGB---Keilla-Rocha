import javax.swing.*;
import java.awt.*;

public class TelaEstoque extends JFrame {

    private JTextField txtPesquisaNome;
    private JButton btnPesquisar;
    private JButton btnVoltar;
    private JTextArea areaResultado;

    public TelaEstoque() {

        // Configuração básica da janela.
        setTitle("Checagem de Estoque");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Design.
        Color azul = new Color(0, 102, 204);
        Color fundo = new Color(245, 245, 245);
        Font fonte = new Font("Segoe UI", Font.BOLD, 13);

        //painel de busca.
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelBusca.setBackground(Color.WHITE);
        painelBusca.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(azul), "Buscar Produto", 0, 0, fonte, azul));

        JLabel lbNome = new JLabel("Nome do Produto:");
        lbNome.setForeground(azul);
        lbNome.setFont(fonte);

        txtPesquisaNome = new JTextField(20);
        txtPesquisaNome.setBorder(BorderFactory.createLineBorder(azul));

        //Design do botão.
        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setBackground(azul);
        btnPesquisar.setForeground(Color.WHITE);
        btnPesquisar.setFont(fonte);
        btnPesquisar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        painelBusca.add(lbNome);
        painelBusca.add(txtPesquisaNome);
        painelBusca.add(btnPesquisar);

        //Painel de resultado.
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(azul), "Informações do Estoque", 0, 0, fonte, azul));

        //Painéis dos botões de baixo.
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelInferior.setBackground(fundo);

        btnVoltar = new JButton("Voltar para Gerenciamento");
        btnVoltar.setBackground(azul);
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(fonte);
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        painelInferior.add(btnVoltar);

        //Add os componentes na tela.
        add(painelBusca, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
        getContentPane().setBackground(fundo);

        //Eventos dos botões.
        btnPesquisar.addActionListener(e -> acaoPesquisarEstoque());
        btnVoltar.addActionListener(e -> {
            new TelaGerenciamento().setVisible(true); // Abre o gerenciamento de volta
            dispose();
        });

        // Mostra todos os produtos logo ao abrir a tela.
        listarTodosOsProdutos();

        setVisible(true);
    }

    //Possibilita busca pelo nome digitado.
    private void acaoPesquisarEstoque() {
        String nomeBuscado = txtPesquisaNome.getText().trim();

        if (nomeBuscado.isEmpty()) {
            listarTodosOsProdutos(); // Se pesquisar em branco, mostra tudo de novo.
            return;
        }

        areaResultado.setText(""); // Limpa o painel de resultados.
        boolean encontrou = false;

        // Cabeçalho formatado bonito.
        areaResultado.append(String.format("%-10s %-20s %-12s %-10s\n", "CÓDIGO", "NOME", "PREÇO", "ESTOQUE"));
        areaResultado.append("---------------------------------------------------------\n");

        for (Produto p : Banco.listaProdutos) {

            if (p.getNome().toLowerCase().contains(nomeBuscado.toLowerCase())) {
                areaResultado.append(String.format("%-10s %-20s R$ %-10.2f %-10s\n",
                        p.getCodigo(),
                        p.getNome(),
                        p.getPreco(),
                        p.getEstoque()));
                encontrou = true;
            }
        }

        if (!encontrou) {
            areaResultado.setText("\n   Nenhum produto encontrado com o nome: " + nomeBuscado);
        }
    }

    //listar produto inteiro
    private void listarTodosOsProdutos() {
        areaResultado.setText("");

          //se tiver vazio aparecer essa mensagem
        if (Banco.listaProdutos.isEmpty()) {
            areaResultado.setText("\n   Nenhum produto cadastrado no sistema ainda.");
            return;
        }

        areaResultado.append(String.format("%-10s %-20s %-12s %-10s\n", "CÓDIGO", "NOME", "PREÇO", "ESTOQUE"));
        areaResultado.append("---------------------------------------------------------\n");

        for (Produto p : Banco.listaProdutos) {
            areaResultado.append(String.format("%-10s %-20s R$ %-10.2f %-10s\n",
                    p.getCodigo(), p.getNome(), p.getPreco(), p.getEstoque()));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaEstoque());
    }
}
