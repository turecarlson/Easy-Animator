package cs5004.animator.model;

/**
 * This class represents a scale animation command in the model of our animation. It can both
 * update the shape it acts upon's information and represent itself in text and SVG.
 */
public class AnimationScale extends AbstractAnimationCommand {

  private int startValue1;
  private int startValue2;
  private int newValue1;
  private int newValue2;

  /**
   * Constructor for an AnimationScale Object. Represents an animation that changes the size values
   * of the Shape Object.
   *
   * @param shape       The shape the command
   * @param startValue1 The old X value width or radius of the shape.
   * @param startValue2 The old Y value height or radius of the shape.
   * @param newValue1   The new X value width or radius of the shape.
   * @param newValue2   The new Y value height or radius of the shape.
   * @param startTime   The start of the animation.
   * @param endTime     The end of the animation.
   */
  public AnimationScale(Shape shape, int startValue1, int startValue2, int newValue1, int newValue2,
                        int startTime, int endTime) {
    super(shape, startTime, endTime);

    this.startValue1 = startValue1;
    this.startValue2 = startValue2;
    this.newValue1 = newValue1;
    this.newValue2 = newValue2;
  }

  /**
   * A method that updates the shape's data to the value it would have at a given tick.
   *
   * @param tick  the frame of an animation represented as an integer value.
   * @param shape the shape to be updated.
   */
  public void update(int tick, Shape shape) {

    int value1Update = tweening(getStartTime(), getEndTime(), tick, startValue1, newValue1);
    int value2Update = tweening(getStartTime(), getEndTime(), tick, startValue2, newValue2);

    shape.scale(value1Update, value2Update);
  }

  /**
   * Returns a String representation of this command, and associated data.
   *
   * @return a String representation of this command, and associated data.
   */
  @Override
  public String toString() {
    return "Shape " + super.getShape().getName() + " " + "scales" + " from Width: "
            + startValue1
            + ", Height: " + startValue2 + " to Width: " + newValue1
            + ", Height: " + newValue2
            + " from t=" + super.getStartTime() + " to t=" + super.getEndTime();
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

    if (super.getShape().getM1() != newValue1) {
      svgRep += "\t<animate attributeType=\"xml\" begin=\"" + super.getStartTime() * speedMultiplier
              + "ms\" " + "dur=\"" + (super.getEndTime() - super.getStartTime()) * speedMultiplier
              + "ms\" " + "attributeName=\"" + super.getShape().getM1SVG() + "\" from=\"" +
              super.getShape().getM1() + "\" " + "to=\"" + (newValue1) + "\" fill=\"freeze\" />\n";

    } else if (super.getShape().getM2() != newValue2) {
      svgRep += "\t<animate attributeType=\"xml\" begin=\"" + super.getStartTime() * speedMultiplier
              + "ms\" " + "dur=\"" + (super.getEndTime() - super.getStartTime()) * speedMultiplier
              + "ms\" " + "attributeName=\"" + super.getShape().getM2SVG() + "\" from=\""
              + super.getShape().getM2() + "\" " + "to=\"" + (newValue2)
              + "\" fill=\"freeze\" />\n";
    }
    return svgRep;
  }
}