package com.dbModel.todolist;

public class TodoListShareDTO {
    private int pk_listNo;  //할 일 리스트 번호
    private int pk_no;      //할 일 리스트를 공유 받는 사람
    private int pk_authNo;  //할 일 리스트의 수정 권한

    public TodoListShareDTO(int pk_listNo, int pk_no, int pk_authNo) {
        this.pk_listNo = pk_listNo;
        this.pk_no = pk_no;
        this.pk_authNo = pk_authNo;
    }

    public TodoListShareDTO() {
        super();
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

    public int getPk_authNo() {
        return pk_authNo;
    }

    public void setPk_authNo(int pk_authNo) {
        this.pk_authNo = pk_authNo;
    }

    @Override
    public String toString() {
        return "TodoListShareDTO{" +
                "pk_listNo=" + pk_listNo +
                ", pk_no=" + pk_no +
                ", pk_authNo=" + pk_authNo +
                '}';
    }
}
