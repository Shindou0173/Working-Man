package com.ttvhtttt.workingmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "issues")
public class Issues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int issuesID;
    @ManyToOne
    @JoinColumn(name = "taskID")
    private Task taskID;
    @Column
    private String issuesName;
    @Column(length = 5000)
    private String description;

    public int getIssuesID() {
        return issuesID;
    }

    public void setIssuesID(int issuesID) {
        this.issuesID = issuesID;
    }

    public Task getTaskID() {
        return taskID;
    }

    public void setTaskID(Task taskID) {
        this.taskID = taskID;
    }

    public String getIssuesName() {
        return issuesName;
    }

    public void setIssuesName(String issuesName) {
        this.issuesName = issuesName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
