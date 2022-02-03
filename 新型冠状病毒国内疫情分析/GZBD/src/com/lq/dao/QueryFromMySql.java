package com.lq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lq.bean.BinLin;

public class QueryFromMySql 
{
    private static String DriverName="com.mysql.jdbc.Driver";
    private static String UName="root";
    private static String Upwd="root";
    private static String Url="jdbc:mysql://192.168.25.1:3306/xinjian?useUnicode=true&characterEncoding=UTF-8";
	 public ArrayList<BinLin> SearchList()  //查询所有的省份疫情信息
	  {
		  ArrayList<BinLin> BinLins=new ArrayList<BinLin>();
		  BinLin BinLin=null;
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet result=null;
	      try {
	          Class.forName(DriverName);
	          conn=DriverManager.getConnection(Url, UName, Upwd);
	          String sql="select nd,confirm,dead,suspect,heal,shen,sum(dconfirm) As dconfirm,sum(dheal) As dheal,sum(ddead) As ddead from zbl GROUP BY shen ORDER BY confirm DESC;";
	          pstmt= conn.prepareStatement(sql);
	          result=pstmt.executeQuery();
	        	  while(result.next())
	        	  {
	        		 String nd=result.getString("nd");  
	        		 int confirm=result.getInt("confirm");
	        		 int dead=result.getInt("dead");
	        		 int suspect=result.getInt("suspect");
	        		 int heal=result.getInt("heal");
	        		 String shen=result.getString("shen");
	        		 int dconfirm=result.getInt("dconfirm");
	        		 int dheal=result.getInt("dheal");
	        		 int ddead=result.getInt("ddead");
	        		 BinLin=new BinLin(nd, confirm, dead, suspect, heal, shen,dconfirm, dheal, ddead);
	        		 BinLins.add(BinLin);
	        	  }
	          return BinLins;
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
	 
//	 public List<BinLin> SearchByName(String name)  //根据名称查询所有的词条信息
//	  {
//		  List<BinLin> BinLins=new ArrayList<BinLin>();
//		  BinLin BinLin=null;
//	      Connection conn=null;
//	      PreparedStatement pstmt=null;
//	      ResultSet result=null;
//	      try {
//	          Class.forName(DriverName);
//	          conn=DriverManager.getConnection(Url, UName, Upwd);
//	          String sql="select * from BinLin where name like ?";
//	          pstmt= conn.prepareStatement(sql);
//	          pstmt.setString(1,"%"+name+"%");
//	          result=pstmt.executeQuery();
//	        	  while(result.next())
//	        	  {
//	        		  name=result.getString("name");  //词条名称2
//	        		   String des=result.getString("des");  //词条简单内容3
//	        		   String url=result.getString("url");  //词条链接4
//	        		   
//	        		 BinLin=new BinLin(name,des,url);
//	        		 BinLins.add(BinLin);
//	        	  }
//	          return BinLins;
//	      }
//	      catch(ClassNotFoundException e)
//	      {
//	          e.printStackTrace();
//	          return null;
//	      } 
//	      catch (SQLException e) 
//	      {
//	        e.printStackTrace();
//	        return null;
//	    }
//	      catch(Exception e)
//	      {
//	          e.printStackTrace();
//	          return null;
//	      }
//	      finally {
//	          try {
//	              if(result!=null) result.close();
//	              if(pstmt!=null) pstmt.close();
//	              if(conn!=null) conn.close();
//	          }
//	          catch(SQLException e)
//	          {
//	              e.printStackTrace();
//	          }
//	      }
//	  }
//	 
	 public ArrayList<BinLin> SearchByShen(String shen)  //根据省查询所有的病例信息
	  {
		  ArrayList<BinLin> BinLins=new ArrayList<BinLin>();
		  BinLin BinLin=null;
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet result=null;
	      try {
	          Class.forName(DriverName);
	          conn=DriverManager.getConnection(Url, UName, Upwd);
	          String sql="select * from zbl where shen=?";
	          pstmt= conn.prepareStatement(sql);
	          pstmt.setString(1,shen);
	          result=pstmt.executeQuery();
	        	  while(result.next())
	        	  {
	        		shen=result.getString("shen");
	        		String diqu=result.getString("diqu");
		        	int dconfirm=result.getInt("dconfirm");
		        	int dheal=result.getInt("dheal");
		        	int ddead=result.getInt("ddead");
		        	BinLin=new BinLin(shen,diqu,dconfirm, dheal, ddead);
		        	BinLins.add(BinLin);
	        	  }
	          return BinLins;
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
//	 

	 public static void main(String[] args) {
		QueryFromMySql queryFromMySql=new QueryFromMySql();
		//queryFromMySql.QueryBySend_time();
	}

}
