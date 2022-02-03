import requests
import json
import os
import re
import datetime
   
class item:
    def __init__(self):
        self.country=list()#国家
        self.province = list()#省份
        self.area=list()#地区
        self.confirm=list()#确诊
        self.suspect=list()#疑似
        self.heal=list()#治愈
        self.dead=list()#死亡
Data_Box=item()#数据盒子

#创建链接数据库
def connect(opt):
    config={'host':opt['host'] ,
            'user':opt['user'],
            'password':opt['password'] ,
            'port':opt['port'] ,
            'database':opt['database'] ,
            'charset':opt['charset'] 
            }
    try:
        mydb=mysql.connector.connect(**config)#connect方法加载config的配置进行数据库的连接，完成后用一个变量进行接收
    except mysql.connector.Error as e:
        print('数据库链接失败！',str(e))
    else:#try没有异常的时候才会执行
        print("数据库连接sucessfully!")
        return mydb
    
    # 插入
    # sql = "INSERT INTO sites (name, url) VALUES (%s, %s)"
    # val = ("RUNOOB", "https://www.runoob.com")
def add(mydb,sql,val):
    mycursor = mydb.cursor()
    mycursor.execute(sql, val)
    mydb.commit()    # 数据表内容有更新，必须使用到该语句
    print(mycursor.rowcount, "记录插入成功。")

    # 查询
    # sql="SELECT * FROM sites"
def query(mydb,sql,val):
    mycursor = mydb.cursor()
    mycursor.execute(sql,val)
    myresult = mycursor.fetchall()     # fetchall() 获取所有记录
    for x in myresult:
        print(x)
    return myresult

#获取链接内容
def GetHtmlText(url):
    try:
        res = requests.get(url,timeout = 30)
        res.raise_for_status()
        res.encoding = res.apparent_encoding
        return res.text
    except:
        return "Error"
#获取数据转为json
def getJson(China):
    City_Count_json = json.loads(China)
    City_Count_json = City_Count_json["data"]#将json数据中的data字段的数据提取处理
    City_Count_json = json.loads(City_Count_json)#将提取出的字符串转换为json数据
    return City_Count_json

#获取每日总信息
def getSum(City_Count_json):
    lastUpdateTime = City_Count_json["lastUpdateTime"]
    lastUpdateTime=re.search(r"[0-9]{4}-[0-9]{2}-[0-9]{2}",lastUpdateTime)
    lastUpdateTime=lastUpdateTime.group(0)
    chinaTotal_json = City_Count_json["chinaTotal"]#提取处其chinaTotal字段中的数据
    confirmCount = str(chinaTotal_json["confirm"])
    suspectCount = str(chinaTotal_json["suspect"])
    deadCount = str(chinaTotal_json["dead"])                   #GetTextCenter(China,r"\"deadCount\": ",r",\n")      #疑似人数
    cure = str(chinaTotal_json["heal"])                             #GetTextCenter(China,r"\"cure\": ",r"\n")                 #治愈人数
    print("更新时间：" + lastUpdateTime + "\n" + "确诊人数为：" + confirmCount + "人\n" + "死亡人数为：" +
          deadCount + "人\n" + "疑似人数为：" + suspectCount + "人\n" + "治愈人数为：" + cure +
          "人\n" )
    sum="。"+lastUpdateTime +","+ confirmCount +","+deadCount + "," +suspectCount +","+cure +"。\n"
    return sum
    
    
######用于循环中备注信息，防止混淆变量名而出错，然而还是耗费了相当长的时间理清这些变量
#areaTree_json[i]["children"]省份
#areaTree_json[i]["children"][j]["name"]省份名
#areaTree_json[i]["children"][j]["children"][n]省份中的地区 list
#areaTree_json[i]["children"][j]["children"][n]省份中的地区 json
#areaTree_json[i]["children"][j]["children"][n]["name"]省份中的地区名
#areaTree_json[i]["children"][j]["children"][n]["total"]省份中的地区数据json {'confirm': 134, 'suspect': 0, 'dead': 0, 'heal': 4}
#获取各省市信息
def Get_Data_China(City_Count_json):
    areaTree_json=City_Count_json["areaTree"]#包含国家、省份、地区的所有信息，且国家为首索引
    country_len = len(areaTree_json)
    for i in range(0,country_len):
        if(areaTree_json[i]["name"]=="中国"):            #如果为中国则说明具有省份信息
            province_len = len(areaTree_json[i]["children"])  #获取省份长度
            for j in range(0,province_len):
                area_len=len(areaTree_json[i]["children"][j]["children"])#获取地区长度
                for n in range(0,area_len):
                    total=areaTree_json[i]["children"][j]["children"][n]["total"]                  #获取地区的总体疫情情况+
                    Data_Box.country.append("中国")
                    Data_Box.province.append(areaTree_json[i]["children"][j]["name"])
                    Data_Box.area.append(areaTree_json[i]["children"][j]["children"][n]["name"])
                    Data_Box.confirm.append(total["confirm"])
                    Data_Box.dead.append(total["dead"])
                    Data_Box.heal.append(total["heal"])  #中国区域获取完毕
    return len(Data_Box.area)
#创建文件
#file_path：文件路径
#msg：即要写入的内容
def save__file(file_path,msg):
    f=open(file_path,"a",encoding='utf-8')
    f.write(msg)
    f.close

def Add_Area(fpath,length):
    print("国家  省份  地区  确诊人数  治愈人数  死亡人数  ")
    for n in range(0,length):
        print(Data_Box.country[n]+"  "+Data_Box.province[n]+"  "+Data_Box.area[n]+"  "
              +str(Data_Box.confirm[n])+"  "+str(Data_Box.heal[n])+"  "+ str(Data_Box.dead[n]))
        area_yiqing=Data_Box.country[n]+"&&"+Data_Box.province[n]+"&&"+Data_Box.area[n]+"&&"+str(Data_Box.confirm[n])+"&&"+str(Data_Box.heal[n])+"&&"+ str(Data_Box.dead[n])+"\n"
        save__file(fpath, area_yiqing)


def main():
    fpath="D://javaxiangmu//GZBD//WebContent//outfile/yiqing.txt"
    if(os.path.exists(fpath)):
        os.remove(fpath)
    else:
        Get_China=r"https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5"
        China = GetHtmlText(Get_China)    #获取链接内容
        City_Count_json=getJson(China)    #获取json数据
        sum=getSum(City_Count_json)         #获取每日信息
        save__file(fpath, sum)              #添加每日信息到sum表
        length=Get_Data_China(City_Count_json)#获取每个省市信息并获取长度
        Add_Area(fpath,length)

main()

