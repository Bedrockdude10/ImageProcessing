package model.modifiers;

import model.ImageModel;

/**
 *
 */
public class BrightnessModifier implements Modifier {
  private int value;

  public BrightnessModifier(int value) {
    if (value < -255 || value > 255) {
      throw new IllegalArgumentException("Value cannot be less than -255 or greater than 255");
    }
    this.value = value;
  }

  /**
   * Applies a modifier to an image and returns the new modified image.
   */
  @Override
  public ImageModel apply(ImageModel model) {
    ImageModel build = new ImageModel(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int red = model.getPixel(i, j).getRGB()[0] + value;
        int green = model.getPixel(i, j).getRGB()[1] + value;
        int blue = model.getPixel(i, j).getRGB()[2] + value;
//        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
//          throw new IllegalArgumentException("Brightness value is invalid");
//        }
//        build.assignPixels(i, j, red, green, blue);
        // alt-design choice
        build.assignPixels(i, j, Math.min(255, red), Math.min(255, green), Math.min(255, blue));
      }
    }
    return build;
  }
}