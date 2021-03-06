package model;

import java.time.LocalDate;
import java.util.Locale;

/**
 * An object of this class represents one datapoint needed to fill out a single row in CEZ procedure web form.
 */
public class Procedure {

    private final String patientLastName;
    private final String patientFirstName;
    private final String procedureName;
    private LocalDate date;

    public Procedure(String patientLastName, String patientFirstName, String procedureName, LocalDate date) {
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
        return procedureName.equalsIgnoreCase("fistulografia") ||
                procedureName.equalsIgnoreCase("gopp") ||
                procedureName.equalsIgnoreCase("pasaż") ||
                procedureName.equalsIgnoreCase("urografia") ||
                procedureName.equalsIgnoreCase("cystografia") ||
                procedureName.equalsIgnoreCase("przełyk");
    }

    public String getPatientInitials() {
        return (patientFirstName.charAt(0) + "" + patientLastName.charAt(0)).toUpperCase(Locale.ROOT);
    }

    public String getSex() {
        return patientFirstName.toUpperCase().endsWith("A") ? "K" : "M";
    }

    public LocalDate getDate() {
        return date;
    }

    public String getProcedureName() {
        return procedureName;
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
