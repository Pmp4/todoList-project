package com.dbModel.todolist;

import java.sql.Timestamp;

public class TodoListDTO {
    private int pk_listNo;
    private int pk_no;
    private int pk_ctgNo;
    private String listContent;
    private Timestamp listDate;
    private String listYn;

    public TodoListDTO() {
        super();
    }

    public TodoListDTO(int pk_listNo, int pk_no, int pk_ctgNo, String listContent, Timestamp listDate, String listYn) {
        this.pk_listNo = pk_listNo;
        this.pk_no = pk_no;
        this.pk_ctgNo = pk_ctgNo;
        this.listContent = listContent;
        this.listDate = listDate;
        this.listYn = listYn;
    }

    public int getPk_listNo() {
        return pk_listNo;
    }

    public void setPk_listNo(int pk_listNo) {
        this.pk_listNo = pk_listNo;
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

    public String getListContent() {
        return listContent;
    }

    public void setListContent(String listContent) {
        this.listContent = listContent;
    }

    public Timestamp getListDate() {
        return listDate;
    }

    public void setListDate(Timestamp listDate) {
        this.listDate = listDate;
    }

    public String getListYn() {
        return listYn;
    }

    public void setListYn(String listYn) {
        this.listYn = listYn;
    }

    @Override
    public String toString() {
        return "TodoListDTO{" +
                "pk_listNo=" + pk_listNo +
                ", pk_no=" + pk_no +
                ", pk_ctgNo=" + pk_ctgNo +
                ", listContent='" + listContent + '\'' +
                ", listDate=" + listDate +
                ", listYn='" + listYn + '\'' +
                '}';
    }
}
