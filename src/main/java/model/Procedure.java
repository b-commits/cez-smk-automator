package model;

import java.time.LocalDate;

public class Procedure {
    private String patientLastName;
    private String patientFirstName;
    private String procedureName;
    private String doctor;
    private LocalDate date;

    public Procedure(String patientLastName, String patientFirstName, String procedureName, String doctor, LocalDate date) {
        this.patientLastName = patientLastName;
        this.patientFirstName = patientFirstName;
        this.procedureName = procedureName;
        this.doctor = doctor;
        this.date = date;
    }

    public String getPatientInitials() {
        return patientFirstName.charAt(0)+" "+patientLastName.charAt(0);
    }

    public String getSex() {
        return patientFirstName.toUpperCase().endsWith("A") ? "K" : "M";
    }

}
