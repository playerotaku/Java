import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroPage {
    private JTextField Nome;
    private JTextField Email;
    private JPasswordField Senha1;
    private JPasswordField Senha2;
    public JButton Login;
    private JButton Cadastrar;
    private JPanel PainelCadastroUI;
    private JFrame frame;



    public JPanel criarPainelCadastro(){
        return this.PainelCadastroUI;
    }

    public JButton getBotaoLogin(){
        return this.Login;
    }
    public JButton getBotaoCadastrar(){
        return this.Cadastrar;
    }
    public JTextField getNome(){
        return this.Nome;
    }
    public JPasswordField getSenha(){
        return this.Senha1;
    }
    public JPasswordField getSenha2(){
        return this.Senha2;
    }
    public JTextField getEmail(){
        return this.Email;
    }


}
