package views;


import models.YutNoRiSet;

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
  static ImagePanel btn[][];
  public static JPanel yutBoard;
  static YutNoRiSet yutnoriset;

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

    btn = new ImagePanel[7][7];

    for(int i = 0; i < 7; i++) {
      for(int j = 0; j < 7; j++) {
        btn[i][j] = new ImagePanel();
        btn[i][j].setOpaque(true);
        System.out.println("yolo");
        if (!(yutnoriset.getCircleIsClickableByLocation(i, j))) {
          btn[i][j].setBackground(Color.DARK_GRAY);
        } else {
          btn[i][j].setBackground(Color.WHITE);
        }

        yutBoard.add(btn[i][j]);
        btn[i][j].repaint();
      }
    }

    contentPane.add(yutBoard, BorderLayout.CENTER);
    mainFrame.setVisible(true);

  }

  public static void main(String[] args) throws IOException {
    setupStartUI();
    return;
  }
}
