import React from "react";
import { MAX_SVG_COUNT } from "./Constants";
import ReactTestRenderer from "react-test-renderer";
import { PixelRatio } from "react-native";
import FlowerIcon from "./FlowerIcon";
import { SvgImageView } from "./SvgImageView";

const OffScreenSVG = () => {

  return (
    <>
      {
        Array(MAX_SVG_COUNT).fill(null).map(() => {
          const svgJson = ReactTestRenderer.create(
            <FlowerIcon width={PixelRatio.getPixelSizeForLayoutSize(150)} height={PixelRatio.getPixelSizeForLayoutSize(150)} />
          ).toJSON()
          return (
            <SvgImageView
              svgComponent={svgJson}
              style={{
                width: 150,
                height: 150,
              }}
            />
          )
        })
      }
    </>
  )
}

export default OffScreenSVG