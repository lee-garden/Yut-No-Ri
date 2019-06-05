package views;


import models.YutNoRiSet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class YutGui {
  static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 1000;
  public JFrame mainFrame;
  static ImagePanel btn[][];
  public static JPanel yutBoard;
  static YutNoRiSet yutnoriset = new YutNoRiSet();
  static PieceSprite pieceSprite = new PieceSprite();
  static JLabel player[];
  static ImagePanel beginPiece[];
  // private static UIclick clickBridge;

  public BackGroundPanel midPanel;

  private static void setPlayerLabel() {
    JLabel player1 = new JLabel("Player 1");
    JLabel player2 = new JLabel("Player 2");
    JLabel player3 = new JLabel("Player 3");
    JLabel player4 = new JLabel("Player 4");
    player = new JLabel[] { player1, player2, player3, player4};
  }

  private static void setPiecePanel(BufferedImage[] pieceList, UIclick clickAct) {
    ImagePanel piece1 = new ImagePanel();
    ImagePanel piece2 = new ImagePanel();
    ImagePanel piece3 = new ImagePanel();
    ImagePanel piece4 = new ImagePanel();

    piece1.setImage(pieceList[0]);
    piece2.setImage(pieceList[1]);
    piece3.setImage(pieceList[2]);
    piece4.setImage(pieceList[3]);

    piece1.addMouseListener(clickAct);
    piece2.addMouseListener(clickAct);
    piece3.addMouseListener(clickAct);
    piece4.addMouseListener(clickAct);

    beginPiece = new ImagePanel[] { piece1, piece2, piece3, piece4};
  }

  public void setupStartUI(){
    // Create max number of player and pieces for test
    //yutnoriset.setOption(4, 5);
    mainFrame = new JFrame("Mode Selection");
    mainFrame.setSize(FRAME_WIDTH / 2, FRAME_HEIGHT / 2);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(new BorderLayout(10, 10));
    mainFrame.setLocationRelativeTo(null);

    midPanel = new BackGroundPanel();
    midPanel.setGUI();

    mainFrame.add(midPanel);

    mainFrame.setVisible(true);
  }

  public void setupYutGUI(int playerNumber, int pieceNumber) {
    mainFrame = new JFrame("Yut-No-Ri");
    mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLocationRelativeTo(null);

    Container contentPane = mainFrame.getContentPane();
    contentPane.setLayout(new BorderLayout(10, 10));


    // set board
    yutBoard= new JPanel();
    yutBoard.setLayout(new GridLayout(7, 7));
    yutBoard.setBackground(Color.WHITE);
    yutBoard.setBorder(new EmptyBorder(30, 30, 30, 30));

    btn = new ImagePanel[8][8];
    UIclick clickAction = new UIclick();

    for(int i = 1; i < 8; i++) {
      for(int j = 1; j < 8; j++) {
        btn[i][j] = new ImagePanel();
        btn[i][j].setOpaque(true);
        // btn[i][j].addMouseListener(clickBridge);

        if ((yutnoriset.isCircleClickable(i, j))) {
          if(yutnoriset.isCircleChangeable(i,j)){
            btn[i][j].setBackground(Color.GREEN);
          } else {
            btn[i][j].setBackground(Color.DARK_GRAY);
          }
        } else {
          btn[i][j].setBackground(Color.WHITE);
        }
        btn[i][j].addMouseListener(clickAction);
        yutBoard.add(btn[i][j]);
        btn[i][j].repaint();
      }
    }

    JPanel statusPanels = new JPanel();
    // Fix outlook which is not compatible with name+image+number of piece.
    statusPanels.setLayout(new GridLayout(playerNumber, 3));
    statusPanels.setBorder(new EmptyBorder(0, 30, 0, 30));

    /* Right side border */
    JPanel yutButtonPanels = new JPanel();
    yutButtonPanels.setBorder(new EmptyBorder(0, 30, 0, 30));

    // It' for yut result image
    ImagePanel yutResultPanel = new ImagePanel();

    ImageIcon ii = new ImageIcon(getClass().getResource("loadyut.gif"));

    // resize image in button
    ii.setImage(ii.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
    JButton yutBtn = new JButton("윷 던지기", ii);

    yutBtn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // controller에서 윷 던졌음을 알리기
        // System.out.print("hihi");
      }
    });

    yutButtonPanels.add(yutBtn);

    // For piece Sprite
    BufferedImage[] pieceList = pieceSprite.pieceList;

    // set Player name and Piece at the side border
    setPiecePanel(pieceList, clickAction);
    setPlayerLabel();
    for (int i=0; i< playerNumber; i++) {
      statusPanels.add(player[i]);
      statusPanels.add(beginPiece[i]);
      // Add number if player's left piece
      statusPanels.add(new JLabel(Integer.toString(yutnoriset.howManyPiecesIsOutOfBoards(i))));
      // beginPiece[i].addMouseListener(clickBridge);
    }

    contentPane.add(yutButtonPanels,  BorderLayout.LINE_START);
    contentPane.add(statusPanels, BorderLayout.LINE_END);
    contentPane.add(yutBoard, BorderLayout.CENTER);
    mainFrame.setVisible(true);

  }
}