package cs5004.animator.model;

/**
 * An abstract class that represents all commands for the model of our animation. It holds the
 * shared functionality of the (currently) three concrete command classes for constructing the
 * command and using its getter methods.
 */
public abstract class AbstractAnimationCommand implements AnimationCommand {

  private Shape shape;
  private int startTime;
  private int endTime;

  /**
   * Construct the abstract animation command.
   *
   * @param shape     The shape the command acts upon.
   * @param startTime The beginning time of the animation.
   * @param endTime   The end time of the animation.
   * @throws IllegalArgumentException if the start time is before 0 or after the end time or if the
   *                                  shape passed in is null.
   */
  public AbstractAnimationCommand(Shape shape, int startTime, int endTime)
          throws IllegalArgumentException {

    //Check for valid start time
    if (startTime < 0 || endTime < 0 || startTime >= endTime) {
      throw new IllegalArgumentException("Start and end time must be greater than zero and"
              + "the start time must be before end time");
    }

    //Check for valid shape
    if (shape == null) {
      throw new IllegalArgumentException("Shape must not be null");
    }

    this.shape = shape;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * A getter method for the shape held by the command.
   *
   * @return the shape the command acts upon.
   */
  public Shape getShape() {
    return this.shape;
  }

  /**
   * A setter method for the shape held by the command.
   */
  public void setShape(Shape shape) {
    this.shape = shape;
  }

  /**
   * A getter method for the beginning of the animation.
   *
   * @return the time the animation starts.
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * A getter method for the end of the animation.
   *
   * @return the end time of the animation.
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Helper method for the Visual view class. Returns an int value at a given time, given starting
   * and ending values and times, using liner interpolation.
   *
   * @param startTime  - The reference time of the startValue.
   * @param endTime    - The reference time of the endValue.
   * @param tick       - The time value whose value is being determined.
   * @param startValue - The starting value being used to determine the value at tick.
   * @param endValue   -The ending value being used to determine the value at tick.
   * @return - The value at time: tick.
   * @throws IllegalArgumentException if the provided tick is less than 0.
   */
  int tweening(int startTime, int endTime, int tick, int startValue, int endValue)
          throws IllegalArgumentException {
    if (tick < 0) {
      throw new IllegalArgumentException("tick value must be a non-negative integer value.");
    }
    if (tick > endTime || tick < startTime) {
      throw new IllegalArgumentException("tick value must be equal to or between given time " +
              "values.");
    }
    return Math.round(startValue * ((float) (endTime - tick) / (endTime - this.getStartTime()))
            + endValue * ((float) (tick - startTime) / (endTime - startTime)));
  }
}
