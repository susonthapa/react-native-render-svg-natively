import React, { useMemo } from "react";
import {
  Image,
  PixelRatio, Text, View
} from 'react-native';
import ReactTestRenderer from "react-test-renderer";
import FlowerIcon from "./FlowerIcon";
import { SvgImageView } from "./SvgImageView";

const SVGDemo = () => {
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