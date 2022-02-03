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

def get_citiao_massage(lst,url,fpath):
    #url="http://baike.baidu.com/item/COUNTA/7669327"
    print(url)
    citiao_html=getHTMLText(url)
    soup=etree.HTML(citiao_html)
    #词条名称dd class="lemmaWgt-lemmaTitle-title"  h1 text()
    name1=soup.xpath("//dd[@class='lemmaWgt-lemmaTitle-title']/h1/text()")
    name2=soup.xpath("//dd[@class='lemmaWgt-lemmaTitle-title']/h2/text()")
    name1="".join(name1)
    if "".join(name2)=="":
        name2=""
    else:
        name2="".join(name2)
    name=name1+name2
    #词条详情div class="lemma-summary"  text()
    desc=soup.xpath("//div[@class='lemma-summary']/div/text()")
    desc="".join(desc)
    desc=re.sub(r"\s+", "", desc)
    url=re.sub(r"\s+", "", url)
    #citiao=name+"&&"+desc+"&&"+url+"\n"
    citiao=name+"&&"+desc+"\n"
    print(citiao)
    save__file(fpath,citiao)
    return citiao
    
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
        citiao=""
        citiao=get_citiao_massage(lst,i,fpath) 
        if citiao=="":
            continue
        lst.append(citiao)
        cond+=1
        print("\n当前速度：{:.2f}%".format(cond*100/len(lsts)),end="") 
    return lst
    
#主函数
def main():
    #fpath="citiao.txt"
    fpath="dancitiao.txt"
    out_put_file="citiao_list.txt"
    if(os.path.exists(fpath)):
        os.remove(fpath)
    else:
        lst=[]
        lst=Run(out_put_file, fpath)
        for i in lst:
            print(i+"aaa")
    
#程序入口
main()