import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;

public class MessageHistory extends JPanel {

  private final MainController controler;

  private String source;
  private String dest;
  private byte[] data;
  private String timestamp;

  private JPanel infoPane = new JPanel();
  private JLabel source_info;
  private JLabel dest_info;
  private JLabel timestamp_info;

  private JLabel message_content;

  public MessageHistory(Message msg, MainController controler) {
    super(new FlowLayout(FlowLayout.LEFT, 0, 0));
    this.controler = controler;

    // Récupération des informations du message
    InetAddress source_address = msg.getSource();
    if(source_address.equals(this.controler.getModel().getLocalUser().getIp())) {
      this.source = this.controler.getModel().getLocalUser().getPseudo();
    }
    else {
      this.source = this.controler.getModel().getPseudoFromIP(source_address);
    }

    InetAddress dest_address = msg.getDest();
    if(dest_address.equals(this.controler.getModel().getLocalUser().getIp())) {
      this.dest = this.controler.getModel().getLocalUser().getPseudo();
    }
    else {
      this.dest = this.controler.getModel().getPseudoFromIP(dest_address);
    }

    this.data = msg.getData();
    this.timestamp = msg.getTimestamp();

    // Création des éléments du Panel
    source_info = new JLabel(this.source);
    dest_info = new JLabel(this.dest);
    timestamp_info = new JLabel(this.timestamp);
    String data_str = new String(this.data);
    message_content = new JLabel(data_str);

    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.add(infoPane);
    this.add(message_content);

    infoPane.setLayout(new BorderLayout());
    infoPane.add(source_info, BorderLayout.LINE_START);
    infoPane.add(dest_info, BorderLayout.CENTER);
    infoPane.add(timestamp_info, BorderLayout.LINE_END);
  }

}
