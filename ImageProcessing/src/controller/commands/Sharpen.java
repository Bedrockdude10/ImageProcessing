package controller.commands;

import controller.ImageController;
import model.modifiers.SharpenModifier;

/**
 * Class to represent the sharpen command. Contains the commandApply() to apply the command.
 */
public class Sharpen extends ACommand {
  /**
   * Constructor for the blue component command. Consumes the current controller to access its
   * versions, the name of the model to be modified, and the name of the new modified model.
   *
   * @param c       the controller passed to this command
   * @param name    the name of the model being modified
   * @param newName the name of the new model created from the modifications
   */
  public Sharpen(ImageController c, String name, String newName)
          throws IllegalArgumentException {
    super(c, name, newName);
  }

  /**
   * Method to apply this command. Gets the map of versions from the controller, modifies the image
   * and puts the modified version in the map of versions under the new name.
   */
  @Override
  public void commandApply() {
    c.getVersions().put(newName, model.newModdedImage(new SharpenModifier()));
  }
}
