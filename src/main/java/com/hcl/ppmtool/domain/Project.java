package com.hcl.ppmtool.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;


    @NotBlank(message = "Project name is required")
    @Getter @Setter private String projectName;

    @NotBlank(message ="Project Identifier is required")
    @Size(min=4, max=5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    @Getter @Setter private String projectIdentifier;

    @NotBlank(message = "Project description is required")
    @Getter @Setter private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Getter @Setter private Date start_date;


    @JsonFormat(pattern = "yyyy-mm-dd")
    @Getter @Setter private Date end_date;


    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    @Getter @Setter private Date created_At;


    @JsonFormat(pattern = "yyyy-mm-dd")
    @Getter @Setter private Date updated_At;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    @Getter @Setter private Backlog backlog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @Getter @Setter private User user;


    @Getter @Setter private String projectLeader;





    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }


    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }

}
