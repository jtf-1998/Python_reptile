package com.lq.webmagic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLDivElement;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLElement;
import com.lq.file.LocalFile;
import com.lq.file.StringHandle;

public class HtmlUnit
{
    static List<String> lines_zi=new ArrayList<String>();
    static List<String> lines_jian=new ArrayList<String>();
    static List<String> lines_tou=new ArrayList<String>();
    static String line;
    public static void Value_start()
    {
        WebClient webClient=new WebClient(BrowserVersion.FIREFOX_60);
        try {
            webClient.getOptions().setActiveXNative(false);   //设置是否允许本地ActiveX组件
            webClient.getOptions().setJavaScriptEnabled(true);//启用/禁用JavaScript的支持。默认情况下，这个属性被启用。
            webClient.getOptions().setDoNotTrackEnabled(true);//启用/禁用“不跟踪”的支持。默认情况下，这个属性被禁用。
            webClient.getOptions().setThrowExceptionOnScriptError(false);//更改此Web客户端时出现脚本错误的行为。
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//指定是否也不例外将在一个失败的状态代码的情况下被抛出。成功的状态码的范围是200-299。默认值为true。
            webClient.getCache().setMaxSize(100);  //获取当前正在使用的缓存。
            webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置当前AJAX控制器 NicelyResynchronxxx()：这AjaxController重新同步调用从主线程中调用。这个想法是，异步AJAX调用直接响应于用户动作（因此在“主”线程，而不是在后台任务的线程）进行直接对用户有用的。轻松拥有一个可测试的状态，这些调用同步进行。
            webClient.getOptions().setCssEnabled(false);//不支持css
            webClient.getOptions().setUseInsecureSSL(true);//如果设置为true，客户端将接受连接到任何主机，而不管他们是否有有效证件或没有。当你试图连接到使用过期或损坏的证书的服务器，这是特别有用的。
            webClient.getCookieManager().setCookiesEnabled(true); //返回此web客户端使用cookie管理器。启用/禁用cookie支持。 Cookies是默认启用
            webClient.getCache().clear();
            webClient.setRefreshHandler(new ImmediateRefreshHandler());//设置每当刷新触发所使用的处理程序 这刷新处理程序后立即刷新指定的页面，使用指定的URL，而忽略了等待时间。
            webClient.getOptions().setTimeout(15*1000);	//设置WebConnection的超时。设置为2秒
            webClient.setJavaScriptTimeout(600*1000);   //设置了一个脚本所允许被终止之前执行的毫秒数
            webClient.waitForBackgroundJavaScript(600*1000);//安排通过window.setTimeout，window.setInterval或异步的XMLHttpRequest执行JavaScript的任务。决定了后台任务等待（毫秒）延迟
            HtmlPage page = webClient.getPage("http://www.beijing.gov.cn/hudong/hdjl/com.web.search.mailList.flow");
            HtmlElement a=page.getElementByName("nextPage");
            int j=1,lastj=0;
            StringHandle sh=new StringHandle();
            List<String> lastInfo_zi=new ArrayList<String>();
            List<String> lastInfo_jian=new ArrayList<String>();
            List<String> lastInfo_tou=new ArrayList<String>();

            while(j!=2240)   //设置爬取2235页，从现在到2015年1月
            {
                String nowInfo=page.asXml();
                String re="letterdetail\\('.*?','AH[0-9]{11}'\\)";
                List<String> infoList_zi=sh.getExpString(re, nowInfo);
                int g_size_zi=infoList_zi.size();
                if(sh.StringListSameOutStringList(infoList_zi, lastInfo_zi).size()!=g_size_zi&&g_size_zi==6)
                {
                    for(int i=0;i<g_size_zi;i++)
                    {
                        String type=sh.getExpToString("(?<=[(]')[^x00-xff]{2}",infoList_zi.get(i));
                        System.out.println(type);
                        String type1=new String("建议");
                        String type2=new String("咨询");
                        if(type.equals(type1)){type="com.web.suggest.suggesDetail.flow?originalId=";}
                        else if(type.equals(type2)){type="com.web.consult.consultDetail.flow?originalId=";}
                        else {type="com.web.complain.complainDetail.flow?originalId=";}
                        String theWeb=sh.getExpToString("AH[0-9]{11}", infoList_zi.get(i));
                        theWeb=type+theWeb;
                        System.out.println(theWeb);
                        lines_zi.add(theWeb);
                        System.out.println("-------------------------------------------");

                        if(i==g_size_zi-1)
                        {
                            lastInfo_zi=infoList_zi;
                            System.out.println(j);
                            j++;
                            break;
                        }

                    }
                    page=a.click();
                }
            }

            LocalFile localFile=new LocalFile();
            try {
                localFile.list_local(lines_zi,"xj_list.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (FailingHttpStatusCodeException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            webClient.close(); //
        }

    }
    public static void main(String[] args) {
        Value_start();
    }

}
