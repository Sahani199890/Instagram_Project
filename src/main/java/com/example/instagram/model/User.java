package com.example.instagram.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name="first_Name")
    private String fName;
    @Column(name="last_Name")
    private String lName;
    @Column(name="age")
    private Integer age;
    @Column(name="email")
    private String email;
    @Column(name="number")
    private String number;
}
