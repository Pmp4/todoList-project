package com.dbModel.project;

import java.sql.Timestamp;

public class ProjectListDTO {
    private int pk_pjtNo;           /* 프로젝트 넘버 */
    private int pk_no;              //사용자 넘버
    private int pk_ctgNo;           /* 카테고리 넘버 */
    private String projectTitle;    /* 프로젝트 제목 */
    private String projectContent;  /* 프로젝트 내용 */
    private Timestamp startDate;    /* 프로젝트 시작일 */
    private Timestamp endDate;      /* 프로젝트 종료일 */
    private String projectYN; /* 완료 여부 */

    public ProjectListDTO() {
        super();
    }

    public ProjectListDTO(int pk_pjtNo, int pk_no, int pk_ctgNo, String projectTitle, String projectContent, Timestamp startDate, Timestamp endDate, String projectYN) {
        this.pk_pjtNo = pk_pjtNo;
        this.pk_no = pk_no;
        this.pk_ctgNo = pk_ctgNo;
        this.projectTitle = projectTitle;
        this.projectContent = projectContent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectYN = projectYN;
    }

    public int getPk_pjtno() {
        return pk_pjtNo;
    }

    public void setPk_pjtno(int pk_pjtNo) {
        this.pk_pjtNo = pk_pjtNo;
    }

    public int getPk_no() {
        return pk_no;
    }

    public void setPk_no(int pk_no) {
        this.pk_no = pk_no;
    }

    public int getPk_ctgNo() {
        return pk_ctgNo;
    }

    public void setPk_ctgNo(int pk_ctgNo) {
        this.pk_ctgNo = pk_ctgNo;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getProjectYN() {
        return projectYN;
    }

    public void setProjectYN(String projectYN) {
        this.projectYN = projectYN;
    }

    @Override
    public String toString() {
        return "ProjectListDTO{" +
                "pk_pjtNo=" + pk_pjtNo +
                ", pk_no=" + pk_no +
                ", pk_ctgNo=" + pk_ctgNo +
                ", projectTitle='" + projectTitle + '\'' +
                ", projectContent='" + projectContent + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", projectYN='" + projectYN + '\'' +
                '}';
    }
}
