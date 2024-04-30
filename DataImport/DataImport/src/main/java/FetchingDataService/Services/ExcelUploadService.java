package FetchingDataService.Services;

import FetchingDataService.Entities.ImportedData;
import FetchingDataService.Repository.ImportedDataRepository;
import NEWStudentApi.Entities.Student;
import NEWStudentApi.Repo.StudentRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;

// Service
@Service
public class ExcelUploadService {
    @Autowired
    private ImportedDataRepository importedDataRepository;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
     Student student;
    public void uploadData(MultipartFile file) throws IOException {
        // Read Excel file
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        // Iterate through rows
        for (Row row : sheet) {
            // Skip header row
            if (row.getRowNum() == 0) continue;

            // Map Excel row to ImportedData entity
            ImportedData importedData = mapRowToImportedData(row);

            // Check if data already exists in StudentData table
            if (!isDataExistsInStudentData(importedData)) {
                // Transfer data to StudentData table
                transferDataToStudentData(importedData);
            }

            // Save imported data regardless
            saveImportedData(importedData);
        }

        workbook.close();
    }

    private ImportedData mapRowToImportedData(Row row) {
        ImportedData importedData = new ImportedData();
        importedData.setName(row.getCell(0).getStringCellValue());
        importedData.setFathersName(row.getCell(1).getStringCellValue());
        // Map other fields similarly
        return importedData;
    }

    private boolean isDataExistsInStudentData(ImportedData importedData) {
        return studentRepository.existsByNameAndFathersName(importedData.getName(), importedData.getFathersName());
    }

    private void transferDataToStudentData(ImportedData importedData) {
        Student studentData = new Student();
        // Map fields from importedData to studentData
        studentData.setName(importedData.getName());
        studentData.setFathersName(importedData.getFathersName());
        // Map other fields similarly
        studentRepository.save(studentData);
    }

    private void saveImportedData(ImportedData importedData) {
        importedDataRepository.save(importedData);
    }
}

