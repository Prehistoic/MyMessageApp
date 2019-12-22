import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.InetAddress;

public class ChatWindow extends JFrame implements Observer {

    private MainController controler;

    private JPanel users = new JPanel();
    private JPanel chat = new JPanel();

    private JLabel online_users = new JLabel("Utilisateurs en ligne:");
    private JList<String> user_list = null;
    private JTabbedPane history = new JTabbedPane();
    private JLabel current_pseudo = null;
    private JButton change_pseudo_button = new JButton("Changer de pseudo");

    public ChatWindow(MainController controler, String current_pseudo) {
        this.controler = controler;
        this.controler.getModel().addObserver(this);
        this.user_list = new JList<String>(this.controler.getModel().getPseudoList());
        this.current_pseudo = new JLabel("Connecté en tant que "+current_pseudo);
        this.showGUI();
    }

    public void addComponentsToPanes() {
      users.setLayout(new BoxLayout(users, BoxLayout.PAGE_AXIS));
      users.add(current_pseudo);
      users.add(change_pseudo_button);
      users.add(online_users);
      users.add(user_list);

      user_list.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          String selectedItem = (String) user_list.getSelectedValue();
          boolean alreadyExists = false;
          for(int i = 0; i<history.getTabCount(); i++) {
            if(history.getTitleAt(i).equals(selectedItem)) {
              alreadyExists = true;
            }
          }
          if(!alreadyExists) {
            InetAddress tab_ip = controler.getModel().getIpFromPseudo(selectedItem);
            UserTabPane historyPane = new UserTabPane(history,controler,tab_ip);
            history.addTab(selectedItem,historyPane);
            history.setTabComponentAt(history.getTabCount()-1,new ButtonTabComponent(history));
            history.setSelectedIndex(history.getTabCount()-1);
          }
        }
      });

      change_pseudo_button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            controler.getModel().removeObserver();
            controler.disconnect();
            dispose();
            LoginWindow login = new LoginWindow(controler,true);
          }
      });

      chat.setLayout(new BoxLayout(chat, BoxLayout.PAGE_AXIS));
      chat.add(history);

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
        this.setSize(new Dimension(1000, 500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());
        users.setPreferredSize(new Dimension(300, 500));
        chat.setPreferredSize(new Dimension(700, 500));
        this.add(users, BorderLayout.LINE_START);
        this.add(chat, BorderLayout.LINE_END);

        this.addComponentsToPanes();

        this.pack();
        this.setVisible(true);
    }

    // Implémentation du pattern observer
    public void update(String str) {
      System.out.println(str);
      if(str.equals("new_user_online") || str.equals("new_user_offline")) {
        this.user_list = null;
        this.user_list = new JList<String>(this.controler.getModel().getPseudoList());
        this.users.repaint();
      }
      else if(str.contains("new_message_to_")) {
        String tmp = str.replace("new_message_to_","");
        tmp = tmp.replace("_",".");
        try {
          InetAddress tmp_address = InetAddress.getByName(tmp);
          for(int i=0; i<history.getTabCount(); i++) {
            UserTabPane tab = (UserTabPane) history.getComponentAt(i);
            if(tab.getUserIp().getHostAddress().equals(tmp)) {
              UserTabPane new_tab = new UserTabPane(history, controler, tmp_address);
              history.setComponentAt(i,new_tab);
            }
          }
        }
        catch (Exception e) {
          System.err.println(e.getClass().getName()+":"+e.getMessage());
          System.exit(0);
        }
      }
      else if(str.contains("new_message_from_")) {
        String tmp = str.replace("new_message_from_","");
        tmp = tmp.replace("_",".");
        try {
          InetAddress tmp_address = InetAddress.getByName(tmp);
          for(int i=0; i<history.getTabCount(); i++) {
            UserTabPane tab = (UserTabPane) history.getComponentAt(i);
            if(tab.getUserIp().getHostAddress().equals(tmp)) {
              UserTabPane new_tab = new UserTabPane(history, controler, tmp_address);
              history.setComponentAt(i,new_tab);
            }
          }
        }
        catch (Exception e) {
          System.err.println(e.getClass().getName()+":"+e.getMessage());
          System.exit(0);
        }
      }
    }


}
