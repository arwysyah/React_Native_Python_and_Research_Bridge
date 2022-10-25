import numpy as np
import cv2 
import matplotlib.pyplot as plt
from PIL import Image
import io
import base64

def main (X,Y):
    fig = plt.figure()
    x = X.split(",")
    y = Y.split(",")

    x_data = []
    y_data = []
    
    for i in x:
        x_data.append(int(i))
    for i in y:
        y_data.append(int(i))    
    
    ay = fig.add_subplot(1,1,1)
    ay.plot(x_data,y_data)

    fig.canvas.draw()
    
    img = np.fromstring(fig.canvas.tostring_rgb(),dtype=np.uint8,sep='')
    img = img.reshape(fig.canvas.get_width_height()[::-1]+(3,))
    img = cv2.cvtColor(img,cv2.COLOR_RGB2BGR)

    #now it is converted to cv2 image
    #now it will convert to PIL IMAGE AND THEN FINALY BYTE TO STRING AND THEN WIKLL RETURN BYTE STRING  TO OUR JAVA CODE


    pil_im = Image.fromarray(img)
    buff = io.BytesIO()
    pil_im.save(buff,format="PNG")
    img_str = base64.b64encode(buff.getvalue())
    return ""+str(img_str,'utf-8')


