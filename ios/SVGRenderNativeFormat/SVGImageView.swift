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
  
  @objc var imageUri: String = "" {
    didSet {
      let svgImage = SVGKImage(contentsOf: URL(string: imageUri))
      imageView.image = svgImage?.uiImage
      imageView.contentMode = .scaleAspectFit
      
//      SDWebImageManager.shared.loadImage(with: URL(string: imageUri), options: [], context: [.imageThumbnailPixelSize: CGSize(width: 100, height: 100)], progress: nil) { (image, _, _, _, _, _) in
//        if let image = image {
//          self.imageView.image = image
//        }
//      }
    }
  }
}
