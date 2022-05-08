package com.dbModel.project;

public class ProjectShareDTO {
    private int pk_pjtNo; /* 프로젝트 넘버 */
    private int pk_no; /* 회원 넘버 */
    private int pk_authNo; /* 권한 넘버 */
    private String divied;  //분담 내용

    public ProjectShareDTO() {
        super();
    }

    public ProjectShareDTO(int pk_pjtNo, int pk_no, int pk_authNo, String divied) {
        this.pk_pjtNo = pk_pjtNo;
        this.pk_no = pk_no;
        this.pk_authNo = pk_authNo;
        this.divied = divied;
    }

    public int getPk_pjtNo() {
        return pk_pjtNo;
    }

    public void setPk_pjtNo(int pk_pjtNo) {
        this.pk_pjtNo = pk_pjtNo;
    }

    public int getPk_no() {
        return pk_no;
    }

    public void setPk_no(int pk_no) {
        this.pk_no = pk_no;
    }

    public int getPk_authNo() {
        return pk_authNo;
    }

    public void setPk_authNo(int pk_authNo) {
        this.pk_authNo = pk_authNo;
    }

    public String getDivied() {
        return divied;
    }

    public void setDivied(String divied) {
        this.divied = divied;
    }

    @Override
    public String toString() {
        return "ProjectShareDTO{" +
                "pk_pjtNo=" + pk_pjtNo +
                ", pk_no=" + pk_no +
                ", pk_authNo=" + pk_authNo +
                ", divied='" + divied + '\'' +
                '}';
    }
}
