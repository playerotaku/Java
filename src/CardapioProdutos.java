import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class CardapioProdutos {
    private JPanel panelPrincipal;
    private JPanel panelProdutos;
    private JButton botaoVoltar;
    private JPanel topPanel;
    private ArrayList<Produto> produtos;

    public CardapioProdutos(Consumer<Produto> onComprarProduto) {
        carregaPainelPrincipal(onComprarProduto);
    }

    private void carregaPainelPrincipal(Consumer<Produto> onComprarProduto) {
        panelPrincipal = new JPanel(new BorderLayout());

        // Configuração do painel do botão "Voltar"
        botaoVoltar = new JButton("Voltar");
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Alinha o botão à esquerda
        topPanel.add(botaoVoltar);
        topPanel.setPreferredSize(new Dimension(100, 60));

        // Adiciona o painel do botão "Voltar" ao topo do painel principal
        panelPrincipal.add(topPanel, BorderLayout.NORTH);

        // Configuração do painel de produtos
        panelProdutos = new JPanel();
        panelProdutos.setLayout(new GridLayout(0, 2, 10, 10)); // Grid com duas colunas e espaçamento entre os cards
        panelProdutos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Recupera e carrega os produtos em cards
        recuperaProdutos();
        carregarProdutos(produtos, onComprarProduto);

        // Adiciona o painel de produtos dentro de um JScrollPane e o insere no panelPrincipal
        JScrollPane scrollPane = new JScrollPane(panelProdutos);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
    }

    public void atualizarProdutos() {
        produtos = Dados.obterProdutos();

        panelProdutos.removeAll();

        carregarProdutos(produtos, produto -> {
            JOptionPane.showMessageDialog(panelPrincipal, "Produto " + produto.getNome() + " adicionado ao carrinho.");
        });

        panelProdutos.revalidate();
        panelProdutos.repaint();

    }

    private void recuperaProdutos() {
        produtos = Dados.obterProdutos();
    }

    private void carregarProdutos(ArrayList<Produto> produtos, Consumer<Produto> onComprarProduto) {
        for (Produto produto : produtos) {
            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            card.setPreferredSize(new Dimension(250, 150));

            JLabel nomeLabel = new JLabel(produto.getNome());
            JLabel descricaoLabel = new JLabel("<html><body style='width: 200px'>" + produto.getDescricao() + "</body></html>");
            JLabel precoLabel = new JLabel(String.format("Preço: R$ %.2f", produto.getPreco()));

            JButton comprarButton = new JButton("Comprar");
            comprarButton.addActionListener(e -> onComprarProduto.accept(produto));

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(nomeLabel);
            infoPanel.add(descricaoLabel);
            infoPanel.add(precoLabel);

            card.add(infoPanel, BorderLayout.CENTER);
            card.add(comprarButton, BorderLayout.SOUTH);

            panelProdutos.add(card);
        }
    }

    public JPanel getPanel() {
        return panelPrincipal;
    }

    public JButton getBotaoVoltar() {
        return botaoVoltar;
    }
}
