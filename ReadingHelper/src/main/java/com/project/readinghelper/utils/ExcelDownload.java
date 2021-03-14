package com.project.readinghelper.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/excel")
public class ExcelDownload {
	
	Logger logger= LoggerFactory.getLogger(ExcelDownload.class);
	
	@Autowired
	ExcelService excelService;
	
	@Value("${file.upload.excel.path}")
	private String path;

	@PostMapping("/exceldownload")
	@ResponseBody
	public void excelDownload(@Validated(Select.class) @RequestBody Map params, HttpServletResponse response) {
		System.out.println("엑셀파람:" + params);

		  //디렉토리 확인
		  File dir = new File(path);
		  if(!dir.isDirectory()) {
		    dir.mkdir();
		  }

		  //파일생성(위에서 설정한 경로에 poi로 임시파일생성 후 경로 return)
		  String filePath = null;
		  try {
		   filePath = excelService.getSheetFormload(params);
		  } catch(IOException e) {
		    if(logger.isErrorEnabled()) {
		      logger.error(e.getMessage(),e);
		    }
		  } catch(Exception e) {
		    if(logger.isErrorEnabled()) {
		      logger.error(e.getMessage(),e);
		    }
		  }

		  SimpleDateFormat formatter_ymdhms = new SimpleDateFormat("yyyyMMddHHmmss");
		  Date curTime = new Date();
		  String fileTime = formatter_ymdhms.format(curTime);
		  FileInputStream fis = null;
		  String fileFullName = params.get("fileName") + "_" + fileTime + ".xlsx";
		  
		  try {
		    response.setHeader("Content-Disposition","attachment;fileName=\""+java.net.URLEncoder.encode(fileFullName,"UTF-8")+"\";");
		    response.setContentType("application/octet-stream");
		    response.setHeader("Content-transfer-Encoding","binary");
		    response.setHeader("mediaType","application/vnd.ms-excel");
		  
		    OutputStream out = null;
		    out = response.getOutputStream();
		    fis = new FileInputStream(filePath);
		    FileCopyUtils.copy(fis,out);
		    out.flush();
		  } catch(FileNotFoundException e) {
		    if(logger.isErrorEnabled()) {
		      logger.error(e.getMessage(),e);
		    }
		  } catch(IOException e) {
		    if(logger.isErrorEnabled()) {
		      logger.error(e.getMessage(),e);
		    }
		  } finally {
		    if(fis != null) {
		      try {
		        fis.close();
		      } catch(IOException e) {
		        logger.error(e.getMessage(),e);
		      }
		    }
		  }

		  //파일삭제
		  File fileDelete = null;
		  try {
		     if(filePath != null) {
		       fileDelete = new File(filePath);
		     }
		  } catch(Exception e) {
		     logger.error(e.getMessage(),e);
		  } finally {
		     fileDelete.delete();
		  }
	}
}
