import React, { useState } from "react";
import {
  findNodeHandle,
  Image,
  PixelRatio,
  requireNativeComponent, StyleProp, Text, View, ViewStyle
} from 'react-native';
import SVGIcon from "./SVGIcon";

type SvgImageViewProps = {
  param: {
    uri: string,
    width: number,
    height: number,
  }
  nativeRef?: number | null,
  style?: StyleProp<ViewStyle>;
}

const SvgImageView = requireNativeComponent<SvgImageViewProps>('SvgImageView');

const SVGDemo = () => {

  const [svgRef, setSvgRef] = useState<number | null>()

  return (
    <View>
      <Text>SVG Render on Native Side</Text>
      <SvgImageView param={{
        uri: Image.resolveAssetSource(require('./assets/test-svg-2.xsvg')).uri,
        width: PixelRatio.getPixelSizeForLayoutSize(100),
        height: PixelRatio.getPixelSizeForLayoutSize(100),
      }} style={{
        width: 100,
        height: 100,
        backgroundColor: 'pink'
      }} />
      <SvgImageView param={{
        uri: Image.resolveAssetSource(require('./assets/test-svg.xsvg')).uri,
        width: PixelRatio.getPixelSizeForLayoutSize(200),
        height: PixelRatio.getPixelSizeForLayoutSize(200),
      }} style={{
        width: 200,
        height: 200,
        backgroundColor: 'yellow'
      }} />
      <SvgImageView param={{
        uri: Image.resolveAssetSource(require('./assets/test-tube.xsvg')).uri,
        width: PixelRatio.getPixelSizeForLayoutSize(150),
        height: PixelRatio.getPixelSizeForLayoutSize(150),
      }} style={{
        width: 150,
        height: 150,
        backgroundColor: 'gray'
      }}
        nativeRef={svgRef}
      />

      <Text>Native SVG Rendering</Text>
      <View ref={(ref) => {
        setTimeout(() => {
          setSvgRef(findNodeHandle(ref))
        }, 3000)
      }} style={{
        height: 150,
        width: 150,
      }}>
        <SVGIcon viewBox="0 0 1024 1024" style={{
          width: 150,
          height: 150,
        }} />
      </View>
    </View>
  )
}

export default SVGDemo