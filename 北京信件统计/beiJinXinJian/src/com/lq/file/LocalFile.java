package com.lq.file;

import com.lq.bean.XinJian;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocalFile
{
    public void Save_local(XinJian xinJian,String files) throws Exception//xieru xinjian de xiangxi xingxi
    {
        File file=new File("D:\\javaxiangmu\\beiJinXinJian\\WebContent\\outfile"+File.separator+files);


        if (!file.getParentFile().exists())
        {
            file.getParentFile().mkdirs();
        }
        file.delete();
        FileWriter local_file=new FileWriter(file);
        BufferedWriter output=new BufferedWriter(local_file);
        //Writer output=new FileWriter(local_file,true);

        String contents=xinJian.toString()+",\n";
        output.write(contents);
        output.close();
    }

    public void list_local(List<String> lists,String files) throws Exception// xieru xinjian de liebiao
    {
        File file=new File("D:\\javaxiangmu\\beiJinXinJian\\WebContent\\outfile"+File.separator+files);
        if (!file.getParentFile().exists())
        {
            file.getParentFile().mkdirs();
        }
        file.delete();
        FileWriter local_file=new FileWriter(file);
        BufferedWriter output=new BufferedWriter(local_file);
        //Writer output=new FileWriter(local_file,true);
        for(String list:lists){
            String url="http://www.beijing.gov.cn/hudong/hdjl/"+list.toString();
            String contents=url+",\n";
            output.write(contents);
        }
        output.close();
    }

    public String[] read_list_local(String files) throws Exception
    {
        File file =new File("D:\\javaxiangmu\\beiJinXinJian\\WebContent\\outfile"+File.separator+files);
        FileReader locla_file=new FileReader(file);
        BufferedReader input=new BufferedReader(locla_file);
        char bb[]=new char[1024];
        String []read_all=null;
        while(input.readLine()!=null){
            int lines=input.read(bb);
            String read=new String(bb,0,lines);
            read_all=read.split(",");
        }
        return read_all;
    }

    public String[] read_xinjian_local(String files) throws Exception
    {
        File file =new File("D:\\javaxiangmu\\beiJinXinJian\\WebContent\\outfile"+File.separator+files);
        FileReader locla_file=new FileReader(file);
        BufferedReader input=new BufferedReader(locla_file);
        char bb[]=new char[1024];
        String []read_all=null;
        String str=null;
        int len=-1;
        List<String> reads=new ArrayList<String>();
        while((len=input.read(bb))!=-1){
            String read=new String(bb,0,len);
            reads.add(read);

        }
        StringHandle stringHandle=new StringHandle();
        str=stringHandle.ListToString(reads);
        read_all=str.split("\\r?\\n");
        return read_all;
    }

}
