package NEWStudentApi.Services;




import NEWStudentApi.Entities.ClassName;
import NEWStudentApi.Repo.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;
    public ClassName createName(String name, String section, String session){
        ClassName className1=new ClassName(name,section,session);
        return classRepository.save(className1);

    }
    public List<ClassName> getClassName(){
        return classRepository.findAll();
    }
    public Optional<ClassName> getSubjectById(Long id) {
        return classRepository.findById(id);
    }
    @Transactional
    public void updateClass(String className, String section, String session) {
        // Assuming you have a method to find class by name
        ClassName existingClass = classRepository.findByClassName(className)
                .orElseThrow(() -> new IllegalArgumentException("Class not found with name: " + className));

        existingClass.setSection(section);
        classRepository.save(existingClass);
    }
    public boolean hasClassWithName(String className) {
        // Check if a class with the given name exists in the database
        return classRepository.existsByClassName(className);
    }

    public boolean hasClassWithNameAndSection(String className, String section) {
        // Check if a class with the given name and section exists in the database
        return classRepository.existsByClassNameAndSection(className, section);
    }

}
