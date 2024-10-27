import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


// ABA DO CARDAPIO
public class ProdutoGUI {

    public static void main(String[] args) {
        Admin.cadastrarProduto("cafe","Cafe com depreSsJava", 19.99);
        Admin.cadastrarProduto("cafe1","Cafe com depreSsJava1", 199.99);
        Admin.cadastrarProduto("cafe2","Cafe com depreSsJava2", 1999.99);
        Admin.cadastrarProduto("cafe3","Cafe com depreSsJava3", 19999.99);



        // Cria a interface gráfica
        SwingUtilities.invokeLater(() -> criarInterface(Dados.obterProdutos()));
    }

    public static void criarInterface(ArrayList<Produto> Produtos) {
        // Janela principal
        JFrame frame = new JFrame("Gestão de Produtoss");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Painel para os cards dos Produtoss
        JPanel panelProdutoss = new JPanel();
        panelProdutoss.setLayout(new GridLayout(0, 2, 10, 10)); // Layout de grade com 2 colunas e espaço entre cards
        panelProdutoss.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Cria um card para cada Produtos
        for (Produto Prod : Produtos) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setBackground(Color.WHITE);
            card.setPreferredSize(new Dimension(250, 150));

            // Informações do Produtos
            JLabel nomeLabel = new JLabel("Nome: " + Prod.getNome());
            JLabel descricaoLabel = new JLabel("<html><body style='width: 200px'>Descrição: " + Prod.getDescricao() + "</body></html>");
            JLabel precoLabel = new JLabel("Preço: R$ " + Prod.getPreco());

            // Botão de compra
            JButton comprarButton = new JButton("Comprar");
            comprarButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Produtos comprado: " + Prod.getNome()));

            // Painel para os detalhes do Produtos
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(nomeLabel);
            infoPanel.add(descricaoLabel);
            infoPanel.add(precoLabel);

            // Adiciona os componentes ao card
            card.add(infoPanel, BorderLayout.CENTER);
            card.add(comprarButton, BorderLayout.SOUTH);

            // Adiciona o card ao painel principal
            panelProdutoss.add(card);
        }

        // Adiciona o painel de Produtoss à janela com rolagem
        JScrollPane scrollPane = new JScrollPane(panelProdutoss);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
}




// ABA DO CARRINHO
//public class ProdutoGUI {
//
//    public static void main(String[] args) {
//        // Lista de produtos
//        ArrayList<Produtos> listaDeProdutos = new ArrayList<>();
//        listaDeProdutos.add(new Produtos("Produto A", "Descrição A", 10.99));
//        listaDeProdutos.add(new Produtos("Produto B", "Descrição B", 20.50));
//        listaDeProdutos.add(new Produtos("Produto C", "Descrição C", 15.75));
//
//        // Cria a interface gráfica
//        SwingUtilities.invokeLater(() -> criarInterface(listaDeProdutos));
//    }
//
//    public static void criarInterface(ArrayList<Produtos> produtos) {
//        // Janela principal
//        JFrame frame = new JFrame("Gestão de Produtos");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(600, 400);
//
//        // Painel de abas
//        JTabbedPane tabbedPane = new JTabbedPane();
//
//        // Tabela de produtos
//        String[] colunas = {"Nome", "Descrição", "Preço"};
//        DefaultTableModel model = new DefaultTableModel(colunas, 0);
//        JTable tabelaProdutos = new JTable(model);
//
//        // Preenche a tabela com os dados dos produtos
//        for (Produtos produto : produtos) {
//            Object[] rowData = {produto.getNome(), produto.getDescricao(), produto.getPreco()};
//            model.addRow(rowData);
//        }
//
//        // Adiciona a tabela em um painel com barra de rolagem
//        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
//        JPanel panelProdutos = new JPanel(new BorderLayout());
//        panelProdutos.add(scrollPane, BorderLayout.CENTER);
//
//        // Adiciona a aba ao painel de abas
//        tabbedPane.addTab("Lista de Produtos", panelProdutos);
//
//        // Adiciona o painel de abas à janela
//        frame.add(tabbedPane);
//        frame.setVisible(true);
//    }
//}
