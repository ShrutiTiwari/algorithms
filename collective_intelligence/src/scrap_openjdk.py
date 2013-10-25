import threading
import Queue
import httplib2
from bs4 import BeautifulSoup

def createThread(thread_list, argsList):
    t = threading.Thread(target=processUrl, args=argsList)
    t.daemon = True
    thread_list.append(t)

URL = "http://openjdk.java.net"
resp, content = httplib2.Http().request(URL)
soup = BeautifulSoup(content)
count = 1
# print(soup.prettify())
thread_list = []

lock = threading.Lock()
counter=1
def processUrl(*argsList):    
    global counter
    for link in argsList:
     try:
      completeUrl = URL + link.get('href')
      p_resp, p_content = httplib2.Http().request(completeUrl)
      soup1 = BeautifulSoup(p_content)  # print('%25s' % link.string)
      pa = soup1.find_all('p')
      lock.acquire()
      print( '\n '+str(counter)+'.  ***************   %25s' % link.string + "    ***************     ")
      print(pa[0].text.strip())
      if(len(pa)>1): 
       print(pa[1].text.strip())      
     except Exception as e:
      print('*************** Error ***************')
      print(link)
      print(e)
     finally:
      counter = counter+1   
      lock.release()                
        
# interestedKeywords=  ['title'];
# print([(soup.find(each).name + "==>"+soup.find(each).string) for each in interestedKeywords])
argsList = [];
for link in soup.find_all('a'):
    href1 = link.get('href')
    if '/projects' in href1:        
        argsList.append(link)
        if(count % 10 == 0):            
            createThread(thread_list, argsList)            
            argsList = [];    
        count = count + 1
if(argsList!=[]):
    createThread(thread_list, argsList)            
    argsList = [];                

print('count===>  ' + str(count))
print('threads===>  ' + str(len(thread_list)))

for t1 in thread_list:
 t1.start()
      
for t1 in thread_list:
 t1.join()
 
print('all processed')