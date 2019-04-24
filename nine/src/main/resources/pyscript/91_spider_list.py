import requests
import sys
import os,re,time,random
def random_ip():
    a=random.randint(1,255)
    b=random.randint(1,255)
    c=random.randint(1,255)
    d=random.randint(1,255)
    return(str(a)+'.'+str(b)+'.'+str(c)+'.'+str(d))
def get_list(flag):
    list=[]
    tittle=[]
    base_url='http://91porn.com/view_video.php?viewkey='
    page_url='http://91porn.com/v.php?next=watch&page='+str(flag)
    get_page=requests.get(url=page_url)
    viewkey=re.findall('http://91porn.com/view_video.php\?viewkey=(.*?)&page=.*?&viewtype=basic&category=.*?" title=',get_page.text)
    for key in viewkey:
        item={}
        headers={'Accept-Language':'zh-CN,zh;q=0.9','User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36','X-Forwarded-For':random_ip(),'referer':page_url,'Content-Type': 'multipart/form-data; session_language=cn_CN'}
        video_url=[]
        base_req=requests.get(url=base_url+key,headers=headers)
        ifm=re.findall('<iframe width="560" height="315" src="(.*?)" frameborder="0" allowfullscreen></iframe>',base_req.text)
        bases_req = requests.get(ifm[0], headers=headers)
        video_url=re.findall(r'<source src="(.*?)" type=\'video/mp4\'>',str(bases_req.content,'utf-8',errors='ignore'))
        tittle=re.findall(r'<div id="viewvideo-title">(.*?)</div>',str(base_req.content,'utf-8',errors='ignore'),re.S)
        try:
            t=tittle[0]
            tittle[0]=t.replace('\n','')
            t=tittle[0].replace(' ','')
        except Exception as e:
            print(e)
        item["key"] = key
        item["link"] = str(video_url[0])
        item["name"] = str(t)
        list.append(item)
    return list
if __name__ == "__main__":
    page = sys.argv[1]
    print(get_list(page))