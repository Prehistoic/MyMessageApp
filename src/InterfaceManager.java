import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfaceManager implements Observer {

  /*private static String LOOKANDFEEL = "GTK+";

  protected MainController controller;

  public InterfaceManager(MainController controller) {
      this.controller = controller;
  }

  public void addComponentsToPanes() {
      users.setLayout(new BoxLayout(users, BoxLayout.PAGE_AXIS));
      users.add(online_users);

      login.setLayout(new BoxLayout(login, BoxLayout.PAGE_AXIS));
      login.add(labelPseudo);
      login.add(textPseudo);
      login.add(button);

      button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String pseudo = this.textPseudo.getText();
            this.dispose();
            ChatWindow chat = new ChatWindow(this.controler, pseudo);
            this.controler.getModel().removeObserver();
            this.controler.getModel().addObserver(chat);
            chat.setVisible(true);
          }
      });
  }

  public static void show() {
      initLookAndFeel();
      JFrame.setDefaultLookAndFeelDecorated(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

  private static void initLookAndFeel() {
      String lookAndFeel = null;

      if(LOOKANDFEEL != null) {
          if(LOOKANDFEEL.equals("Metal")) {
              lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
          }
          else if (LOOKANDFEEL.equals("System")) {
              lookAndFeel = UIManager.getSystemLookAndFeelClassName();
          }
          else if (LOOKANDFEEL.equals("Motif")) {
              lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
          }
          else if (LOOKANDFEEL.equals("GTK+")) {
              lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
          }
          else {
              System.err.println("Unexpected value of LOOKANDFEEL specified: " + LOOKANDFEEL);
              lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
          }

          try {
              UIManager.setLookAndFeel(lookAndFeel);
          } catch (ClassNotFoundException e) {
              System.err.println("Couldn't find class for specified look and feel:"
                      + lookAndFeel);
              System.err.println("Did you include the L&F library in the class path?");
              System.err.println("Using the default look and feel.");
          } catch (UnsupportedLookAndFeelException e) {
              System.err.println("Can't use the specified look and feel ("
                      + lookAndFeel
                      + ") on this platform.");
              System.err.println("Using the default look and feel.");
          } catch (Exception e) {
              System.err.println("Couldn't get specified look and feel ("
                      + lookAndFeel
                      + "), for some reason.");
              System.err.println("Using the default look and feel.");
              e.printStackTrace();
          }
      }
    }*/

    // Implémentation du pattern observer
    public void update(String str) {
    }

}
