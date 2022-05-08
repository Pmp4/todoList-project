package com.dbModel.todolist;

import com.util.DBUtil;
import com.util.DateUtil;
import com.util.UserStateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TodoListDAO {
    private final TodoListShareDAO listShareDAO = new TodoListShareDAO();

    // 해당 사용자의 할 일 리스트 전체 리턴(오늘의)
//    public List<TodoListDTO> selectTodayAll() throws SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        List<TodoListDTO> list = new ArrayList<>();     //할 일 리스트를 담는다
//        List<TodoListShareDTO> sDTO = listShareDAO.todoList(UserStateUtil.userNo); //할 일 사용자의 넘버를 받아옴
//
//        try {
//            con = DBUtil.getConnection();
//
//            for (TodoListShareDTO sNo : sDTO) {
//                TodoListDTO dto = new TodoListDTO();
//
//                String sql = "select * from TODOLIST where PK_LISTNO = ? and TO_CHAR(LISTDATE, 'yyyy-MM-dd') = ? order by PK_LISTNO desc";
//                ps = con.prepareStatement(sql);
//                ps.setInt(1, sNo.getPk_listNo());
//                ps.setString(2, DateUtil.toStringDate());
//
//                rs = ps.executeQuery();
//                if (rs.next()) {
//                    dto.setPk_listNo(rs.getInt(1));
//                    dto.setPk_ctgNo(rs.getInt(2));
//                    dto.setPk_ctgNo(rs.getInt(3));
//                    dto.setListContent(rs.getString(4));
//                    dto.setListDate(rs.getTimestamp(5));
//                    dto.setListYn((rs.getString(6)));
//
//                    list.add(dto);
//                }
//            }
////            for(TodoListDTO dto : list)
////                System.out.println("리스트 이름 : " + dto.getListContent());
//
//            return list;
//        } finally {
//            DBUtil.dbClose(rs, ps, con);
//        }
//    }

    // 해당 사용자의 할 일 리스트 전체 리턴(모든)
    public List<TodoListDTO> selectAll(String result) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<TodoListDTO> list = new ArrayList<>();     //할 일 리스트를 담는다
        List<TodoListShareDTO> sDTO = listShareDAO.todoList(UserStateUtil.userNo); //할 일 사용자의 넘버를 받아옴

        try {
            con = DBUtil.getConnection();

            for (TodoListShareDTO no : sDTO) {
                TodoListDTO dto = new TodoListDTO();

                String sql = "select * from TODOLIST where PK_LISTNO = ? and LISTYN = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, no.getPk_listNo());
                ps.setString(2, result);

                rs = ps.executeQuery();
                if(rs.next()) {
                    dto.setPk_listNo(rs.getInt(1));
                    dto.setPk_ctgNo(rs.getInt(2));
                    dto.setPk_ctgNo(rs.getInt(3));
                    dto.setListContent(rs.getString(4));
                    dto.setListDate(rs.getTimestamp(5));
                    dto.setListYn((rs.getString(6)));

                    list.add(dto);
                }
            }
            for(TodoListDTO dto : list) System.out.println("리스트 이름 : " + dto.getListContent());
            return list;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }



    // 캘린더에서 일자 클릭하여 추가할 경우


    // textField에서 직접 추가할 경우
    public int insertBoxTodoList(TodoListDTO todoListDTO) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "insert into TODOLIST(PK_LISTNO, PK_NO, LISTCONTENT, LISTDATE) " +
                    "values(TODOLIST_SEQ.nextval, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, todoListDTO.getPk_no());
            ps.setString(2, todoListDTO.getListContent());
            ps.setTimestamp(3, todoListDTO.getListDate());

            return ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }



    //TodoList 수정
    public int updateTodoList(TodoListDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "update TODOLIST set LISTCONTENT = ?, LISTDATE = ?, LISTYN = ? where PK_LISTNO = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(4, dto.getPk_listNo());
            ps.setString(1, dto.getListContent());
            ps.setTimestamp(2, dto.getListDate());
            ps.setString(3, dto.getListYn());

            return ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }

    //TodoList 삭제
    public int deleteTodoList(int no) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "delete TODOLIST where PK_LISTNO = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, no);

            return ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }
}
