package DownloadDataStudent.Services;

import DownloadDataStudent.Entities.Student;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExcelService {

    public byte[] generateExcelData(List<Student> students) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Create a new workbook
            Workbook workbook = new XSSFWorkbook();

            // Create a sheet in the workbook
            Sheet sheet = workbook.createSheet("Students");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Roll No.");
            headerRow.createCell(3).setCellValue("Class");
            headerRow.createCell(4).setCellValue("Section");
            headerRow.createCell(5).setCellValue("Category");
            headerRow.createCell(6).setCellValue("Sex");

            // Write data rows
            int rowNum = 1;
            for (Student student : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(student.getId());
                row.createCell(1).setCellValue(student.getName());
                row.createCell(2).setCellValue(student.getRollNumber());
                row.createCell(3).setCellValue(student.getClassName());
                row.createCell(4).setCellValue(student.getSection());
                row.createCell(5).setCellValue(student.getCategory());
                row.createCell(6).setCellValue(student.getSex());
            }

            // Auto size columns
            for (int i = 0; i < 7; i++) {
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
