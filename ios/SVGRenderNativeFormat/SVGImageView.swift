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
  
  @objc var svgComponent: [String: Any]? = nil {
    didSet {
      if let props = svgComponent {
        let uiImage = ReactSvgRenderer(bridge, props).renderToImage()
        self.imageView.image = uiImage
        self.imageView.contentMode = .scaleAspectFit
      }
    }
  }
  
}
