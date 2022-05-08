package com.dbModel.todolist;

import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoListShareDAO {
    //현재 사용자와 연결되어있는 할 일 리스트 리턴
    public List<TodoListShareDTO> todoList(int no) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<TodoListShareDTO> list = new ArrayList<>();

        try {
            con = DBUtil.getConnection();

            String sql = "select * from TODOLIST_SHARE where PK_NO = ? order by PK_LISTNO desc";
            ps = con.prepareStatement(sql);
            ps.setInt(1, no);

            rs = ps.executeQuery();
            while(rs.next()) {
                TodoListShareDTO dto = new TodoListShareDTO();
                dto.setPk_listNo(rs.getInt(1));
                dto.setPk_no(rs.getInt(2));
                dto.setPk_authNo(rs.getInt(3));

                list.add(dto);
            }

//            System.out.println("리스트 개수 : " + list.size());
//            for (Integer integer : list) {
//                System.out.println("리스트 넘버 : " + integer);
//            }
            return list;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }

    //TodoListShare 공유 사용자 추가
    public int insertTodoListShare(int listNo, int no) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "insert into TODOLIST_SHARE(PK_LISTNO, PK_NO) " +
                    "values (?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, listNo);
            ps.setInt(2, no);

            return ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }

    //TodoListShare 목록 삭제
    public int deleteTodoListShare(int no) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "delete TODOLIST_SHARE where PK_LISTNO = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, no);

            return ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }

    //TodoListShare 특정 사용자만 삭제
    public int deleteTodoListShare(int no, int userNo) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "delete TODOLIST_SHARE where PK_LISTNO = ? and PK_NO = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, no);
            ps.setInt(2, userNo);

            return ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }
}
