import React, {useState} from 'react';
import {
  View,
  NativeModules,
  Button,
  Image,
  ScrollView,
  StyleSheet,
  ActivityIndicator,
  DeviceEventEmitter,
} from 'react-native';
// import TurboModules from './NativeModules/jsi';
import ButtonNativeAnimation from './android/app/src/component/ButtonNative';
import ButtonSwitchNative from './android/app/src/component/SwitchNative';
const {ConverterFunctionModule, PythonModule} = NativeModules;
const property = {data: '1'};
const renderImage = uri => {
  return (
    uri !== '' && (
      <Image
        resizeMode="contain"
        source={{
          uri: 'file:////' + uri,
          width: 300,
          height: 300,
        }}
      />
    )
  );
};

const x = [80, 70, 40, 10];
const y = [12, 14, 18, 18];
const heightFlex = {height: 80};

const App = () => {
  const [uri, setUri] = useState('');
  const [counterEvent, setCounterEvent] = useState(0);

  React.useEffect(() => {
    //return event
    const emit = DeviceEventEmitter.addListener('counter', data => {
      setCounterEvent(data);
    });

    return () => emit.remove();
  }, []);

  //Java Bridge

  //send props to native and display with Toast
  const onPress = () => {
    ConverterFunctionModule.createFunction('testName', 'testLocation');
  };

  //send props and Return value from native
  const returnFromNative = async () => {
    try {
      const data = await ConverterFunctionModule.returnFromNative(
        'params',
        JSON.stringify(property),
      );
      // console.log(`Created a new event with id ${data}`);
    } catch (e) {
      return;
    }
  };

  //send event to Java native
  const sendEvents = () => {
    ConverterFunctionModule.sendEvents();
  };

  //Python Bridge
  const returnFromPython = async () => {
    try {
      const data = await PythonModule.returnFromPython('12', '16');
      // console.log(`returning  ${data}`); open thos console to see the value that return by Jsva Native
    } catch (e) {
      return;
    }
  };
  //call Python Script
  const invokePython = () => {
    PythonModule.InvokePython();
  };

  //send props and python will run the script with function
  const passingToPython = async () => {
    try {
      const data = await PythonModule.passingToPython('200', '167');
      console.log(`passing and return from python ${data}`);
    } catch (e) {
      console.log(e);
    }

    try {
      const b = PythonModule.convertImageFromBitmap(
        'android.graphics.Bitmap@e4e34df',
      );
      console.log(b);
    } catch (error) {
      console.log('first', error);
    }
  };
  // console.log(uri);

  //running Matplotlib python
  const generateMathplotlib = async () => {
    try {
      const data = await PythonModule.generateMathplotlib();
      // console.log(data, 'Bitmap');
    } catch (error) {
      return;
    }
  };

  const processMathPlotlib = async () => {
    setUri('');
    try {
      const data = await PythonModule.processMathplotlib(
        x.toString(),
        y.toString(),
      );
      setUri(data);

      // console.log(data, 'data file');
    } catch (error) {
      return;
    }
  };

  const plot3DGraph = async script => {
    setUri('');
    try {
      const data = await PythonModule.plot3D(script);
      setUri(data);

      // console.log(data, 'data file');
    } catch (error) {
      // console.log(error);
      return;
    }
  };

  // // C++ JSI
  // const invokeJSI = () => {
  //   const message = TurboModules.getString('name', 'data');
  //   console.log(message);
  // };

  console.log(counterEvent);
  const texts =
    counterEvent > 0 && counterEvent < 301
      ? counterEvent.toString()
      : 'Send Event';
  return (
    <ScrollView>
      {uri == null && <ActivityIndicator size={'small'} color="blue" />}
      <View style={styles.container}>{renderImage(uri)}</View>
      <View style={heightFlex} />
      <ButtonSwitchNative style={styles.native} />
      {/* <ButtonNativeAnimation
        isOn={true}
        title={'Button Animation Native'}
        style={styles.native}
      /> */}
      <View style={heightFlex} />

      <Button title={texts} onPress={sendEvents} />
      <View style={heightFlex} />

      <Button title="Brdige to Java" onPress={onPress} />
      <View style={heightFlex} />
      <Button title="Return from Java Native" onPress={returnFromNative} />
      <View style={heightFlex} />
      <Button title="Invoke Python Script" onPress={invokePython} />
      <View style={heightFlex} />
      <Button title="Returning from Python" onPress={returnFromPython} />
      <View style={heightFlex} />
      <Button
        title="Pass data and get from Python File"
        onPress={passingToPython}
      />
      <View style={heightFlex} />
      <Button title="Matplotlib" onPress={generateMathplotlib} />
      <View style={heightFlex} />
      <Button title="Process Matplotlib" onPress={processMathPlotlib} />

      <View style={heightFlex} />
      <Button title="Plot 3D" onPress={() => plot3DGraph('plotGraph')} />
      <View style={heightFlex} />
      <Button
        title="Plot Contour"
        onPress={() => plot3DGraph('displayContour')}
      />

      {/* <View style={{height: 80}} />
      <Button title="Invoke JSI" onPress={invokeJSI} /> */}
    </ScrollView>
  );
};
const styles = StyleSheet.create({
  container: {width: '100%', justifyContent: 'center', alignItems: 'center'},
  native: {
    height: 60,
    width: '80%',
    alignItems: 'center',
    top: 40,
    alignSelf: 'center',
  },
});
export default App;
