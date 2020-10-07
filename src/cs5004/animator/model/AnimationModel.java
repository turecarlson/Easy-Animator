package cs5004.animator.model;

import java.util.ArrayList;

/**
 * An interface that defines methods of an AnimationModel. Implemented by AnimationModelImpl.java.
 */
public interface AnimationModel extends ReadOnlyModel {

  /**
   * Add a shape to the model.
   *
   * @param shape The shape to be added.
   * @throws IllegalArgumentException if the shape provided is null.
   */
  void addShape(Shape shape);

  /**
   * Remove a shape from a list in the model.
   *
   * @param shape the shape to be added.
   * @param list  the list to be added to.
   * @throws IllegalArgumentException If the shape or list is null, or if the list does not
   *                                  contatain the shape.
   */
  void removeShape(Shape shape, ArrayList<Shape> list) throws IllegalArgumentException;

  /**
   * Add an animation command to the model.
   *
   * @param command The animation command to be added.
   * @throws IllegalArgumentException if the command provided is null or conflicting with another
   *                                  existent command.
   */
  void addAnimationCommand(AnimationCommand command);

  /**
   * A setter method for the value speedMultiplier.
   *
   * @param speedMultiplier - The length of one 'tick' in milliseconds.
   */
  void setSpeedMultiplier(int speedMultiplier);

  /**
   * Sets the String value outfile.
   *
   * @param outfile - A String representing where to write the file to, if the model calls for it.
   */
  void setOut(String outfile);

  /**
   * Create a list of shapes for each tick in the animation so that the intermediate representation
   * of each shape at each tick can be accessed.
   */
  void createShapesByTick();
}
