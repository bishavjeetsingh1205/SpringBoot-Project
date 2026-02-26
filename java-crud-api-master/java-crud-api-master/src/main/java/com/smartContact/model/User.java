package com.smartContact.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;


@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "User_id")
    private int id;
    
    @Column(name = "User_name")
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "User_Email", unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "User_Phone")
    private String phone;
    
    @Column(name = "User_Password")
    @NotBlank(message = "Password is required")
    private String password;
    
    @Column(name = "User_Role")
    private String role;
    
    @Column(name = "User_About", length = 500)
    private String about;
    
    @Column(name = "User_Address")
    private String address;
    
    @Column(name = "User_City")
    private String city;
    
    @Column(name = "User_State")
    private String state;
    
    @Column(name = "User_Country")
    private String country;
    
    @Column(name = "User_Status")
    private String status;
    
    @Column(name = "Created_At", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "Updated_At")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
//
//
//
//    public String getAbout() {
//        return about;
//    }
//
//    public void setAbout(String about) {
//        this.about = about;
//    }


}
