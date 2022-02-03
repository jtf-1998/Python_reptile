package com.lq.dao;

import com.lq.bean.CiTiao;
import com.lq.file.LocalFile;
import com.lq.file.StringHandle;

import java.sql.*;
import java.util.List;

public class AddToMySql
{
    private static String DriverName="com.mysql.jdbc.Driver";
    private static String UName="root";
    private static String Upwd="root";
    private static String Url="jdbc:mysql://192.168.25.1:3306/xinjian?useUnicode=true&characterEncoding=UTF-8";
    public boolean exit(String name)   //根据词名称查询是否存在 如果存在返回true，不存在返回false
    {
        //System.out.println(query(id));
        return query(name)==1?true:false;
    }
    public int query(String name)   //查询是否存在;存在return 1;否存在return 0;
    {
        CiTiao CiTiao=null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet result=null;
        int count=-1;
        try {
            Class.forName(DriverName);
            conn=DriverManager.getConnection(Url, UName, Upwd);
            String sql="select count(*) from citiao where name=?";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,name);
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
    public boolean Add(CiTiao CiTiao)   //添加信息
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            AddToMySql addToMySql=new AddToMySql();
            //System.out.println(addToMySql.exit(CiTiao.getId()));
            if(addToMySql.exit(CiTiao.getName())!=true) {
                //System.out.println("2");
                Class.forName(DriverName);
                conn = DriverManager.getConnection(Url, UName, Upwd);
                String sql = "insert into citiao values(NULL,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, CiTiao.getName());
                pstmt.setString(2, CiTiao.getDes());
                pstmt.setString(3, CiTiao.getUrl());

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
            String []  strs = localFile.read_CiTiao_local("citiao.txt");
            String[] str=null;
            System.out.println(strs.length);
            for(int i=0;i<strs.length;i++){
                str=strs[i].split("&&");
                StringHandle stringHandle=new StringHandle();
                CiTiao CiTiao=null;
                CiTiao=new CiTiao(str[0],str[1],str[2]);
                addToMySql.Add(CiTiao);
                System.out.println(CiTiao.toString());
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
