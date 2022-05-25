package Survery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class infoDao {
	
	private static DataSource ds = null;        
    {
        try{
            Context init = new InitialContext();
            ds = (DataSource)init.lookup("java:comp/env/jdbc/myOracle");
        }catch(Exception e){
            System.err.println("Connection 실패");
        }
    }
    
    public Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
    private static infoDao instance = null;
    private infoDao(){ } 
    
    public static infoDao getInstance(){
        if(instance == null){
            synchronized(infoDao.class){
                instance = new infoDao();
            }
        }
        return instance;
    }
    
    public List<String> getInfo() {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	List<String> ls = new ArrayList<String>();
    	
    	try {
			conn = getConnection();
			ps = conn.prepareStatement("select \"REILGION\" from \"INFO\" group by \"REILGION\" order by \"REILGION\"");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ls.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	finally{
            if(rs != null) 
                try{
                    rs.close();
                }catch(SQLException sqle1){}
            if(ps != null) 
                try{
                    ps.close();
                }catch(SQLException sqle2){}
            if(conn != null) 
                try{
                    conn.close();
                }catch(SQLException sqle3){}
        }
    	return ls;
    }
    
    public void insertInfo(String rel, int age) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	
    	try {
			conn = getConnection();
			ps = conn.prepareStatement("insert into INFO values (?,?)");
			ps.setInt(1, age);
			ps.setString(2, rel);
			
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	finally{
            if(ps != null) 
                try{
                    ps.close();
                }catch(SQLException sqle2){}
            if(conn != null) 
                try{
                    conn.close();
                }catch(SQLException sqle3){}
        }
    }
    
    public List<infoDto> allInfo() {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	List<infoDto> ls = new ArrayList<infoDto>();
    	
    	try {
			conn = getConnection();
			ps = conn.prepareStatement("select \"REILGION\",COUNT(\"REILGION\") from \"INFO\" group by \"REILGION\" order by COUNT(\"REILGION\") desc");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				infoDto id = new infoDto(rs.getString(1), rs.getInt(2));
				ls.add(id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	finally{
            if(rs != null) 
                try{
                    rs.close();
                }catch(SQLException sqle1){}
            if(ps != null) 
                try{
                    ps.close();
                }catch(SQLException sqle2){}
            if(conn != null) 
                try{
                    conn.close();
                }catch(SQLException sqle3){}
        }
    	return ls;
    }
    
    public List<infoDto> ageInfo(String age) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	List<infoDto> ls = new ArrayList<infoDto>();
    	String sql = null;
    	int a = Integer.parseInt(age);
    	if(a==100) {
    		sql="select \"REILGION\",COUNT(\"REILGION\") from \"INFO\" "
    				+ "where \"AGE\" >= ? group by \"REILGION\" order by COUNT(\"REILGION\") desc";
    	}else {
    		sql="select \"REILGION\",COUNT(\"REILGION\") from \"INFO\" "
    				+ "where \"AGE\" between ? and ? group by \"REILGION\" order by COUNT(\"REILGION\") desc";
    	}
    	
    	try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			if(a==100) {
	    		ps.setString(1, age);
	    	}else {
	    		ps.setString(1, age);
	    		ps.setString(2, age+9);	    		
	    	}
			rs = ps.executeQuery();
			
			while(rs.next()) {
				infoDto id = new infoDto(rs.getString(1), rs.getInt(2));
				ls.add(id);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	finally{
            if(rs != null) 
                try{
                    rs.close();
                }catch(SQLException sqle1){}
            if(ps != null) 
                try{
                    ps.close();
                }catch(SQLException sqle2){}
            if(conn != null) 
                try{
                    conn.close();
                }catch(SQLException sqle3){}
        }
    	return ls;
    }
    
    public int countInfo() {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	int number = 0;
    	
    	try {
			conn = getConnection();
			ps = conn.prepareStatement("select COUNT(*) from \"INFO\"");
			rs = ps.executeQuery();
			
			rs.next();
			number = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	finally{
            if(rs != null) 
                try{
                    rs.close();
                }catch(SQLException sqle1){}
            if(ps != null) 
                try{
                    ps.close();
                }catch(SQLException sqle2){}
            if(conn != null) 
                try{
                    conn.close();
                }catch(SQLException sqle3){}
        }
    	return number;
    }
    
    public int countAgeInfo(String age) {
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String sql = null;
    	
    	int a = Integer.parseInt(age);
    	if(a==100) {
    		sql="select COUNT(*) from \"INFO\" where \"AGE\" >= ?";
    	}else {
    		sql="select COUNT(*) from \"INFO\" where \"AGE\" between ? and ?";
    	}
    	
    	try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			if(a==100) {
	    		ps.setString(1, age);
	    	}else {
	    		ps.setString(1, age);
	    		ps.setString(2, age+9);	    		
	    	}
			rs = ps.executeQuery();
			
			rs.next();
			a = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	finally{
            if(rs != null) 
                try{
                    rs.close();
                }catch(SQLException sqle1){}
            if(ps != null) 
                try{
                    ps.close();
                }catch(SQLException sqle2){}
            if(conn != null) 
                try{
                    conn.close();
                }catch(SQLException sqle3){}
        }
    	return a;
    }
}