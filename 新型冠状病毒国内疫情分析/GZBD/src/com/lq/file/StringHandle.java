package com.lq.file;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandle
{
	public static final String UTF_8 = "UTF-8"; 
    //将String容器转换为String数组
    public String[] StringListToStringNlist(List<String> list)
    {
        return list.toArray(new String[list.size()]);
    }

    //获得字符串中的符合正则表达式的值
    public List<String> getExpString(String exp,String str)
    {
        Pattern pattern=Pattern.compile(exp);
        Matcher matcher=pattern.matcher(str);
        List<String> result=new ArrayList<String>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }

    public String ExpToString(String exp,String str)
    {
        Pattern pattern=Pattern.compile(exp);
        Matcher matcher=pattern.matcher(str);
        while (matcher.find())
        {
            str=matcher.group();
        }
        return str;
    }
    public String changeCharset(String str, String oldCharset, String newCharset) 
    		  throws UnsupportedEncodingException { 
    		 if (str != null) { 
    		  //用旧的字符编码解码字符串。解码可能会出现异常。 
    		  byte[] bs = str.getBytes(oldCharset); 
    		  //用新的字符编码生成字符串 
    		  return new String(bs, newCharset); 
    		 } 
    		 return null; 
    		 } 

    public String toUTF_8(String str) throws UnsupportedEncodingException{ 
    	 return this.changeCharset("ascii",str, UTF_8); 
    	 } 

    //获得字符串中的符合正则表达式的值
    public String getExpToString(String exp,String str)
    {
        StringHandle handle=new StringHandle();
        Pattern pattern=Pattern.compile(exp);
        Matcher matcher=pattern.matcher(str);
        List<String> resultt=new ArrayList<String>();
        while (matcher.find()) {
            resultt.add(matcher.group());
        }
        return handle.ListToString(resultt);
    }

    //将Stringlist转换为String
    public String ListToString(List<String> lists)
    {
        String str="";
        for(int i=0;i<lists.size();i++)
        {
            str+=lists.get(i);
        }
        return str;
    }

    //将String数组转换为String容器
    public List<String> StringNlistToStringList(String []strlist)
    {
        List<String> list = java.util.Arrays.asList(strlist);
        return list;
    }

    //字符串容器和字符串容器中将互相包含的元素取出
    public List<String> StringListSameOutStringList(List<String> strlist1,List<String> strlist2)
    {
        List<String> result=new ArrayList<String>();
        int g_size=strlist1.size();
        int g_size2=strlist2.size();
        if(g_size==0||g_size2==0)
            return result;
        for(int i=0;i<g_size;i++)
        {
            String itTemp=strlist1.get(i);
            if(StringListIsExContainString(strlist2,itTemp))
            {
                result.add(itTemp);
            }
        }
        return result;
    }

    //字符串列表精确包含某个字符串
    public boolean StringListIsExContainString(List<String> strlist,String it)
    {
        int num=strlist.size();
        for(int i=0;i<num;i++)
        {
            if(strlist.get(i).equals(it))
                return true;
        }
        return false;
    }

    //将字符串中重复的元素移除
    public List<String> StringListRemoveRepeat(List<String> infos)
    {
        List<String> result=new ArrayList<String>();
        int g_size=infos.size();
        for(int i=0;i<g_size;i++)
        {
            String temp=infos.get(i);
            if(!StringListContainString(result,temp))
            {
                result.add(temp);
            }
        }
        return result;
    }

    //判断字符串中是否含有
    public boolean StringListContainString(List<String> info,String txt)
    {
        int g_size=info.size();
        for(int i=0;i<g_size;i++)
        {
            if(info.get(i).equals(txt))
            {
                return true;
            }
        }
        return false;
    }


    //返回对应子字符串容器中对应字符串中的位置的容器
    public List<Integer> StringListInStringListIndexof(List<String> allinfo,List<String> sublist)
    {
        List<Integer> numlist=new ArrayList<Integer>();
        int g_size=allinfo.size();
        int g_size2=sublist.size();
        if(g_size==0||g_size2==0)
            return numlist;
        for(int i=0;i<g_size2;i++)
        {
            numlist.add(allinfo.indexOf(sublist.get(i)));
        }
        return numlist;
    }

}
