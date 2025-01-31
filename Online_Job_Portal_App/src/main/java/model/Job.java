package model;

import java.sql.Timestamp;

public class Job {

    private int jobId;
    private String jobTitle;
    private String jobDescription;
    private String jobCompany;
    private String jobLocation;
    private String jobSkills;
    private Timestamp jobPostedDate;

    // Default constructor
    public Job() {
    }

    // Constructor with parameters
    public Job(int jobId, String jobTitle, String jobDescription, String jobCompany,
               String jobLocation, String jobSkills, Timestamp jobPostedDate) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobCompany = jobCompany;
        this.jobLocation = jobLocation;
        this.jobSkills = jobSkills;
        this.jobPostedDate = jobPostedDate;
    }

    // Getter and Setter methods

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(String jobSkills) {
        this.jobSkills = jobSkills;
    }

    public Timestamp getJobPostedDate() {
        return jobPostedDate;
    }

    public void setJobPostedDate(Timestamp jobPostedDate) {
        this.jobPostedDate = jobPostedDate;
    }

    // Override toString() method for better display
    @Override
    public String toString() {
        return "Job [jobId=" + jobId + ", jobTitle=" + jobTitle + ", jobDescription=" + jobDescription
                + ", jobCompany=" + jobCompany + ", jobLocation=" + jobLocation + ", jobSkills=" + jobSkills
                + ", jobPostedDate=" + jobPostedDate + "]";
    }
}

