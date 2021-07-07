package com.wantsome.workshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudentById(long id) {
        return studentRepository.getById(id);
    }

    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudentLastName(String lastName, long id) {
        if(!studentRepository.findById(id).isPresent()) {
            throw new RuntimeException("Student was not found");
        }
        studentRepository.updateStudentLastName(lastName, id);
    }

    public Optional<Student> updateLastName(String lastName, long id) {
        Optional<Student> foundStudent = studentRepository.findById(id);
        if(!foundStudent.isPresent()) {
            return Optional.empty();
        }
        Student studentToUpdate = foundStudent.get();
        studentToUpdate.setLastName(lastName);

        return Optional.of(studentRepository.save(studentToUpdate));
    }
}
