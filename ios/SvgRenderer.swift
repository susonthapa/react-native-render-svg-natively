//
//  SvgRenderer.swift
//  SVGRenderNativeFormat
//
//  Created by Susan Thapa on 11/09/2023.
//

import Foundation
import React

class SvgRenderer: UIView {
  
  var props: SvgProps? = nil
  
  private var dummyViewList: [UIView] = []
  
  @objc var svgProps: [String: Any]? = nil {
    didSet {
      props = SvgProps(
        name: svgProps!["name"] as! String,
        width: svgProps!["width"] as! Int,
        height: svgProps!["height"] as! Int
      )
    }
  }
  
  func getRenderedUIImage(_ child: UIView) {
    if let view = child as? RNSVGSvgView {
      return view.render(toUIImage: CGRect(x: 0, y: 0, width: props?.width!, height: props?.height!))
    }
  }
  
  // MARK: - React Children
  override func insertReactSubview(_ subview: UIView!, at atIndex: Int) {
    dummyViewList.insert(subview, at: atIndex)
  }
  
  override func removeReactSubview(_ subview: UIView!) {
    dummyViewList.remove(at: dummyViewList.firstIndex(of: subview)!)
  }
  
  override func reactSubviews() -> [UIView]! {
    return dummyViewList
  }
  
  
  
}
