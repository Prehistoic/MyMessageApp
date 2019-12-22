import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.ArrayList;

public class UserTabPane extends JPanel {
  private final JTabbedPane containerPane;
  private final MainController controler;
  private InetAddress user_ip;

  private JPanel historyPane = new JPanel();
  private ArrayList<MessageHistory> history = new ArrayList<MessageHistory>();

  private JPanel msgPane = new JPanel();
  private JButton send_button = new JButton("Envoyer");
  private JTextField msg_to_send = new JTextField();

  public UserTabPane(final JTabbedPane containerPane, final MainController controler, InetAddress user_ip) {
    super(new FlowLayout(FlowLayout.LEFT, 0, 0));
    this.containerPane = containerPane;
    this.controler = controler;
    this.user_ip = user_ip;
    this.setLayout(new BorderLayout());
    this.add(historyPane, BorderLayout.PAGE_START);
    this.add(msgPane, BorderLayout.PAGE_END);

    this.updateHistory();
    historyPane.setLayout(new BoxLayout(historyPane, BoxLayout.PAGE_AXIS));
    for(int i=0; i<history.size(); i++) {
        historyPane.add(history.get(i));
    }

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

  public void updateHistory() {
    Log log = controler.getModel().getMsgHistory(user_ip);
    ArrayList<Message> msg_list = log.getHistory();
    for(int i=0; i<msg_list.size(); i++) {
      MessageHistory new_msg = new MessageHistory(msg_list.get(i), controler);
      history.add(new_msg);
    }
  }

}
