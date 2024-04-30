package DownloadDataStudent.Controller;

import DownloadDataStudent.Entities.Student;
import DownloadDataStudent.Entities.TimeTable;
import DownloadDataStudent.Services.ExcelServiceTimetable;
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
@RequestMapping("/download/timetable")
public class TimeTableController {
    @Autowired
    private ExcelServiceTimetable excelServiceTimetable;
    @GetMapping("/excel")
    public ResponseEntity<Resource> downloadExcel() {
        // Fetch data from API
        List<TimeTable> timeTables = fetchDataFromApi();

        // Generate Excel data
        byte[] excelData = excelServiceTimetable.generateExcelData(timeTables);

        // Set the headers for triggering download
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "data.xlsx");

        // Return Excel data as ResponseEntity
        return ResponseEntity.ok()
                .headers(headers)
                .body(new ByteArrayResource(excelData));
    }

    private List<TimeTable> fetchDataFromApi() {
        // Fetch data from API using RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TimeTable[]> responseEntity = restTemplate.getForEntity("http://localhost:8086/api/timetable/full-timetable", TimeTable[].class);
        TimeTable[] timeTables = responseEntity.getBody();
        return Arrays.asList(timeTables);
    }
}
