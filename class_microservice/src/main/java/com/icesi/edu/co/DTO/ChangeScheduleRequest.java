package com.icesi.edu.co.DTO;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

public class ChangeScheduleRequest {
    private String classId;
    private LocalDateTime newSchedule;

    public ChangeScheduleRequest(String classId, LocalDateTime newSchedule) {
        this.classId = classId;
        this.newSchedule = newSchedule;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public LocalDateTime getNewSchedule() {
        return newSchedule;
    }

    public void setNewSchedule(LocalDateTime newSchedule) {
        this.newSchedule = newSchedule;
    }

}
