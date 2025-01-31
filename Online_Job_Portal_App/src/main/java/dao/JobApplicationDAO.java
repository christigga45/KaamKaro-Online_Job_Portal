package dao;


import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import model.JobApplication;

public class JobApplicationDAO {
    

    public boolean saveJobApplication(JobApplication jobApplication) throws SQLException {
        String sql = "INSERT INTO job_applications (APPLICATION_ID,APPLICANT_ID, JOB_ID, APPLICATION_STATUS, APPLICATION_DATE, FORM_DATA) VALUES (JOB_APPLICATIONS_SEQ.NEXTVAL,?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getCon();
        		PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, jobApplication.getApplicantId());
            stmt.setInt(2, jobApplication.getJobId());
            stmt.setString(3, jobApplication.getApplicationStatus());
            stmt.setTimestamp(4, jobApplication.getApplicationDate());

            StringReader stringReader = new StringReader(jobApplication.getFormData());
            stmt.setCharacterStream(5, stringReader, jobApplication.getFormData().length());
            
            return stmt.executeUpdate() > 0;
        }
        
    }

    public Map<String, List<JSONObject>> getApplicantFormData(int applicantId,String companyname)  {
        String sql = "SELECT j.JOB_COMPANY, ja.FORM_DATA " +
                     "FROM job_applications ja " +
                     "JOIN job_Listings j ON ja.JOB_ID = j.JOB_ID " +
                     "WHERE ja.APPLICANT_ID = ? and job_company= ? ";
        
        Map<String, List<JSONObject>> applicationsByCompany = new LinkedHashMap<>();

        try (Connection connection = DBConnect.getCon();
        		PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, applicantId);
            stmt.setString(2,companyname);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String companyName = rs.getString("JOB_COMPANY");
                    String formDataStr = rs.getString("FORM_DATA");

                    if (formDataStr != null && !formDataStr.isEmpty()) {
                        JSONObject formDataJson = new JSONObject(formDataStr);
                        
                        // Group applications by company
                        applicationsByCompany.computeIfAbsent(companyName, k -> new ArrayList<>()).add(formDataJson);
                    }
                }
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        return applicationsByCompany;
    }
    
    public List<JobApplication> getAllApplicationsSortedByCompany() {
        List<JobApplication> applications = new ArrayList<>();
        String sql = "SELECT ja.application_id, ja.applicant_id, ja.job_id, ja.application_status, ja.application_date, ja.form_data, jl.job_company " +
                     "FROM job_applications ja " +
                     "INNER JOIN job_listings jl ON ja.job_id = jl.job_id " +
                     "ORDER BY jl.job_company";  // Sorting by company name in the job_listings table

        try (Connection con = DBConnect.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                JobApplication app = new JobApplication();
                app.setApplicationId(rs.getInt("application_id")); // Set applicationId
                app.setApplicantId(rs.getInt("applicant_id")); // Set applicantId
                app.setJobId(rs.getInt("job_id")); // Set jobId
                app.setApplicationStatus(rs.getString("application_status")); // Set applicationStatus
                app.setApplicationDate(rs.getTimestamp("application_date")); // Set applicationDate (Timestamp)
                app.setFormData(rs.getString("form_data")); // Set formData


                // Add the application to the list
                applications.add(app);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applications;
    }
    
 // Method to update application status
    public boolean updateApplicationStatus(int applicationId, String status) {
        String sql = "UPDATE job_applications SET application_status = ? WHERE application_id = ?";

        try (Connection con = DBConnect.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);  // Set the new status
            ps.setInt(2, applicationId);  // Set the application ID

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;  // If rows are updated, return true
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

 // Method to check if a user has applied for a specific job
    public boolean hasUserAppliedForJob(int applicantId, int jobId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM job_applications WHERE applicant_id = ? AND job_id = ?";
        
        try (Connection con = DBConnect.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, applicantId);  // Set the applicantId
            ps.setInt(2, jobId);         // Set the jobId
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;  // If count > 0, the user has applied
                }
            }
        }
        return false;  // Default: user hasn't applied
    }
    
 // Method to get all applications for a user
    public List<JobApplication> getApplicationsByUser(int applicantId) {
        List<JobApplication> applications = new ArrayList<>();
        String sql = "SELECT * FROM job_applications WHERE applicant_id = ?";

        try (Connection con = DBConnect.getCon();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, applicantId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                JobApplication application = new JobApplication();
                application.setApplicationId(rs.getInt("application_id"));
                application.setApplicantId(rs.getInt("applicant_id"));
                application.setJobId(rs.getInt("job_id"));
                application.setApplicationStatus(rs.getString("application_status"));
                application.setApplicationDate(rs.getTimestamp("application_date"));
                application.setFormData(rs.getString("form_data")); // Optional, if you want to include form data

                applications.add(application);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

}
