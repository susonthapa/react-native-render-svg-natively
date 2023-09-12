//
//  SVGImageViewManager.swift
//  SVGRenderNativeFormat
//
//  Created by Susan Thapa on 13/07/2023.
//

import Foundation

@objc(SvgImageView)
class SVGImageViewManager: RCTViewManager {
  
  override class func requiresMainQueueSetup() -> Bool {
    true
  }
  
  override func view() -> UIView! {
    return SVGImageView(frame: .zero, bridge: bridge)
  }
}
