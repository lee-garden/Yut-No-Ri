package views;


import models.YutNoRiSet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
  static YutNoRiSet yutnoriset = new YutNoRiSet();
  static PieceSprite pieceSprite = new PieceSprite();
  static JLabel player[];
  static ImagePanel beginPiece[];
  // private static UIclick clickBridge;

  private static void setPlayerLabel() {
    JLabel player1 = new JLabel("Player 1");
    JLabel player2 = new JLabel("Player 2");
    JLabel player3 = new JLabel("Player 3");
    JLabel player4 = new JLabel("Player 4");
    player = new JLabel[] { player1, player2, player3, player4};
  }

  private static void setPiecePanel(BufferedImage[] pieceList) {
    ImagePanel piece1 = new ImagePanel();
    ImagePanel piece2 = new ImagePanel();
    ImagePanel piece3 = new ImagePanel();
    ImagePanel piece4 = new ImagePanel();

    piece1.setImage(pieceList[0]);
    piece2.setImage(pieceList[1]);
    piece3.setImage(pieceList[2]);
    piece4.setImage(pieceList[3]);

    beginPiece = new ImagePanel[] { piece1, piece2, piece3, piece4};
  }

  public static void setupStartUI(){
    yutnoriset.setOption(2, 5);
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

  public static void setupYutGUI(int playerNumber, int pieceNumber) {
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

    btn = new ImagePanel[8][8];

    for(int i = 1; i < 8; i++) {
      for(int j = 1; j < 8; j++) {
        btn[i][j] = new ImagePanel();
        btn[i][j].setOpaque(true);
        // btn[i][j].addMouseListener(clickBridge);

        if ((yutnoriset.getCircleIsClickableByLocation(i, j))) {
          btn[i][j].setBackground(Color.DARK_GRAY);
        } else {
          btn[i][j].setBackground(Color.WHITE);
        }

        yutBoard.add(btn[i][j]);
        btn[i][j].repaint();
      }
    }

    JPanel statusPanels = new JPanel();

    statusPanels.setLayout(new GridLayout(5, 1));
    statusPanels.setBorder(new EmptyBorder(0, 30, 0, 30));

    // For piece Sprite
    BufferedImage[] pieceList = pieceSprite.pieceList;

    // set Player name and Piece at the side border
    setPiecePanel(pieceList);
    setPlayerLabel();
    for (int i=0; i< playerNumber; i++) {
      statusPanels.add(player[i]);
      statusPanels.add(beginPiece[i]);
      // beginPiece[i].addMouseListener(clickBridge);
    }

    contentPane.add(statusPanels, BorderLayout.LINE_END);
    contentPane.add(yutBoard, BorderLayout.CENTER);
    mainFrame.setVisible(true);

  }

  public static void main(String[] args) throws IOException {
    setupStartUI();
    return;
  }
}
