package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Job;

public class JobDAO {

	    // Method to get job details by jobId
	public Job getJobById(int jobId) {
	        Job job = null;
	        String sql = "SELECT * FROM job_listings WHERE JOB_ID = ?";

	        try{
	        	Connection conn = DBConnect.getCon();
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, jobId);
	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                // Creating the Job object from the result set
	                job = new Job(
	                    rs.getInt("JOB_ID"),
	                    rs.getString("JOB_TITLE"),
	                    rs.getString("JOB_DESCRIPTION"),
	                    rs.getString("JOB_COMPANY"),
	                    rs.getString("JOB_LOCATION"),
	                    rs.getString("JOB_SKILLS"),
	                    rs.getTimestamp("JOB_POSTED_DATE")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return job;
	    }
	



    // Method to fetch all jobs
    public List<Job> getAllJobs() {
        List<Job> jobList = new ArrayList<>();
        String sql = "SELECT JOB_ID, JOB_TITLE, JOB_DESCRIPTION, JOB_COMPANY, JOB_LOCATION, JOB_SKILLS, JOB_POSTED_DATE FROM job_listings";

        try {
        	Connection conn = DBConnect.getCon();
        	PreparedStatement stmt = conn.prepareStatement(sql); 
        	ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Job job = new Job(
                    rs.getInt("JOB_ID"),
                    rs.getString("JOB_TITLE"),
                    rs.getString("JOB_DESCRIPTION"),
                    rs.getString("JOB_COMPANY"),
                    rs.getString("JOB_LOCATION"),
                    rs.getString("JOB_SKILLS"),
                    rs.getTimestamp("JOB_POSTED_DATE")
                );
                jobList.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    // Method to fetch jobs based on skill, location, or company
    public List<Job> getJobsByFilters(String search) {
        List<Job> jobList = new ArrayList<>();
        String sql = "SELECT JOB_ID, JOB_TITLE, JOB_DESCRIPTION, JOB_COMPANY, JOB_LOCATION, JOB_SKILLS, JOB_POSTED_DATE " +
                     "FROM job_listings WHERE " +
                     "JOB_SKILLS LIKE ? OR JOB_LOCATION LIKE ? OR JOB_COMPANY LIKE ?";

        try (Connection conn = DBConnect.getCon();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String searchValue = "%" + (search != null ? search.trim() : "") + "%";

            // Set the same search value for all placeholders
            stmt.setString(1, searchValue); // Filter for JOB_SKILLS
            stmt.setString(2, searchValue); // Filter for JOB_LOCATION
            stmt.setString(3, searchValue); // Filter for JOB_COMPANY

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Job job = new Job(
                        rs.getInt("JOB_ID"),
                        rs.getString("JOB_TITLE"),
                        rs.getString("JOB_DESCRIPTION"),
                        rs.getString("JOB_COMPANY"),
                        rs.getString("JOB_LOCATION"),
                        rs.getString("JOB_SKILLS"),
                        rs.getTimestamp("JOB_POSTED_DATE")
                    );
                    jobList.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobList;
    }


    // Method to update a job
    public boolean updateJob(Job job) {
        boolean updated = false;
        String sql = "UPDATE job_listings SET JOB_TITLE = ?, JOB_DESCRIPTION = ?, JOB_COMPANY = ?, JOB_LOCATION = ?, JOB_SKILLS = ?, JOB_POSTED_DATE = ? WHERE JOB_ID = ?";

        try {
        	
        	Connection conn = DBConnect.getCon(); 
    		PreparedStatement stmt = conn.prepareStatement(sql);
    		
            stmt.setString(1, job.getJobTitle());
            stmt.setString(2, job.getJobDescription());
            stmt.setString(3, job.getJobCompany());
            stmt.setString(4, job.getJobLocation());
            stmt.setString(5, job.getJobSkills());
            stmt.setTimestamp(6, job.getJobPostedDate());
            stmt.setLong(7, job.getJobId());

            int rowsUpdated = stmt.executeUpdate();
            updated = rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    // Method to delete a job
    public boolean deleteJob(long jobId) {
        boolean deleted = false;
        String sql = "DELETE FROM job_listings WHERE JOB_ID = ?";

        try {
        	Connection conn = DBConnect.getCon(); 
    		PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, jobId);

            int rowsDeleted = stmt.executeUpdate();
            deleted = rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deleted;
    }

    // Method to add a job
    public boolean addJob(Job job) {
        boolean added = false;
        String sql = "INSERT INTO job_listings (JOB_ID, JOB_TITLE, JOB_DESCRIPTION, JOB_COMPANY, JOB_LOCATION, JOB_SKILLS, JOB_POSTED_DATE) "
                + "VALUES (job_listings_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        try {
        	Connection conn = DBConnect.getCon();
    		PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, job.getJobTitle());
            stmt.setString(2, job.getJobDescription());
            stmt.setString(3, job.getJobCompany());
            stmt.setString(4, job.getJobLocation());
            stmt.setString(5, job.getJobSkills());
            
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

            stmt.setDate(6,sqlDate );

            int rowsAdded = stmt.executeUpdate();
            added = rowsAdded > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return added;
    }
}
