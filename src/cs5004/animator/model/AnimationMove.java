package cs5004.animator.model;

/**
 * This class represents a move animation command in the model of our animation. git It can both
 * update the shape it acts upon's information and represent itself in text and SVG.
 */
public class AnimationMove extends AbstractAnimationCommand {

  private final int xStart;
  private final int yStart;
  private final int xDestination;
  private final int yDestination;

  /**
   * Construct the move command using both the super and concrete class.
   *
   * @param shape        The shape that the command acts upon.
   * @param xDestination The x coordinate of the destination of the move.
   * @param yDestination The y coordinate of the destination of the move.
   * @param startTime    The start time of the move animation.
   * @param endTime      The end time of the move animation.
   */
  public AnimationMove(Shape shape, int xStart, int yStart, int xDestination, int yDestination,
                       int startTime, int endTime) {
    super(shape, startTime, endTime);

    this.xStart = xStart;
    this.yStart = yStart;
    this.xDestination = xDestination;
    this.yDestination = yDestination;
  }

  /**
   * Returns a String representation of this command, and associated data.
   *
   * @return a String representation of this command, and associated data.
   */
  @Override
  public String toString() {
    return "Shape " + super.getShape().getName() + " " + "moves" + " from ("
            + xStart + ','
            + yStart + ") " + "to " + "(" + xDestination + ','
            + yDestination
            + ") " + "from t=" + super.getStartTime() + " to t=" + super.getEndTime();
  }

  /**
   * A method that updates the shape's data to the value it would have at a given tick.
   *
   * @param tick  the frame of an animation represented as an integer value.
   * @param shape the shape to be updated.
   */
  public void update(int tick, Shape shape) {

    int xUpdate = tweening(getStartTime(), getEndTime(), tick, xStart, xDestination);
    int yUpdate = tweening(getStartTime(), getEndTime(), tick, yStart, yDestination);

    shape.move(xUpdate, yUpdate);
  }

  /**
   * Returns a String representing this command, formatted for a .svg file-type.
   *
   * @param myModel - the AnimationModel that this representation is tied to.
   * @return a String representing this command, formatted for a .svg file-type.
   */
  @Override
  public String getSVG(ReadOnlyModel myModel) {

    int xLeft = myModel.getXLeft();
    int yTop = myModel.getYTop();
    int speedMultiplier = myModel.getSpeedMultiplier();
    String svgRep = "";

    if (xStart != xDestination) {
      svgRep += "\t<animate attributeType=\"xml\" begin=\"" + super.getStartTime() * speedMultiplier
              + "ms\" " + "dur=\"" + (super.getEndTime() - super.getStartTime()) * speedMultiplier
              + "ms\" " + "attributeName=\"" + super.getShape().getXSVG() + "\" from=\"" +
              (xStart - xLeft) + "\" " + "to=\"" + (xDestination - xLeft) + "\" " +
              "fill=\"freeze\" />\n";
    }

    if (yStart != yDestination) {
      svgRep += "\t<animate attributeType=\"xml\" begin=\"" + super.getStartTime() * speedMultiplier
              + "ms\" " + "dur=\"" + (super.getEndTime() - super.getStartTime()) * speedMultiplier
              + "ms\" " + "attributeName=\"" + super.getShape().getYSVG() + "\" from=\"" +
              (yStart - yTop) + "\" " + "to=\"" + (yDestination - yTop) + "\" fill=\"freeze\" />\n";
    }

    return svgRep;
  }
}