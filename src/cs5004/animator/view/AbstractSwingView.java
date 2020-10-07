package cs5004.animator.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import cs5004.animator.controller.SwingController;
import cs5004.animator.model.ReadOnlyModel;

/**
 * An Abstract parent class for Views that utilize Swing components  for the EasyAnimator.
 */
public abstract class AbstractSwingView implements View {
  private final ReadOnlyModel readOnlyModel;
  private SwingController controller;
  private JFrame frame;
  private ViewPanel panel;

  /**
   * Constructor for an AbstractSwingView.
   *
   * @param readOnlyModel - A read-only implementation of the AnimationModel that this view is
   *                      representing graphically.
   */
  public AbstractSwingView(ReadOnlyModel readOnlyModel) {
    this.readOnlyModel = readOnlyModel;
  }

  /**
   * Sets the title of the JFrame.
   *
   * @param title - A String representing the new title of the JFrame.
   */
  public void setFrameTitle(String title) {
    if (frame == null) {
      throw new IllegalArgumentException("JFrame has not been initialized. Please render view " +
              "first.");
    }
    frame.setTitle(title);
  }

  /**
   * Sets the controller variable with the controller tied to this View.
   *
   * @param controller - A SwingController Object that controls this View.
   */
  public void setController(SwingController controller) {
    this.controller = controller;
  }

  /**
   * Returns the SwingController object tied to this View.
   *
   * @return the SwingController object tied to this View.
   */
  public SwingController getController() {
    if (controller == null) {
      throw new IllegalStateException("Controller has not been set.");
    }
    return controller;
  }

  /**
   * Returns the ViewPanel object that contains the animation components for this View.
   *
   * @return the ViewPanel object that contains the animation components for this View.
   */
  public ViewPanel getPanel() {
    if (panel == null) {
      throw new IllegalStateException("Panel has not yet been created.");
    }
    return panel;
  }

  /**
   * Creates the JFrame, JPanel, and JScrollPane elements of this View. Makes the JFrame visible.
   */
  public void render() {
    frame = new JFrame();
    frame.setSize(readOnlyModel.getBoundsWidth(), readOnlyModel.getBoundsHeight());
    frame.setLocation(readOnlyModel.getXLeft(), readOnlyModel.getYTop());
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    panel = new ViewPanel(readOnlyModel, this);
    JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    frame.add(scrollPane);
    frame.setVisible(true);
  }


}
