package NEWStudentApi.Entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
