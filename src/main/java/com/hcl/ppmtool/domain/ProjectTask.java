package com.hcl.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Entity
@NoArgsConstructor
public class ProjectTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column(updatable = false, unique = true)
    @Getter @Setter private String projectSequence;

    @NotBlank(message = "Please include a project summary")
    @Getter @Setter private String summary;
    @Getter @Setter private String acceptanceCriteria;
    @Getter @Setter private String status;
    @Getter @Setter private Integer priority;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Getter @Setter private Date dueDate;
    //ManyToOne with Backlog
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    @Getter @Setter private Backlog backlog;

    @Column(updatable = false)
    @Getter @Setter private String projectIdentifier;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Getter @Setter private Date create_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Getter @Setter private Date update_At;



    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.update_At = new Date();
    }
    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", backlog=" + backlog +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", create_At=" + create_At +
                ", update_At=" + update_At +
                '}';
    }
}