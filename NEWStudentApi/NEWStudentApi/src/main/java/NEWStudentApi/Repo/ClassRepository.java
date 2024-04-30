package NEWStudentApi.Repo;



import NEWStudentApi.Entities.ClassName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface ClassRepository extends JpaRepository<ClassName, Long> {
    Optional<ClassName> findByClassName(String className);
    boolean existsByClassName(String className);
    boolean existsByClassNameAndSection(String className, String section);
}





