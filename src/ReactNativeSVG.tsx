import React from "react"
import { MAX_SVG_COUNT } from "./Constants"
import FlowerIcon from "./FlowerIcon"

const ReactNativeSVG = () => {
  return (
    <>
      {
        Array(MAX_SVG_COUNT).fill(null).map(() => {
          return (
            <FlowerIcon width={150} height={150} />
          )
        })
      }
    </>
  )
}

export default ReactNativeSVG