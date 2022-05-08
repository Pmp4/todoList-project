package com.dbModel.project;

import com.dbModel.todolist.TodoListShareDTO;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectShareDAO {
    //유저넘버가 가지고 있는 프로젝트 리스트 리턴
    public List<ProjectShareDTO> projectList(int userNo) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProjectShareDTO> list = new ArrayList<>();

        try {
            con = DBUtil.getConnection();

            String sql = "select * from PROJECT_SHARE where PK_NO = ? order by PK_PJTNO desc";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userNo);

            rs = ps.executeQuery();
            while(rs.next()) {
                ProjectShareDTO dto = new ProjectShareDTO();
                dto.setPk_pjtNo(rs.getInt(1));
                dto.setPk_no(rs.getInt(2));
                dto.setPk_authNo(rs.getInt(3));
                dto.setDivied(rs.getString(4));

                list.add(dto);
            }
            return list;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }


    //프로젝트 공동 사용자 리턴
    public List<ProjectShareDTO> selectUserNo(int pNo) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProjectShareDTO> list = new ArrayList<>();

        try {
            con = DBUtil.getConnection();

            String sql = "select * from PROJECT_SHARE where PK_PJTNO = ? order by PK_PJTNO desc";
            ps = con.prepareStatement(sql);
            ps.setInt(1, pNo);

            rs = ps.executeQuery();
            while(rs.next()) {
                ProjectShareDTO dto = new ProjectShareDTO();
                dto.setPk_pjtNo(rs.getInt(1));
                dto.setPk_no(rs.getInt(2));
                dto.setPk_authNo(rs.getInt(3));
                dto.setDivied(rs.getString(4));

                list.add(dto);
            }

            return list;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }


    //프로젝트 공동 사용자 추가
    public int insertProjectShareUser(int no, int pNo) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "insert into PROJECT_SHARE(pk_pjtno, pk_no) " +
                    "values(?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, no);
            ps.setInt(2, pNo);

            return ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }


    //프로젝트 공유리스트 삭제
    public void deleteProjectShareUser(int pNo) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "delete PROJECT_SHARE where PK_PJTNO = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, pNo);

            ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }

    //사용자의 공유리스트 리턴
    public ProjectShareDTO selectProjectShare(ProjectShareDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ProjectShareDTO projectShareDTO = new ProjectShareDTO();

        try {
            con = DBUtil.getConnection();

            String sql = "select * from PROJECT_SHARE where PK_PJTNO = ? and PK_NO = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, dto.getPk_pjtNo());
            ps.setInt(2, dto.getPk_no());

            rs = ps.executeQuery();
            if(rs.next()) {
                projectShareDTO.setPk_pjtNo(rs.getInt(1));
                projectShareDTO.setPk_no(rs.getInt(2));
                projectShareDTO.setPk_authNo(rs.getInt(3));
                projectShareDTO.setDivied(rs.getString(4));
            }

            return projectShareDTO;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }


    //프로젝트 공유리스트 분담내용 수정
    public int updateProjectShare(ProjectShareDTO projectShareDTO) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "update PROJECT_SHARE set DIVIDE = ? where PK_PJTNO = ? and PK_NO = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, projectShareDTO.getDivied());
            ps.setInt(2, projectShareDTO.getPk_pjtNo());
            ps.setInt(3, projectShareDTO.getPk_no());

            return ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }
}
