import matplotlib
import matplotlib.pyplot as plt #数据可视化
import jieba #词语切割
import wordcloud #分词
from wordcloud import WordCloud,ImageColorGenerator,STOPWORDS #词云，颜色生成器，停止
import numpy as np #科学计算
from PIL import Image #处理图片

def ciyun():
    #打开文本
    with open('dancitiao.txt','r',encoding='UTF-8') as f:  # 打开新的文本转码为utf-8
        textfile= f.read()  #读取文本内容
    wordlist = jieba.cut_for_search(textfile)#切割词语
    space_list = ' '.join(wordlist) # 链接词语
    backgroud = np.array(Image.open('beijin.png')) #背景图片，只有黑白图才能按照形状生成词云
    mywordcloud = WordCloud(width=14000, height=2200,
                            background_color=None, mode="RGBA",#背景颜色
                            mask=backgroud, #写字用的背景图，从图片中提取颜色
                            max_words=500, #最大词语数
                            stopwords=STOPWORDS,#停止的默认词语
                            font_path='simkai.ttf',#源码自带字体
                            max_font_size=200,#最大字体尺寸
                            random_state=50,#随机角度
                            scale=1).generate(space_list) #生成词云
    image_color = ImageColorGenerator(backgroud)#生成词云的颜色
    plt.imshow(mywordcloud) #显示词云
    plt.axis('off') #关闭坐标（x,y轴）
    plt.savefig('cytu.png') #保存图片
    plt.show()#显示
 
def main():
    ciyun()
 
if __name__ == '__main__':
    main()



