package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A series of tests for the AbstractShape class.
 */
public class AbstractShapeTest {
  private Oval testOval1;
  private Oval testOval2;

  /**
   * Sets up Shape Objects for use in the following tests.
   */
  @Before
  public void setUp() {
    testOval1 = new Oval("C", 1, 1, 3, 4,
            1.0f, 0.0f, 0.5f, 5, 10);
    testOval2 = new Oval("O", 5, 5, 10, 6,
            Color.pink, 7, 30);
  }

  /**
   * Tests that the Constructor throws the proper exceptions.
   */
  @Test
  public void testBadConstructor() {
    try {
      Oval badOval = new Oval("", 1, 1, 3, 4,
              1, 0, 5, 5, 10);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }

    try {
      Oval badOval = new Oval(null, 1, 1, 3, 4,
              1, 0, 5, 5, 10);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }

    try {
      Oval badOval = new Oval("T", 1, 1, 0, 4,
              1, 0, 5, 5, 10);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }
    try {
      Oval badOval = new Oval("T", 1, 1, -1, 4,
              1, 0, 5, 5, 10);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }

    try {
      Oval badOval = new Oval("T", 1, 1, 1, 0,
              1, 0, 5, 5, 10);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }

    try {
      Oval badOval = new Oval("T", 1, 1, 1, -1,
              1, 0, 5, 5, 10);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }

    try {
      Oval badOval = new Oval("T", 1, 1, 1, 4,
              null, 5, 10);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }

    try {
      Oval badOval = new Oval("T", 1, 1, 1, 4,
              1, 0, 5, -1, 10);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }

    try {
      Oval badOval = new Oval("T", 1, 1, 1, 4,
              1, 0, 5, 5, 0);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }

    try {
      Oval badOval = new Oval("T", 1, 1, 1, 4,
              1, 0, 5, 5, -1);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }

    try {
      Oval badOval = new Oval("T", 1, 1, 1, 4,
              1, 0, 5, 5, 3);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if exception is thrown.
    }
  }

  /**
   * Tests the getName() method of Shape Objects.
   */
  @Test
  public void getName() {
    assertEquals("C", testOval1.getName());
    assertEquals("O", testOval2.getName());
  }

  /**
   * Tests the getXPosition() method of Shape Objects.
   */
  @Test
  public void getXPosition() {
    assertEquals(1, testOval1.getXPosition(), .001);
    assertEquals(5, testOval2.getXPosition(), .001);
  }

  /**
   * Tests the getYPosition() method of Shape Objects.
   */
  @Test
  public void getYPosition() {
    assertEquals(1, testOval1.getYPosition(), .001);
    assertEquals(5, testOval2.getYPosition(), .001);
  }

  /**
   * Tests the getM1() method of Shape Objects.
   */
  @Test
  public void getM1() {
    assertEquals(3, testOval1.getM1(), .001);
    assertEquals(10, testOval2.getM1(), .001);
  }

  /**
   * Tests the getM2() method of Shape Objects.
   */
  @Test
  public void getM2() {
    assertEquals(4, testOval1.getM2(), .001);
    assertEquals(6, testOval2.getM2(), .001);
  }

  /**
   * Tests the getColor() method of Shape Objects.
   */
  @Test
  public void getColor() {
    assertEquals(new Color(1.0f, 0.0f, 0.5f), testOval1.getColor());
    assertEquals(Color.pink, testOval2.getColor());
  }

  /**
   * Tests the getInTime() method of Shape Objects.
   */
  @Test
  public void getInTime() {
    assertEquals(5, testOval1.getInTime());
    assertEquals(7, testOval2.getInTime());
  }

  /**
   * Tests the getOutTime() method of Shape Objects.
   */
  @Test
  public void getOutTime() {
    assertEquals(10, testOval1.getOutTime());
    assertEquals(30, testOval2.getOutTime());
  }

  /**
   * Tests the setInTime() method of Shape Objects.
   */
  @Test
  public void setInTime() {
    testOval1.setInTime(2);
    assertEquals(2, testOval1.getInTime());

    try {
      testOval1.setInTime(-1);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.setOutTime(70);
      testOval1.setInTime(75);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }
  }

  /**
   * Tests the setOutTime() method of Shape Objects.
   */
  @Test
  public void setOutTime() {
    testOval1.setOutTime(7);
    assertEquals(7, testOval1.getOutTime());

    try {
      testOval1.setOutTime(-1);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.setOutTime(0);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.setOutTime(3);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }
  }


  /**
   * Tests the scale() method of Shape Objects.
   */
  @Test
  public void scale() {
    testOval1.scale(65, 65);
    assertEquals(65, testOval1.getM1(), .001);
    assertEquals(65, testOval1.getM2(), .001);

    try {
      testOval1.scale(0, 1);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.scale(-1, 1);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.scale(1, 0);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.scale(1, -1);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }
  }

  /**
   * Tests the changeColor() method of Shape Objects.
   */
  @Test
  public void changeColor() {
    Color testColor = new Color(22, 24, 26);
    testOval1.changeColor(22, 24, 26);
    assertEquals(testColor, testOval1.getColor());

    testOval2.changeColor(Color.black);
    assertEquals(Color.black, testOval2.getColor());

    try {
      testOval1.changeColor(null);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.changeColor(-1, 0, 0);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.changeColor(256, 0, 0);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.changeColor(0, -1, 0);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.changeColor(0, 256, 0);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.changeColor(0, 0, -1);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }

    try {
      testOval1.changeColor(0, 0, 256);
      fail("An IllegalArgumentException should have been thrown.");
    } catch (IllegalArgumentException e) {
      //Test passes if Exception is thrown.
    }


  }

  /**
   * Tests the move() method of Shape Objects.
   */
  @Test
  public void move() {
    testOval1.move(45, 65);
    assertEquals(45, testOval1.getXPosition(), .001);
    assertEquals(65, testOval1.getYPosition(), .001);
  }

}