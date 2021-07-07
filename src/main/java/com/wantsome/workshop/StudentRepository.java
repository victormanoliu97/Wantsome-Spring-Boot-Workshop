package com.wantsome.workshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query(value = "UPDATE Student s SET s.lastName = :lastName where s.id = :id")
    @Modifying
    void updateStudentLastName(@PathParam("lastName") String lastName, @PathParam("id") long id);
}
