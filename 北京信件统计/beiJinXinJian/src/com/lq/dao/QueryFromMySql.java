package com.lq.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lq.bean.LeiXinTj;
import com.lq.bean.XinJian;

public class QueryFromMySql 
{
    private static String DriverName="com.mysql.jdbc.Driver";
    private static String UName="root";
    private static String Upwd="root";
    private static String Url="jdbc:mysql://192.168.25.1:3306/xinjian?useUnicode=true&characterEncoding=UTF-8";
	 public int query()   //查询信件总数
	    {
	        Connection conn=null;
	        PreparedStatement pstmt=null;
	        ResultSet result=null;
	        int count=-1;
	        try {
	            Class.forName(DriverName);
	            conn=DriverManager.getConnection(Url, UName, Upwd);
	            String sql="select count(*) from xinjian";
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
	 public List<XinJian> SearchList()  //查询所有的信件信息
	  {
		  List<XinJian> xinJians=new ArrayList<XinJian>();
		  XinJian xinJian=null;
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet result=null;
	      try {
	          Class.forName(DriverName);
	          conn=DriverManager.getConnection(Url, UName, Upwd);
	          String sql="SELECT * FROM xinjian";
	          pstmt= conn.prepareStatement(sql);
	          result=pstmt.executeQuery();
	        	  while(result.next())
	        	  {
	        		   String Id=result.getString("Id");    //信件编号1
	        		   String type=result.getString("typet");  //信件类型2
	        		   String massage=result.getString("massage");  //信件简单内容3
	        		   String statue=result.getString("statue");  //信件状态4
	        		   String send_time=result.getString("send_time"); //发起时间5
	        		   String addresser=result.getString("addresser");  //发信人6
	        		   String asker=result.getString("asker");   //网友同问7
	        		   String details=result.getString("details");   //信件详细内容8
	        		   String answering=result.getString("answering");  //回信人9
	        		   String answer_time=result.getString("answer_time");   //回信时间10
	        		   String reply=result.getString("reply");    //回复内容11
	        		   
	        		 xinJian=new XinJian(Id, type, massage, statue, send_time, addresser, asker, details, answering, answer_time, reply);
	        		 xinJians.add(xinJian);
	        	  }
	          return xinJians;
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
	 
	 public XinJian SearchById(String Id)   //查询根据信件编号查询单个信件详细信息
	    {
		 	XinJian xinJian=new XinJian();
	        Connection conn=null;
	        PreparedStatement pstmt=null;
	        ResultSet result=null;
	        int count=-1;
	        try {
	            Class.forName(DriverName);
	            conn=DriverManager.getConnection(Url, UName, Upwd);
	            String sql="select * from xinjian where Id=?";
	            pstmt= conn.prepareStatement(sql);
	            pstmt.setString(1, Id);
	            result=pstmt.executeQuery();
	            if(result.next()) {
	            	   Id=result.getString("Id");    //信件编号1
	        		   String type=result.getString("typet");  //信件类型2
	        		   String massage=result.getString("massage");  //信件简单内容3
	        		   String statue=result.getString("statue");  //信件状态4
	        		   String send_time=result.getString("send_time"); //发起时间5
	        		   String addresser=result.getString("addresser");  //发信人6
	        		   String asker=result.getString("asker");   //网友同问7
	        		   String details=result.getString("details");   //信件详细内容8
	        		   String answering=result.getString("answering");  //回信人9
	        		   String answer_time=result.getString("answer_time");   //回信时间10
	        		   String reply=result.getString("reply");    //回复内容11
	        		   
	        		 xinJian=new XinJian(Id, type, massage, statue, send_time, addresser, asker, details, answering, answer_time, reply);
	            }
	            return xinJian;
	            
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
	 
	 public ArrayList<LeiXinTj> QueryByTypet()  //查询信件类型信息
	  {
		  ArrayList<LeiXinTj> leiXinTjs=new ArrayList<LeiXinTj>();
		  LeiXinTj leiXinTj=null;
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet result=null;
	      try {
	          Class.forName(DriverName);
	          conn=DriverManager.getConnection(Url, UName, Upwd);
	          String sql="select typet As name,count(1) As num from xinjian GROUP BY typet ORDER BY num DESC;";
	          pstmt= conn.prepareStatement(sql);
	          result=pstmt.executeQuery();
	        	  while(result.next())
	        	  {
	        		   String name=result.getString("name");
	        		   String num=result.getString("num");
	        		   leiXinTj=new LeiXinTj(name, num);
	        		   System.out.println(name+":"+num);
	        		   System.out.println("---------------------------");
	        		   leiXinTjs.add(leiXinTj);
	        	  }
	          return leiXinTjs;
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
	 
	 public ArrayList<LeiXinTj> QueryByAnswering()  //查询信件回复部门信息
	  {
		  ArrayList<LeiXinTj> leiXinTjs=new ArrayList<LeiXinTj>();
		  LeiXinTj leiXinTj=null;
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet result=null;
	      try {
	          Class.forName(DriverName);
	          conn=DriverManager.getConnection(Url, UName, Upwd);
	          String sql="select answering As name,count(1) As num from xinjian GROUP BY answering ORDER BY num DESC;";
	          pstmt= conn.prepareStatement(sql);
	          result=pstmt.executeQuery();
	        	  while(result.next())
	        	  {
	        		   String name=result.getString("name");
	        		   String num=result.getString("num");
	        		   leiXinTj=new LeiXinTj(name, num);
	        		   System.out.println(name+":"+num);
	        		   System.out.println("---------------------------");
	        		   leiXinTjs.add(leiXinTj);
	        	  }
	          return leiXinTjs;
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
	 
	 public ArrayList<LeiXinTj> QueryBySend_time()  //查询年度信件
	  {
		  ArrayList<LeiXinTj> leiXinTjs=new ArrayList<LeiXinTj>();
		  LeiXinTj leiXinTj=null;
	      Connection conn=null;
	      PreparedStatement pstmt=null;
	      ResultSet result=null;
	      try {
	          Class.forName(DriverName);
	          conn=DriverManager.getConnection(Url, UName, Upwd);
	          String sql="SELECT COUNT(1) AS num, YEAR(send_time) as name FROM xinjian GROUP BY YEAR(send_time);";
	          pstmt= conn.prepareStatement(sql);
	          result=pstmt.executeQuery();
	        	  while(result.next())
	        	  {
	        		   String name=result.getString("name");
	        		   String num=result.getString("num");
	        		   leiXinTj=new LeiXinTj(name, num);
	        		   System.out.println(name+":"+num);
	        		   System.out.println("---------------------------");
	        		   leiXinTjs.add(leiXinTj);
	        	  }
	          return leiXinTjs;
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
		queryFromMySql.QueryBySend_time();
	}

}
