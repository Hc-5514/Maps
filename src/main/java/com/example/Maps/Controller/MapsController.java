package com.example.Maps.Controller;

import com.example.Maps.Dto.ExcelDto;
import com.example.Maps.Dto.SelectDto;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MapsController {

    @GetMapping("/")
    public String Maps() {
        return "/index";
    }

    @PostMapping("/select")
    public String Select(@RequestParam("file") MultipartFile file, SelectDto selectDto, Model model)
            throws IOException {

        List<ExcelDto> excelDtoList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        String selectTime = selectDto.getSelectHour() + ":" + selectDto.getSelectMinute();

        for (int i = 5; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            if(!selectDto.getSelectDate().equals((int)row.getCell(0).getNumericCellValue()))
                continue;

            if(!selectTime.equals(row.getCell(1).getStringCellValue()))
                continue;

            ExcelDto excelDto = new ExcelDto();
            excelDto.setDate((int)row.getCell(0).getNumericCellValue());
            excelDto.setTime(row.getCell(1).getStringCellValue());
            excelDto.setSpot(row.getCell(2).getStringCellValue());
            excelDto.setTemp(row.getCell(4).getNumericCellValue());
            excelDto.setWind(row.getCell(5).getNumericCellValue());
            excelDto.setHumidity((int)row.getCell(6).getNumericCellValue());
            excelDto.setRain(row.getCell(3).getStringCellValue());

            excelDtoList.add(excelDto);
        }
        model.addAttribute("excelDtoList", excelDtoList);
        return "/maps";
    }
}
