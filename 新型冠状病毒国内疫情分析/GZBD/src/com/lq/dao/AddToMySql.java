package com.lq.dao;

import com.lq.bean.BinLin;
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
    public boolean exit(String shen,String diqu,String nd)   //根据省名称和时间查询是否存在 如果存在返回true，不存在返回false
    {
        //System.out.println(query(id));
        return query(shen,diqu,nd)==1?true:false;
    }
    public int query(String shen,String diqu,String nd)   //查询是否存在;存在return 1;否存在return 0;
    {
        BinLin BinLin=null;
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet result=null;
        int count=-1;
        try {
            Class.forName(DriverName);
            conn=DriverManager.getConnection(Url, UName, Upwd);
            String sql="select count(*) from zbl where shen=? and diqu=? and nd=?";
            pstmt= conn.prepareStatement(sql);
            pstmt.setString(1,shen);
            pstmt.setString(2,diqu);
            pstmt.setString(3, nd);
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
    public boolean Add(BinLin BinLin)   //添加信息
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            AddToMySql addToMySql=new AddToMySql();
            //System.out.println(addToMySql.exit(BinLin.getId()));
            if(addToMySql.exit(BinLin.getShen(),BinLin.getDiqu(),BinLin.getNd())!=true) {
                //System.out.println("2");
                Class.forName(DriverName);
                conn = DriverManager.getConnection(Url, UName, Upwd);
                String sql = "insert into zbl values(NULL,?,?,?,?,?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, BinLin.getNd());
                pstmt.setInt(2, BinLin.getConfirm());
                pstmt.setInt(3, BinLin.getDead());
                pstmt.setInt(4, BinLin.getSuspect());
                pstmt.setInt(5, BinLin.getHeal());
                pstmt.setString(6, BinLin.getShen());
                pstmt.setString(7, BinLin.getDiqu());
                pstmt.setInt(8, BinLin.getDconfirm());
                pstmt.setInt(9, BinLin.getDheal());
                pstmt.setInt(10, BinLin.getDdead());

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
            String []  strs = localFile.read_BinLin_local("yiqing.txt");
            String[] str_country=null;
            String[] str_area=null;
            System.out.println(strs.length);
            String country=strs[0];
            country=country.replace("。",""); 
            //System.out.println(country);
            str_country=country.split(",");
            String nd=str_country[0];		//时间
            int confirm=Integer.parseInt(str_country[1]);		//全国确诊
            int dead=Integer.parseInt(str_country[2]);     //全国死亡
            int suspect=Integer.parseInt(str_country[3]);      //全国疑似
            int heal=Integer.parseInt(str_country[4]);      //全国治愈
            
            for(int i=1;i<strs.length;i++){
                str_area=strs[i].split("&&");
                StringHandle stringHandle=new StringHandle();
                BinLin BinLin=null;
                BinLin=new BinLin(nd, confirm, dead, suspect, heal, str_area[1], str_area[2], Integer.parseInt(str_area[3]), Integer.parseInt(str_area[4]), Integer.parseInt(str_area[5]));
                addToMySql.Add(BinLin);
                //System.out.println(BinLin.toString());
            }
            //System.out.println(str_area.length);
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
