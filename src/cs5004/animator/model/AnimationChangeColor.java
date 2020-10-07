package cs5004.animator.model;

import java.awt.Color;

/**
 * This class represents a change color animation command in the model of our animation. It can
 * both update the shape it acts upon's information and represent itself in text and SVG.
 */
public class AnimationChangeColor extends AbstractAnimationCommand {

  private int startR;
  private int startG;
  private int startB;
  private int newR;
  private int newG;
  private int newB;
  private Color startColor;
  private Color newColor;

  /**
   * A constructor for the change color command that uses RGB int values.
   *
   * @param shape     The shape that the command acts upon.
   * @param startR    The old red value of the color.
   * @param startG    The old green value of the color.
   * @param startB    The old blue value of the color.
   * @param newR      The new red value of the color.
   * @param newG      The new green value of the color.
   * @param newB      The new blue value of the color.
   * @param startTime The start time of the animation.
   * @param endTime   The end time of the animation.
   */
  public AnimationChangeColor(Shape shape, int startR, int startG, int startB, int newR,
                              int newG, int newB, int startTime, int endTime) {
    super(shape, startTime, endTime);
    this.startR = startR;
    this.startG = startG;
    this.startB = startB;
    this.newR = newR;
    this.newG = newG;
    this.newB = newB;
    this.startColor = new Color(startR, startG, startB);
    this.newColor = new Color(newR, newG, newB);
  }

  /**
   * A constructor for the change color command that takes in a color object.
   *
   * @param shape      The shape that the command acts upon.
   * @param startColor The original color the shape is changing from.
   * @param newColor   The color object that the animation will become.
   * @param startTime  The start time of the animation.
   * @param endTime    The end time of the animation.
   */
  public AnimationChangeColor(Shape shape, Color startColor, Color newColor, int startTime,
                              int endTime) {
    super(shape, startTime, endTime);
    this.startColor = startColor;
    this.newColor = newColor;
    this.startR = startColor.getRed();
    this.startG = startColor.getGreen();
    this.startB = startColor.getBlue();
    this.newR = newColor.getRed();
    this.newG = newColor.getGreen();
    this.newB = newColor.getBlue();
  }

  /**
   * A method that updates the shape's data to the value it would have at a given tick.
   *
   * @param tick  the frame of an animation represented as an integer value.
   * @param shape the shape to be updated.
   */
  public void update(int tick, Shape shape) {
    int updateR = tweening(getStartTime(), getEndTime(), tick, startR, newR);
    int updateG = tweening(getStartTime(), getEndTime(), tick, startG, newG);
    int updateB = tweening(getStartTime(), getEndTime(), tick, startB, newB);
    newColor = new Color(updateR, updateG, updateB);
    shape.changeColor(newColor);
  }

  /**
   * Returns a String representation of this command, and associated data.
   *
   * @return a String representation of this command, and associated data.
   */
  @Override
  public String toString() {

    return "Shape " + super.getShape().getName() + " " + "changes color" + " from ("
            + startR + ','
            + startG + ',' + startB + ") " + "to " + "(" + newR + ','
            + newG + ','
            + newB + ") " + "from t=" + super.getStartTime() + " to t="
            + super.getEndTime();
  }

  /**
   * Returns a String representing this command, formatted for a .svg file-type.
   *
   * @param myModel - the AnimationModel that this representation is tied to.
   * @return a String representing this command, formatted for a .svg file-type.
   */
  @Override
  public String getSVG(ReadOnlyModel myModel) {

    int speedMultiplier = myModel.getSpeedMultiplier();
    String svgRep = "";

    String oldColors = "rgb(" + startR + "," + startG + "," + startB + ")";
    String newColors = "rgb(" + newColor.getRed() + "," + newColor.getGreen() + "," +
            newColor.getBlue() + ")";

    svgRep += "\t<animate attributeType=\"xml\" begin=\"" + super.getStartTime() *
            speedMultiplier + "ms\" " +
            "dur=\"" + (super.getEndTime() - super.getStartTime()) * speedMultiplier +
            "ms\" " + "attributeName=\"fill" + "\" from=\"" + oldColors + "\" "
            + "to=\"" + newColors + "\" fill=\"freeze\" />\n";

    return svgRep;
  }

}