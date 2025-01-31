package model;

import java.sql.Timestamp;

public class JobApplication {

    private int applicationId;
    private int applicantId;
    private int jobId;
    private String applicationStatus;
    private Timestamp applicationDate;
    private String formData; // Or use java.sql.Clob if needed for large data

    // Default constructor
    public JobApplication() {
    }

    // Constructor with parameters
    public JobApplication(int applicationId, int applicantId, int jobId, String applicationStatus,
                          Timestamp applicationDate, String formData) {
        this.applicationId = applicationId;
        this.applicantId = applicantId;
        this.jobId = jobId;
        this.applicationStatus = applicationStatus;
        this.applicationDate = applicationDate;
        this.formData = formData;
    }

    // Getter and Setter methods

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Timestamp getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Timestamp applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    // Override toString() method for better display
    @Override
    public String toString() {
        return "JobApplication [applicationId=" + applicationId + ", applicantId=" + applicantId + ", jobId=" + jobId
                + ", applicationStatus=" + applicationStatus + ", applicationDate=" + applicationDate + ", formData="
                + formData + "]";
    }
}
