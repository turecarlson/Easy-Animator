package cs5004.animator.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.view.AbstractSwingView;
import cs5004.animator.view.View;

/**
 * The EasyAnimator class is our stand in controller for this week. It takes in user input from the
 * command line, parses it, and then creates the model and view. It takes arguments in the form:
 * -in "name-of-animation-file" -view "type-of-view" -out "where-output-show-go" -speed "1". The
 * inputs may be given in any order, but -in and -view are mandatory. If not specified, speed and
 * out will be set at 1 tick per second and System.out, respectively. Additionally, if invalid
 * input is given, a JOptionPane error message will appear.
 */
public final class EasyAnimator {

  AnimationModel myModel;

  /**
   * The main driver of the .jar file. Takes Command line arguments.
   *
   * @param args Command line arguments of the format: -in "name-of-animation-file" -view
   *             "type-of-view" -out "where-output-show-go" -speed "1"
   * @throws IOException if input file cannot be found.
   */
  public static void main(String[] args) throws IOException {

    //Mandatory fields
    String view;
    String in;

    //Optional fields, initialized at defaults.
    String out = "System.out";
    int speed = 1;

    //Convert array to a list
    List<String> input = Arrays.asList(args);

    //-in and -view are mandatory if they are not there throw an error message and exit.
    if (!input.contains("-in") || !input.contains("-view")) {
      JOptionPane.showMessageDialog(null, "The command line arguments " +
                      "you have provided are invalid, please try again with -in and -view.",
              "Missing required input -in and -view", JOptionPane.ERROR_MESSAGE);
      System.exit(-1);
    }

    //Check that there is an input for each field
    if (input.size() % 2 == 1) {
      JOptionPane.showMessageDialog(null, "The command line arguments " +
                      "you have provided are invalid, please try again.",
              "Unequal input arguments", JOptionPane.ERROR_MESSAGE);
      System.exit(-1);
    }

    //Check for invalid flags
    for (String arg : input) {
      if (arg.startsWith("-") && !(arg.contains("-in") || arg.contains("-out") ||
              arg.contains("-speed") || arg.contains("-view"))) {
        JOptionPane.showMessageDialog(null, "You have provided an " +
                        "illegal argument, please try again.", "Invalid input flag provided",
                JOptionPane.ERROR_MESSAGE);
        System.exit(-1);
      }
    }

    //Get the value of the arguments passed into each command.
    in = input.get(input.indexOf("-in") + 1);
    view = input.get(input.indexOf("-view") + 1).toLowerCase();

    if (!(view.equalsIgnoreCase("text")
            || view.equalsIgnoreCase("svg")
            || view.equalsIgnoreCase("visual")
            || view.equalsIgnoreCase("playback"))) {
      JOptionPane.showMessageDialog(null, "You have provided an " +
                      "illegal view, please try again.", "Invalid view",
              JOptionPane.ERROR_MESSAGE);
      System.exit(-1);
    }

    if (input.contains("-speed")) {
      String speedString = input.get(input.indexOf("-speed") + 1);
      try {
        speed = Integer.parseInt(speedString);
      } catch (NumberFormatException n) {
        JOptionPane.showMessageDialog(null, "The command line arguments "
                        + "you have provided are invalid, please try again.", "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        System.exit(-1);
      }
    }

    if (input.contains("-out")) {
      out = input.get(input.indexOf("-out") + 1);
    }

    //Check that the file exists
    FileReader fileReader = null;
    try {
      fileReader = new FileReader(in);
    } catch (FileNotFoundException e) {

      JOptionPane.showMessageDialog(null, "File not found at "
                      + "provided filepath. Please provide a valid input file.",
              "Invalid Input", JOptionPane.ERROR_MESSAGE);
      System.exit(-1);
    }

    //Create AnimationModel using AnimationReader.parseFile
    AnimationModel myModel = cs5004.animator.util.AnimationReader.parseFile(fileReader,
            new AnimationModelImpl.Builder());

    //Set myModel's speedMultiplier. This will be used in properly generating views.
    myModel.setSpeedMultiplier(1000 / speed);

    //Set the outfile for the model for the views that use it
    myModel.setOut(out);

    //Create proper view of Model based command line input.
    View myView = AnimationModelImpl.Factory.build(view, myModel);

    //If the view is visual or playback
    if (myView instanceof AbstractSwingView) {
      SwingController myController = new SwingController(myModel, (AbstractSwingView)myView);
      ((AbstractSwingView) myView).setController(myController);
      myController.launch();
    } else {
      myView.render(); //Creates text-based views
    }
  }
}
