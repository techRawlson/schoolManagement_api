package FetchingDataService.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
public class ImportedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fathersName;
    private String sex;
    private Long mobile;
    private String address;
    private String className;
    private char section;
    private int admissionYear;
    private LocalDate dob;
    private String category;
    private String email;
    private int rollNumber;

    private Long enrollmentNumber;
}
