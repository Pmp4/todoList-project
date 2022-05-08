package com.dbModel.project;

import com.dbModel.todolist.TodoListShareDTO;
import com.util.DBUtil;
import com.util.UserStateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectListDAO {
    ProjectShareDAO projectShareDAO = new ProjectShareDAO();

    //현재 사용자와 연결되어있는 모든 리스트
    public List<ProjectListDTO> selectAll(String result) throws SQLException {
        //해당 사용자와 연결되어있는 리스트 목록(리스트와 연결되어있는 사용자 목록)을 받아와야함
        //받아온 사용자 목록을 사용하여 해당 리스트 정보를 받아옴
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProjectListDTO> list = new ArrayList<>();
        List<ProjectShareDTO> sDTO = projectShareDAO.projectList(UserStateUtil.userNo); //연결된 사용자의 넘버를 받아옴
        System.out.println("project : " + sDTO.size());
        for(ProjectShareDTO projectShareDTO : sDTO) {
            System.out.println("project no"+ projectShareDTO.getPk_pjtNo());
        }
        try {
            con = DBUtil.getConnection();

            for (ProjectShareDTO projectShareDTO : sDTO) {
                ProjectListDTO projectListDTO = new ProjectListDTO();

                String sql = "select * from PROJECT where PK_PJTNO = ? and PROJECTYN = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, projectShareDTO.getPk_pjtNo());
                ps.setString(2, result);

                rs = ps.executeQuery();
                while(rs.next()) {
                    projectListDTO.setPk_pjtno(rs.getInt(1));
                    projectListDTO.setPk_no(rs.getInt(2));
                    projectListDTO.setPk_ctgNo(rs.getInt(2));
                    projectListDTO.setProjectTitle(rs.getString(4));
                    projectListDTO.setProjectContent(rs.getString(5));
                    projectListDTO.setStartDate(rs.getTimestamp(6));
                    projectListDTO.setEndDate(rs.getTimestamp(7));
                    projectListDTO.setProjectYN(rs.getString(8));

                    list.add(projectListDTO);
                }
            }
            return list;

        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }


    //Project 추가
    public int insertProject(ProjectListDTO projectListDTO) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "insert into PROJECT(pk_pjtno, pk_no, projecttitle, projectcontent, startdate, enddate) " +
                    "values(PROJECT_seq.nextval, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, projectListDTO.getPk_no());
            ps.setString(2, projectListDTO.getProjectTitle());
            ps.setString(3, projectListDTO.getProjectContent());
            ps.setTimestamp(4, projectListDTO.getStartDate());
            ps.setTimestamp(5, projectListDTO.getEndDate());

            int cnt = ps.executeUpdate();
            System.out.println(cnt > 0 ? "등록 성공" : "등록 실패");
            return cnt;
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }

    //Project 삭제
    public void deleteProject(int pNo) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "delete PROJECT where PK_PJTNO = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, pNo);

            ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }

    }



    //프로젝트 공유리스트 분담내용 수정
    public int updateProject(ProjectListDTO projectListDTO) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "update PROJECT set PROJECTTITLE = ?, PROJECTCONTENT = ?, STARTDATE = ?, ENDDATE = ? where PK_PJTNO = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, projectListDTO.getProjectTitle());
            ps.setString(2, projectListDTO.getProjectContent());
            ps.setTimestamp(3, projectListDTO.getStartDate());
            ps.setTimestamp(4, projectListDTO.getEndDate());
            ps.setInt(5, projectListDTO.getPk_pjtno());

            return ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }
}
