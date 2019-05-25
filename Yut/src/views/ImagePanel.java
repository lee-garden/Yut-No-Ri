package views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
  BufferedImage image;

  public ImagePanel() {
    setOpaque(false);
  }

  /**
   * set Image to draw
   *
   * @param image
   *            image to set.
   */
  public void setImage(BufferedImage image) {
    this.image = image;
  }

  /**
   * get current Image
   *
   * @return BufferedImage
   */
  public BufferedImage getImage() {
    return image;
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    /*
     * inform render to use bilinear interpolation
     */
    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);

    if (image != null) {
      /*
       * if image is set, draw to fill.
       * with aspect ratio
       */
      float wr = (float)getWidth() / (float)image.getWidth();
      float hr = (float)getHeight() / (float)image.getHeight();

      float r = Math.min(wr, hr);

      int w = (int)(image.getWidth()*r);
      int h = (int)(image.getHeight()*r);
      g.drawImage(image, (getWidth()-w)/2,(getHeight()-h)/2,w,h, this);
    }
  }
}
