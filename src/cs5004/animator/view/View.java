package cs5004.animator.view;

import java.io.IOException;

/**
 * An interface to represent our four views, visual, playback, textual, and SVG. It provides one
 * method, render, that executes the display of each view.
 */
public interface View {

  /**
   * Render causes each animation view to be displayed visually, outputted to STDOUT, or to be
   * written to a file if specified.
   *
   * @throws IOException if there is an issue writing to the file.
   */
  void render() throws IOException;
}
