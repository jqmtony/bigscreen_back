package com.gochinatv.accelarator.bmapi.util.imageUpload;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import magick.ImageInfo;
import magick.MagickImage;

import org.imgscalr.Scalr;

public class ImageTool
{

  public InputStream createThumbnail(File F, String suffix, int width, int height) throws Exception
  {
    InputStream is = null;
    Image img = null;
    try {
      img = ImageIO.read(F);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (img.getWidth(null) == -1) {
      System.out.println(" can't read,retry!<BR>");
    }
    else
    {
      int new_w = width;
      int new_h = height;
      BufferedImage buffImg = new BufferedImage(new_w, new_h, 1);

      Graphics g = buffImg.createGraphics();
      g.setColor(Color.white);
      g.fillRect(0, 0, new_w, new_h);
      g.drawImage(img, 0, 0, new_w, new_h, null);
      g.dispose();

      ByteArrayOutputStream bs = new ByteArrayOutputStream();
      try
      {
        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);

        ImageIO.write(buffImg, suffix, imOut);

        is = new ByteArrayInputStream(bs.toByteArray());
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return is;
  }

  public static String getUUID() {
    String s = UUID.randomUUID().toString();

    return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
  }

  public File createThumbnailNew(File f, String suffix, int width, int height) throws Exception
  {
    File file = scalr(f, suffix, width, height);
    return file;
  }

  public File createThumb(File f, String suffix, int width, int height) {
    System.out.println("im4java fileName=" + f.getName() + ",suffix=" + suffix + ",width=" + width + ",height=" + height);
    ImageInfo info = null;
    MagickImage image = null;
    MagickImage scaled = null;
    String tmpName = f.getAbsolutePath().replace(f.getName(), getUUID() + "." + suffix);
    try
    {
      String file = f.getAbsolutePath();
      info = new ImageInfo(file);
      image = new MagickImage(info);
      scaled = image.scaleImage(width, height);
      scaled.setFileName(tmpName);
      scaled.writeImage(info);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (scaled != null)
        scaled.destroyImages();
    }

    System.out.println("createThumb dst=" + tmpName);
    return new File(tmpName);
  }


  public File scalr(File f, String suffix, int width, int heigth) {
    System.out.println("scalr fileName=" + f.getName() + ",suffix=" + suffix + ",width=" + width + ",height=" + heigth);
    String dst = f.getAbsolutePath().replace(f.getName(), getUUID() + "." + suffix);
    try {
      BufferedImage img = ImageIO.read(f);
      BufferedImage scaledImg = Scalr.resize(img, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_EXACT, width, heigth, new BufferedImageOp[0]);
      //Scalr.resize(img, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, 360, 480, new BufferedImageOp[0]);
      File destFile = new File(dst);
      ImageIO.write(scaledImg, suffix, destFile);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("scalr dst=" + dst);
    return new File(dst);
  }
}