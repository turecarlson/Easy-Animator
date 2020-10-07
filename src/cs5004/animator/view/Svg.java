package cs5004.animator.view;

import cs5004.animator.model.AnimationCommand;
import cs5004.animator.model.ReadOnlyModel;
import cs5004.animator.model.Shape;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A view representing the animation as an SVG file. It relies on the shapes and commands all
 * knowing how to represent themselves in SVG. The representation will either be output as a file
 * or to standard out if no file is specified.
 */
public class Svg implements View {
  private String outFile;
  ReadOnlyModel model;
  String svgRepresentation;

  /**
   * Construct an SVG view using data from the model.
   *
   * @param model the model of our animation.
   */
  public Svg(ReadOnlyModel model) {

    this.model = model;
    outFile = model.getOutFile();

    //Create the header of the animation.
    String animation = "<svg width=\"" + model.getBoundsWidth() + "\" height=\""
            + model.getBoundsHeight()
            + "\" version=\"1.1\"\n\txmlns=\"http://www.w3.org/2000/svg\">\n\n";

    //Declare each shape
    for (Shape shape : model.getShapeList()) {
      animation += shape.getSVG(model.getXLeft(), model.getYTop()) + "\n";

      //Add all the corresponding commands for that shape, formatted.
      for (AnimationCommand command : model.getCommandList()) {
        if (command.getShape().equals(shape)) {
          if (command.getSVG(model) != null) {
            animation += command.getSVG(model);
          }
        }
      }
      //Add the closing tag for the shape.
      animation += shape.getSVGType() + "\n";
    }
    //Add the closing tag for the animation.
    animation += "</svg>";

    //Assign the string to an instance variable for later use.
    this.svgRepresentation = animation;
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
   * Returns the svgRepresentation to either the console, or writes to a defined outFile.
   *
   * @throws IOException if the outFile cannot be found.
   */
  public void render() throws IOException {

    //Output the file to standard out if a file is not given, otherwise write the file
    if (outFile.equalsIgnoreCase("System.out")) {
      System.out.println(this.svgRepresentation);
    } else {
      BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
      writer.write(this.svgRepresentation);
      writer.close();
    }
  }

}
