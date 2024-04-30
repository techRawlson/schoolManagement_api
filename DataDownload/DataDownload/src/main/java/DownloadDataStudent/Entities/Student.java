package DownloadDataStudent.Entities;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Student {
    private Long id;
    private String Name;
    private String sex;
    private int rollNumber;
    private String section;
    private String className;
    private String category;

}
