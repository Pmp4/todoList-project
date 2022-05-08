package com.dbModel.job;

import com.util.DBUtil;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {
    //모든 리스트
    public List<JobDTO> selectAll() throws SQLException {
        Connection con = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<JobDTO> list = new ArrayList<>();

        try {
            String sql = "select * from JOB";
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while(rs.next()) {
                JobDTO dto = new JobDTO();
                dto.setPkJno(rs.getInt(1));
                dto.setJobTitle(rs.getString(2));

                list.add(dto);
            }
            return list;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }
    
    //직종 이름, 기본키 리턴
    public int selectJobTitle(String jobTitle) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int jNo = 0;

        try {
            con = DBUtil.getConnection();

            String sql = "select * from JOB where JOBTITLE = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, jobTitle);

            rs = ps.executeQuery();
            if(rs.next()) jNo = rs.getInt(1);

            return jNo;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }

    }
}
