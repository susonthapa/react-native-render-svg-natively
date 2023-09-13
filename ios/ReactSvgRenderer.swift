//
//  ReactSvgRenderer.swift
//  SVGRenderNativeFormat
//
//  Created by Susan Thapa on 13/09/2023.
//

import Foundation

class ReactSvgRenderer {
  
  let bridge: RCTBridge
  let input: [String: Any]
  let componentDataByName: [AnyHashable: Any]
  
  init(_ bridge: RCTBridge, _ input: [String : Any]) {
    self.bridge = bridge
    self.input = input
    
    let uiManagerClass: AnyClass = object_getClass(bridge.uiManager)!
    let ivarLayout = class_getInstanceVariable(uiManagerClass, "_componentDataByName")
    componentDataByName = bridge.uiManager.value(forKey: NSString(utf8String: ivar_getName(ivarLayout!)!)! as String) as! [AnyHashable: Any]
  }
  
  func renderToUIImage() -> UIImage {
    let rootView = renderChildView(nil, input) as! RNSVGSvgView
    return rootView.render(toUIImage: .init(x: 0, y: 0, width: 413, height: 413))
  }
  
  private func renderChildView(_ parent: UIView?, _ childProps: [String: Any]) -> UIView {
    let type = childProps["type"] as! String
    
    let props = childProps["props"] as! [String: Any]
    let view = createView(type, props)
    parent?.addSubview(view)
    
    let children = childProps["children"] as? [Any]
    if children?.isEmpty ?? true {
      return view
    }
    
    for i in 0..<children!.count {
      let tempProp = children![i]
      renderChildView(view, tempProp as! [String: Any])
    }
    
    return view
  }
  
  private func getViewManager(_ name: String) -> RCTViewManager {
    return bridge.uiManager.moduleRegistry.module(forName: "\(name)Manager") as! RCTViewManager
  }
  
  private func createView(_ name: String, _ props: [String: Any]) -> UIView {
    let managerName = "\(name)Manager"
    let manager = bridge.uiManager.moduleRegistry.module(forName: managerName) as! RCTViewManager
    let view = manager.view()!
    (componentDataByName[name] as! RCTComponentData).setProps(props, forView: view)
    
    return view
  }
  
}
