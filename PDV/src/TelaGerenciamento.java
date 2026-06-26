import javax.swing.*;
import java.awt.*;

public class TelaGerenciamento extends JFrame {

    public TelaGerenciamento() {

        // Criação da janela
        setTitle("Gerenciamento de Produtos");
        setSize(650, 430); // 💡 Aumentei um pouquinho o tamanho para acomodar o novo botão
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Design
        Color azul = new Color(0, 102, 204);
        Color fundo = new Color(245, 245, 245);
        Font fonte = new Font("Segoe UI", Font.BOLD, 13);

        // Criação do painel que irá abrigar os atributos
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBackground(Color.WHITE);
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Atributos
        JLabel lbCodigo = new JLabel("Código:");
        JLabel lbNome = new JLabel("Nome:");
        JLabel lbPreco = new JLabel("Preço:");
        JLabel lbEstoque = new JLabel("Estoque:");

        // Design dos atributos
        lbCodigo.setForeground(azul); lbCodigo.setFont(fonte);
        lbNome.setForeground(azul); lbNome.setFont(fonte);
        lbPreco.setForeground(azul); lbPreco.setFont(fonte);
        lbEstoque.setForeground(azul); lbEstoque.setFont(fonte);

        // Criação do campo dos atributos
        JTextField txtCodigo = new JTextField();
        JTextField txtNome = new JTextField();
        JTextField txtPreco = new JTextField();
        JTextField txtEstoque = new JTextField();

        // Borda nos campos
        txtCodigo.setBorder(BorderFactory.createLineBorder(azul));
        txtNome.setBorder(BorderFactory.createLineBorder(azul));
        txtPreco.setBorder(BorderFactory.createLineBorder(azul));
        txtEstoque.setBorder(BorderFactory.createLineBorder(azul));

        // Adicionar ao painel
        painel.add(lbCodigo); painel.add(txtCodigo);
        painel.add(lbNome); painel.add(txtNome);
        painel.add(lbPreco); painel.add(txtPreco);
        painel.add(lbEstoque); painel.add(txtEstoque);

        // Botão salvar produto
        JButton btnSalvar = new JButton("Salvar Produto");

        // Botão ir para o pdv
        JButton btnIrPDV = new JButton("Ir para PDV");

        //Botão estoque
        JButton btnChecarEstoque = new JButton("Checar Estoque");

        // Design e padronização de todos os botões
        JButton[] botoesArray = {btnSalvar, btnIrPDV, btnChecarEstoque};
        for (JButton btn : botoesArray) {
            btn.setBackground(azul);
            btn.setForeground(Color.WHITE);
            btn.setFont(fonte);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        // Evento do botão Salvar
        btnSalvar.addActionListener(e -> {
            try {
                // Dentro do evento do botão salvar do Gerenciamento:
                String codigo = txtCodigo.getText().trim();
                String nome = txtNome.getText().trim();
                double preco = Double.parseDouble(txtPreco.getText().replace(",", "."));
                int estoque = Integer.parseInt(txtEstoque.getText().trim());

                if(codigo.isEmpty() || nome.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios.");
                    return;
                }

                Produto novoProduto = new Produto(codigo, nome, preco, estoque);

                // Adiciona na sua classe Banco
                Banco.listaProdutos.add(novoProduto);
                JOptionPane.showMessageDialog(this, "Produto cadastrado no estoque!");

                // Limpa os campos
                txtCodigo.setText("");
                txtNome.setText("");
                txtPreco.setText("");
                txtEstoque.setText("");
                txtCodigo.requestFocus();

                System.out.println("Total de produtos: " + Banco.listaProdutos.size());

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para preço e estoque.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento do botão Ir para o PDV
        btnIrPDV.addActionListener(e -> {
            new PDV().setVisible(true);
            dispose();
        });

       //evento do botão estoque
        btnChecarEstoque.addActionListener(e -> {
            new TelaEstoque().setVisible(true);
            dispose();
        });

        // Design do painel de botões (Adicionado o novo botão no alinhamento)
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        painelBotoes.setBackground(fundo);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnChecarEstoque); // Coloquei ele no meio para ficar esteticamente bonito
        painelBotoes.add(btnIrPDV);

        // Adiciona os componentes à tela
        add(painel, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        getContentPane().setBackground(fundo);
        setVisible(true);
    }

    //Coloquei isso só para ficar um pouquinho mais profissional.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaGerenciamento());
    }
}