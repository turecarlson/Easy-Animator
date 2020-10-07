package cs5004.animator.controller;

import java.io.IOException;

/**
 * Interface for a Controller for our animation model: handles user actions by executing them using
 * the model and conveying the impact to the user through the view.
 */
public interface Controller {

  /**
   * Returns an int representing the current tick (time/frame) value of the animation.
   * @return an int representing the current tick (time/frame) value of the animation.
   */
  int getTick();

  /**
   * Function to be executed when the playButton in a View is pressed. Starts the Animation.
   */
  void playButton();

  /**
   * Function to be executed when the stopButton in a View is pressed. Stops the Animation
   * and sets tick to zero.
   */
  void stopButton();

  /**
   * Function to be executed when the playButton in a View is pressed. Pauses the Animation.
   */
  void pauseButton();

  /**
   * Function to be executed when the playButton in a View is pressed. Starts the Animation
   * over from tick 0.
   */
  void restartButton();

  /**
   * Function to be executed when the playButton in a View is pressed. Increases the speed of the
   * Animation by a multiple of 2.
   */
  void speedUpButton();

  /**
   * Function to be executed when the playButton in a View is pressed. Decreases the speed of the
   * Animation by half.
   */
  void speedDownButton();

  /**
   * Generates a .svg file representation of the Animation.
   *
   * @throws IOException if there is an issue writing to the outFile using render().
   */
  void generateSVGButton() throws IOException;

  /**
   * Starts the timer in the Controller.
   */
  void startTimer();

  /**
   * Function to be called to start the Animation.
   */
  void launch();


}
