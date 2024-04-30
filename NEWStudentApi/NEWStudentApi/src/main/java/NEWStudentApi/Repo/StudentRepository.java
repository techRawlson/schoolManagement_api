package NEWStudentApi.Repo;


import NEWStudentApi.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findStudentByName(String name);
    boolean existsByNameAndFathersName(String name, String fathersName);

}

