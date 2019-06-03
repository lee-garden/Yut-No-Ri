package views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


public class PieceSprite {
  private String [] name = { "bPiece", "rPiece", "gPiece", "yPiece"};
  BufferedImage[] pieceList;

  public PieceSprite() {
    try {
      pieceList = new BufferedImage[4];
      pieceList = bufferedImage(name);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  private BufferedImage[] bufferedImage(String[] name) throws Exception {
    BufferedImage[] list = new BufferedImage[4];
    String path = PieceSprite.class.getResource("").getPath();

    for (int i=0; i<name.length; i++) {
      File file = new File(path + name[i] + ".png");
      list[i] = ImageIO.read(file);
    }

    return list;
  }

}
