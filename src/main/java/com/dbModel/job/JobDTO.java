package com.dbModel.job;

public class JobDTO {
    private int pkJno;
    private String jobTitle;

    public JobDTO() {
        super();
    }
    public JobDTO(int pkjno, String jobTitle) {
        this.pkJno = pkjno;
        this.jobTitle = jobTitle;
    }

    public int getPkJno() {
        return pkJno;
    }

    public void setPkJno(int pkJno) {
        this.pkJno = pkJno;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "pkJno=" + pkJno +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
