package com.gochinatv.accelarator.bmapi.util.imageUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class AmazonS3Tools {
	private static Logger logger = LoggerFactory.getLogger(AmazonS3Tools.class);

	/**
	 * 
	 * @param suffix
	 *            文件类型
	 * @param file
	 *            文件
	 * @return
	 * @throws ProgramException
	 */
	public static String uploadFileToAmazon(String suffix, File file) throws ProgramException {
		String returnFileName = "";
		AmazonS3 s3 = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
		Region usWest2 = Region.getRegion(Regions.US_EAST_1);
		s3.setRegion(usWest2);
		try {
			ObjectMetadata om = new ObjectMetadata();
			if (suffix.equalsIgnoreCase("jpg")) {
				om.setContentType("image/jpeg");
			} else if (suffix.equalsIgnoreCase("gif")) {
				om.setContentType("image/gif");
			} else if (suffix.equalsIgnoreCase("png")) {
				om.setContentType("image/png");
			} else {
				om.setContentType("image/jpeg");
			}

			om.setContentLength(file.length());
			InputStream fileInputStream = new FileInputStream(file);
			PutObjectRequest por = new PutObjectRequest("img.vego.tv", "/" + file.getName(), fileInputStream, om);
			s3.putObject(por);
			fileInputStream.close();
			Random random = new Random();
			returnFileName = "http://" + "img" + random.nextInt(10) +".gochinatv.com" + "/" + file.getName();
			logger.info("success:" + returnFileName);
		} catch (Exception e) {
			logger.error("上传图片至亚马逊出错,错误信息:", e);
			throw new ProgramException("上传图片至亚马逊出错", e);
		}
		return returnFileName.replace("img0.gochinatv.com", "image.vegocdn.com");
	}

	public static void main(String[] args) {
		Long start = System.currentTimeMillis();
		uploadFileToAmazon("png", new File("E:/33.png"));
		Long end = System.currentTimeMillis();
		System.out.println((end - start));
	}

}
