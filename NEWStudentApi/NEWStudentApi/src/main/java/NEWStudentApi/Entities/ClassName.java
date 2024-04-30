package NEWStudentApi.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ClassName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
    private String section;
    private String session;

    public ClassName(String className, String section, String session){
        this.className=className;
        this.section=section;
        this.session=session;
    }
    public ClassName(){
    }


}
