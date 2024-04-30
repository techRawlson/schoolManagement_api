package DownloadDataStudent.Services;

import DownloadDataStudent.Entities.Student;
import DownloadDataStudent.Entities.TimeTable;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class ExcelServiceTimetable {
    public byte[] generateExcelData(List<TimeTable> timeTables) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Create a new workbook
            Workbook workbook = new XSSFWorkbook();

            // Create a sheet in the workbook
            Sheet sheet = workbook.createSheet("TimeTable");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("lectureNumber");
            headerRow.createCell(2).setCellValue("startTime");
            headerRow.createCell(3).setCellValue("endTime");
            headerRow.createCell(4).setCellValue("Monday");
            headerRow.createCell(5).setCellValue("Tuesday");
            headerRow.createCell(6).setCellValue("wednesday");
            headerRow.createCell(7).setCellValue("thursday");
            headerRow.createCell(8).setCellValue("friday");

            // Write data rows
            int rowNum = 1;
            for (TimeTable timeTable : timeTables) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(timeTable.getId());
                row.createCell(1).setCellValue(timeTable.getLectureNumber());
                row.createCell(2).setCellValue((timeTable.getStartTime()));
                row.createCell(3).setCellValue((timeTable.getEndTime()));
                row.createCell(4).setCellValue(timeTable.getMonday());
                row.createCell(5).setCellValue(timeTable.getTuesday());
                row.createCell(6).setCellValue(timeTable.getWednesday());
                row.createCell(7).setCellValue(timeTable.getThursday());
                row.createCell(8).setCellValue(timeTable.getFriday());
            }

            // Auto size columns
            for (int i = 0; i < 9; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write workbook content to output stream
            workbook.write(outputStream);

            // Close the workbook
            workbook.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            // Handle exceptions if any
            e.printStackTrace();
            return new byte[0];
        }
    }
}
