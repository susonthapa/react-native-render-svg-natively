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
  
  override init(frame: CGRect) {
    self.imageView = .init(frame: frame)
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
      let svgImage = SVGKImage(contentsOf: URL(string: uri))
      svgImage?.scaleToFit(inside: size)
      imageView.image = svgImage?.uiImage
      imageView.contentMode = .scaleAspectFit
    }
  }
}
