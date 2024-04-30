package DownloadDataStudent.Entities;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class TimeTable {
    private Long id;
    private String Monday;
    private String Tuesday;
    private String Wednesday;
    private String Thursday;
    private String Friday;
    private String startTime;
    private String endTime;
    private int lectureNumber;
    private String session;
    private String className;
    private String section;
}
