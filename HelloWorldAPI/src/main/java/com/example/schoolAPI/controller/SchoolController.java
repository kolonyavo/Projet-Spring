package com.example.schoolAPI.controller;

import com.example.schoolAPI.model.Students;
import com.example.schoolAPI.repository.StudentsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SchoolController extends StudentsRepository {
    @GetMapping("/students/delete/{id}")
    public void delete(@PathVariable(required = true) Long id){
        deletByID(id);
    }
    @GetMapping("/students/new")
    public Students addStudents(@RequestBody(required = true) Students s){
        return save(s);
    }
    @GetMapping("/students/update")
    public Students updateStudents(@PathVariable(required = true) Long id, String newName){
        return updateNameById(id, newName);
    }
    @GetMapping("/students/")
    public List<Students> allStudents(){
        return findAll();
    }
    @GetMapping("/students/{query}")
    public List<Students> findStudents(@PathVariable(required = true) String query){
        return findWhereNameLike(query);
    }
}

