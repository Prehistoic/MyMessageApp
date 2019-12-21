import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserTabPane extends JPanel {
  private final JTabbedPane containerPane;
  private final MainController controler;

  private JPanel msgPane = new JPanel();
  private JButton send_button = new JButton("Envoyer");
  private JTextField msg_to_send = new JTextField();

  public UserTabPane(final JTabbedPane containerPane, final MainController controler) {
    super(new FlowLayout(FlowLayout.LEFT, 0, 0));
    this.containerPane = containerPane;
    this.controler = controler;
    this.setLayout(new BorderLayout());
    this.add(msgPane, BorderLayout.PAGE_END);

    msgPane.setLayout(new BorderLayout());
    msgPane.add(send_button, BorderLayout.LINE_END);
    msgPane.add(msg_to_send, BorderLayout.LINE_START);

    send_button.addActionListener(new ActionListener () {
      public void actionPerformed(ActionEvent e) {
        String msg = msg_to_send.getText();
        if(!msg.equals("")) {
          msg_to_send.setText("");
          String dest_pseudo = containerPane.getTitleAt(containerPane.getSelectedIndex());
          controler.sendMessage(msg.getBytes(),dest_pseudo);
        }
      }
    });
  }

}
