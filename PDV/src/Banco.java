import java.util.ArrayList;
//criei essa classe para armazenar produtos do estoque e puxalos do estoque para o caixa quando digitado o código.
public class Banco {
    public static ArrayList<Produto> listaProdutos = new ArrayList<>();

    //criada para listar as vendas finalizadas.
    public static ArrayList<String> historicoVendas = new ArrayList<>();
}
