package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.ImageModel;
import view.TextView;

/**
 * Class to represent the controller for an image.
 */
public class ImageControllerImpl {
  private final ImageModel model;
  private final TextView view;
  private final Readable input;
  private final Map versions;

  /**
   * Constructor for this implementation of an image controller.
   * @param model the model being controlled
   * @param view the view of this model being controlled
   * @param input the input being given to the controller
   * @throws IllegalArgumentException
   */
  public ImageControllerImpl(ImageModel model, TextView view, Readable input)
          throws IllegalArgumentException {
    if (model == null || view == null || input == null) {
     throw new IllegalArgumentException("Parameters cannot be null");
    }
    this.model = model;
    this.view = view;
    this.input = input;
    this.versions = new HashMap<String, ImageModel>();
  }

  public void playGame() throws IllegalStateException {
    Scanner scanner = new Scanner(input);



    // when a filter is applied
    // make filter object
    // apply it, and add the returned model to versions:
    // versions.put(new name, new model)
  }
}
