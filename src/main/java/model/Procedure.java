package model;

import java.time.LocalDate;

public class Procedure {
    private String patientLastName;
    private String patientFirstName;
    private String procedureName;
    private LocalDate date;

    public Procedure(String patientLastName, String patientFirstName, String procedureName,  LocalDate date) {
        this.patientLastName = patientLastName;
        this.patientFirstName = patientFirstName;
        this.procedureName = procedureName;
        this.date = date;
    }

    public Procedure(String patientLastName, String patientFirstName, String procedureName) {
        this.patientLastName = patientLastName;
        this.patientFirstName = patientFirstName;
        this.procedureName = procedureName;

    }

    public String getPatientInitials() {
        return patientFirstName.charAt(0)+" "+patientLastName.charAt(0);
    }

    public String getSex() {
        return patientFirstName.toUpperCase().endsWith("A") ? "K" : "M";
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "patientLastName='" + patientLastName + '\'' +
                ", patientFirstName='" + patientFirstName + '\'' +
                ", procedureName='" + procedureName + '\'' +
                ", date=" + date +
                '}';
    }
}
