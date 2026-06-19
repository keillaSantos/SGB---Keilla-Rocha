import javax.swing.*;
import java.awt.*;
import java.time.LocalDate; // import que será usado no evento do botão emprestar
import java.time.format.DateTimeFormatter;

public class TelaEmprestimo extends JFrame {

    private LocalDate dataDevolucao;


    public TelaEmprestimo() {

        //Criação da janela
        setTitle("Empréstimos");
        setSize(600, 500);
        setLocationRelativeTo(null);


        // Titulo, borda e os seus designs
        JLabel titulo = new JLabel("EMPRÉSTIMOS", SwingConstants.CENTER);
        titulo.setOpaque(true);
        titulo.setBackground(new Color(0, 102, 204));
        titulo.setForeground(Color.WHITE);
        titulo.setPreferredSize(new Dimension(600, 50));
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 2, 10, 10));
        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );


        //Atributos
        JTextField txtTitulo = new JTextField();
        JTextField txtAutor = new JTextField();
        JTextField txtAno = new JTextField();
        JTextField txtPessoa = new JTextField();
        JTextField txtData = new JTextField();


        //Criação dos espaços para abrigar os atributos
        painel.add(new JLabel("==== Título ===="));
        painel.add(txtTitulo);

        painel.add(new JLabel("| Autor"));
        painel.add(txtAutor);

        painel.add(new JLabel("| Ano"));
        painel.add(txtAno);

        painel.add(new JLabel("| Nome do Cliente"));
        painel.add(txtPessoa);

        painel.add(new JLabel("| Data do Empréstimo"));
        painel.add(txtData);


        //Botões e os seus designs
        JButton btnEmprestar = new JButton("Emprestar");
        JButton btnDevolver = new JButton("Devolver");
        btnEmprestar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnDevolver.setFont(new Font("Segoe UI", Font.BOLD, 15));

        Color azul = new Color(0, 102, 204);

        btnEmprestar.setBackground(azul);
        btnEmprestar.setForeground(Color.WHITE);

        btnDevolver.setBackground(azul);
        btnDevolver.setForeground(Color.WHITE);

        btnEmprestar.setFocusPainted(false);
        btnDevolver.setFocusPainted(false);


        painel.add(btnEmprestar);
        painel.add(btnDevolver);

        setLayout(new BorderLayout());

        add(titulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);


        //Evento dos botões

        //Implementei nesse botao para quando  pessoa preencher com as informações do livro a messagem final der uma data de validade completa não somente o prazo de ate 30 dias
        btnEmprestar.addActionListener(e -> {


            //Compara a data digitada com a data de hoje
            LocalDate dataEmprestimo = LocalDate.now();
            //Adiciona a data digitada + 30 dias
            dataDevolucao = dataEmprestimo.plusDays(30);

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");


            //Mensagem que aparece após o botão emprestar
            JOptionPane.showMessageDialog(
                    this,
                    "Livro emprestado com sucesso!\n\n" +
                            "Data do empréstimo: " +
                            dataEmprestimo.format(formato) + "\n" +
                            "Válido até: " +
                            dataDevolucao.format(formato)


            );
        });

       //Nesse botao se o prazo de entrega não for comprimido aparece uma menssagem e exibi uma multa de 15 reais, caso não aparece dizendo que o livro foi devolvido dentro do prazo.
        btnDevolver.addActionListener(e -> {

            LocalDate hoje = LocalDate.now();

            if (dataDevolucao == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Nenhum livro foi emprestado!"
                );
                return;
            }

            if (hoje.isAfter(dataDevolucao)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Livro devolvido com atraso!\n" +
                                "Taxa de atraso: R$ 15,00"
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Livro devolvido dentro do prazo!\n" +
                                "Nenhuma taxa cobrada."
                );
            }
        });


    }
}


