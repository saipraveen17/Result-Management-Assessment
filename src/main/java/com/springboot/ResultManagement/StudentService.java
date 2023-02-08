package com.springboot.ResultManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BranchRepository branchRepository;

    public List<String> listOfContactOfHodsWithMaxPassingStudents() {

        List<Branch> branchListOfMaxPassStudents = findMaxStudentPassingBranches();

        List<String> listOfHodContact = new ArrayList<>();

        //Add contact numbers of Hods with max passing students and return that list.
        for(Branch branch : branchListOfMaxPassStudents) {
            listOfHodContact.add(branch.getContactNo());
        }

        return listOfHodContact;
    }

    public int getGrantForBranchWithMaxPassingStudents() {

        List<Branch> branchListOfMaxPassStudents = findMaxStudentPassingBranches();

        //Find grant given to branch with max passing students, added if there are multiple branches.
        int grant = 0;
        for(Branch branch : branchListOfMaxPassStudents) {
            grant += branch.getGrant();
        }

        return grant;
    }

    public List<Integer> getRollNoOfStudentsFromLatestBranch() {

        List<Branch> branchList = branchRepository.findAll();

        //Find the latest branch year.
        int latestYear = 0;
        for(Branch branch : branchList) {
            if(branch.getLaunchYear()>latestYear) {
                latestYear = branch.getLaunchYear();
            }
        }

        //Add all the student rollNo with latest branch and return
        List<Integer> listOfRollNo = new ArrayList<>();
        for(Branch branch : branchList) {
            if(branch.getLaunchYear()==latestYear) {
                for(Student student : branch.getStudentList()) {
                    listOfRollNo.add(student.getRollNo());
                }
            }
        }
        return listOfRollNo;
    }

    public List<Branch> findMaxStudentPassingBranches() {

        //Get all branch list from Db.
        List<Branch> branchList = branchRepository.findAll();

        //Map to count of passed students for particular branch.
        Map<Branch, Integer> countMap = new HashMap<>();

        //Update the count
        for(Branch branch : branchList) {
            for(Student student : branch.getStudentList()) {
                if(student.getMarks()>=40) {
                    countMap.put(branch, countMap.getOrDefault(branch,0)+1);
                }
            }
        }

        //Find max count
        int getMaxCountOfPassStudents = 0;
        for(int count : countMap.values()) {
            if(count>getMaxCountOfPassStudents) {
                getMaxCountOfPassStudents = count;
            }
        }

        //Now find list of branches having max passing students.
        List<Branch> branchesWithMaxPassStudents = new ArrayList<>();

        for(Branch branch : countMap.keySet()) {
            if(countMap.get(branch)==getMaxCountOfPassStudents) {
                branchesWithMaxPassStudents.add(branch);
            }
        }

        return branchesWithMaxPassStudents;
    }
}
