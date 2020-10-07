package cs5004.animator.model;

import java.awt.Color;

/**
 * Concrete-class for an Oval Object. Extends the AbstractShape abstract class. Its main function is
 * to hold information about its SVG and Text representation, but can also change it's size through
 * the scale method.
 */
public class Oval extends AbstractShape {

  String svgType; //The tag for the shape in SVG.
  String xSVG; //The x attribute for the shape in SVG.
  String ySVG; //The y attribute for the shape in SVG.
  String m1SVG; //The width attribute for the shape in SVG.
  String m2SVG; //The height attribute for the shape in SVG.

  /**
   * Constructor for an Oval Object. Takes in all necessary information and passes to the super().
   * Takes a Color Object as a parameter for color.
   *
   * @param name      - The name given to the Oval object.
   * @param xPosition - The x-coordinate of the center of the Oval Object.
   * @param yPosition - The y-coordinate of the center of the Oval Object.
   * @param xRadius   - The distance from the center to the edge of the Oval Object on the x-axis.
   * @param yRadius   - The distance from the center to the edge of the Oval Object on the y-axis.
   * @param color     - The color of the Oval Object.
   * @param inTime    - The time value at which the Oval Object appears.
   * @param outTime   - The time value at which the Oval Object disappears.
   */
  public Oval(String name, int xPosition, int yPosition, int xRadius,
              int yRadius, Color color, int inTime, int outTime) {
    super(name, xPosition, yPosition, xRadius, yRadius, color, inTime, outTime);

    this.svgType = "</ellipse>";
    this.xSVG = "cx";
    this.ySVG = "cy";
    this.m1SVG = "rx";
    this.m2SVG = "ry";
    setType("oval");

  }

  /**
   * Constructor for an Oval Object. Takes in all necessary information and passes to the super().
   * Takes RGB values (type float) as parameters for color.
   *
   * @param name      - The name given to the Oval object.
   * @param xPosition - The x-coordinate of the center of the Oval Object.
   * @param yPosition - The y-coordinate of the center of the Oval Object.
   * @param xRadius   - The distance from the center to the edge of the Oval Object on the x-axis.
   * @param yRadius   - The distance from the center to the edge of the Oval Object on the y-axis.
   * @param colorR    - A float value representing the red in an RGB color value.
   * @param colorG    - A float value representing the green in an RGB color value.
   * @param colorB    - A float value representing the blue in an RGB color value.
   * @param inTime    - The time value at which the Oval Object appears.
   * @param outTime   - The time value at which the Oval Object disappears.
   */
  public Oval(String name, int xPosition, int yPosition, int xRadius,
              int yRadius, float colorR, float colorG, float colorB,
              int inTime, int outTime) {
    super(name, xPosition, yPosition, xRadius, yRadius, new Color(colorR, colorG, colorB),
            inTime, outTime);

    this.svgType = "</ellipse>";
    this.xSVG = "cx";
    this.ySVG = "cy";
    this.m1SVG = "rx";
    this.m2SVG = "ry";
    setType("oval");
  }

  /**
   * Constructor for an Oval Object. Takes in all necessary information and passes to the super().
   * Takes RGB values (type int) as parameters for color.
   *
   * @param name      - The name given to the Oval object.
   * @param xPosition - The x-coordinate of the center of the Oval Object.
   * @param yPosition - The y-coordinate of the center of the Oval Object.
   * @param xRadius   - The distance from the center to the edge of the Oval Object on the x-axis.
   * @param yRadius   - The distance from the center to the edge of the Oval Object on the y-axis.
   * @param colorR    - A int value representing the red in an RGB color value.
   * @param colorG    - A int value representing the green in an RGB color value.
   * @param colorB    - A int value representing the blue in an RGB color value.
   * @param inTime    - The time value at which the Oval Object appears.
   * @param outTime   - The time value at which the Oval Object disappears.
   */
  public Oval(String name, int xPosition, int yPosition, int xRadius,
              int yRadius, int colorR, int colorG, int colorB,
              int inTime, int outTime) {
    super(name, xPosition, yPosition, xRadius, yRadius, new Color(colorR, colorG, colorB),
            inTime, outTime);

    this.svgType = "</ellipse>";
    this.xSVG = "cx";
    this.ySVG = "cy";
    this.m1SVG = "rx";
    this.m2SVG = "ry";
    setType("oval");
  }

  /**
   * Gets the value representing the xRadius of the Oval Object.
   *
   * @return - super.m1 - The variable representing the xRadius of the Oval Object.
   */
  public int getXRadius() {
    return getM1();
  }

  /**
   * Gets the value representing the yRadius of the Oval Object.
   *
   * @return - super.m2 - The variable representing the yRadius of the Oval Object.
   */
  public int getYRadius() {
    return getM2();
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

    return new Oval(super.getName(), super.getXPosition(), super.getYPosition(),
              super.getM1(), super.getM2(), getColor(), getInTime(), getOutTime());
  }


  /**
   * Changes the size values of the Oval Object.
   *
   * @param xRadius - The new xRadius of the Oval Object.
   * @param yRadius - The new yRadius of the Oval Object.
   */
  @Override
  public void scale(int xRadius, int yRadius) {
    super.scale(xRadius, yRadius);
  }

  /**
   * Returns the Object as a formatted string. Overrides Object.toString().
   *
   * @return a string representing the Oval Object, of the following format: Name: C Type: oval
   *          Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (ints)(0,0,1) Appears
   *          at t=6 Disappears at t=100.
   */
  @Override
  public String toString() {
    return "Name: " + getName() + "\n"
            + "Type: " + "oval" + "\n"
            + "Center: (" + getXPosition() + "," + getYPosition()
            + "), "
            + "X radius: " + getXRadius() + ", Y radius: " + getYRadius()
            + " Color: (" + getColor().getRed() + "," + getColor().getBlue() + ","
            + getColor().getGreen() + ")\n"
            + "Appears at t=" + getInTime() + "\n"
            + "Disappears at t=" + getOutTime();
  }

  /**
   * Creates a string representing the shape in SVG.
   *
   * @return a string representing the shape in SVG.
   */
  @Override
  public String getSVG(int xLeft, int yTop) {

    String colors = "rgb(" + super.getColor().getRed() + "," + super.getColor().getGreen() + "," +
            super.getColor().getBlue() + ")\"";

    String svgRep = "";
    svgRep += "<ellipse id=\"" + getName() + "\" cx=\"" + (super.getXPosition() - xLeft) + "\" " +
            "cy=\"" +
            (super.getYPosition() - yTop)
            + "\" rx=\"" + super.getM1() + "\" ry=\"" + super.getM2() + "\" fill=\"" + colors
            + " visibility=\"visible\" >";

    return svgRep;
  }
}
