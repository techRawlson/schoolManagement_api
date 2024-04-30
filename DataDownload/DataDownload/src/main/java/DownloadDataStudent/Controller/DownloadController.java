package DownloadDataStudent.Controller;

import DownloadDataStudent.Entities.Student;
import DownloadDataStudent.Services.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
//http://localhost:9091/api/download/excel


@RestController
@RequestMapping("/api/download")
public class DownloadController {

    private static final String API_URL = "http://192.168.1.10:8082/api/students/savedData";

    @Autowired
    private ExcelService excelService;

    @GetMapping("/excel")
    public ResponseEntity<Resource> downloadExcel() {
        // Fetch data from API
        List<Student> students = fetchDataFromApi();

        // Generate Excel data
        byte[] excelData = excelService.generateExcelData(students);

        // Set the headers for triggering download
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "data.xlsx");

        // Return Excel data as ResponseEntity
        return ResponseEntity.ok()
                .headers(headers)
                .body(new ByteArrayResource(excelData));
    }

    private List<Student> fetchDataFromApi() {
        // Fetch data from API using RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Student[]> responseEntity = restTemplate.getForEntity(API_URL, Student[].class);
        Student[] studentsArray = responseEntity.getBody();
        return Arrays.asList(studentsArray);
    }
}
