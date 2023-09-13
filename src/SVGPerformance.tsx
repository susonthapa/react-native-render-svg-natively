import React from "react";
import { ScrollView, View } from "react-native";
import OffScreenSVG from "./OffScreenSVG";
import ReactNativeSVG from "./ReactNativeSVG";

const SVGPerformance = () => (
  <View style={{
    height: '100%',
  }}>
    <ScrollView style={{
      height: '100%',
    }}>
      <View>
        <ReactNativeSVG />
        {/* <OffScreenSVG /> */}
      </View>
    </ScrollView>
  </View>
)

export default SVGPerformance