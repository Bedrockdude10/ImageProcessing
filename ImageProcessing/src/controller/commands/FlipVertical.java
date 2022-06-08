package controller.commands;

import controller.ImageControllerImpl;
import model.modifiers.FlipModifier;

public class FlipVertical extends ACommand {

  public FlipVertical(ImageControllerImpl c, String name, String newName) {
    super(c, name, newName);
  }

  @Override
  public void go() {
    c.getVersions().put(newName, model.applyFilter(new FlipModifier(true)));
  }
}
