package controller.commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ImageControllerImpl;
import model.Image;
import model.Pixel;


/**
 * Class to represent the save command. Running the command takes the current view and writes
 * it to a new PPM file with the specified file name and path.
 */
public class Save implements Command {

  private final String filename;
  private final Image model;


  /**
   * Constructor for the save command. Consumes a controller to access its list of versions, the
   * name of the file to save, and the name and path of the new file created by saving.
   *
   * @param c        the controller controlling the command
   * @param filename the path and name to save the file to
   * @param name     the name of the PPM file being saved
   */
  public Save(ImageControllerImpl c, String filename, String name) throws IllegalArgumentException {
    if (c == null || filename == null || name == null || c.getVersions().get(name) == null) {
      throw new IllegalArgumentException("Invalid parameters.");
    }
    this.filename = filename;
    this.model = c.getVersions().get(name);
    if (this.model == null) {
      throw new IllegalArgumentException("This name is not in the list of image versions");
    }
  }

  /**
   * This method actually runs the command, and is required of all command objects.
   * The method goes through the path string given to it, and searches for just the name of
   * the file.
   * It then takes this name and writes a new PPM file of the given image
   * with that name in the specified path.
   */
  @Override
  public void commandApply() {
    String fileType = "";
    for (int i = 0; i < filename.length(); i++) {
      if (filename.charAt(i) == '.') {
        fileType = filename.substring(i + 1);
      }
    }
    switch (fileType) {
      case "ppm":
        try {
          File ppm = new File(filename);
          FileWriter ppmWriter = new FileWriter(filename);
          StringBuilder writtenFile = new StringBuilder("P3\n" + this.model.getDimensions()[0]
                  + "\n" + this.model.getDimensions()[1] + "\n255\n");
          for (int i = 0; i < this.model.getDimensions()[1]; i++) {
            for (int j = 0; j < this.model.getDimensions()[0]; j++) {
              Pixel cur = this.model.getPixel(j, i);
              writtenFile.append(cur.getRGB()[0]).append(" ")
                      .append(cur.getRGB()[1]).append(" ").append(cur.getRGB()[2]).append(" ");
            }
            writtenFile.append("\n");
          }
          ppmWriter.write(writtenFile.toString());
          ppmWriter.close();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        break;
      case "png":
      case "bmp":
      case "jpg":
      case "jpeg":
        BufferedImage img = new BufferedImage(model.getDimensions()[0],
                model.getDimensions()[1], BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < img.getWidth(); i++) {
          for (int j = 0; j < img.getHeight(); j++) {
            int[] p = model.getPixel(i, j).getRGB();
            Color c = new Color(p[0], p[1], p[2]);
            img.setRGB(i, j, c.getRGB());
          }
        }
        try {
          ImageIO.write(img, fileType, new File(filename));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        break;
      default:
        throw new IllegalArgumentException("Wrong file type.");
    }
  }
}
