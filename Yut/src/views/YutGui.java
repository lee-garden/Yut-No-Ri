package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class YutGui {
  static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 1000;
  static JFrame mainFrame;
  public static JPanel yutBoard;

  public static void setupStartUI(){
    mainFrame = new JFrame("Mode Selection");
    mainFrame.setSize(FRAME_WIDTH / 2, FRAME_HEIGHT / 2);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(new BorderLayout(10, 10));
    mainFrame.setLocationRelativeTo(null);

    BackGroundPanel midPanel = new BackGroundPanel();
    midPanel.setGUI();

    mainFrame.add(midPanel);

    mainFrame.setVisible(true);
  }

  public static void setupYutGUI() {
    mainFrame = new JFrame("Yut-No-Ri");
    mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLocationRelativeTo(null);

    Container contentPane = mainFrame.getContentPane();
    contentPane.setLayout(new BorderLayout(10, 10));
    yutBoard= new JPanel();
    yutBoard.setLayout(new GridLayout(7, 7));
    yutBoard.setBackground(Color.WHITE);
    yutBoard.setBorder(new EmptyBorder(30, 30, 30, 30));

    contentPane.add(yutBoard, BorderLayout.CENTER);
    mainFrame.setVisible(true);

  }

  public static void main(String[] args) throws IOException {
    setupStartUI();
    return;
  }
}
