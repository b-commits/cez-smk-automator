package model;

import java.time.LocalDate;

public class Procedure {
    private final String patientLastName;
    private final String patientFirstName;
    private final String procedureName;
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

    public boolean isNonRTGProcedure() {
        // Todo refactor with enum
        return procedureName.equalsIgnoreCase("fistulografia") ||
                procedureName.equalsIgnoreCase("GOPP") ||
                procedureName.equalsIgnoreCase("Pasaż") ||
                procedureName.equalsIgnoreCase("urografia") ||
                procedureName.equalsIgnoreCase("cystografia") ||
                procedureName.equalsIgnoreCase("przełyk");
    }

    public String getPatientInitials() {
        return patientFirstName.charAt(0)+" "+patientLastName.charAt(0);
    }

    public String getSex() {
        return patientFirstName.toUpperCase().endsWith("A") ? "K" : "M";
    }

    @Override
    public String toString() {
        return "Procedure \t{" +
                "patientLastName='" + patientLastName + '\'' +
                ", patientFirstName='" + patientFirstName + '\'' +
                ", procedureName='" + procedureName + '\'' +
                ", date=" + date +
                '}';
    }
}
