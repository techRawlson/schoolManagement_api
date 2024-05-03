package NEWStudentApi.Services;

import NEWStudentApi.Entities.Student;
import NEWStudentApi.Entities.StudentImage;
import NEWStudentApi.Repo.StudentImageRepository;
import NEWStudentApi.Repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class ImageService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentImageRepository studentImageRepository;

    public void saveImage(Long studentId, MultipartFile file) throws IOException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        StudentImage studentImage = new StudentImage();
        studentImage.setImage(file.getBytes());
        studentImage.setFilename(file.getOriginalFilename());
        studentImage.setMimetype(file.getContentType());
        studentImage.setUploadDate(LocalDateTime.now());
        studentImage.setStudent(student);

        studentImageRepository.save(studentImage);
    }
    public void updateImage(Long studentId, MultipartFile file) throws IOException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        StudentImage studentImage = studentImageRepository.findByStudentId(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found for student"));

        studentImage.setImage(file.getBytes());
        studentImage.setFilename(file.getOriginalFilename());
        studentImage.setMimetype(file.getContentType());
        studentImage.setUploadDate(LocalDateTime.now());

        studentImageRepository.save(studentImage);
    }
    public byte[] getImageByStudentId(Long studentId) {
        StudentImage studentImage = studentImageRepository.findByStudentId(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found for student"));

        return studentImage.getImage();
    }
}
