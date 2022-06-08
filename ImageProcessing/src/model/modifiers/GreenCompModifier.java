package model.modifiers;

import model.ImageModel;

public class GreenCompModifier implements Modifier {

  public GreenCompModifier() {
  }

  @Override
  public ImageModel apply(ImageModel model) {
    ImageModel build = new ImageModel(model.getDimensions()[0], model.getDimensions()[1]);
    for (int i = 0; i < model.getDimensions()[0]; i++) {
      for (int j = 0; j < model.getDimensions()[1]; j++) {
        int green = model.getPixel(i, j).getRGB()[1];
        build.assignPixels(i, j, green, green, green);
      }
    }
    return build;
  }
}