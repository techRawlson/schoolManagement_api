package NEWStudentApi.Entities;



import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name = "Student_Data")
public class Student {
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

    private String profilePicture;




    // Explicit getter and setter for Name
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    // Explicit getter and setter for Fathers_Name
//    public String getFathersName() {
//        return fathersName;
//    }
//
//    public void setFathersName(String fathersName) {
//        this.fathersName = fathersName;
//    }
    public Student(String name, String fathersName, String sex, Long mobile, String address, String className,
                   char section, int admissionYear, LocalDate dob, String category, String email,
                   int rollNumber, Long enrollmentNumber) {
        this.name = name;
        this.fathersName = fathersName;
        this.sex = sex;
        this.mobile = mobile;
        this.address = address;
        this.className = className;
        this.section = section;
        this.admissionYear = admissionYear;
        this.dob = dob;
        this.category = category;
        this.email = email;
        this.rollNumber = rollNumber;
        this.enrollmentNumber = enrollmentNumber;
    }
}
