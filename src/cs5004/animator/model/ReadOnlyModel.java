package cs5004.animator.model;

import java.util.ArrayList;

/**
 * An interface that defines only the read only methods of an AnimationModel. Implemented by
 * AnimationModelImpl.java.
 */
public interface ReadOnlyModel {

  /**
   * A getter method for the speed multiplier.
   *
   * @return the speed multiplier.
   */
  int getSpeedMultiplier();

  /**
   * Outputs the model as a specialized formatted string in the format: Shapes: Name: R Type:
   * rectangle Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0) Appears
   * at t=1 Disappears at t=100 Name: C Type: oval Center: (500.0,100.0), X radius: 60.0, Y radius:
   * 30.0, Color: (0.0,0.0,1.0) Appears at t=6 Disappears at t=100 Shape R moves from (200.0,200.0)
   * to (300.0,300.0) from t=10 to t=50 Shape C moves from (500.0,100.0) to (500.0,400.0) from t=20
   * to t=70 Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80 Shape R
   * scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from t=51 to t=70 Shape R
   * moves from (300.0,300.0) to (200.0,200.0) from t=70 to t=100
   *
   * @return a formatted string in the above format.
   */
  String outputDescription();

  /**
   * A getter method for the command list.
   *
   * @return the command list.
   */
  ArrayList<AnimationCommand> getCommandList();

  /**
   * Returns the shapeList.
   *
   * @return the list shapeList.
   */
  ArrayList<Shape> getShapeList();

  /**
   * A getter method for the height of the display.
   *
   * @return the height of the display.
   */
  int getBoundsHeight();

  /**
   * A getter method for the width of the display.
   *
   * @return the width of the display
   */
  int getBoundsWidth();

  /**
   * Returns the int value xLeft.
   *
   * @return the int value xLeft.
   */
  int getXLeft();

  /**
   * Returns the int value yTop.
   *
   * @return the int value yTop.
   */
  int getYTop();

  /**
   * Returns the String value outfile.
   *
   * @return the String value outfile.
   */
  String getOutFile();

  /**
   * Get the last tick of the animation as determined by the latest out time of a shape.
   *
   * @return the last tick of the animation.
   */
  int getLastTick();

  /**
   * Return a list of shapes that includes their intermediary representations for each tick, mapped
   * to each index.
   *
   * @return the list of shapes and all their intermediary representations indexed by tick.
   */
  ArrayList<ArrayList<Shape>> getShapesByTick();


}
