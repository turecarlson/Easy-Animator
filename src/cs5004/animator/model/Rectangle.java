package cs5004.animator.model;

import java.awt.Color;

/**
 * Concrete-class for a Rectangle Object. Extends the AbstractShape abstract class. Its main
 * function is to hold information about its SVG and Text representation, but can also change it's
 * size through the scale method.
 */
public class Rectangle extends AbstractShape {

  String svgType; //The tag for the shape in SVG.
  String xSVG; //The x attribute for the shape in SVG.
  String ySVG; //The y attribute for the shape in SVG.
  String m1SVG; //The width attribute for the shape in SVG.
  String m2SVG; //The height attribute for the shape in SVG.

  /**
   * Constructor for an Rectangle Object. Takes in all necessary information and passes to the
   * super(). Takes a Color Object as a parameter for color.
   *
   * @param name      - The name given to the Rectangle object.
   * @param xPosition - The x-coordinate of the lower-left corner of the Rectangle Object.
   * @param yPosition - The y-coordinate of the lower-left corner of the Rectangle Object.
   * @param width     - The length from edge to edge of the Rectangle Object on the x-axis.
   * @param height    - The length from edge to edge of the Rectangle Object on the y-axis.
   * @param color     - The color of the Rectangle Object.
   * @param inTime    - The time value at which the Rectangle Object appears.
   * @param outTime   - The time value at which the Rectangle Object disappears.
   */
  public Rectangle(String name, int xPosition, int yPosition, int width,
                   int height, Color color, int inTime, int outTime) {
    super(name, xPosition, yPosition, width, height, color, inTime, outTime);
    this.svgType = "</rect>";
    this.xSVG = "x";
    this.ySVG = "y";
    this.m1SVG = "width";
    this.m2SVG = "height";
    setType("rectangle");
  }

  /**
   * Constructor for a Rectangle Object. Takes in all necessary information and passes to the
   * super(). Takes RGB values as parameters for color.
   *
   * @param name      - The name given to the Rectangle object.
   * @param xPosition - The x-coordinate of the lower-left corner of the Rectangle Object.
   * @param yPosition - The y-coordinate of the lower-left corner of the Rectangle Object.
   * @param width     - The length from edge to edge of the Rectangle Object on the x-axis.
   * @param height    - The length from edge to edge of the Rectangle Object on the y-axis.
   * @param colorR    - A float value representing the red in an RGB color value.
   * @param colorG    - A float value representing the green in an RGB color value.
   * @param colorB    - A float value representing the blue in an RGB color value.
   * @param inTime    - The time value at which the Rectangle Object appears.
   * @param outTime   - The time value at which the Rectangle Object disappears.
   */
  public Rectangle(String name, int xPosition, int yPosition, int width,
                   int height, float colorR, float colorG, float colorB, int inTime,
                   int outTime) {
    super(name, xPosition, yPosition, width, height, new Color(colorR, colorG, colorB),
            inTime, outTime);

    this.svgType = "</rect>";
    this.xSVG = "x";
    this.ySVG = "y";
    this.m1SVG = "width";
    this.m2SVG = "height";
    setType("rectangle");
  }

  /**
   * Constructor for a Rectangle Object. Takes in all necessary information and passes to the
   * super(). Takes RGB values (type int) as parameters for color.
   *
   * @param name      - The name given to the Rectangle object.
   * @param xPosition - The x-coordinate of the lower-left corner of the Rectangle Object.
   * @param yPosition - The y-coordinate of the lower-left corner of the Rectangle Object.
   * @param width     - The length from edge to edge of the Rectangle Object on the x-axis.
   * @param height    - The length from edge to edge of the Rectangle Object on the y-axis.
   * @param colorR    - A int value representing the red in an RGB color value.
   * @param colorG    - A int value representing the green in an RGB color value.
   * @param colorB    - A int value representing the blue in an RGB color value.
   * @param inTime    - The time value at which the Rectangle Object appears.
   * @param outTime   - The time value at which the Rectangle Object disappears.
   */
  public Rectangle(String name, int xPosition, int yPosition, int width,
                   int height, int colorR, int colorG, int colorB, int inTime,
                   int outTime) {
    super(name,  xPosition, yPosition, width, height, new Color(colorR, colorG, colorB),
            inTime, outTime);

    this.svgType = "</rect>";
    this.xSVG = "x";
    this.ySVG = "y";
    this.m1SVG = "width";
    this.m2SVG = "height";
    setType("rectangle");
  }

  /**
   * Gets the value representing the width of the Rectangle Object.
   *
   * @return - super.m1 - The variable representing the width of the Rectangle Object.
   */
  public int getWidth() {
    return getM1();
  }

  /**
   * Gets the value representing the height of the Rectangle Object.
   *
   * @return - super.m2 - The variable representing the height of the Rectangle Object.
   */
  public int getHeight() {
    return getM2();
  }

  /**
   * Changes the size values of the Rectangle object.
   *
   * @param width  - The new width of the Rectangle Object.
   * @param height - The new Height of the Rectangle Object.
   */
  @Override
  public void scale(int width, int height) {
    super.scale(width, height);
  }

  /**
   * Returns the Rectangle Object as a formatted string. Overrides Object.toString().
   *
   * @return a string representing the Oval Object, of the following format: Name: R Type: rectangle
   *            Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
   *            Appears at t=1 Disappears at t=100.
   */
  @Override
  public String toString() {
    return "Name: " + getName() + "\n"
            + "Type: " + "rectangle" + "\n"
            + "Min corner: (" + getXPosition() + "," + getYPosition()
            + "), "
            + "Width: " + getWidth() + ", Height: " + getHeight() + ", "
            + "Color: (" + getColor().getRed() + "," + getColor().getGreen() + ","
            + getColor().getBlue() + ")\n"
            + "Appears at t=" + getInTime() + "\n"
            + "Disappears at t=" + getOutTime();
  }

  /**
   * Creates a string representing the shape in SVG.
   *
   * @return a string representing the shape in SVG.
   */
  public String getSVG(int xLeft, int yTop) {

    String colors = "rgb(" + super.getColor().getRed() + "," + super.getColor().getGreen() + "," +
            super.getColor().getBlue() + ")\"";

    String svgRep = "";
    svgRep += "<rect id=\"" + getName() + "\" x=\"" + (super.getXPosition() - xLeft) + "\" y=\"" +
            (super.getYPosition() - yTop)
            + "\" width=\"" + super.getM1() + "\" height=\"" + super.getM2() + "\" fill=\"" + colors
            + " visibility=\"visible\" >";

    return svgRep;
  }


  /**
   * A getter method for the SVG tag of the shape.
   *
   * @return The tag for the shape in SVG.
   */
  @Override
  public String getSVGType() {
    return svgType;
  }

  /**
   * A getter method for the x attribute for the shape in SVG.
   *
   * @return the x attribute for the shape in SVG.
   */
  @Override
  public String getXSVG() {
    return xSVG;
  }

  /**
   * A getter method for the y attribute for the shape in SVG.
   *
   * @return the y attribute for the shape in SVG.
   */
  @Override
  public String getYSVG() {
    return ySVG;
  }

  /**
   * A getter method for the width or x radius of the shape in SVG.
   *
   * @return the width attribute for the shape in SVG.
   */
  @Override
  public String getM1SVG() {
    return m1SVG;
  }

  /**
   * A getter method for the height or y radius of the shape in SVG.
   *
   * @return the height attribute for the shape in SVG.
   */
  @Override
  public String getM2SVG() {
    return m2SVG;
  }

  /**
   * Create a shallow copy of the shape.
   * @return a shallow copy of the shape.
   */
  public Shape copy() {
    return new Rectangle(super.getName(), super.getXPosition(), super.getYPosition(),
            super. getM1(), super.getM2(), super.getColor(), super.getInTime(),
            super.getOutTime());
  }

}
