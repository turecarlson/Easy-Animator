package cs5004.animator.model;

import java.awt.Color;

/**
 * A class that represents a 2d shape in an animation. It centralizes the methods for retrieving and
 * changing a shape's data as well as self mutation.
 */
public abstract class AbstractShape implements Shape {

  private final String name;
  private String type;
  private int xPosition;
  private int yPosition;
  private int m1;
  private int m2;
  private Color color;
  private int inTime;
  private int outTime;

  /**
   * Public Constructor for an AbstractShape (Parent class of concrete-Shape classes.).
   *
   * @param name      the name of the shape.
   * @param xPosition the x-position of the shape.
   * @param yPosition the y-position of the shape.
   * @param m1        The first measurement of the shape, this could be either a width or x radius
   *                  depending on the nature of the shape.
   * @param m2        The second measurement of the shape, this could be either a length or y radius
   *                  depending on the nature of the shape.
   * @param color     The color of the object represented in (r, g, b) format.
   * @param inTime    The time the shape appears in the animation.
   * @param outTime   The time the shape disappears in the animation.
   * @throws IllegalArgumentException when an invalid position, color, measurement, or time is
   *                                  provided.
   */
  public AbstractShape(String name, int xPosition, int yPosition, int m1,
                       int m2, Color color, int inTime, int outTime)
          throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name must not be null or an empty string.");
    }
    if (m1 <= 0 || m2 <= 0) {
      throw new IllegalArgumentException("m-values must be greater than 0.");
    }
    if (color == null) {
      throw new IllegalArgumentException("color must not be a null value.");
    }
    if (inTime < 0 || outTime <= 0 || outTime < inTime) {
      throw new IllegalArgumentException("inTime may not be less than 0, outTime must be "
              + "greater than 0, and outTime must be greater than inTime.");
    }

    this.name = name;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    this.m1 = m1;
    this.m2 = m2;
    this.color = color;
    this.inTime = inTime;
    this.outTime = outTime;
  }

  void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the name of the Shape Object.
   *
   * @return the name of the Shape Object.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the xPosition of the Shape Object.
   *
   * @return the xPosition of the Shape Object.
   */
  public int getXPosition() {
    return xPosition;
  }

  /**
   * Gets the yPosition of the Shape Object.
   *
   * @return the yPosition of the Shape Object.
   */
  public int getYPosition() {
    return yPosition;
  }

  /**
   * Gets the m1 value of the Shape Object.
   *
   * @return the m1 value of the Shape Object.
   */
  public int getM1() {
    return m1;
  }

  /**
   * Gets the m2 value of the Shape Object.
   *
   * @return the m2 value of the Shape Object.
   */
  public int getM2() {
    return m2;
  }

  /**
   * Gets the color value of the Shape Object.
   *
   * @return the color value of the Shape Object.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Gets the inTime value of the Shape Object.
   *
   * @return the inTime value of the Shape Object.
   */
  public int getInTime() {
    return inTime;
  }

  /**
   * Gets the outTime value of the Shape Object.
   *
   * @return the outTime value of the Shape Object.
   */
  public int getOutTime() {
    return outTime;
  }

  /**
   * Returns the String 'type'.
   * @return the String 'type'.
   */
  public String getShapeType() {
    return type;
  }

  /**
   * Sets the inTime value for the Shape Object.
   *
   * @param inTime - the new value for inTime.
   * @throws IllegalArgumentException if provided inTime is less than 0 or greater than current
   *                                  outTime.
   */
  public void setInTime(int inTime) throws IllegalArgumentException {
    if (inTime < 0) {
      throw new IllegalArgumentException("Provided inTime may not be less than 0.");
    }
    if (inTime > this.outTime) {
      throw new IllegalArgumentException("Provided inTime may not be greater than current "
              + "outTime.");
    }
    this.inTime = inTime;
  }

  /**
   * Sets the outTime value for the Shape Object.
   *
   * @param outTime - the new value for outTime.
   * @throws IllegalArgumentException if provided outTime is less than or equal to 0, or less than
   *                                  current inTime.
   */
  public void setOutTime(int outTime) throws IllegalArgumentException {
    if (outTime <= 0) {
      throw new IllegalArgumentException("Provided outTime must be greater than 0.");
    }
    if (outTime < this.inTime) {
      throw new IllegalArgumentException("Provided outTime may not be less than current inTime.");
    }
    this.outTime = outTime;
  }

  /**
   * A setter method that updates, or scales, the size of the shape.
   *
   * @param newM1 the new first radius or width measurement.
   * @param newM2 the new second radius or width measurement.
   * @throws IllegalArgumentException if provided m-values are less than or equal to 0.
   */
  public void scale(int newM1, int newM2) throws IllegalArgumentException {
    if (newM1 <= 0 || newM2 <= 0) {
      throw new IllegalArgumentException("Provided m-values must be greater than 0.");
    }
    this.m1 = newM1;
    this.m2 = newM2;
  }

  /**
   * A setter method that changes the color of the shape. Takes a Color Object as its parameter.
   *
   * @param color - A Color Object representing the new color of the shape.
   * @throws IllegalArgumentException if provided Color color is null.
   */
  public void changeColor(Color color) throws IllegalArgumentException {
    if (color == null) {
      throw new IllegalArgumentException("Provided color value must not be null.");
    }
    this.color = color;
  }

  /**
   * A setter method that changes the color of the shape. Takes three int values representing RGB
   * values as its parameters.
   *
   * @param colorR - An int value representing the red in an RGB color value.
   * @param colorG - An int value representing the green in an RGB color value.
   * @param colorB - An int value representing the blue in an RGB color value.
   * @throws IllegalArgumentException if given RGB int values are less than 0 or greater than 255.
   */
  public void changeColor(int colorR, int colorG, int colorB) throws IllegalArgumentException {
    if (colorR < 0 || colorR > 255 || colorG < 0 || colorG > 255 || colorB < 0
            || colorB > 255) {
      throw new IllegalArgumentException("Provided RGB int values may not be negative and "
              + "may not be greater than 255.");
    }
    this.color = new Color(colorR, colorG, colorB);
  }

  /**
   * A method to move the shape by updating its x and y coordinates.
   *
   * @param newX the x coordinate of the destination
   * @param newY the y coordinate of the destination.
   */
  public void move(int newX, int newY) {
    this.xPosition = newX;
    this.yPosition = newY;
  }

}
