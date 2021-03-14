package com.project.readinghelper.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.readinghelper.domain.UserInfoEntity;

@Service
public class ExcelService {
	
	@Value("${file.upload.excel.path}")
	private String filePath;

	public String getSheetFormload(Map param) throws IOException {
		System.out.println("파람:" + param);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("사용자 리스트");  //엑셀파일 아래의 title지정
		XSSFRow row = null;
		XSSFCell cell = null;
		int rowNo = 0;
		System.out.println("1번");
		// 테이블 헤더용 스타일
		XSSFCellStyle headStyle = wb.createCellStyle();

		// 가는 경계선을 가집니다.
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		
		//배경색은 DARK YELLOW
		headStyle.setFillForegroundColor(HSSFColorPredefined.GOLD.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		//데이터는 가운데로 정렬
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		
		//데이터용 경계 스타일 테두리만 지정
		CellStyle bodyStyle = wb.createCellStyle();
		
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);

		// 헤더 생성
		row = sheet.createRow(rowNo++);
		String[]headerParam = param.get("header").toString().split(",");
		for(int i = 0; i < headerParam.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(headStyle);
			cell.setCellValue(headerParam[i]);
		}

		//데이터 부분 생성
		List<String> bodyList = (List<String>) param.keySet().stream().collect(Collectors.toList());
		
		System.out.println("bodyList:" + bodyList);
		/*row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(bodyStyle);
		cell.setCellValue(resultDto.getGetTime());
		cell = row.createCell(1);
		cell.setCellStyle(bodyStyle);
		cell.setCellValue(resultDto.getGuestLength());
		*/
		rowNo++;
		
		//데이터 부분 생성
		/*for(GuestDto guestDto: resultDto.getGuestList()) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(guestDto.getId());
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(guestDto.getPw());
			
		}*/

		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = dayTime.format(new Date(time));
		
		String localFile = filePath;
		
		File file = new File(localFile);
		
		if(!file.exists()) file.mkdirs();
		
		localFile += "\\" + param.get("fileName").toString() + str + ".xlsx";		
		file = new File(localFile);
		
		FileOutputStream fos = null;
		fos = new FileOutputStream(file);
		wb.write(fos);

		if (wb != null)	wb.close();
		if (fos != null) fos.close();
		return localFile;
		
	}
}
