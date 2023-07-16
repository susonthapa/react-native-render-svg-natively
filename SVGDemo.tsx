import React from "react";
import {
  Image,
  PixelRatio,
  requireNativeComponent, StyleProp, Text, View, ViewStyle
} from 'react-native';

type SvgImageViewProps = {
  param: {
    uri: string,
    width: number,
    height: number,
  }
  style?: StyleProp<ViewStyle>;
}

const SvgImageView = requireNativeComponent<SvgImageViewProps>('SvgImageView');

const SVGDemo = () => {
  return (
    <View>
      <Text>SVG Render on Native Side</Text>
      <SvgImageView param={{
        uri: Image.resolveAssetSource(require('./test-svg-2.svg')).uri,
        width: PixelRatio.getPixelSizeForLayoutSize(100),
        height: PixelRatio.getPixelSizeForLayoutSize(100),
      }} style={{
        width: 100,
        height: 100,
        backgroundColor: 'pink'
      }} />
      <SvgImageView param={{
        uri: Image.resolveAssetSource(require('./test-svg.svg')).uri,
        width: PixelRatio.getPixelSizeForLayoutSize(200),
        height: PixelRatio.getPixelSizeForLayoutSize(200),
      }} style={{
        width: 200,
        height: 200,
        backgroundColor: 'yellow'
      }} />
      <SvgImageView param={{
        uri: Image.resolveAssetSource(require('./test-tube.svg')).uri,
        width: PixelRatio.getPixelSizeForLayoutSize(150),
        height: PixelRatio.getPixelSizeForLayoutSize(150),
      }} style={{
        width: 150,
        height: 150,
        backgroundColor: 'gray'
      }} />
    </View>
  )
}

export default SVGDemo