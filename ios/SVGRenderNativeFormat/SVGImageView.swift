//
//  File.swift
//  SVGRenderNativeFormat
//
//  Created by Susan Thapa on 13/07/2023.
//

import Foundation
import UIKit

class SVGImageView: UIView {
  
  private let imageView: UIImageView
  private let bridge: RCTBridge
  
  init(frame: CGRect, bridge: RCTBridge) {
    self.imageView = .init(frame: frame)
    self.bridge = bridge
    super.init(frame: frame)
    addSubview(imageView)
    self.imageView.translatesAutoresizingMaskIntoConstraints = false
    NSLayoutConstraint.activate([
      imageView.leadingAnchor.constraint(equalTo: self.leadingAnchor),
      imageView.trailingAnchor.constraint(equalTo: self.trailingAnchor),
      imageView.topAnchor.constraint(equalTo: self.topAnchor),
      imageView.bottomAnchor.constraint(equalTo: self.bottomAnchor)
    ])
  }
  
  required init?(coder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }
  
  @objc var param: [String: Any] = [:] {
    didSet {
      let uri = param["uri"] as! String
      let width = param["width"] as! Double
      let height = param["height"] as! Double
      let size = CGSize(width: width, height: height)
//      let svgImage = SVGKImage(contentsOf: URL(string: uri))
//      svgImage?.scaleToFit(inside: size)
//      imageView.image = svgImage?.uiImage
//      imageView.contentMode = .scaleAspectFit
    }
  }
  
  @objc var svgName: String? = nil {
    didSet {
      if let name = svgName, let viewManager = bridge.module(for: SvgRendererManager.self) as? SvgRendererManager {
        viewManager.addRenderCallback(name) { it in
          self.imageView.image = it
          self.imageView.contentMode = .scaleAspectFit
        }
      }
    }
  }
  
  @objc var svgComponent: [String: Any]? = nil {
    didSet {
      if let props = svgComponent {
        let uiImage = ReactSvgRenderer(bridge, props).renderToImage()
        self.imageView.image = uiImage
        self.imageView.contentMode = .scaleAspectFit
      }
    }
  }
  
  private func demoSVGRender() -> RNSVGSvgView? {
    guard let uiManagerClass = object_getClass(bridge.uiManager) else {
      print("Failed to get uiManagerClass")
      return nil
    }
    
    let ivarLayout = class_getInstanceVariable(uiManagerClass, "_componentDataByName")
    if let unwrappedIvar = ivarLayout, let componentDataByName = bridge.uiManager.value(forKey: NSString(utf8String: ivar_getName(unwrappedIvar)!)! as String) as? [AnyHashable: Any] {
        let svgManager = bridge.uiManager.moduleRegistry.module(forName: "RNSVGSvgViewManager") as! RCTViewManager
        let pathManager = bridge.uiManager.moduleRegistry.module(forName: "RNSVGPathManager") as! RCTViewManager
        let groupManager = bridge.uiManager.moduleRegistry.module(forName: "RNSVGGroupManager") as! RCTViewManager
        
        let svgProps: [String: Any] = [
          "stroke": [
            "type": 0,
            "payload": -16181
          ],
          "fill": [
            "type": 0,
            "payload": -1
          ],
          "minX": 0,
          "minY": 0,
          "vbWidth": 1024,
          "vbHeight": 1024,
          "bbWidth": 150,
          "bbHeight": 150,
          "align": "xMidYMid",
          "meetOrSlic": 0,
          "strokeWidth": 5,
          "propList": [
            "fill",
            "stroke",
            "strokeWidth"
          ]
        ]
        
        let svgView = svgManager.view() as! RNSVGSvgView
        (componentDataByName["RNSVGSvgView"] as! RCTComponentData).setProps(svgProps, forView: svgView)
        let groupView = groupManager.view()!
        (componentDataByName["RNSVGGroup"] as! RCTComponentData).setProps(svgProps, forView: groupView)
        svgView.addSubview(groupView)
        
        groupView.addSubview(getPathView(pathManager, componentDataByName, "M964.858 634.71c-5.924 0-11.848-3.384-15.233-9.308-56.7-112.556-279.272-245.422-280.965-247.114-8.463-5.078-11.002-15.233-5.924-22.85 5.078-8.463 15.233-11.002 22.85-5.924 6.77 4.231 116.786 69.395 203.953 150.638-89.705-166.717-189.567-266.578-190.413-268.271-6.77-6.77-6.77-16.926 0-23.696 6.77-6.77 16.926-6.77 23.696 0 5.924 5.924 155.716 157.408 257.27 402.83 3.384 8.463 0 17.772-8.463 22.003-1.693.846-4.232 1.693-6.77 1.693z"))
        groupView.addSubview(getPathView(pathManager, componentDataByName, "M897.156 583.934a50.777 50.777 0 1 0 101.554 0 50.777 50.777 0 1 0-101.554 0Z"))
        groupView.addSubview(getPathView(pathManager, componentDataByName, "M947.933 651.636c-16.926 0-33.005-5.924-45.7-17.772-13.54-12.694-21.157-28.773-22.003-47.391-.846-17.772 5.924-35.544 17.772-48.238 11.848-13.54 28.774-21.157 47.392-22.004 17.772-.846 35.544 5.924 48.238 17.772 13.54 11.848 21.157 28.774 22.003 47.392.846 17.772-5.924 35.544-17.772 48.238s-28.773 21.157-47.391 22.003h-2.54zm0-101.553h-1.693c-9.309 0-17.772 4.231-23.696 11.001-5.924 6.77-9.309 15.233-9.309 24.542 0 9.31 4.232 17.772 11.002 23.696 13.54 12.695 35.544 11.848 48.238-1.692 5.924-6.77 9.309-15.233 9.309-24.542 0-9.31-4.231-17.772-11.002-23.696-5.924-5.924-14.387-9.31-22.85-9.31zM50.875 634.71c-2.539 0-5.078-.847-6.77-1.693-8.463-3.385-11.848-13.54-8.463-22.003 101.553-245.422 251.345-396.906 257.27-402.83 6.77-6.77 16.925-6.77 23.695 0s6.77 16.926 0 23.696c-.846.846-100.707 101.554-190.413 267.425 87.167-80.397 197.183-146.407 203.954-149.792 8.462-5.078 18.618-1.693 22.85 5.924 5.077 7.617 1.692 18.618-5.925 22.85-2.539 1.692-225.11 134.558-280.965 247.114-3.385 5.924-9.31 9.309-15.233 9.309z"))
        groupView.addSubview(getPathView(pathManager, componentDataByName, "M677.123 440.066c-23.696 0-42.314-18.618-42.314-42.314s18.618-42.314 42.314-42.314 42.314 18.618 42.314 42.314-18.618 42.314-42.314 42.314zm0-50.777c-5.078 0-8.463 3.385-8.463 8.463s3.385 8.463 8.463 8.463 8.463-3.385 8.463-8.463-3.386-8.463-8.463-8.463z"))
        groupView.addSubview(getPathView(pathManager, componentDataByName, "M584.032 507.769a25.388 25.388 0 1 0 50.777 0 25.388 25.388 0 1 0-50.777 0Z"))
        groupView.addSubview(getPathView(pathManager, componentDataByName, "M609.42 550.083c-23.696 0-42.314-18.619-42.314-42.314s18.618-42.314 42.314-42.314 42.314 18.618 42.314 42.314-18.618 42.314-42.314 42.314zm0-50.777c-5.077 0-8.463 3.385-8.463 8.463s3.386 8.462 8.463 8.462 8.463-3.385 8.463-8.462-3.385-8.463-8.463-8.463z"))
        
        return svgView
    }
    
    return nil
  }
  
    
  private func getPathView(_ manager: RCTViewManager, _ componentDataByName: [AnyHashable: Any], _ path: String) -> UIView {
    let view = manager.view()!
    let props = [
      "d": path
    ]
    (componentDataByName["RNSVGPath"] as! RCTComponentData).setProps(props, forView: view)
    return view
  }
}
