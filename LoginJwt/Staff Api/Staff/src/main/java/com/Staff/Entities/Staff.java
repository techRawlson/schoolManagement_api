package com.Staff.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String profilePicture;





    @ElementCollection

    @CollectionTable(name = "staff_subjects", joinColumns = @JoinColumn(name = "staff_id"))
    @Column(name = "subject")
    private List<String> subjects = new ArrayList<>();
}
