package cs5004.animator.view;

import cs5004.animator.model.ReadOnlyModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A textual view that outputs a written description of the animation to a file or standard out.
 */
public class Text implements View {
  private String outFile;
  ReadOnlyModel model;

  /**
   * Construct the view using data from the model.
   *
   * @param model Our model in an MVC architecture.
   */
  public Text(ReadOnlyModel model) {

    this.model = model;
    outFile = model.getOutFile();
  }

  /**
   * Sets the outFile for the View.
   * @param outFile - Where the render() will write to.
   */
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

  /**
   * Returns the outFile for the view.
   * @return the outFile for the view.
   */
  public String getOutFile() {
    return outFile;
  }

  /**
   * Outputs the text output to either the console, or an outFile defined in the AnimationModel.
   *
   * @throws IOException if the provided outFile in the AnimationModel cannot be found.
   */
  public void render() throws IOException {

    //Output the file to standard out if a file is not given, otherwise write the file
    if (outFile.equalsIgnoreCase("System.out")) {
      System.out.println(model.outputDescription());
    } else {
      BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
      writer.write(model.outputDescription());
      writer.close();
    }
  }
}
