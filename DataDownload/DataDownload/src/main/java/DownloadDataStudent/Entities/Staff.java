package DownloadDataStudent.Entities;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Staff {
    private Long id;
    private String name;
    private String gender;
    private Long mobile;
    private LocalDate dateOfJoining;
    private String address;
    private LocalDate dob;
    private String designation;
    private String email;
    private String staffId;
    private String department;
}
