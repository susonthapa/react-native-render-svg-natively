/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import {
  Image,
  requireNativeComponent,
  SafeAreaView, StatusBar, StyleProp, Text, useColorScheme, ViewStyle
} from 'react-native';

import {
  Colors
} from 'react-native/Libraries/NewAppScreen';

type SvgImageViewProps = {
  imageUri: string,
  style?: StyleProp<ViewStyle>;
}

const SvgImageView = requireNativeComponent<SvgImageViewProps>('SvgImage'); 

function App(): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <Text>SVG Render on Native Side</Text>
      <SvgImageView imageUri={Image.resolveAssetSource(require('./test-svg-2.svg')).uri} style={{
        width: 100,
        height: 100,
      }} />
      <SvgImageView imageUri={Image.resolveAssetSource(require('./test-svg.svg')).uri} style={{
        width: 100,
        height: 100,
      }} />
      <SvgImageView imageUri={Image.resolveAssetSource(require('./test-tube.svg')).uri} style={{
        width: 100,
        height: 100,
      }} />
    </SafeAreaView>
  );
}

export default App;
