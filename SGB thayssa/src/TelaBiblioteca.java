import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaBiblioteca extends JFrame {

    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAno;

    private JTextArea areaLivros;

    private ArrayList<String> livros;

    public TelaBiblioteca() {

        livros = new ArrayList<>();

        //Criação da janela
        setTitle("Biblioteca");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("BIBLIOTECA");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.BLUE);

        // Painel dos campos
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 2, 5, 5));

        painel.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        painel.add(txtCodigo);

        painel.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        painel.add(txtTitulo);

        painel.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        painel.add(txtAutor);

        painel.add(new JLabel("Ano:"));
        txtAno = new JTextField();
        painel.add(txtAno);

        //botões
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnListar = new JButton("Listar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnRemover = new JButton("Remover");

        painel.add(btnCadastrar);
        painel.add(btnListar);
        painel.add(btnBuscar);
        painel.add(btnRemover);

        // Painel de cima
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.add(titulo, BorderLayout.NORTH);
        painelSuperior.add(painel, BorderLayout.CENTER);

        add(painelSuperior, BorderLayout.NORTH);

        // Área
        areaLivros = new JTextArea();
        areaLivros.setEditable(false);
        areaLivros.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaLivros);
        scroll.setBorder(
                BorderFactory.createTitledBorder("Livros Cadastrados")
        );

        add(scroll, BorderLayout.CENTER);

        // Evento do botão cadastrar
        btnCadastrar.addActionListener(e -> {

            String livro =
                    "Código: " + txtCodigo.getText()
                            + " | Título: " + txtTitulo.getText()
                            + " | Autor: " + txtAutor.getText()
                            + " | Ano: " + txtAno.getText();

            livros.add(livro);

            JOptionPane.showMessageDialog(
                    null,
                    "Livro cadastrado com sucesso!"
            );

            txtCodigo.setText("");
            txtTitulo.setText("");
            txtAutor.setText("");
            txtAno.setText("");
        });

        // Evento do botão listar
        btnListar.addActionListener(e -> {

            areaLivros.setText("");

            if (livros.isEmpty()) {
                areaLivros.setText("Nenhum livro cadastrado.");
            } else {
                for (String livro : livros) {
                    areaLivros.append(livro + "\n");
                }
            }
        });

        // Evento do botão Buscar
        btnBuscar.addActionListener(e -> {

            String tituloBusca = JOptionPane.showInputDialog(
                    "Digite o título do livro:"
            );

            boolean encontrado = false;

            for (String livro : livros) {

                if (livro.toLowerCase()
                        .contains(tituloBusca.toLowerCase())) {

                    JOptionPane.showMessageDialog(
                            null,
                            livro
                    );

                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(
                        null,
                        "Livro não encontrado."
                );
            }

        });

        // Evento do botão remover
        btnRemover.addActionListener(e -> {

            String tituloRemover = JOptionPane.showInputDialog(
                    "Digite o título do livro:"
            );

            for (int i = 0; i < livros.size(); i++) {

                if (livros.get(i).toLowerCase()
                        .contains(tituloRemover.toLowerCase())) {

                    livros.remove(i);

                    JOptionPane.showMessageDialog(
                            null,
                            "Livro removido."
                    );

                    return;
                }
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Livro não encontrado."
            );

        });
    }

    public static void main(String[] args) {

        TelaBiblioteca tela = new TelaBiblioteca();
        tela.setVisible(true);

    }
}