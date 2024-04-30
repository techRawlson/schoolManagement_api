package NEWStudentApi.Services;
import NEWStudentApi.Entities.Student;
import NEWStudentApi.Repo.StudentRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student fetchStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void createStudent(Student student) {
        // Add any additional business logic if needed
        studentRepository.save(student); // Save the student object to the database
    }
    public void saveBulkStudents(List<Student> students) {
        // Convert DTOs to entities and save them in bulk
        List<Student> entities = students.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        studentRepository.saveAll(entities);
    }

    private Student convertToEntity(Student dto) {
        // Convert StudentDTO to Student entity
        Student student = new Student();
        student.setName(dto.getName());
        student.setFathersName(dto.getFathersName());
        student.setSex(dto.getSex());
        student.setMobile(dto.getMobile());
        student.setAddress(dto.getAddress());
        student.setClassName(dto.getClassName());
        student.setSection(dto.getSection());
        student.setAdmissionYear(dto.getAdmissionYear());
        student.setDob(dto.getDob());
        student.setCategory(dto.getCategory());
        student.setEmail(dto.getEmail());
        student.setRollNumber(dto.getRollNumber());
        student.setEnrollmentNumber(dto.getEnrollmentNumber());
        return student;
    }
    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    public Student save(Student student) {
        return studentRepository.save(student);
    }
    @Value("${api.endpoint.url}")
    private String apiUrl; // This should be defined in your application.properties or application.yml

    public String processExcelFile(MultipartFile file) {
        try {
            // Read Excel file into a Workbook
            Workbook workbook = new XSSFWorkbook(file.getInputStream());

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate over rows
            List<Student> students = new ArrayList<>();
            for (Row row : sheet) {
                // Skip header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Assuming student data is in columns A, B, C of the Excel sheet
                // Assuming the columns in Excel sheet are ordered according to the fields in the Student entity
                String name = row.getCell(0).getStringCellValue();
                String fathersName = row.getCell(1).getStringCellValue();
                String sex = row.getCell(2).getStringCellValue();
                Long mobile = (long) row.getCell(3).getNumericCellValue();
                String address = row.getCell(4).getStringCellValue();
                String className = row.getCell(5).getStringCellValue();
                char section = row.getCell(6).getStringCellValue().charAt(0); // Assuming section is a single character
                int admissionYear = (int) row.getCell(7).getNumericCellValue();
                LocalDate dob = row.getCell(8).getLocalDateTimeCellValue().toLocalDate(); // Assuming date of birth is in date format
                String category = row.getCell(9).getStringCellValue();
                String email = row.getCell(10).getStringCellValue();
                int rollNumber = (int) row.getCell(11).getNumericCellValue();
                Long enrollmentNumber = (long) row.getCell(12).getNumericCellValue();


                // Create Student object and add to list
                students.add(new Student(name, fathersName, sex, mobile, address, className, section, admissionYear, dob, category, email, rollNumber, enrollmentNumber));
            }

            // Create RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

            // Prepare headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Prepare request entity
            HttpEntity<List<Student>> requestEntity = new HttpEntity<>(students, headers);

            // Send POST request to the API endpoint
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);

            // Check response status code
            if (response.getStatusCode() == HttpStatus.OK) {
                return "Data uploaded successfully.";
            } else {
                return "Failed to upload data. Server returned status: " + response.getStatusCodeValue();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to process Excel file.";
        }
    }
}



