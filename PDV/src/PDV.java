import javax.swing.*;
import java.awt.*;

public class PDV extends JFrame {

    // Campos dos atributos
    private JTextField txtCodigo;
    private JTextField txtQtd;
    private JTextField txtNomePesquisa;
    private JTextField txtTotal;
    private JTextField txtPago;
    private JTextField txtTroco;
    private JTextArea areaItens;
    private double valorTotalVenda = 0.0;

    // Criação dos botões
    private JButton btnAdicionar;
    private JButton btnFinalizar;
    private JButton btnNovaVenda;
    private JButton btnGerenciamento;
    private JButton btnCancelarVenda;
    private JButton btnHistorico;

    private Venda vendaAtual;

    public PDV() {
        vendaAtual = new Venda();

        setTitle("PDV - Caixa");
        setSize(1000, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Chama a configuração dos componentes e eventos
        configurarComponentes();
        configurarEventos();

        setVisible(true);
    }

    // void criado apenas para tratar do enquadramento e design dos botões.
    private void configurarComponentes() {

        Color azul = new Color(0, 102, 204);
        Color fundo = new Color(245, 245, 245);
        Font fonte = new Font("Segoe UI", Font.BOLD, 13);

        //painel esquerdo
        JPanel item = new JPanel(new GridLayout(2, 3, 10, 10));
        item.setBackground(Color.WHITE);
        item.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(azul), "Item Venda", 0, 0, fonte, azul));

        //criação dos campos codigo,qtd e pesquisa.
        txtCodigo = new JTextField();
        txtQtd = new JTextField("1");
        txtNomePesquisa = new JTextField();

        //Design
        txtCodigo.setBorder(BorderFactory.createLineBorder(azul));
        txtQtd.setBorder(BorderFactory.createLineBorder(azul));
        txtNomePesquisa.setBorder(BorderFactory.createLineBorder(azul));

        JLabel lbCodigo = new JLabel("Código");
        JLabel lbQuantidade = new JLabel("Quantidade");
        JLabel lbNomePesquisa = new JLabel("Pesquisar por Nome");

        //Design
        lbCodigo.setForeground(azul);
        lbCodigo.setFont(fonte);
        lbQuantidade.setForeground(azul);
        lbQuantidade.setFont(fonte);
        lbNomePesquisa.setForeground(azul);
        lbNomePesquisa.setFont(fonte);

        // Todos os textos explicativos
        item.add(lbCodigo);
        item.add(lbQuantidade);
        item.add(lbNomePesquisa);

        // Todos os campos onde o usuário digita.
        item.add(txtCodigo);
        item.add(txtQtd);
        item.add(txtNomePesquisa);

        //Botões
        btnAdicionar = new JButton("Adicionar");
        btnFinalizar = new JButton("Finalizar Venda");
        btnNovaVenda = new JButton("Nova Venda");
        btnCancelarVenda = new JButton("Cancelar Venda");
        btnGerenciamento = new JButton("Gerenciamento");
        btnHistorico = new JButton("Histórico de Vendas");

        // Design dos botões.
        JButton[] botoesArray = {btnAdicionar, btnFinalizar, btnNovaVenda, btnCancelarVenda, btnGerenciamento, btnHistorico};
        for (JButton btn : botoesArray) {
            btn.setBackground(azul);
            btn.setForeground(Color.WHITE);
            btn.setFont(fonte);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        // centralizar os botões .
        JPanel botoes = new JPanel(new GridLayout(2, 3, 10, 8));
        botoes.setBackground(fundo);
        botoes.add(btnAdicionar);
        botoes.add(btnFinalizar);
        botoes.add(btnNovaVenda);
        botoes.add(btnCancelarVenda);
        botoes.add(btnGerenciamento);
        botoes.add(btnHistorico);

        // Painel criado somente para organização dos itens localizados no lado esquerdo da tela.
        JPanel itemBox = new JPanel(new BorderLayout(5, 5));
        itemBox.setBackground(fundo);
        itemBox.add(item, BorderLayout.CENTER);
        itemBox.add(botoes, BorderLayout.SOUTH);

        //Painel direito do caixa.
        JPanel caixa = new JPanel(new GridLayout(3, 2, 10, 10));
        caixa.setBackground(Color.WHITE);
        caixa.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(azul), "Caixa", 0, 0, fonte, azul));

        txtTotal = new JTextField("0.00");

        //Bloqueia Editação
        txtTotal.setEditable(false);

        txtPago = new JTextField();
        txtTroco = new JTextField();

        //Bloqueia Editação
        txtTroco.setEditable(false);

        //Design.
        txtTotal.setBorder(BorderFactory.createLineBorder(azul));
        txtPago.setBorder(BorderFactory.createLineBorder(azul));
        txtTroco.setBorder(BorderFactory.createLineBorder(azul));

        //Criação dos textos e os seus designs.
        JLabel lbTotal = new JLabel("Total");
        JLabel lbPago = new JLabel("Pago");
        JLabel lbTroco = new JLabel("Troco");
        lbTotal.setForeground(azul);
        lbTotal.setFont(fonte);
        lbPago.setForeground(azul);
        lbPago.setFont(fonte);
        lbTroco.setForeground(azul);
        lbTroco.setFont(fonte);

        caixa.add(lbTotal);
        caixa.add(txtTotal);
        caixa.add(lbPago);
        caixa.add(txtPago);
        caixa.add(lbTroco);
        caixa.add(txtTroco);

        // Cria um painel para organizar os atributos do lado direito.
        JPanel caixaBox = new JPanel(new BorderLayout());
        caixaBox.setBackground(fundo);
        caixaBox.add(caixa, BorderLayout.CENTER);

        //Divisão dos painéis (direita e esquerda) e habilitar os ajustes da tela.
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, itemBox, caixaBox);
        split.setDividerLocation(550);
        split.setResizeWeight(0.5);
        split.setBorder(null);

        // Lista de itens
        areaItens = new JTextArea();
        areaItens.setEditable(false);
        areaItens.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaItens);
        scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(azul), "Itens da Venda", 0, 0, fonte, azul));

        // Adiciona na janela principal.
        add(split, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        //Todos os eventos dos botões.
        btnAdicionar.addActionListener(e -> acaoAdicionarItem());
        btnFinalizar.addActionListener(e -> acaoFinalizarVenda());
        btnNovaVenda.addActionListener(e -> acaoNovaVenda());
        btnCancelarVenda.addActionListener(e -> acaoCancelarVenda());
        btnGerenciamento.addActionListener(e -> acaoAbrirGerenciamento());
        btnHistorico.addActionListener(e -> acaoAbrirHistorico());
    }

    private void acaoAdicionarItem() {
        String codigoDigitado = txtCodigo.getText().trim();
        String qtdDigitada = txtQtd.getText().trim();
        String nomeDigitado = txtNomePesquisa.getText().trim();

        //Aparecer mensagem caso um dos campos esteja vazio.
        if (codigoDigitado.isEmpty() && nomeDigitado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o código OU o nome do produto para buscar.");
            return;
        }

        Produto produtoEncontrado = null;

        //Digitou código busca por código, senão busca pelo nome.
        if (!codigoDigitado.isEmpty()) {
            // puxa os dados na classe banco
            for (Produto p : Banco.listaProdutos) {
                if (p.getCodigo().equals(codigoDigitado)) {
                    produtoEncontrado = p;
                    break;
                }
            }
        } else if (!nomeDigitado.isEmpty()) {
            for (Produto p : Banco.listaProdutos) {
                //Ignoracase serve para não distinguir letras maiúsculas e minúsculas.
                if (p.getNome().equalsIgnoreCase(nomeDigitado) || p.getNome().toLowerCase().contains(nomeDigitado.toLowerCase())) {
                    produtoEncontrado = p;
                    break;
                }
            }
        }

        //caso o produto não seja encontrado.
        if (produtoEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado no estoque!");
            return;
        }

        try {
            int quantidade = Integer.parseInt(qtdDigitada);
            double subtotalItem = produtoEncontrado.getPreco() * quantidade;

            valorTotalVenda += subtotalItem;
            txtTotal.setText(String.format("%.2f", valorTotalVenda));

            // Adiciona na área de texto do cupom fiscal.
            areaItens.append(String.format("%s - %s x%d | R$ %.2f\n",
                    produtoEncontrado.getCodigo(),
                    produtoEncontrado.getNome(),
                    quantidade,
                    subtotalItem));

            // Limpa os campos de busca
            txtCodigo.setText("");
            txtNomePesquisa.setText("");
            txtQtd.setText("1");
            txtCodigo.requestFocus();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade inválida.");
        }
    }

    private void acaoFinalizarVenda() {
        try {               //Todas as possibilidades de tentativa
            if (valorTotalVenda == 0) {
                JOptionPane.showMessageDialog(this, "Não há itens na venda para calcular o troco!");
                return;
            }

            String textoPago = txtPago.getText().trim();
            if (textoPago.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, informe o valor pago pelo cliente.");
                return;
            }

            // Substitui vírgulas por pontos para evitar erro caso digitem "10,00"
            double pago = Double.parseDouble(textoPago.replace(",", "."));

            if (pago < valorTotalVenda) {
                JOptionPane.showMessageDialog(this, "Valor pago é menor que o total da venda!");
                txtTroco.setText("0.00");
                return;
            }

            //LISTAR VENDA.
            String dadosDaVenda = "--- VENDA CONCLUÍDA ---\n" + areaItens.getText() + "TOTAL: R$ " + txtTotal.getText() + "\n=====================\n";

            //BANCO DE DADOS DA VENDA.
            Banco.historicoVendas.add(dadosDaVenda);

            double troco = pago - valorTotalVenda;
            txtTroco.setText(String.format("%.2f", troco));
            JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido no campo 'Pago'. Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void acaoCancelarVenda() {
        if (valorTotalVenda == 0 && areaItens.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não há nenhuma venda em andamento para cancelar.");
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja CANCELAR esta venda?", "Confirmar Cancelamento", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            acaoNovaVenda(); // Reutiliza a limpeza dos campos.
            JOptionPane.showMessageDialog(this, "Venda cancelada e cupom limpo!");
        }
    }

    // Adiciona uma nova venda e limpa os campos.
    private void acaoNovaVenda() {
        //limpa campo da velha venda para a nova.
        vendaAtual = new Venda();
        valorTotalVenda = 0.0;
        txtTotal.setText("0.00");
        txtPago.setText("");
        txtTroco.setText("");
        txtCodigo.setText("");
        txtNomePesquisa.setText("");
        txtQtd.setText("1");
        areaItens.setText("");
        txtCodigo.requestFocus();
    }

    // Botão para direcionar ao gerenciamento.
    private void acaoAbrirGerenciamento() {
        new TelaGerenciamento().setVisible(true);
        dispose();
    }

    // Botão para direcionar ao histórico de vendas.
    private void acaoAbrirHistorico() {
        new TelaHistorico().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        // Execução segura da GUI no Swing
        SwingUtilities.invokeLater(() -> new PDV());
    }
}