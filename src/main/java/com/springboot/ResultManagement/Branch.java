package com.springboot.ResultManagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private BranchName branchName;

    private String hodName;

    private String contactNo;

    private int grant;

    private int launchYear;

    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private List<Student> studentList;
}
