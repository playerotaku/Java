import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private JPanel panel1;
    private JLabel Loginlabel;
    private JTextField Email;
    private JPasswordField Senha;
    private JButton Admin;
    private JButton Cadastrar;
    private JButton BotaoEntrar;
    private JPanel PainelLogin;

    public LoginPage() {
        BotaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = Email.getText();
                String senha = Senha.getText();

                if(Dados.logar(email, senha)){

                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
