import javax.swing.*;
import java.awt.*;
public class TelaBiblioteca extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAno;

    private JTextArea areaLivros;

    private Biblioteca biblioteca;

    //Criação da janela
    public TelaBiblioteca() {

        biblioteca = new Biblioteca();

        setTitle("Sistema Gerencial de Biblioteca");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);




        //Titulo blibioteca
        JLabel titulo = new JLabel("BIBLIOTECA", SwingConstants.CENTER);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(0, 102, 204));
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));


        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 2, 10, 10));

        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



        // Atributos dos livros e a criação dos seus campos
        painel.add(new JLabel("Código"));
        txtCodigo = new JTextField();
        painel.add(txtCodigo);

        painel.add(new JLabel("Título"));
        txtTitulo = new JTextField();
        painel.add(txtTitulo);

        painel.add(new JLabel("Autor"));
        txtAutor = new JTextField();
        painel.add(txtAutor);

        painel.add(new JLabel("Ano"));
        txtAno = new JTextField();
        painel.add(txtAno);



        //todos os botões
        JButton btnCadastrar = new JButton("Cadastrar Livro");
        JButton btnListar = new JButton("Listar Livros");
        JButton btnBuscar = new JButton("Buscar Livros");
        JButton btnEmprestimo = new JButton("→ Aba de Empréstimos");
        JButton btnRemover = new JButton("Remover Livro");




        //add botões
        painel.add(btnCadastrar);
        painel.add(btnListar);

        painel.add(btnBuscar);
        painel.add(btnRemover);

        painel.add(btnEmprestimo);
        painel.add(new JLabel(""));



        // design dos botoes
        btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnListar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRemover.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEmprestimo.setFont(new Font("Segoe UI", Font.BOLD, 12));

        //BORDA DOS BOTOES
        btnCadastrar.setFocusPainted(false);
        btnListar.setFocusPainted(false);
        btnBuscar.setFocusPainted(false);
        btnRemover.setFocusPainted(false);
        btnEmprestimo.setFocusPainted(false);



        //area do texto
        areaLivros = new JTextArea();
        areaLivros.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaLivros);



        //painel
        JPanel topo = new JPanel(new BorderLayout());

        topo.add(titulo, BorderLayout.NORTH);
        topo.add(painel, BorderLayout.CENTER);

        add(topo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);



        //chamar as informações dos botões
        btnCadastrar.addActionListener(e -> cadastrarLivro());

        btnListar.addActionListener(e -> listarLivros());

        btnBuscar.addActionListener(e -> Buscarlivro());

        btnRemover.addActionListener(e -> removerLivro());


        btnEmprestimo.addActionListener(e -> {
            TelaEmprestimo tela = new TelaEmprestimo();
            tela.setVisible(true);
        });

    }

    // VOIDS
    private void cadastrarLivro() {

        int codigo = Integer.parseInt(txtCodigo.getText());
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        int ano = Integer.parseInt(txtAno.getText());

        Livro livro = new Livro(
                codigo,
                titulo,
                autor,
                ano
        );

        biblioteca.adicionarLivro(livro);

        JOptionPane.showMessageDialog(
                this,
                "Livro cadastrado com sucesso!"
        );

        limparCampos();
    }

    private void listarLivros() {

        areaLivros.setText("");

        areaLivros.append("Total de livros cadastrados: "
                + biblioteca.getLivros().size() + "\n\n");

        for (Livro livro : biblioteca.getLivros()) {
            areaLivros.append(livro + "\n");
        }
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAno.setText("");
    }

    private void Buscarlivro() {

        String busca = JOptionPane.showInputDialog(
                this,
                "Informe o título do livro:"
        );

        boolean encontrado = false;

        for (Livro livro : biblioteca.getLivros()) {

            if (livro.getTitulo().equalsIgnoreCase(busca)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Livro encontrado!\n\n" +
                                "Código: " + livro.getCodigo() + "\n" +
                                "Título: " + livro.getTitulo() + "\n" +
                                "Autor: " + livro.getAutor() + "\n" +
                                "Ano: " + livro.getAno()
                );

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(
                    this,
                    "Livro não encontrado!"
            );
        }

    }

    private void removerLivro() {

        String busca = JOptionPane.showInputDialog(
                this,
                "Informe o título do livro para remover:"
        );

        Livro remover = null;

        for (Livro livro : biblioteca.getLivros()) {

            if (livro.getTitulo().equalsIgnoreCase(busca)) {
                remover = livro;
                break;
            }
        }

        if (remover != null) {

            biblioteca.getLivros().remove(remover);

            JOptionPane.showMessageDialog(
                    this,
                    "Livro removido com sucesso!"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Livro não encontrado!"
            );
        }
    }

}