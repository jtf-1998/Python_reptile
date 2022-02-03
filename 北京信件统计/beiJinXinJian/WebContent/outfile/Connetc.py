import requests;
import re
import os
import traceback
from lxml import html
from lxml import etree
from _multiprocessing import send
from _overlapped import NULL

#获取HTML内容
def getHTMLText(url):
    access={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0"}
    try:
        r=requests.get(url,headers=access)
        r.raise_for_status()
        r.encoding=r.apparent_encoding
        return r.text
    except:
        return "无法连接"

def get_xinjian_massage(lst,url,fpath):
    #url="http://www.beijing.gov.cn/hudong/hdjl/com.web.complain.complainDetail.flow?originalId=AH17071400095"
    print(url)
    xinjian_html=getHTMLText(url)
    soup=etree.HTML(xinjian_html)
    #编号
    Id=soup.xpath("//a[@class='dex_yes font12']/@onclick")
    if "".join(Id)=="":
        Id=soup.xpath("//a[@class='dex_yes font12 ']/@onclick")
        Id=re.search(r"AH[0-9]{11}","".join(Id))
        Id=Id.group(0)
    else:
        Id=re.search(r"AH[0-9]{11}","".join(Id))
        Id=Id.group(0)
    #信件类型
    type=soup.xpath("//meta[@name='Keywords']/@content")
    if "".join(type)=="":
        type=soup.xpath("//meta[@name='Keywords ']/@content")
        type=re.search(r"^[^x00-xff]{2}","".join(type))
        type=type.group(0)
    else:
        type=re.search(r"^[^x00-xff]{2}","".join(type))
        type=type.group(0)
    #回复状态
    statue=str("已回复")
    #信件简略内容
    massage=soup.xpath("//div[@class='col-xs-10 col-sm-10 col-md-10 o-font4 my-2']/strong/text()")
    if "".join(massage)=="":
        massage=soup.xpath("//div[@class='col-xs-10 col-sm-10 col-md-10 o-font4 my-2 ']/strong/text()")
        massage="".join(massage)
    else:
        massage="".join(massage)
    #发信人
    addresser=soup.xpath("//div[@class='col-xs-10 col-lg-3 col-sm-3 col-md-4 text-muted']/text()")
    if "".join(addresser)=="":
        addresser=soup.xpath("//div[@class='col-xs-10 col-lg-3 col-sm-3 col-md-4 text-muted ']/text()")
        addresser="".join(addresser)
        addresser=re.search(r"(?<=来信人：).*","".join(addresser.split()))
        addresser=addresser.group(0)
    else:
        addresser="".join(addresser)
        addresser=re.search(r"(?<=来信人：).*","".join(addresser.split()))
        addresser=addresser.group(0)
    #发信时间
    send_time=soup.xpath("//div[@class='col-xs-5 col-lg-3 col-sm-3 col-md-3 text-muted']/text()")
    if "".join(send_time)=="":
        send_time=soup.xpath("//div[@class='col-xs-5 col-lg-3 col-sm-3 col-md-3 text-muted ']/text()")
        send_time=re.search(r"[0-9]{4}-[0-9]{2}-[0-9]{2}","".join(send_time))
        send_time=send_time.group(0)
    else:
        send_time=re.search(r"[0-9]{4}-[0-9]{2}-[0-9]{2}","".join(send_time))
        send_time=send_time.group(0)
    #网友同问
    asker=soup.xpath("//div[@class='col-xs-4 col-lg-3 col-sm-3 col-md-3 text-muted']/label/text()")
    if "".join(asker)=="":
        asker=soup.xpath("//div[@class='col-xs-4 col-lg-3 col-sm-3 col-md-3 text-muted ']/label/text()")
        asker=re.search(r"[0-9]+","".join(asker))
        asker=asker.group(0)
    else:
        asker=re.search(r"[0-9]+","".join(asker))
        asker=asker.group(0)
    #信件详细内容
    details=soup.xpath("//div[@class='col-xs-12 col-md-12 column p-2 text-muted mx-2']/text()")
    if "".join(details)=="":
        details=soup.xpath("//div[@class='col-xs-12 col-md-12 column p-2 text-muted mx-2 ']/text()")
        details="".join(details)
        details="".join(details.split())
    else:
        details="".join(details)
        details="".join(details.split())
    #回信人
    answering=soup.xpath("//div[@class='col-xs-9 col-sm-7 col-md-5 o-font4 my-2']/span/text()")
    
    if "".join(answering)=="":
        answering=soup.xpath("//div[@class='col-xs-9 col-sm-7 col-md-5 o-font4 my-2 ']/span/text()")
        if "".join(answering)=="":
            answering=soup.xpath("//div[@class='col-xs-9 col-sm-7 col-md-5 o-font4 my-2']/text()")
            if "".join(answering)=="":
                answering=soup.xpath("//div[@class='col-xs-9 col-sm-7 col-md-5 o-font4 my-2 ']/text()")
                answering="".join(answering)
                answering="".join(answering.split())
            else:
                answering="".join(answering)
                answering="".join(answering.split())
        else:
            answering="".join(answering)
            answering="".join(answering.split())
    else:
        answering="".join(answering)
        answering="".join(answering.split())
    #回信时间
    answer_time=soup.xpath("//div[@class='col-xs-12 col-sm-3 col-md-3 my-2']/text()")
    if "".join(answer_time)=="":
        answer_time=soup.xpath("//div[@class='col-xs-12 col-sm-3 col-md-3 my-2 ']/text()")
        answer_time=re.search(r"[0-9]{4}-[0-9]{2}-[0-9]{2}","".join(answer_time))
        answer_time=answer_time.group(0) 
    else:
        answer_time=re.search(r"[0-9]{4}-[0-9]{2}-[0-9]{2}","".join(answer_time))
        answer_time=answer_time.group(0) 
    #回复内容
    replyt=soup.xpath("//div[@class='col-xs-12 col-md-12 column p-4 text-muted my-3']")
    reply=replyt[0].xpath("string(.)").strip()
    if "".join(reply)=="":
        replyt=soup.xpath("//div[@class='col-xs-12 col-md-12 column p-4 text-muted my-3 ']")
        reply=replyt[0].xpath("string(.)").strip()
        reply="".join(reply)
        reply="".join(reply.split())
    else:
        reply="".join(reply)
        reply="".join(reply.split())
    #xinjian=Id
    xinjian=Id+"&&"+type+"&&"+massage+"&&"+statue+"&&"+send_time+"&&"+addresser+"&&"+asker+"&&"+details+"&&"+answering+"&&"+answer_time+"&&"+reply+"\n"
    print(xinjian)
    save__file(fpath,xinjian)
    return xinjian
    
#创建文件
#file_path：文件路径
#msg：即要写入的内容
def save__file(file_path,msg):
    f=open(file_path,"a",encoding='utf-8')
    f.write(msg)
    f.close
        
def Run(out_put_file,fpath):
    urls=""
    lsts=[]
    lst=[]
    cond=0
    with open(out_put_file,"r") as f:
        urls=f.read()
    lsts=urls.split(",")
    for i in lsts:
        xinjian=""
        xinjian=get_xinjian_massage(lst,i,fpath) 
        if xinjian=="":
            continue
        lst.append(xinjian)
        cond+=1
        print("\n当前速度：{:.2f}%".format(cond*100/len(lsts)),end="") 
    return lst
    
#主函数
def main():
    fpath="xinnjian.txt"
    out_put_file="xj_list.txt"
    if(os.path.exists(fpath)):
        os.remove(fpath)
    else:
        lst=[]
        lst=Run(out_put_file, fpath)
        for i in lst:
            print(i+"aaa")
    
#程序入口
main()


    
    
    
    
    
    
    
    
    
    
    
    
    