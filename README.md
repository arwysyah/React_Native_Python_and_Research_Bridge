
React Native Bridge 


How To Install
yarn install (to install node modules)
probably you wont get local.properties  so you can create by copy from your another project

if you get unlicensed chaquopy .... , it because because it used restricted version ,you can copy ``chaquopy.license=free`` in local.properties to run fully chaquopy
but it download all python package and make this project bigger , so if you just want to test this library ,no need to install fully chaquopy



# React Native Bridge

## Installation

1. **Install dependencies:**
   ```bash
   yarn install
   ```
   This will install the necessary node modules.

2. **Set up `local.properties`:**
   If you don’t already have a `local.properties` file, you can create one by copying it from another project. 

3. **Handling Chaquopy licensing issues:**
   If you encounter an "unlicensed Chaquopy" issue, it's due to the project using a restricted version of Chaquopy. To resolve this, add the following to your `local.properties` file:
   ```
   chaquopy.license=free
   ```
   However, note that fully installing Chaquopy will download all Python packages and significantly increase the project size. If you just want to test the library, there’s no need to fully install Chaquopy.

## Contents

1. **Bridge Native Module**
2. **Bridge Native Module (Sending Property to Java Native)**
3. **Bridge Native Module (Sending and Receiving Properties from Java Native)**
4. **Inject Python Script from React Native and Java Native**
5. **Invoke, Run, and Receive Properties from Python**
6. **Running Python Modules (e.g., Numpy, Matplotlib, OpenCV)**
7. **Bridge Native UI Component**
8. **Send and Receive Events (Bridge)**
9. **Bridge Native UI Component with UI Animation on the UI Thread (Java)**
10. **Create Modules to Convert Images to Bitmap and Save Them to the Camera Folder**

## Video Demo

[Watch the demo here](https://user-images.githubusercontent.com/105067142/197696112-4971517b-8bae-428e-89f2-8538f803956f.mp4)

## Article

For more detailed information, refer to this article:  
[React Native and Python: Run Python Script on the Frontend](https://expans.io/2022/11/04/react-native-and-python-run-python-script-on-the-frontend-side/)
