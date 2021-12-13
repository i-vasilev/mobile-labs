package ru.vasilev.labs.utils.forms.data;

import java.io.Serializable;

import ru.vasilev.labs.R;

public class Form implements Serializable {
    private String familyName;
    private String firstName;
    private String secondName;

    private Education education;
    private Industry industry;
    private Experience experience;

    private int desiredPay;
    private boolean isFullTime;
    private boolean isPartTime;
    private boolean isInternship;
    private boolean isOnetime;

    private boolean hasPhysicalLimitations;
    private boolean hasCriminalRecord;

    private String additionalInformation;

    private boolean hasConfirmation;

    @Override
    public String toString() {
        return "" + familyName +
                " " + firstName +
                " " + secondName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Education getEducation() {
        return education;
    }

    public Industry getIndustry() {
        return industry;
    }

    public Experience getExperience() {
        return experience;
    }

    public int getDesiredPay() {
        return desiredPay;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public boolean isPartTime() {
        return isPartTime;
    }

    public boolean isInternship() {
        return isInternship;
    }

    public boolean isOnetime() {
        return isOnetime;
    }

    public boolean isHasPhysicalLimitations() {
        return hasPhysicalLimitations;
    }

    public boolean isHasCriminalRecord() {
        return hasCriminalRecord;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public boolean isHasConfirmation() {
        return hasConfirmation;
    }
}
