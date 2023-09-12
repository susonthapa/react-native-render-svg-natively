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
  var delegate: SvgRendererProtocol? = nil
  
  @objc var svgProps: [String: Any]? = nil {
    didSet {
      props = SvgProps(
        name: svgProps!["name"] as! String,
        width: svgProps!["width"] as! Int,
        height: svgProps!["height"] as! Int
      )
    }
  }
  
  func getRenderedUIImage(_ child: RNSVGSvgView) -> UIImage? {
    return child.render(toUIImage: .init(x: 0, y: 0, width: .init(integerLiteral: props!.width), height: .init(integerLiteral: props!.height)))
  }
  
  override func addSubview(_ view: UIView) {
    // no-op
  }
  
  // MARK: - React Children
  override func insertReactSubview(_ subview: UIView!, at atIndex: Int) {
    dummyViewList.insert(subview, at: atIndex)
    if let view = subview as? RNSVGSvgView, let props = props {
      delegate?.onInsertView(self, view, props)
    }
  }
  
  override func removeReactSubview(_ subview: UIView!) {
    if let props = props {
      delegate?.onRemoveView(props)
      dummyViewList.remove(at: dummyViewList.firstIndex(of: subview)!)
    }
  }
  
  override func willMove(toSuperview newSuperview: UIView?) {
    if newSuperview == nil {
      delegate = nil
    }
  }
  
  override func reactSubviews() -> [UIView]! {
    return dummyViewList
  }
  
}
