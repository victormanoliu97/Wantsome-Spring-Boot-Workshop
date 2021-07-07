package com.wantsome.workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "addNewStudent")
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping(value = "getStudent/{id}")
    public Student getStudentById(@PathVariable long id) {
        return studentService.findStudentById(id);
    }

    @DeleteMapping(value = "deleteStudent/{id}")
    public void deleteStudentById(@PathVariable long id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping(value = "updateStudentLastName/{id}")
    public void updateStudentLastName(@PathVariable long id, @RequestParam("lastName") String lastName) {
        studentService.updateStudentLastName(lastName, id);
    }

    @PutMapping(value = "updateLastName/{id}")
    public ResponseEntity<Student> updateLastName(@PathVariable long id, @RequestParam("lastName") String lastName) {
        Optional<Student> student = studentService.updateLastName(lastName, id);
        if(!student.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }
}
