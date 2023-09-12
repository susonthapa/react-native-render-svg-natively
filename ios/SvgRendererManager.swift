//
//  SvgRendererManager.swift
//  SVGRenderNativeFormat
//
//  Created by Susan Thapa on 11/09/2023.
//

import Foundation

protocol SvgRendererProtocol {
  func onInsertView(_ parent: SvgRenderer, _ child: RNSVGSvgView, _ props: SvgProps)
  func onRemoveView(_ props: SvgProps)
}

@objc(SvgRenderer)
class SvgRendererManager: RCTViewManager, SvgRendererProtocol {
  
  override class func requiresMainQueueSetup() -> Bool {
    true
  }
  
  private var imageCache: [String: UIImage] = [:]
  private var renderCallbacks: [String: [(UIImage) -> Void]] = [:]
  
  override func view() -> UIView! {
    let view = SvgRenderer()
    view.delegate = self
    return view
  }
  
  func addRenderCallback(_ name: String, _ callback: @escaping (UIImage) -> Void) {
    let cachedImage = imageCache[name]
    if cachedImage != nil {
      callback(cachedImage!)
    } else {
      if (renderCallbacks[name] == nil) {
        renderCallbacks[name] = []
      }
      renderCallbacks[name]?.append(callback)
    }
  }
  
  func onInsertView(_ parent: SvgRenderer, _ child: RNSVGSvgView, _ props: SvgProps) {
    if let image = parent.getRenderedUIImage(child) {
      imageCache[props.name] = image
      renderCallbacks[props.name]?.forEach { it in
        it(image)
      }
      renderCallbacks.removeValue(forKey: props.name)
    }
  }
  
  func onRemoveView(_ props: SvgProps) {
    imageCache.removeValue(forKey: props.name)
  }
  
}
