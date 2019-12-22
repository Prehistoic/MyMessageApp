import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame implements Observer {

    private MainController controler;
    private boolean alreadyConnected;

    private JPanel users = new JPanel();
    private JPanel login = new JPanel();

    private JLabel labelPseudo = new JLabel("Pseudo :");
    private JButton connect_button = new JButton("Connexion !");
    private JTextField textPseudo = new JTextField();

    private JLabel online_users = new JLabel("Utilisateurs en ligne:");
    private JList<String> user_list = null;

    public LoginWindow(MainController controler, boolean alreadyConnected) {
      this.alreadyConnected = alreadyConnected;
      this.controler = controler;
      this.controler.getModel().addObserver(this);
      this.user_list = new JList<String>(this.controler.getModel().getPseudoList());

      this.showGUI();
    }

    public void addComponentsToPanes() {
        users.setLayout(new BoxLayout(users, BoxLayout.PAGE_AXIS));
        users.add(online_users);
        users.add(user_list);

        login.setLayout(new BoxLayout(login, BoxLayout.PAGE_AXIS));
        login.add(labelPseudo);
        login.add(textPseudo);
        login.add(connect_button);

        connect_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              String pseudo = textPseudo.getText();
              if(!pseudo.equals("")) {
                String[] pseudo_list = controler.getModel().getPseudoList();
                boolean pseudoAlreadyTaken = false;
                for(int i=0; i<pseudo_list.length; i++) {
                  if(pseudo_list[i].equals(pseudo)) {
                    pseudoAlreadyTaken = true;
                  }
                }
                if(!pseudoAlreadyTaken) {
                    if(!alreadyConnected)
                      controler.connect(pseudo);
                    else
                      controler.updatePseudo(pseudo);
                    controler.getModel().removeObserver();
                    dispose();
                    ChatWindow chat = new ChatWindow(controler, pseudo);
                }
                else {
                  JOptionPane.showMessageDialog(null,"Ce pseudo n'est pas disponible pour le moment. Veuillez en choisir un autre !");
                }
              }
              else {
                JOptionPane.showMessageDialog(null,"Vous devez choisir un pseudo non vide !");
              }
            }
        });
    }

    public void showGUI() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
          @Override
          public void windowClosing(java.awt.event.WindowEvent e) {
            controler.disconnect();
            e.getWindow().dispose();
          }
        });

        this.setTitle("MessageApp");
        this.setSize(new Dimension(500, 200));
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        users.setPreferredSize(new Dimension(200, 200));
        login.setPreferredSize(new Dimension(300, 200));
        this.add(users, BorderLayout.LINE_START);
        this.add(login, BorderLayout.LINE_END);

        this.addComponentsToPanes();

        this.pack();
        this.setVisible(true);
    }

    // Implémentation du pattern observer
    public void update(String str) {
    }

}
