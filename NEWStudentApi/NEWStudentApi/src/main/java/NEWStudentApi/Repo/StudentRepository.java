package NEWStudentApi.Repo;


import NEWStudentApi.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findStudentByName(String name);
    boolean existsByNameAndFathersName(String name, String fathersName);
    @Query("SELECT COUNT(s) FROM Student s WHERE s.name = :name AND s.className = :className AND s.section = :section AND s.rollNumber = :rollNumber")
    int countByDetails(@Param("name") String name, @Param("className") String className, @Param("section") char section, @Param("rollNumber") int rollNumber);
}

