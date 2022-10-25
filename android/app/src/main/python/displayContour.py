from mpl_toolkits import mplot3d
import numpy as np
import matplotlib.pyplot as plt
from PIL import Image
import io
import base64
def main():
    fig = plt.figure()
    ax = fig.add_subplot(111, projection="3d")
    X, Y = np.mgrid[-1:1:30j, -1:1:30j]
    Z = np.sin(np.pi*X)*np.sin(np.pi*Y)
    ax.plot_surface(X, Y, Z, cmap="autumn_r", lw=0.5, rstride=1, cstride=1, alpha=0.5)
    ax.contour(X, Y, Z, 10, lw=3, cmap="autumn_r", linestyles="solid", offset=-1)
    ax.contour(X, Y, Z, 10, lw=3, colors="k", linestyles="solid")

    fig.canvas.draw()
    
    img = np.fromstring(fig.canvas.tostring_rgb(),dtype=np.uint8,sep='')
    img = img.reshape(fig.canvas.get_width_height()[::-1]+(3,))

    #now it is converted to cv2 image
    #now it will convert to PIL IMAGE AND THEN FINALY BYTE TO STRING AND THEN WIKLL RETURN BYTE STRING  TO OUR JAVA CODE


    pil_im = Image.fromarray(img)
    buff = io.BytesIO()
    pil_im.save(buff,format="PNG")
    img_str = base64.b64encode(buff.getvalue())
    plt.show()
    return ""+str(img_str,'utf-8')
