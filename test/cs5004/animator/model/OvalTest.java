package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A series of tests for the methods of Oval Objects.
 */
public class OvalTest {
  private Oval testOval1;
  private Oval testOval2;


  /**
   * Constructs Oval objects to be used in the testing suite.
   */
  @Before
  public void setUp() {
    testOval1 = new Oval("C", 1, 1, 3, 4,
            1.0f, 0.0f, 0.5f, 5, 10);
    testOval2 = new Oval("O", 5, 5, 10, 6,
            Color.pink, 7, 30);
  }

  /**
   * Tests the getXRadius() method of the Oval Object.
   */
  @Test
  public void getXRadius() {
    assertEquals(3, testOval1.getXRadius(), .001);
    assertEquals(10, testOval2.getXRadius(), .001);
  }

  /**
   * Tests the getYRadius() method of the Oval Object.
   */
  @Test
  public void getYRadius() {
    assertEquals(4, testOval1.getYRadius(), .001);
    assertEquals(6, testOval2.getYRadius(), .001);
  }

  /**
   * Tests the scale() method of the Oval Object.
   */
  @Test
  public void scale() {
    testOval1.scale(4, 10);
    assertEquals(4, testOval1.getXRadius(), .001);
    assertEquals(10, testOval1.getYRadius(), .001);
  }

  /**
   * Tests the toString() method of the Oval Object.
   */
  @Test
  public void testToString() {
    assertEquals("Name: C\n"
            + "Type: oval\n"
            + "Center: (1,1), X radius: 3, Y radius: 4 Color: (255,128,0)\n"
            + "Appears at t=5\n"
            + "Disappears at t=10", testOval1.toString());
  }

  @Test
  public void getSVGType() {
    assertEquals("</ellipse>", testOval1.getSVGType());
  }

  @Test
  public void getXSVG() {
    assertEquals("cx", testOval1.getXSVG());
  }

  @Test
  public void getYSVG() {
    assertEquals("cy", testOval1.getYSVG());
  }

  @Test
  public void getM1SVG() {
    assertEquals("rx", testOval2.getM1SVG());
  }

  @Test
  public void getM2SVG() {
    assertEquals("ry", testOval2.getM2SVG());
  }

  @Test
  public void getSVG() {
    testOval2 = new Oval("C", 440, 70, 120, 60,
            new Color(0, 0, 255), 10, 20);
    assertEquals("<ellipse id=\"C\" cx=\"240\" cy=\"0\" rx=\"120\" ry=\"60\" " +
            "fill=\"rgb(0,0,255)\" " +
            "visibility=\"visible\" >", testOval2.getSVG(200, 70));
  }

  @Test

  public void copy() {

    Oval test1 = new Oval("O", 1, 1, 3, 4,
            1.0f, 0.0f, 0.5f, 5, 10);

    Oval test2 = (Oval) test1.copy();

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