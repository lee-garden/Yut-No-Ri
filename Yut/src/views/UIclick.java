package views;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class UIclick implements MouseListener{
  MouseClick mouseClick = new MouseClick();
  @Override
  public void mouseClicked(MouseEvent e) {
    mouseClick.mouseInput(e);
  }
  @Override
  public void mousePressed(MouseEvent e) {
  }
  @Override
  public void mouseReleased(MouseEvent e) {
  }
  @Override
  public void mouseEntered(MouseEvent e) {
  }
  @Override
  public void mouseExited(MouseEvent e) {
  }
}
