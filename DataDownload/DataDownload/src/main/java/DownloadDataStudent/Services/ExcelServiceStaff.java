package DownloadDataStudent.Services;

import DownloadDataStudent.Entities.Staff;
import DownloadDataStudent.Entities.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
@Service
public class ExcelServiceStaff {
    public byte[] generateExcelData(List<Staff> staff) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // Create a new workbook
            Workbook workbook = new XSSFWorkbook();

            // Create a sheet in the workbook
            Sheet sheet = workbook.createSheet("staff");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("gender");
            headerRow.createCell(3).setCellValue("mobile");
            headerRow.createCell(4).setCellValue("dateOfJoining");
            headerRow.createCell(5).setCellValue("address");
            headerRow.createCell(6).setCellValue("dob");
            headerRow.createCell(7).setCellValue("designation");
            headerRow.createCell(8).setCellValue("email");
            headerRow.createCell(9).setCellValue("staffId");
            headerRow.createCell(10).setCellValue("department");


            // Write data rows
            int rowNum = 1;
            for (Staff staff1: staff) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(staff1.getId());
                row.createCell(1).setCellValue(staff1.getName());
                row.createCell(2).setCellValue(staff1.getGender());
                row.createCell(3).setCellValue(staff1.getMobile());
                row.createCell(4).setCellValue(staff1.getDateOfJoining());
                row.createCell(5).setCellValue(staff1.getAddress());
                row.createCell(6).setCellValue(staff1.getDob());
                row.createCell(7).setCellValue(staff1.getDesignation());
                row.createCell(8).setCellValue(staff1.getEmail());
                row.createCell(9).setCellValue(staff1.getStaffId());
                row.createCell(10).setCellValue(staff1.getDepartment());
            }

            // Auto size columns
            for (int i = 0; i < 11; i++) {
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
