import React, { useEffect, useMemo } from "react";
import {
  Image,
  PixelRatio,
  requireNativeComponent, StyleProp, Text, View, ViewStyle
} from 'react-native';
import ReactTestRenderer from "react-test-renderer";
import { Path, Svg } from "react-native-svg";
import FlowerIcon from "./FlowerIcon";
import { SvgImageView } from "./SvgImageView";


const SvgRenderer = requireNativeComponent<{
  svgProps: {
    name: string,
    width: number,
    height: number,
  }
}>('SvgRenderer')

const SVGDemo = () => {

  // useEffect(() => {
  //   console.log(`TODO: FlowerIcon`, JSON.stringify(ReactTestRenderer.create(
  //     <FlowerIcon style={{
  //       width: 150,
  //       height: 150,
  //     }} color='red' stroke={'pink'} fill='white' strokeWidth={5.0} viewBox='0 0 1024 1024' />
  //   ).toJSON()));


  // }, [])

  const svgJson = useMemo(() => (
    ReactTestRenderer.create(
      <FlowerIcon width={PixelRatio.getPixelSizeForLayoutSize(150)} height={PixelRatio.getPixelSizeForLayoutSize(150)} />
    ).toJSON()
  ), [])

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
      />

      <Text>Off Screen rendering</Text>
      <SvgImageView
        svgComponent={svgJson}
        style={{
          width: 150,
          height: 150,
        }}
      />

      <FlowerIcon width={150} height={150} />

    </View>
  )
}

export default SVGDemo