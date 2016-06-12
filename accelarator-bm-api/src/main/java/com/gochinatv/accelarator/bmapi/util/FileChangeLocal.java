package com.gochinatv.accelarator.bmapi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileChangeLocal {
	
	public File uploadFileLocal(MultipartFile upFile,String filename) { 
		File file = null;
		try {
		long fileSize = upFile.getSize(); 
		/*if(fileSize > 10 * 1024 * 1024){ 
			return null; 
		} */
		String suffix = "";
		if (filename != null && !filename.equals("")) {
			suffix = filename.substring(
					filename.lastIndexOf(".") + 1,
					filename.length());
		} else {
			suffix = "jpg";
		}
		String newFileName = getUUID()+"_default."+suffix; 
		//
		
		 file = new File(PropertiesUtil.getInstance().getProperty("tennis.file.temp.path") + newFileName); 
		
			upFile.transferTo(file);
		} catch (IllegalStateException e) {
			System.out.println("======error333:"+e.getMessage());
		} catch (IOException e) {
			System.out.println("======error444:"+e.getMessage());
		}
		/*byte[] buffer = new byte[1024]; 
		int n = 0; 
		try {
			FileOutputStream fos = new FileOutputStream(file); 
			InputStream fis = upFile.getInputStream(); 
			while ((n = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, n); 
			} 
			fos.close(); 
			fis.close(); 
		}catch(IOException e){ 
			e.printStackTrace(); 
		} */
		return file; 
	}
	

	public String file2String(File file) {  
        BufferedReader br;  
        StringBuilder strBlder = new StringBuilder("");  
        try {  
            br = new BufferedReader(new InputStreamReader(new FileInputStream(  
                    file)));  
            String line = "";  
            while (null != (line = br.readLine())) {  
                strBlder.append(line);  
            }  
            br.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        return strBlder.toString();  
    }  
	
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
	}
	
//	public static void main(String[] args){
//		FileChangeLocal fcl = new FileChangeLocal();
//		JSONObject jj = JSONObject.parseObject(fcl.file2String(new File("/data/youtube/secret_ready/test.gochinatv.com.json")));
//		System.out.println(jj.getJSONObject("installed").getString("client_id"));
//	}

}
