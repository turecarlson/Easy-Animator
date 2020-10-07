package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import cs5004.animator.util.AnimationBuilder;
import cs5004.animator.view.Playback;
import cs5004.animator.view.Svg;
import cs5004.animator.view.Text;
import cs5004.animator.view.View;
import cs5004.animator.view.Visual;

/**
 * A model representing an animation, it takes in shapes and commands and outputs them to the view
 * and controller. While at this point it only does it through a specified text format, it is
 * designed to be expanded to other outputs as well. It has two lists representing the beginning
 * state and list of all commands received.
 */
public class AnimationModelImpl implements AnimationModel {

  ArrayList<Shape> shapeList;
  ArrayList<AnimationCommand> commandList;
  private int xLeft;
  private int yTop;
  private int boundsWidth;
  private int boundsHeight;
  private int speedMultiplier;
  private String outfile;
  private ArrayList<ArrayList<Shape>> shapesByTick;

  /**
   * Initialize the model with an empty list of shapes and commands.
   */
  public AnimationModelImpl() {
    this.shapeList = new ArrayList<>();
    this.commandList = new ArrayList<>();
  }

  /**
   * Add a shape to the model.
   *
   * @param shape The shape to be added.
   * @throws IllegalArgumentException if the shape provided is null.
   */
  public void addShape(Shape shape) throws IllegalArgumentException {

    if (shape == null) {
      throw new IllegalArgumentException("Shape must not be null");
    }
    shapeList.add(shape);
  }

  /**
   * Remove a shape from a list in the model.
   *
   * @param shape the shape to be added.
   * @param list  the list to be added to.
   * @throws IllegalArgumentException If the shape or list is null, or if the list does not
   *                                  contatain the shape.
   */
  public void removeShape(Shape shape, ArrayList<Shape> list) throws IllegalArgumentException {
    //Null check
    if (shape == null || list == null) {
      throw new IllegalArgumentException("Shape and list must not be null");
    }

    //Check to see if the shape is in the list, if so remove it.
    if (list.contains(shape)) {
      list.remove(shape);
    } else {
      throw new IllegalArgumentException("List does not contain shape");
    }
  }

  /**
   * Add an animation command to the model.
   *
   * @param command The animation command to be added.
   * @throws IllegalArgumentException if the command provided is null or conflicting with another
   *                                  existent command.
   */
  public void addAnimationCommand(AnimationCommand command) throws IllegalArgumentException {
    //Check for Null
    if (command == null) {
      throw new IllegalArgumentException("Command must not be null");
    }

    //Check to make sure there are not conflicting move commands.
    if (command instanceof AnimationMove) {
      for (AnimationCommand c : commandList) {
        if (command.getStartTime() >= c.getStartTime() || command.getEndTime() <= c.getEndTime()) {
          throw new IllegalArgumentException("Cannot move in two places at once");
        }
      }
    }

    commandList.add(command);
  }

  /**
   * A setter method for the value speedMultiplier.
   *
   * @param speedMultiplier - The length of one 'tick' in milliseconds.
   */
  public void setSpeedMultiplier(int speedMultiplier) {
    this.speedMultiplier = speedMultiplier;
  }

  /**
   * A getter method for the speed multiplier.
   *
   * @return the speed multiplier.
   */
  public int getSpeedMultiplier() {
    return speedMultiplier;
  }

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
  public String outputDescription() {
    //Create a string to return
    StringBuilder output = new StringBuilder("Shapes:\n");

    //Iterate through the shape list, formatting new lines appropriately.
    for (Shape shape : shapeList) {
      output.append('\n');
      output.append(shape.toString());
      output.append('\n');
    }

    //Iterate through the command list
    for (AnimationCommand command : commandList) {
      output.append('\n');
      output.append(command.toString());
    }

    //Return the string
    return output.toString();
  }

  /**
   * A getter method for the command list.
   *
   * @return the command list.
   */
  public ArrayList<AnimationCommand> getCommandList() {
    return commandList;
  }

  /**
   * Returns the shapeList.
   *
   * @return the list shapeList.
   */
  public ArrayList<Shape> getShapeList() {
    return shapeList;
  }

  /**
   * Get the last tick of the animation as determined by the latest out time of a shape.
   *
   * @return the last tick of the animation.
   */
  public int getLastTick() {

    //Determine the last tick
    int lastTick = 0;
    for (Shape myShape : shapeList) {
      if (myShape.getOutTime() > lastTick) {
        lastTick = myShape.getOutTime();
      }
    }

    return lastTick;
  }

  /**
   * Create a list of shapes that represents the value of each shape at each tick value, with each
   * index representing each tick.
   */
  public void createShapesByTick() {

    //Instantiate the empty shapesByTick, and an empty HashMap 'mutatingList' that will be
    // updated every tick.
    shapesByTick = new ArrayList<>();
    LinkedHashMap<String, Shape> mutatingList = new LinkedHashMap<>();
    //Copy every shape from the shapeList into the mutatingList, setting its key as shape.getName().
    for (Shape shape : shapeList) {
      mutatingList.put(shape.getName(), shape.copy());
    }
    //Iterate over every tick in the animation.
    int tick = 0;
    while (tick <= getLastTick()) {
      //Iterate over every command, and if the command is relevant during the current tick,
      // update the shape in mutatingList with command.update().
      for (AnimationCommand command : commandList) {
        if (tick <= command.getEndTime() && tick >= command.getStartTime()) {
          String targetShapeName = command.getShape().getName();
          Shape targetShape = mutatingList.get(targetShapeName);
          command.update(tick, targetShape);
        }
      }
      //Create a list that will 'stage' our shapes at this specific tick, before we copy it into
      // the shapesByTick list.
      ArrayList<Shape> stagingList = new ArrayList<>();
      //Make a copy of each shape in the mutatingList, and add it to the stagingList.
      for (Shape shape : mutatingList.values()) {
        stagingList.add(shape.copy());
      }
      //add the stagingList as a new element in shapesByTick.
      shapesByTick.add(stagingList);
      tick++;
    }
  }

  /**
   * Returns shapesByTick, a list of lists of shapes that represents every shape in the model's
   * state at every 'tick', described by the commandList.
   *
   * @return the ArrayList shapesByTick.
   */
  public ArrayList<ArrayList<Shape>> getShapesByTick() {
    return shapesByTick;
  }

  /**
   * A Factory design element that builds a View from an AnimationModel.
   */
  public static class Factory {

    /**
     * Returns a View Object representative of the provided AnimationModel.
     *
     * @param view   - A String representing the concrete-type of View we are constructing.
     * @param aModel - A Model to create the View from.
     * @return - A View Object representative of the provided AnimationModel.
     */
    public static View build(String view, AnimationModel aModel) {
      View myView;
      switch (view) {
        case "visual":
          myView = new Visual(aModel);
          aModel.createShapesByTick();
          break;
        case "playback":
          myView = new Playback(aModel);
          aModel.createShapesByTick();
          break;
        case "text":
          myView = new Text(aModel);
          break;
        case "svg":
          myView = new Svg(aModel);
          break;

        default:
          throw new IllegalArgumentException("Unexpected value: " + view);
      }
      return myView;
    }
  }

  /**
   * A getter method for the height of the display.
   *
   * @return the height of the display.
   */
  public int getBoundsHeight() {
    return boundsHeight;
  }

  /**
   * A getter method for the width of the display.
   *
   * @return the width of the display
   */
  public int getBoundsWidth() {
    return boundsWidth;
  }

  /**
   * Returns the int value xLeft.
   *
   * @return the int value xLeft.
   */
  public int getXLeft() {
    return xLeft;
  }

  /**
   * Returns the int value yTop.
   *
   * @return the int value yTop.
   */
  public int getYTop() {
    return yTop;
  }

  /**
   * Sets the String value outfile.
   *
   * @param outfile - A String representing where to write the file to, if the model calls for it.
   */
  public void setOut(String outfile) {
    this.outfile = outfile;
  }

  /**
   * Returns the String value outfile.
   *
   * @return the String value outfile.
   */
  public String getOutFile() {
    return this.outfile;
  }

  /**
   * A Builder for AnimationModel objects. Implements the AnimationBuilder interface.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {
    private ArrayList<Shape> shapeList;
    private ArrayList<AnimationCommand> commandList;
    private int xLeft;
    private int yTop;
    private int boundsWidth;
    private int boundsHeight;
    private HashMap<String, String> shapesDeclared;
    //Key: name Value: type

    private HashMap<String, Shape> shapeMap;
    // Key: name Value: Shape with that name.

    /**
     * Constructor for a Builder Object. Sets default values.
     */
    public Builder() {
      shapeList = new ArrayList<>();
      commandList = new ArrayList<>();
      xLeft = 0;
      yTop = 0;
      boundsWidth = 0;
      boundsHeight = 0;
      shapesDeclared = new HashMap<>();
      shapeMap = new HashMap<>();
    }

    /**
     * Constructs an AnimationModel Object.
     *
     * @return the newly constructed AnimationModel Object.
     */
    public AnimationModel build() {
      AnimationModelImpl myModel = new AnimationModelImpl();
      myModel.shapeList = this.shapeList;
      myModel.commandList = this.commandList;
      myModel.xLeft = this.xLeft;
      myModel.yTop = this.yTop;
      myModel.boundsWidth = this.boundsWidth;
      myModel.boundsHeight = this.boundsHeight;
      return myModel;
    }

    /**
     * Specify the bounding box to be used for the animation.
     *
     * @param x      The leftmost x value
     * @param y      The topmost y value
     * @param width  The width of the bounding box
     * @param height The height of the bounding box
     * @return This {@link AnimationBuilder}
     */
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.xLeft = x;
      this.yTop = y;
      this.boundsWidth = width;
      this.boundsHeight = height;
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     *
     * @param name The unique name of the shape to be added. No shape with this name should already
     *             exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added. The set of supported
     *             shapes is unspecified, but should include "ellipse" and "rectangle" as a
     *             minimum.
     * @return This {@link AnimationBuilder}
     */
    public AnimationBuilder<AnimationModel> declareShape(String name, String type) {
      shapesDeclared.put(name, type);
      return this;
    }

    /**
     * Adds a transformation to the growing document.
     *
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @return This {@link AnimationBuilder}
     */
    public AnimationBuilder<AnimationModel> addMotion(String name,
                                                      int t1, int x1, int y1, int w1, int h1,
                                                      int r1, int g1, int b1, int t2, int x2,
                                                      int y2, int w2, int h2, int r2, int g2,
                                                      int b2) {

      Shape myShape;

      if (!(shapeMap.containsKey(name))) {
        //Create new shape object of this name, with previously declared type from shapesDeclared.
        String type = shapesDeclared.get(name);
        switch (type) {
          case ("rectangle"):
            myShape = new Rectangle(name, x1, y1, w1, h1, r1, g1, b1, t1, t2);
            shapeList.add(myShape);
            shapeMap.put(name, myShape);
            break;
          case ("ellipse"):
            myShape = new Oval(name, x1, y1, w1, h1, r1, g1, b1, t1, t2);
            shapeList.add(myShape);
            shapeMap.put(name, myShape);
            break;
          default:
            throw new IllegalArgumentException("No declared shape exists of the name: " + name);
        }
      } else {
        //If Shape already exists, fetch this shape from shapeMap.
        myShape = shapeMap.get(name);
        myShape.setOutTime(t2);
      }

      //Create an animationMove Object, if necessary.
      if (x1 != x2 || y1 != y2) {
        AnimationMove myMoveCommand = new AnimationMove(myShape, x1, y1, x2, y2, t1, t2);
        commandList.add(myMoveCommand);
      }

      //Create an animationChangeColor Object, if necessary.
      if (r1 != r2 || g1 != g2 || b1 != b2) {
        AnimationChangeColor myChangeColorCommand = new AnimationChangeColor(myShape, r1, g1,
                b1, r2, g2, b2, t1, t2);
        commandList.add(myChangeColorCommand);

      }

      //Create an animationScale Object, if necessary.
      if (w1 != w2 || h1 != h2) {
        AnimationScale myScaleCommand = new AnimationScale(myShape, w1, h1, w2, h2, t1, t2);
        commandList.add(myScaleCommand);
      }

      return this;
    }
  }

}

