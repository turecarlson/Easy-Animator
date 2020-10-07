package cs5004.animator.model;

/**
 * An interface representing our methods for the animation commands in our animation model.
 */

public interface AnimationCommand {

  /**
   * Return the command in a formatted string.
   *
   * @return a string in the form "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=10 to
   *         t=50"
   */
  String toString();

  /**
   * A getter method for the shape held by the command.
   *
   * @return the shape the command acts upon.
   */
  Shape getShape();

  /**
   * A setter method for the shape held by the command.
   */
  void setShape(Shape shape);

  /**
   * A getter method for the beginning of the animation.
   *
   * @return the time the animation starts.
   */
  int getStartTime();

  /**
   * A getter method for the end of the animation.
   *
   * @return the end time of the animation.
   */
  int getEndTime();

  /**
   * A method that updates the shape's data to the value it would have at a given tick.
   *
   * @param tick  the frame of an animation represented as an integer value.
   * @param shape the shape to be updated.
   */
  void update(int tick, Shape shape);

  /**
   * A toString method to return the SVG representation of the command.
   *
   * @param myModel - the AnimationModel that this representation is tied to.
   * @return the command in SVG.
   */
  String getSVG(ReadOnlyModel myModel);
}
