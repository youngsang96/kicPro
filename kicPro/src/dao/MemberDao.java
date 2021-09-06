package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import model.Member;
import util.DBConnection;

public class MemberDao {
	public int memberInsert(Member member) {
		Connection con = DBConnection.getConnection();
		
		PreparedStatement pstmt = null;
		String sql = "insert into member  values (?, ?, ?, ?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPass());
			pstmt.setString(3, member.getName());
			pstmt.setInt(4, member.getGender());
			pstmt.setString(5, member.getTel());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPicture());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(con, pstmt, null);
		}
		
		return 0;
	}
	
	
     public List<Member> memberList() {
    	 //3)
    	Connection con = DBConnection.getConnection();
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		String sql = "select  * from member";
 		List<Member> li = new ArrayList<Member>();
 		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getString("id"));
 				member.setPass(rs.getString("pass"));
 				member.setName(rs.getString("name"));
 				member.setGender(rs.getInt("gender"));
 				member.setTel(rs.getString("tel"));
 				member.setEmail(rs.getString("email"));
 				member.setPicture(rs.getString("picture"));
			li.add(member);	}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(con, pstmt, rs);		}
  		return li;
	}
     
     public Member selectOne(String id) {
     	Connection con = DBConnection.getConnection();
  		PreparedStatement pstmt = null;
  		ResultSet rs = null;
  		String sql = "select  * from member where id= ?";
  		Member member = new Member();
  		try {
 			pstmt = con.prepareStatement(sql);
 			pstmt.setString(1, id);
 			rs=pstmt.executeQuery();
 			if (rs.next()) {
 				member.setId(rs.getString("id"));
 				member.setPass(rs.getString("pass"));
 				member.setName(rs.getString("name"));
 				member.setGender(rs.getInt("gender"));
 				member.setTel(rs.getString("tel"));
 				member.setEmail(rs.getString("email"));
 				member.setPicture(rs.getString("picture"));
 			}	
 		} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} finally {	DBConnection.close(con, pstmt, rs);	}
  		System.out.println(member);
  		return member; 	}
     
     public int  memberUpdate(Member member) {
    	 Connection con = DBConnection.getConnection();
    	 PreparedStatement pstmt = null;
    	 String sql = "update member set name=?, gender=? , tel=?, "+
    	   "email = ?, picture = ? where id =? and pass = ? ";
    	 System.out.println(member);
    	 try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setInt(2, member.getGender());
			pstmt.setString(3, member.getTel());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPicture());
			pstmt.setString(6, member.getId());
			pstmt.setString(7, member.getPass());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(con, pstmt, null);		}    	 
    	 return 0;     }  
     
     
     
     public int  memberDelete(String id, String pass) {
    	 Connection con = DBConnection.getConnection();
    	 PreparedStatement pstmt = null;
    	 String sql = "delete from  member where id =? and pass = ? ";
    	
    	 try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnection.close(con, pstmt, null);		}    	 
    	 return 0; 
    	 
     }  
     
     
} // class end
