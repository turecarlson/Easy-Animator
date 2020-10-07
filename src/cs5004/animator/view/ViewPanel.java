package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import cs5004.animator.controller.SwingController;
import cs5004.animator.model.ReadOnlyModel;
import cs5004.animator.model.Shape;

/**
 * A JPanel Object that holds the graphical representation of a SwingView.
 */
public class ViewPanel extends JPanel {
  private final ReadOnlyModel model;
  private final SwingController controller;

  /**
   * Constructor for a ViewPanel Object.
   *
   * @param model - The ReadOnlyModel that this Panel is graphically representing.
   * @param view  - The AbstractSwingView that this Panel is a part of.
   */
  public ViewPanel(ReadOnlyModel model, AbstractSwingView view) {
    controller = view.getController();
    this.model = model;
    setPreferredSize(new Dimension(model.getBoundsWidth() + model.getXLeft(),
            model.getBoundsHeight() + model.getYTop()));
  }

  /**
   * Creates graphic representations of the Shapes in the model, at a specific tick value in the
   * Animation.
   *
   * @param g - a graphic element being created.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int currentTick = controller.getTick();
    for (Shape shape : model.getShapesByTick().get(currentTick)) {
      if (shape.getInTime() <= currentTick && shape.getOutTime() >= currentTick) {
        g.setColor(shape.getColor());
        if (shape.getShapeType().equals("oval")) {
          g.fillOval(shape.getXPosition(), shape.getYPosition(), shape.getM1(), shape.getM2());
        } else if (shape.getShapeType().equals("rectangle")) {
          g.fillRect(shape.getXPosition(), shape.getYPosition(), shape.getM1(), shape.getM2());
        } else {
          throw new IllegalArgumentException("Shape type not found.");
        }
      }
    }

  }

  /**
   * A function called by the controller to refresh to the latest tick. Calls JPanel.repaint() on
   * this Panel.
   */
  public void refresh() {
    repaint();
  }

}
