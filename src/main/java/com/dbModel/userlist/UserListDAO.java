package com.dbModel.userlist;

import com.dbModel.job.JobDTO;
import com.util.DBUtil;
import com.util.UserStateUtil;
import oracle.jdbc.proxy.annotation.Pre;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserListDAO {
    //계정추가
    public int insertUser(UserListDTO dto) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DBUtil.getConnection();

            String sql = "insert into USERLIST(PK_NO, USERID, PWD, NAME, AGE, PK_JNO, INTRODUCTION) " +
                    "values(USERLIST_SEQ.nextval, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, dto.getUserId());
            ps.setString(2, dto.getPwd());
            ps.setString(3, dto.getName());
            ps.setInt(4, dto.getAge());
            ps.setInt(5, dto.getJno());
            ps.setString(6, dto.getIntroduction());

            return ps.executeUpdate();
        } finally {
            DBUtil.dbClose(ps, con);
        }
    }
    
    //계정확인
    public boolean findUser(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean result = false;  //결과가 없으면 false

        try {
            con = DBUtil.getConnection();

            String sql = "select count(*) from USERLIST where USERID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);

            rs = ps.executeQuery();

            if(rs.next()) result = true;

            return result;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }
    
    //계정 로그인
    public int loginUser(String userId, String pwd) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int result = 0;

        try {
            con = DBUtil.getConnection();

            String sql = "select * from USERLIST where USERID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);

            rs = ps.executeQuery();

            int cnt = 0;
            String checkPwd = "";
            if(rs.next()) {
                checkPwd = rs.getString(3);
                cnt++;
            }

            if(cnt > 0) {
                if(checkPwd.equals(pwd))
                    result = UserStateUtil.USER_CHECK;
                else
                    result = UserStateUtil.PWD_NONE_CHECK;
            }else {
                result = UserStateUtil.USERID_NONE_CHECK;
            }

            return result;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }


    //UserId의 이름 찾기
    public String fineUserName(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String name = "";  //결과가 없으면 false

        try {
            con = DBUtil.getConnection();

            String sql = "select NAME from USERLIST where USERID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);

            rs = ps.executeQuery();

            if(rs.next()) name = rs.getString(1);
            return name;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }

    //사용자아이디의 넘버 찾기
    public int stateUserNo(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = -1;

        try {
            con = DBUtil.getConnection();

            String sql = "select PK_NO from USERLIST where USERID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, userId);

            rs = ps.executeQuery();

            if(rs.next())
                result = rs.getInt(1);
            else
                System.out.println("사용자를 찾을 수 없습니다.(현재 접속중인 USERID가 없음)");

            return result;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }

    //유저 넘버로 아이디 찾기
    public String getUserId(int userNo) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String userId = "";

        try {
            con = DBUtil.getConnection();

            String sql = "select * from USERLIST where PK_NO = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, userNo);

            rs = ps.executeQuery();
            if(rs.next()) userId = rs.getString(2);

            return userId;
        } finally {
            DBUtil.dbClose(rs, ps, con);
        }
    }
}
