package com.lq.dao;

import com.lq.bean.XinJian;
import com.lq.file.LocalFile;

import java.sql.*;
import java.util.List;

public class AddToMySql
{
    private static String DriverName="com.mysql.jdbc.Driver";
    private static String UName="root";
    private static String Upwd="root";
    private static String Url="jdbc:mysql://192.168.25.1:3306/xinjian?useUnicode=true&characterEncoding=UTF-8";
    public boolean exit(String id)   //根据Id查询是否存在 如果存在返回true，不存在返回false
    {
        //System.out.println(query(id));
        return query(id)==1?true:false;
    }
    public int query(String Id)   //查询是否存在;存在return 1;否存在return 0;
    {
        XinJian xinJian=null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet result=null;
        int count=-1;
        try {
            Class.forName(DriverName);
            conn=DriverManager.getConnection(Url, UName, Upwd);
            String sql="select count(*) from xinjian where Id=?";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,Id);
            result=pstmt.executeQuery();
            if(result.next()){
                count=result.getInt(1);
                System.out.println("已存在");
            }
            if(count>0)
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
        return count;
    }
    public boolean Add(XinJian xinJian)   //添加信息
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            AddToMySql addToMySql=new AddToMySql();
            //System.out.println(addToMySql.exit(xinJian.getId()));
            if(addToMySql.exit(xinJian.getId())!=true) {
                //System.out.println("2");
                Class.forName(DriverName);
                conn = DriverManager.getConnection(Url, UName, Upwd);
                String sql = "insert into xinjian(Id,typet,massage,statue,send_time,addresser,asker,details,answering,answer_time,reply) values(?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, xinJian.getId());
                pstmt.setString(2, xinJian.getType());
                pstmt.setString(3, xinJian.getMassage());
                pstmt.setString(4, xinJian.getStatue());
                pstmt.setString(5, xinJian.getSend_time());
                pstmt.setString(6, xinJian.getAddresser());
                pstmt.setString(7, xinJian.getAsker());
                pstmt.setString(8, xinJian.getDetails());
                pstmt.setString(9, xinJian.getAnswering());
                pstmt.setString(10, xinJian.getAnswer_time());
                pstmt.setString(11, xinJian.getReply());

                int connt = pstmt.executeUpdate();
                if (connt > 0) {
                    System.out.println("保存成功");
                    return true;

                }
                else {
                    System.out.println("已存在");
                    return false;
                }
            }
            else return false;
        }
        catch(ClassNotFoundException  e)
        {
            e.printStackTrace();
            return false;  //系统异常
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                if(pstmt!=null){
                    pstmt.close();
                    conn.close();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }
    public void Run(){
        try {
            LocalFile localFile=new LocalFile();
            AddToMySql addToMySql=new AddToMySql();
            String []  strs = localFile.read_xinjian_local("xinjian.txt");
            String[] str=null;
            for(int i=0;i<strs.length;i++){
                str=strs[i].split("&&");
                XinJian xinJian=null;

//                System.out.println(str[0]+str[10]);
//               System.out.println("------------------------------------------------------");
                xinJian=new XinJian(str[0],str[1],str[2],str[3],str[4],str[5],str[6],str[7],str[8],str[9],str[10]);
                addToMySql.Add(xinJian);
               // System.out.println(xinJian.toString());
            }
            //System.out.println(str.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        AddToMySql addToMySql=new AddToMySql();
        //addToMySql.exit("AH19080800081");
        addToMySql.Run();
        System.out.println("finish");
    }

}
