import requests
import json
import re
import os
import traceback
from lxml import html
from lxml import etree
from _multiprocessing import send
from _overlapped import NULL
'''
获取词条的url链接到citiao_list.txt文件
'''
def getUrlText(url,page):
    try:
        access={"limit": "24",
                "timeout": "3000",
                "filterTags": "%5B%5D",
                "tagId": "76607",
                "fromLemma": "false",
                "contentLength": "40",
                "page": page}
        header={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36"}
        r=requests.post(url,data=access,headers=header)
        r.raise_for_status()
        r.encoding=r.apparent_encoding
        print("连接成功")
        return r.text
    except:
        return "连接失败"
    
def getJson(html,fpath):
    date=json.loads(html)
    if date and 'lemmaList' in date.keys():
        lemmaList= date.get('lemmaList')
        for item in lemmaList:
            name = item.get('lemmaTitle')   #词条名称
            name=re.sub(r"\s+", "", name)
            Desc=item.get('lemmaDesc')     #词条简介
            Desc=re.sub(r"\s+", "", Desc)
            url = item.get('lemmaUrl')     #词条链接
            url=re.sub(r"\s+", "",url)
            #citiao=name+"&&"+Desc+"&&"+url+"\n"
            #citiao=name+"&&"+Desc+"\n"
            citiao=url+",\n"
            #citiao=Desc+"---------------"
            print(citiao)
            save__file(fpath,citiao)
            
 #创建文件
#file_path：文件路径
#msg：即要写入的内容
def save__file(file_path,msg):
    f=open(file_path,"a",encoding='utf-8')
    f.write(msg)
    f.close         
    

def main():
    url="https://baike.baidu.com/wikitag/api/getlemmas"
    #pafph="citiao.txt"
    #pfaph="dancitiao.txt"
    #pfaph="fenci.txt"
    fpath="citiao_list.txt"
    page=84
    if(os.path.exists(fpath)):
        os.remove(fpath)
    else:
        for page in range(0,501):
            html= getUrlText(url,page)
            getJson(html,fpath)
            paget=page+1
            print("第%s页"%paget)
    
main()