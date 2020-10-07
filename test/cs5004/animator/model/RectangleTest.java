package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A series of tests for the methods of Rectangle Objects.
 */
public class RectangleTest {
  private Rectangle test1;
  private Rectangle testRectangle2;

  /**
   * Constructs Rectangle Objects to be used in the testing suite.
   */
  @Before
  public void setUp() {

    AnimationModel model = new AnimationModelImpl();
    model.setSpeedMultiplier(1);

    test1 = new Rectangle("R", 1, 1, 3, 4,
            1.0f, 0.0f, 0.5f, 5, 10);
    testRectangle2 = new Rectangle("O", 5, 5, 10, 6,
            Color.pink, 7, 30);
  }

  /**
   * Tests the getWidth() method of the Rectangle Object.
   */
  @Test
  public void getWidth() {
    assertEquals(3, test1.getWidth(), .001);
    assertEquals(10, testRectangle2.getWidth(), .001);
  }

  /**
   * Tests the getHeight() method of the Rectangle Object.
   */
  @Test
  public void getHeight() {
    assertEquals(4, test1.getHeight(), .001);
    assertEquals(6, testRectangle2.getHeight(), .001);
  }

  /**
   * Tests the scale() method of the Rectangle Object.
   */
  @Test
  public void scale() {
    test1.scale(4, 10);
    assertEquals(4, test1.getWidth(), .001);
    assertEquals(10, test1.getHeight(), .001);
  }

  /**
   * Tests the toString() method of the Rectangle Object.
   */
  @Test
  public void testToString() {
    assertEquals("Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (1,1), Width: 3, Height: 4, Color: (255,0,128)\n"
            + "Appears at t=5\n"
            + "Disappears at t=10", test1.toString());
  }

  @Test
  public void getSVG() {
    test1 = new Rectangle("R", 200, 200, 50, 100,
            new Color(255, 0, 0), 0, 10);
    assertEquals("<rect id=\"R\" x=\"0\" y=\"130\" width=\"50\" height=\"100\" " +
            "fill=\"rgb(255,0,0)\" " +
            "visibility=\"visible\" >", test1.getSVG(200, 70));
  }

  @Test
  public void testGetSVGType() {
    assertEquals("</rect>", testRectangle2.getSVGType());
  }

  @Test
  public void testGetXSVG() {
    assertEquals("x", test1.getXSVG());
  }

  @Test
  public void testGetYSVG() {
    assertEquals("y", testRectangle2.getYSVG());
  }

  @Test
  public void testGetM1SVG() {
    assertEquals("width", test1.getM1SVG());
  }

  @Test
  public void testGetM2SVG() {
    assertEquals("height", test1.getM2SVG());
  }

  @Test
  public void copy() {
    test1 = new Rectangle("R", 1, 1, 3, 4,
            1.0f, 0.0f, 0.5f, 5, 10);

    Rectangle test2 = (Rectangle) test1.copy();

    assertEquals(test1.getName(), test2.getName());
    assertEquals(test1.getXPosition(), test2.getXPosition());
    assertEquals(test1.getYPosition(), test2.getYPosition());
    assertEquals(test1.getM1(), test2.getM1());
    assertEquals(test1.getM2(), test2.getM2());
    assertEquals(test1.getColor(), test2.getColor());
    assertEquals(test1.getInTime(), test2.getInTime());
    assertEquals(test1.getOutTime(), test2.getOutTime());

    assertNotEquals(test1, test2);

  }
}