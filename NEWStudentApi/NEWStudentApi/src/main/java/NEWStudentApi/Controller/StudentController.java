package NEWStudentApi.Controller;

import NEWStudentApi.Entities.ClassName;
import NEWStudentApi.Entities.Student;
import NEWStudentApi.Entities.StudentDTO;
import NEWStudentApi.Repo.ClassRepository;
import NEWStudentApi.Repo.StudentRepository;
import NEWStudentApi.Services.ClassService;
import NEWStudentApi.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    private static final String IMAGE_UPLOAD_DIR = "C:\\Users\\HP\\Desktop\\git FOlders\\New Student Git\\MicroServices\\New student\\image";



//    @PutMapping("/update-image/{studentId}")
//    public ResponseEntity<String> updateImage(@PathVariable Long studentId,
//                                              @RequestParam("image") MultipartFile file) {
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("Please select a file to upload.");
//        }
//
//        // Check if student exists
//        Student student = studentService.findById(studentId);
//        if (student == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Process file update
//        try {
//            // Get the path of the directory where images are stored
//            String uploadDir = "C:/Users/HP/Desktop/StudentApi/Images"; // Adjust this path as per your setup
//
//            // Create the directory if it doesn't exist
//            File directory = new File(uploadDir);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//
//            // Get the filename
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//            // Check if the file name already exists for another student
//            // You may want to handle this case differently
//            // For simplicity, let's assume each student has a unique image name
//            if (student.getProfilePicture() != null && !student.getProfilePicture().equals(fileName)) {
//                // Delete the existing image file
//                Files.deleteIfExists(Paths.get(uploadDir + File.separator + student.getProfilePicture()));
//            }
//
//            // Save the new image file
//            Path filePath = Paths.get(uploadDir + File.separator + fileName);
//            Files.write(filePath, file.getBytes());
//
//            // Update the student's profile picture in the database
//            student.setProfilePicture(fileName);
//            studentService.save(student);
//
//            // Build URL for accessing updated image
//
//            return ResponseEntity.ok().body("File uploaded Successfully");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update image.");
//        }
//    }

//    @PostMapping("/create-student")
//    public ResponseEntity<?> createStudent(@RequestParam(value = "name", required = true) String name,
//                                           @RequestParam("fathersName") String fathersName, @RequestParam("sex") String sex,
//                                           @RequestParam("mobile") Long mobile, @RequestParam("address") String address,
//                                           @RequestParam("className") String className, @RequestParam("section") char section,
//                                           @RequestParam("admissionYear") int admissionYear, @RequestParam("dob") String dob,
//                                           @RequestParam("category") String category, @RequestParam("email") String email,
//                                           @RequestParam("rollNumber") int rollNumber, @RequestParam("enrollmentNumber") Long enrollmentNumber,
//                                           @RequestParam("session") int session) throws IOException {
//
//        if (name == null || name.isEmpty()) {
//            return ResponseEntity.badRequest().body("Student name is required");
//        }
//
//        // Check if a student with the same name, class, section, and roll number already exists
//        if (studentService.isDuplicateStudent(name, className, section, rollNumber)) {
//            return ResponseEntity.badRequest().body("A student with the same name, class, section, and roll number already exists");
//        }
//
//        // Create the student object
//        Student student = new Student();
//        student.setName(name);
//        student.setFathersName(fathersName);
//        student.setSex(sex);
//        student.setMobile(mobile);
//        student.setAddress(address);
//        student.setClassName(className);
//        student.setSection(section);
//        student.setAdmissionYear(admissionYear);
//        student.setDob(dob);
//        student.setCategory(category);
//        student.setEmail(email);
//        student.setRollNumber(rollNumber);
//        student.setEnrollmentNumber(enrollmentNumber);
//        student.setSession(session);
//
//        student = studentRepository.save(student);
//
//        // Return the saved student object
//        return ResponseEntity.ok(student);
//    }


    @GetMapping("/savedData")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentData(@PathVariable Long studentId) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            StudentDTO studentDTO = StudentDTO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .fathersName(student.getFathersName())
                    .sex(student.getSex())
                    .mobile(student.getMobile())
                    .address(student.getAddress())
                    .className(student.getClassName())
                    .section(student.getSection())
                    .admissionYear(student.getAdmissionYear())
                    .dob(student.getDob())
                    .category(student.getCategory())
                    .email(student.getEmail())
                    .rollNumber(student.getRollNumber())
                    .session(student.getSession())
                    .enrollmentNumber(student.getEnrollmentNumber())
                    .build();
            return new ResponseEntity<>(studentDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private StudentDTO convertToDto(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getFathersName(),
                student.getSex(),
                student.getMobile(),
                student.getAddress(),
                student.getClassName(),
                student.getSection(),
                student.getAdmissionYear(),
                student.getDob(),
                student.getCategory(),
                student.getEmail(),
                student.getRollNumber(),
                student.getSession(),
                student.getEnrollmentNumber()
        );
    }

    //    @GetMapping("/image/{studentId}")
//    public ResponseEntity<byte[]> getImageByStudentId(@PathVariable("studentId") Long studentId) {
//        // Fetch the student from the database using the student ID
//        // Assuming studentRepository is autowired
//        Optional<Student> optionalStudent = studentRepository.findById(studentId);
//        if (optionalStudent.isPresent()) {
//            Student student = optionalStudent.get();
//            String imageName = student.getProfilePicture();
//            Path imagePath = Paths.get(IMAGE_UPLOAD_DIR, imageName);
//            try {
//                // Read the image bytes from the file
//                byte[] imageBytes = Files.readAllBytes(imagePath);
//                // Return the image bytes with appropriate headers
//                return ResponseEntity.ok()
//                        .contentType(MediaType.IMAGE_JPEG) // Adjust content type if necessary
//                        .body(imageBytes);
//            } catch (IOException e) {
//                // Handle file read error
//                e.printStackTrace();
//                return ResponseEntity.notFound().build();
//            }
//        } else {
//            // Student not found
//            return ResponseEntity.notFound().build();
//        }
//    }


//    @GetMapping("/{studentId}")
//    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Long studentId) {
//        Optional<Student> optionalStudent = studentRepository.findById(studentId);
//        if (optionalStudent.isPresent()) {
//            Student student = optionalStudent.get();
//            return ResponseEntity.ok().body(student);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PutMapping("update-student/{studentId}")
    public ResponseEntity<?> updateStudentDetails(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        // Ensure that the studentId in the URL matches the ID of the updatedStudent
        if (!studentId.equals(updatedStudent.getId())) {
            return ResponseEntity.badRequest().body("Student ID in URL does not match ID in request body");
        }

        // Call the service method to update student details
        try {
            Optional<Student> updated = studentService.updateStudentDetails(studentId, updatedStudent);
            if (updated.isPresent()) { // Check if the Optional contains a value
                return ResponseEntity.ok(updated.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update student details");
        }
    }

//    public ResponseEntity<Student> updateStudentById(@PathVariable("studentId") Long studentId,
//                                                     @RequestParam(value = "name", required = false) String name,
//                                                     @RequestParam(value = "fathersName", required = false) String fathersName,
//                                                     @RequestParam(value = "sex", required = false) String sex,
//                                                     @RequestParam(value = "mobile", required = false) Long mobile,
//                                                     @RequestParam(value = "address", required = false) String address,
//                                                     @RequestParam(value = "className", required = false) String className,
//                                                     @RequestParam(value = "section", required = false) Character section,
//                                                     @RequestParam(value = "admissionYear", required = false) Integer admissionYear,
//                                                     @RequestParam(value = "dob", required = false) String dob,
//                                                     @RequestParam(value = "category", required = false) String category,
//                                                     @RequestParam(value = "email", required = false) String email,
//                                                     @RequestParam(value = "rollNumber", required = false) Integer rollNumber,
//                                                     @RequestParam(value = "session", required = false) Integer session,
//                                                     @RequestParam(value = "enrollmentNumber", required = false) Long enrollmentNumber) {
//        Optional<Student> optionalStudent = studentRepository.findById(studentId);
//        if (optionalStudent.isPresent()) {
//            Student existingStudent = optionalStudent.get();
//
//            // Update the image if provided
//
//
//            // Update other student fields if provided
//            if (name != null) {
//                existingStudent.setName(name);
//            }
//            if (fathersName != null) {
//                existingStudent.setFathersName(fathersName);
//            }
//            if (sex != null) {
//                existingStudent.setSex(sex);
//            }
//            if (mobile != null) {
//                existingStudent.setMobile(mobile);
//            }
//            if (address != null) {
//                existingStudent.setAddress(address);
//            }
//            if (className != null) {
//                existingStudent.setClassName(className);
//            }
//            if (section !=null) {
//                existingStudent.setSection(section);
//            }
//            if (admissionYear != null) {
//                existingStudent.setAdmissionYear(admissionYear);
//            }
//            if (dob != null) {
//                existingStudent.setDob(dob);
//            }
//            if (category != null) {
//                existingStudent.setCategory(category);
//            }
//            if (email != null) {
//                existingStudent.setEmail(email);
//            }
//            if (rollNumber != null) {
//                existingStudent.setRollNumber(rollNumber);
//            }
//            if (session != null) {
//                existingStudent.setSession(session);
//            }
//
//            if (enrollmentNumber != null) {
//                existingStudent.setEnrollmentNumber(enrollmentNumber);
//            }
//
//            // Save the updated student object to the database
//            Student savedStudent = studentRepository.save(existingStudent);
//            return ResponseEntity.ok().body(savedStudent);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok("Student with ID " + id + " deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete student with ID " + id + ": " + e.getMessage());
        }
    }


    @PostMapping("/bulk")
    public ResponseEntity<String> uploadBulkStudents(@RequestBody List<Student> students) {
        try {
            studentService.saveBulkStudents(students);
            return ResponseEntity.status(HttpStatus.OK).body("Bulk student data uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload bulk student data: " + e.getMessage());
        }
    }
    @Autowired
    private ClassService classService;
    @Autowired
    private ClassRepository classRepository;

//    public ClassName createClass(@RequestParam("classname") String name, @RequestParam("section") String section){
//        return classService.createName(name,section);
//    }
@PostMapping("/create-class")
public ResponseEntity<?> createClass(@RequestParam("classname") String name, @RequestParam("section") String section, @RequestParam("session") String session) {
    // Check if the class with the same name already exists
    if (classService.hasClassWithName(name)) {
        // If the class name already exists, check if the section is different
        if (classService.hasClassWithNameAndSection(name, section)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Class with the same name and section already exists");
        }
    }

    // If the class name doesn't exist or the section is different, proceed to create the class
    ClassName className = classService.createName(name, section,session);
    return ResponseEntity.status(HttpStatus.CREATED).body(className);
}




    @GetMapping("/get-AllClasses")
    public List<ClassName> getAll(){
        return classService.getClassName();
    }

    @PutMapping("/update-class/section")
    public void updateClass(
            @RequestParam String className,
            @RequestParam String section,
            @RequestParam String session
    ) {
        classService.updateClass(className, section,session);
    }
    @DeleteMapping("/delete-class/{id}")
    public ResponseEntity<Void> deleteClassName(@PathVariable Long id) {
        try {
            classRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/upload-excel")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file) {
        return studentService.processExcelFile(file);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Parameter '" + ex.getName() + "' with value '" + ex.getValue() +
                "' could not be converted to type '" + ex.getRequiredType().getSimpleName() + "'";
        return ResponseEntity.badRequest().body(message);
    }


}


