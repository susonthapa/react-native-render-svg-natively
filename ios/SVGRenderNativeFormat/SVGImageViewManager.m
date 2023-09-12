//
//  SVGImageViewManager.m
//  SVGRenderNativeFormat
//
//  Created by Susan Thapa on 13/07/2023.
//

#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"

@interface RCT_EXTERN_MODULE(SvgImageView, RCTViewManager)
RCT_EXPORT_VIEW_PROPERTY(param, NSDictionary)
RCT_EXPORT_VIEW_PROPERTY(svgName, NSString)
@end

@interface RCT_EXTERN_MODULE(SvgRenderer, RCTViewManager)
RCT_EXPORT_VIEW_PROPERTY(svgProps, NSDictionary)
@end
