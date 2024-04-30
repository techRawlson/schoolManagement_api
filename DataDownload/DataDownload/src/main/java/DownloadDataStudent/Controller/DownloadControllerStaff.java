package DownloadDataStudent.Controller;

import DownloadDataStudent.Entities.Staff;
import DownloadDataStudent.Entities.Student;
import DownloadDataStudent.Services.ExcelServiceStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/download/staff")
public class DownloadControllerStaff {
    @Autowired
    private ExcelServiceStaff excelServiceStaff;


    @GetMapping("/excel")
    public ResponseEntity<Resource> downloadExcel() {
        // Fetch data from API
        List<Staff> staff = fetchDataFromStaffApi();

        // Generate Excel data
        byte[] excelData =excelServiceStaff.generateExcelData(staff);

        // Set the headers for triggering download
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "data.xlsx");

        // Return Excel data as ResponseEntity
        return ResponseEntity.ok()
                .headers(headers)
                .body(new ByteArrayResource(excelData));
    }
    private List<Staff> fetchDataFromStaffApi() {
        // Fetch data from API using RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Staff[]> responseEntity = restTemplate.getForEntity("http://localhost:8083/api/staff/saved-Staff", Staff[].class);
        Staff[] staffArray = responseEntity.getBody();
        return Arrays.asList(staffArray);
    }



}
