package ru.vasilev.labs.utils.forms.data;

import java.io.Serializable;

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

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public void setDesiredPay(int desiredPay) {
        this.desiredPay = desiredPay;
    }

    public void setFullTime(boolean fullTime) {
        isFullTime = fullTime;
    }

    public void setPartTime(boolean partTime) {
        isPartTime = partTime;
    }

    public void setInternship(boolean internship) {
        isInternship = internship;
    }

    public void setOnetime(boolean onetime) {
        isOnetime = onetime;
    }

    public void setHasPhysicalLimitations(boolean hasPhysicalLimitations) {
        this.hasPhysicalLimitations = hasPhysicalLimitations;
    }

    public void setHasCriminalRecord(boolean hasCriminalRecord) {
        this.hasCriminalRecord = hasCriminalRecord;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

}
