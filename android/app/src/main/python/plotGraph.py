# import numpy as np
# import cv2 as cv


# cap = cv.VideoCapture(-1)
# def main():
    
#     global cap
# if not cap.isOpened():
#     print("Cannot open camera")
# #     exit()
# while True:
#     # Capture frame-by-frame
#     ret, frame = cap.read()
#     # if frame is read correctly ret is True
#     if not ret:
#         print("Can't receive frame (stream end?). Exiting ...")
#         break
#     # Our operations on the frame come here
#     gray = cv.cvtColor(frame, cv.COLOR_BGR2GRAY)
#     # Display the resulting frame
#     cv.imshow("frame", gray)
#     if cv.waitKey(1) == ord("q"):
#         break
# # When everything done, release the capture
# cap.release()
# cv.destroyAllWindows()
from mpl_toolkits import mplot3d
import numpy as np
# import cv2 
import matplotlib.pyplot as plt
from PIL import Image
import io
import base64

def main():
    fig = plt.figure()
    ax = plt.axes(projection='3d')
    ax = plt.axes(projection='3d')

    # Data for a three-dimensional line
    zline = np.linspace(0, 15, 1000)
    xline = np.sin(zline)
    yline = np.cos(zline)
    ax.plot3D(xline, yline, zline, 'gray')

    # Data for three-dimensional scattered points
    zdata = 15 * np.random.random(100)
    xdata = np.sin(zdata) + 0.1 * np.random.randn(100)
    ydata = np.cos(zdata) + 0.1 * np.random.randn(100)
    ax.scatter3D(xdata, ydata, zdata, c=zdata, cmap='Greens')
    plt.show()
    fig.canvas.draw()
    
    img = np.fromstring(fig.canvas.tostring_rgb(),dtype=np.uint8,sep='')
    img = img.reshape(fig.canvas.get_width_height()[::-1]+(3,))

    #now it is converted to cv2 image
    #now it will convert to PIL IMAGE AND THEN FINALY BYTE TO STRING AND THEN WIKLL RETURN BYTE STRING  TO OUR JAVA CODE


    pil_im = Image.fromarray(img)
    buff = io.BytesIO()
    pil_im.save(buff,format="PNG")
    img_str = base64.b64encode(buff.getvalue())
    return ""+str(img_str,'utf-8')