import { requireNativeComponent, StyleProp, ViewStyle } from "react-native";
import { ReactTestRendererJSON } from "react-test-renderer";

type SvgImageViewProps = {
  param?: {
    uri: string,
    width: number,
    height: number,
  }
  svgName?: string,
  svgComponent?: ReactTestRendererJSON | null | ReactTestRendererJSON[],
  style?: StyleProp<ViewStyle>;
}

export const SvgImageView = requireNativeComponent<SvgImageViewProps>('SvgImageView');