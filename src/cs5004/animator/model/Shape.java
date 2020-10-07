package cs5004.animator.model;

import java.awt.Color;

/**
 * Represents a Shape Object. Shape Objects contain information and methods regarding name, type,
 * size, location, color, type, and time of appearance.
 */
public interface Shape {

  /**
   * Gets the name value for the Shape Object.
   *
   * @return - The name value for the Shape Object.
   */
  String getName();

  /**
   * Gets the xPosition value for the Shape Object.
   *
   * @return - The xPosition value for the Shape Object.
   */
  int getXPosition();

  /**
   * Gets the yPosition value for the Shape Object.
   *
   * @return - The yPosition value for the Shape Object.
   */
  int getYPosition();

  /**
   * Gets the m1 value for the Shape Object.
   *
   * @return - The m1 value for the Shape Object.
   */
  int getM1();

  /**
   * Gets the m2 value for the Shape Object.
   *
   * @return - The m2 value for the Shape Object.
   */
  int getM2();

  /**
   * Gets the color value for the Shape Object.
   *
   * @return - The color value for the Shape Object.
   */
  Color getColor();

  /**
   * Gets the inTime value for the Shape Object.
   *
   * @return - The inTime value for the Shape Object.
   */
  int getInTime();

  /**
   * Gets the outTime value for the Shape Object.
   *
   * @return -
   */
  int getOutTime();

  /**
   * Sets the inTime value for the Shape Object.
   *
   * @param inTime - the new value for inTime.
   */
  void setInTime(int inTime);

  /**
   * Sets the outTime value for the Shape Object.
   *
   * @param outTime - the new value for outTime.
   */
  void setOutTime(int outTime);

  //Animation actions.

  /**
   * Changes the size values of the Shape Object.
   *
   * @param newM1 - The new value for m1.
   * @param newM2 - The new value for m2.
   */
  void scale(int newM1, int newM2);

  /**
   * Changes the color values of the Shape Object.
   *
   * @param newColor - The new value for color.
   */
  void changeColor(Color newColor);

  /**
   * Changes the location values of the Shape Object.
   *
   * @param newX - The new value for xPosition.
   * @param newY - The new value for yPosition.
   */
  void move(int newX, int newY);

  /**
   * The toString method for our shape. It returns the name, type, position and size, the time the
   * shape appears, and the time the shape disappears.
   *
   * @return a string representing the shape in the following example format. Name: R Type:
   *          rectangle Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
   *          Appears at t=1 Disappears at t=100.
   */
  String toString();

  /**
   * Creates a string representing the shape in SVG.
   *
   * @return a string representing the shape in SVG.
   */
  String getSVG(int xLeft, int yTop);

  /**
   * A getter method for the SVG tag of the shape.
   *
   * @return The tag for the shape in SVG.
   */
  String getSVGType();

  /**
   * A getter method for the x attribute for the shape in SVG.
   *
   * @return the x attribute for the shape in SVG.
   */
  String getXSVG();

  /**
   * A getter method for the y attribute for the shape in SVG.
   *
   * @return the y attribute for the shape in SVG.
   */
  String getYSVG();

  /**
   * A getter method for the width or x radius of the shape in SVG.
   *
   * @return the width attribute for the shape in SVG.
   */
  String getM1SVG();

  /**
   * A getter method for the height or y radius of the shape in SVG.
   *
   * @return the height attribute for the shape in SVG.
   */
  String getM2SVG();

  /**
   * Create a shallow copy of the shape.
   * @return a shallow copy of the shape.
   */
  Shape copy();

  /**
   * Returns the String 'type'.
   * @return the String 'type'.
   */
  String getShapeType();


}
