package NEWStudentApi.Entities;



import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String fathersName;
    private String sex;
    private Long mobile;
    private String address;
    private String className;
    private char section;
    private int admissionYear;
    private String dob;
    private String category;
    private String email;
    private int rollNumber;
    private int session;
    private Long enrollmentNumber;
@Builder
    public StudentDTO(Long id, String name, String fathersName, String sex, Long mobile, String address,
                      String className, char section, int admissionYear, String dob, String category,
                      String email, int rollNumber, int session, Long enrollmentNumber) {
        this.id = id;
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
        this.session = session;
        this.enrollmentNumber = enrollmentNumber;
    }
}

