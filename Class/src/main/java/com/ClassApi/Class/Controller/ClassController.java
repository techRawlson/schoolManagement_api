package com.ClassApi.Class.Controller;

import com.ClassApi.Class.Entities.ClassInfo;
import com.ClassApi.Class.Repository.ClassInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
//http://localhost:8085/class
@CrossOrigin(
        origins = {
                "http://localhost:5173/"
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
@RestController
@RequestMapping("/class")
public class ClassController {

    private final ClassInfoRepository classInfoRepository;

    @Autowired
    public ClassController(ClassInfoRepository classInfoRepository) {
        this.classInfoRepository = classInfoRepository;
    }

    @PostMapping("/create-class")
    public ResponseEntity<ClassInfo> createClass(@RequestBody ClassInfo classInfo) {
        ClassInfo savedClassInfo = classInfoRepository.save(classInfo);
        return new ResponseEntity<>(savedClassInfo, HttpStatus.CREATED);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<ClassInfo> getClass(@PathVariable Long id) {
        ClassInfo classInfo = classInfoRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Class not found with id: " + id));
        return new ResponseEntity<>(classInfo, HttpStatus.OK);
    }

    @GetMapping("/AllClasses")
    public ResponseEntity<List<ClassInfo>> getAllClasses() {
        List<ClassInfo> classInfoList = classInfoRepository.findAll();
        return new ResponseEntity<>(classInfoList, HttpStatus.OK);
    }
}
