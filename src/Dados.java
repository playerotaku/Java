import java.io.*;
import java.util.ArrayList;

public abstract class Dados {
    static String  arquivoClientes = "clientes.ser" ;
    static String arquivoAdmin = "Adms.ser";
    static String arquivoProdutos = "produtos.ser";



    //ADMS
    public static void salvarAdm(String nome,String email,String senha){
        Admin adm = new Admin(obterAdms().size()+1,nome,email,senha);
        try (FileOutputStream fileOut = new FileOutputStream(arquivoAdmin, true);
             ObjectOutputStream out = new ObjectOutputStream(fileOut) {
                 protected void writeStreamHeader() throws IOException {
                     if (fileOut.getChannel().position() == 0) {
                         super.writeStreamHeader();
                     } else {
                         reset();
                     }
                 }
             }) {
            out.writeObject(adm);
            System.out.println("Usuário salvo com sucesso: " + adm.getNome());
        } catch (IOException e) {
            System.out.println("Erro ao salvar o adm: " + e.getMessage());
        }
    }

    private static ArrayList<Admin> obterAdms() {
        ArrayList<Admin> adms = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(arquivoAdmin);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true) {
                try {
                    Admin adm = (Admin) in.readObject();
                    adms.add(adm);
                } catch (EOFException e) {
                    break;
                }
            }
        }  catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
        return adms;
    }

    // Clientes
    public static boolean logar(String email, String senha){
        if (obterClientePorEmail(email).getSenha().equals(senha)) return true;
        return false;
    }

    public static Cliente obterClientePorEmail(String email){
        ArrayList<Cliente> clientes = obterclientes();
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null;
    }

    public static void salvarCliente(Cliente cliente) {
        boolean arquivoExiste = new File(arquivoClientes).exists();

        try (FileOutputStream fileOut = new FileOutputStream(arquivoClientes, true);
             ObjectOutputStream out = arquivoExiste ? new ObjectOutputStream(fileOut) {
                 protected void writeStreamHeader() throws IOException {
                     reset(); // Evita regravar o cabeçalho
                 }
             }
                     : new ObjectOutputStream(fileOut)) { // Escreve o cabeçalho se o arquivo é novo
            out.writeObject(cliente);
            System.out.println("Cliente salvo com sucesso: " + cliente.getNome());
        } catch (IOException e) {
            System.out.println("Erro ao salvar o cliente: " + e.getMessage());
        }
    }

    public static void salvarCliente(String nome,String email,String senha) {
        Cliente cliente = new Cliente(obterclientes().size()+1,nome,email,senha);
        salvarCliente(cliente);
    }

    private static void salvarclientes(ArrayList<Cliente> clientes) {
        try (FileOutputStream fileOut = new FileOutputStream(arquivoClientes);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(clientes);
            System.out.println("clientes atualizados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    public static ArrayList<Cliente> obterclientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(arquivoClientes);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true){

                try{
                    Cliente cliente = (Cliente) in.readObject();
                    clientes.add(cliente);
                }catch (EOFException e) {
                    break;
                }
            }
        }  catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    public static Cliente obterclientePorId(int Id) {
        ArrayList<Cliente> clientes = obterclientes();
        for (Cliente cliente : clientes) {
            if (cliente.getId()==Id) {
                return cliente;
            }
        }
        return null;
    }

    public static void atualizarcliente(int id, Cliente clienteAtualizado) {
        ArrayList<Cliente> clientes = obterclientes();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                clientes.set(i, clienteAtualizado);
                break;
            }
        }
        salvarclientes(clientes);
    }

    public static void deletarcliente(int Id) {
        ArrayList<Cliente> clientes = obterclientes();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId()==Id) {
                clientes.remove(i);
                System.out.println("Cliente: " +clientes.get(i).getNome() + "removido com sucesso.");
            }
        }
        salvarclientes(clientes);
    }

    //PRODUTOS


    public static void salvarProduto(Produto produto) {
        try (FileOutputStream fileOut = new FileOutputStream(arquivoProdutos, true);
             ObjectOutputStream out = new ObjectOutputStream(fileOut) {
                 protected void writeStreamHeader() throws IOException {
                     if (fileOut.getChannel().position() == 0) {
                         super.writeStreamHeader();
                     } else {
                         reset();
                     }
                 }
             }) {
            out.writeObject(produto);
            System.out.println("Produto salvo com sucesso: " + produto.getNome());
        } catch (IOException e) {
            System.out.println("Erro ao salvar o Produto: " + e.getMessage());
        }
    }

    private static void salvarProdutos(ArrayList<Produto> produtos) {
        try (FileOutputStream fileOut = new FileOutputStream(arquivoProdutos);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(produtos); // Grava a lista inteira novamente
            System.out.println("produtoos atualizados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public static ArrayList<Produto> obterProdutos() {
        ArrayList<Produto> produtos = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(arquivoProdutos);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            while (true) {
                try {
                    Produto produto = (Produto) in.readObject();
                    produtos.add(produto);
                }catch (EOFException e) {
                    break;
                }
            }
        }catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }
        return produtos;
    }


    public static Produto obterProdutoPorId(int id) {
        ArrayList<Produto> produtos = obterProdutos();
        for (Produto prod : produtos) {
            if (prod.getId() == id) {
                return prod;
            }
        }
        return null;
    }

    public static void atualizarProduto(int id, Produto prodAtualizado) {
        ArrayList<Produto> produtos = obterProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                produtos.set(i, prodAtualizado);
                break;
            }
        }
        salvarProdutos(produtos);
    }

    public static void deletarProduto(int id) {
        ArrayList<Produto> produtos = obterProdutos();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                produtos.remove(i);
            }
        }
        salvarProdutos(produtos);
    }


//    CARRINHO
    public static Carrinho getCarrinho(int id){
        Cliente c = Dados.obterclientePorId(id);
        return c.getCarrinho();
    }

    public static void DeletarItemCarrinho(int idCliente,int idProduto){
        Cliente c = Dados.obterclientePorId(idCliente);
        c.getCarrinho().deletaItemCarrinho(Dados.obterProdutoPorId(idProduto));
        atualizarcliente(idCliente,c);

    }

}
