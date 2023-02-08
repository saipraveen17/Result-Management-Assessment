package com.springboot.ResultManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/list-of-hods")
    public ResponseEntity<List<String>> listOfHods() {

        List<String> listOfContacts = studentService.listOfContactOfHodsWithMaxPassingStudents();
        return new ResponseEntity<>(listOfContacts, HttpStatus.OK);
    }

    @GetMapping("/list-of-students")
    public ResponseEntity<List<Integer>> listOfStudents() {

        List<Integer> rollNoList = studentService.getRollNoOfStudentsFromLatestBranch();
        return new ResponseEntity<>(rollNoList, HttpStatus.OK);
    }

    @GetMapping("/get-grant-for-branch")
    public ResponseEntity<Integer> getGrantForBranch() {

        int grant = studentService.getGrantForBranchWithMaxPassingStudents();
        return new ResponseEntity<>(grant, HttpStatus.OK);
    }

}
