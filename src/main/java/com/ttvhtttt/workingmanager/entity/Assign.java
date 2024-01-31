package com.ttvhtttt.workingmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "assign")
public class Assign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignID;
    @ManyToOne
    @JoinColumn(name = "taskGiver")
    private User taskGiver;
    @ManyToOne
    @JoinColumn(name = "taskID")
    private Task taskID;
    @ManyToOne
    @JoinColumn(name = "taskRecipient")
    private User taskRecipient;

    public int getAssignID() {
        return assignID;
    }

    public void setAssignID(int missionID) {
        this.assignID = missionID;
    }

    public User getTaskGiver() {
        return taskGiver;
    }

    public void setTaskGiver(User taskGiver) {
        this.taskGiver = taskGiver;
    }

    public Task getTaskID() {
        return taskID;
    }

    public void setTaskID(Task taskID) {
        this.taskID = taskID;
    }

    public User getTaskRecipient() {
        return taskRecipient;
    }

    public void setTaskRecipient(User taskRecipient) {
        this.taskRecipient = taskRecipient;
    }
}
