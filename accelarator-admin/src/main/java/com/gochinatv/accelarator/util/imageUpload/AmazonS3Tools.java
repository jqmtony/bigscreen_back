package com.gochinatv.accelarator.util.imageUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AmazonS3Tools {
	  private static Logger logger = LoggerFactory.getLogger(AmazonS3Tools.class);

	  public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public static String uploadFileToAmazon(String suffix,  File file)
	  {
		String size ="324X243";
	    String returnFileName = "http://img.vego.tv/common/default.jpg";
	    AmazonS3 s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
	    Region usWest2 = Region.getRegion(Regions.US_EAST_1);
	    s3.setRegion(usWest2);
	    try {
	      String fileName = "common/";
	      fileName = fileName + sdf.format(new Date()) + "/"+file.getName();
	      fileName = fileName + "_" + size + "." + suffix;
	      ObjectMetadata om = new ObjectMetadata();
	      if (suffix.equalsIgnoreCase("jpg"))
	        om.setContentType("image/jpeg");
	      else if (suffix.equalsIgnoreCase("gif"))
	        om.setContentType("image/gif");
	      else if (suffix.equalsIgnoreCase("png"))
	        om.setContentType("image/png");
	      else
	        om.setContentType("image/jpeg");

	      om.setContentLength(file.length());
	      InputStream fileInputStream = new FileInputStream(file);
	      PutObjectRequest por = new PutObjectRequest("img.vego.tv", fileName, fileInputStream, om);
	      s3.putObject(por);
	      fileInputStream.close();
	      returnFileName = "http://" + "img.gochinatv.com".replace("img", getRandomImg()) + "/" + fileName;
	      System.out.println("success:" + returnFileName);
	    } catch (AmazonServiceException ase) {
	      System.out.println("Request ID:       " + ase.getRequestId());
	    } catch (AmazonClientException ace) {
	      System.out.println("Error Message: " + ace.getMessage());
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return returnFileName;
	  }

	  private static String getRandomImg() {
			Random random = new Random();
	    return "img" +  random.nextInt(10) ;
	  }

}
