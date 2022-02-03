package com.lq.file;

import com.lq.bean.BinLin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocalFile
{

    public String[] read_list_local(String files) throws Exception
    {
        File file =new File("D:\\\\javaxiangmu\\\\GZBD\\\\WebContent\\\\outfile"+File.separator+files);
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

    public String[] read_BinLin_local(String files) throws Exception
    {
        File file =new File("D:\\javaxiangmu\\GZBD\\WebContent\\outfile"+File.separator+files);
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
        int cont=1;
        for(String read:read_all) {
        	cont++;
//        	System.out.println(read);
//        	System.out.println("--------------------------------------");
        	System.out.println("第"+cont+"条");
        }
//        System.out.println(read_all[0]);
        return read_all;
    }
//    public static void main(String[] args) {
//    	LocalFile localFile=new LocalFile();
//    	try {
//			read_BinLin_local("yiqing.txt");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
