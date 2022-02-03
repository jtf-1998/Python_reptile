package com.lq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lq.bean.CiTiao;

public class QueryFromMySql 
{
    private static String DriverName="com.mysql.jdbc.Driver";
    private static String UName="root";
    private static String Upwd="root";
    private static String Url="jdbc:mysql://192.168.25.1:3306/xinjian?useUnicode=true&characterEncoding=UTF-8";
	 public int query()   //查询词条总数
	    {
	        Connection conn=null;
	        PreparedStatement pstmt=null;
	        ResultSet result=null;
	        int count=-1;
	        try {
	            Class.forName(DriverName);
	            conn=DriverManager.getConnection(Url, UName, Upwd);
	            String sql="select count(*) from citiao";
	            pstmt= conn.prepareStatement(sql);
	            result=pstmt.executeQuery(sql);
	            result.next();
	            count=result.getInt(1);
	            	return count;
	        }
	        catch(ClassNotFoundException e)
	        {
	            e.printStackTrace();
	            return count;
	        }
	        catch (SQLException e)
	        {
	            e.printStackTrace();
	            return count;
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            return count;
	        }
	        finally {
	            try {
	                if(result!=null) result.close();
	                if(pstmt!=null) pstmt.close();
	                if(conn!=null) conn.close();
	            }
	            catch(SQLException e)
	            {
	                e.printStackTrace();
	            }
	        }
	        
	    }
	 public List<CiTiao> SearchList()  //查询所有的词条信息
	  {
		  List<CiTiao> CiTiaos=new ArrayList<CiTiao>();
		  CiTiao CiTiao=null;
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet result=null;
	      try {
	          Class.forName(DriverName);
	          conn=DriverManager.getConnection(Url, UName, Upwd);
	          String sql="select * from citiao limit 0,50 ";
	          pstmt= conn.prepareStatement(sql);
	          result=pstmt.executeQuery();
	        	  while(result.next())
	        	  {
	        		   int id=result.getInt("id");    //词条编号1
	        		   String name=result.getString("name");  //词条名称2
	        		   String des=result.getString("des");  //词条简单内容3
	        		   String url=result.getString("url");  //词条链接4
	        		   
	        		 CiTiao=new CiTiao(id,name,des,url);
	        		 CiTiaos.add(CiTiao);
	        	  }
	          return CiTiaos;
	      }
	      catch(ClassNotFoundException e)
	      {
	          e.printStackTrace();
	          return null;
	      } 
	      catch (SQLException e) 
	      {
	        e.printStackTrace();
	        return null;
	    }
	      catch(Exception e)
	      {
	          e.printStackTrace();
	          return null;
	      }
	      finally {
	          try {
	              if(result!=null) result.close();
	              if(pstmt!=null) pstmt.close();
	              if(conn!=null) conn.close();
	          }
	          catch(SQLException e)
	          {
	              e.printStackTrace();
	          }
	      }
	  }
	 
	 public List<CiTiao> SearchByName(String name)  //根据名称查询所有的词条信息
	  {
		  List<CiTiao> CiTiaos=new ArrayList<CiTiao>();
		  CiTiao CiTiao=null;
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet result=null;
	      try {
	          Class.forName(DriverName);
	          conn=DriverManager.getConnection(Url, UName, Upwd);
	          String sql="select * from citiao where name like ?";
	          pstmt= conn.prepareStatement(sql);
	          pstmt.setString(1,"%"+name+"%");
	          result=pstmt.executeQuery();
	        	  while(result.next())
	        	  {
	        		  name=result.getString("name");  //词条名称2
	        		   String des=result.getString("des");  //词条简单内容3
	        		   String url=result.getString("url");  //词条链接4
	        		   
	        		 CiTiao=new CiTiao(name,des,url);
	        		 CiTiaos.add(CiTiao);
	        	  }
	          return CiTiaos;
	      }
	      catch(ClassNotFoundException e)
	      {
	          e.printStackTrace();
	          return null;
	      } 
	      catch (SQLException e) 
	      {
	        e.printStackTrace();
	        return null;
	    }
	      catch(Exception e)
	      {
	          e.printStackTrace();
	          return null;
	      }
	      finally {
	          try {
	              if(result!=null) result.close();
	              if(pstmt!=null) pstmt.close();
	              if(conn!=null) conn.close();
	          }
	          catch(SQLException e)
	          {
	              e.printStackTrace();
	          }
	      }
	  }
	 
	 public List<CiTiao> SearchByDes(String des)  //根据分类查询所有的词条信息
	  {
		  List<CiTiao> CiTiaos=new ArrayList<CiTiao>();
		  CiTiao CiTiao=null;
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet result=null;
	      try {
	          Class.forName(DriverName);
	          conn=DriverManager.getConnection(Url, UName, Upwd);
	          String sql="select * from citiao where des like ?";
	          pstmt= conn.prepareStatement(sql);
	          pstmt.setString(1,"%"+des+"%");
	          result=pstmt.executeQuery();
	        	  while(result.next())
	        	  {
	        		   String name=result.getString("name");  //词条名称2
	        		   des=result.getString("des");  //词条简单内容3
	        		   String url=result.getString("url");  //词条链接4
	        		   
	        		 CiTiao=new CiTiao(name,des,url);
	        		 CiTiaos.add(CiTiao);
	        	  }
	          return CiTiaos;
	      }
	      catch(ClassNotFoundException e)
	      {
	          e.printStackTrace();
	          return null;
	      } 
	      catch (SQLException e) 
	      {
	        e.printStackTrace();
	        return null;
	    }
	      catch(Exception e)
	      {
	          e.printStackTrace();
	          return null;
	      }
	      finally {
	          try {
	              if(result!=null) result.close();
	              if(pstmt!=null) pstmt.close();
	              if(conn!=null) conn.close();
	          }
	          catch(SQLException e)
	          {
	              e.printStackTrace();
	          }
	      }
	  }
	 

	 public static void main(String[] args) {
		QueryFromMySql queryFromMySql=new QueryFromMySql();
		//queryFromMySql.QueryBySend_time();
	}

}
