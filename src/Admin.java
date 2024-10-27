public class Admin extends Usuario{

    public Admin(int idUsuario,String nome,String email,String senha ){
        super(idUsuario,nome,email,senha);
    }

    //Produto
    public static void cadastrarProduto(String nome,String descricao,double preco){
        Dados.salvarProduto(new Produto(Dados.obterProdutos().size()+1,nome,descricao,preco));
    }


    public void atualizaPrecoProduto(int id, double preco){
        Produto precoAntigo = Dados.obterProdutoPorId(id);
        precoAntigo.setPreco(preco);
        Dados.atualizarProduto(id, precoAntigo);
    }


    //Cliente
    public void cadastrarCliente(String nome,String email,String senha){
        Dados.salvarCliente(nome,email,senha);
    }

    public void atualizaCliente(int idCliente, String senha){
        Cliente usuarioAntigo = Dados.obterclientePorId(idCliente);
        Cliente novoUsuario = new Cliente(idCliente,usuarioAntigo.getNome(),usuarioAntigo.getEmail(),senha);
        Dados.atualizarcliente(idCliente,novoUsuario);
    }

    public void atualizaCliente(int idCliente, String email, String senha){
        Cliente usuarioAntigo = Dados.obterclientePorId(idCliente);
        Cliente novoUsuario = new Cliente(idCliente,usuarioAntigo.getNome(),email,senha);
        Dados.atualizarcliente(idCliente,novoUsuario);
    }

    public void atualizaCliente(int idCliente, String nome, String email, String senha){
        Cliente usuarioAntigo = Dados.obterclientePorId(idCliente);
        Cliente novoUsuario = new Cliente(idCliente,nome,email,senha);
        Dados.atualizarcliente(idCliente,novoUsuario);

    }

}
