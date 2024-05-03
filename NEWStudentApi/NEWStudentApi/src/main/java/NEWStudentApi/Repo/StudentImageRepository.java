package NEWStudentApi.Repo;

import NEWStudentApi.Entities.StudentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentImageRepository extends JpaRepository<StudentImage, Long> {
    Optional<StudentImage> findByStudentId(Long studentId);

}
