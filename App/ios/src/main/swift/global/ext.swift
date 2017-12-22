//
//  ext.swift
//  Singularity
//
//  Created by Architect bian on 22/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

import UIKit

// MARK: - 生成随机整数
public extension Int {
  
  /// 生成Int型随机数
  ///
  /// - Parameters:
  ///   - lower: min
  ///   - upper: max
  /// - Returns: 随机数
  public static func random(_ lower: Int = 0, _ upper: Int = Int.max) -> Int {
    return lower + Int(arc4random_uniform(UInt32(upper - lower + 1)))
  }
  
  /// 生成Int型随机数
  ///
  /// - Parameter range: [min, max]
  /// - Returns: 随机数
  public static func random(_ range: CountableClosedRange<Int>) -> Int {
    return random(range.lowerBound, range.upperBound)
  }
}

// MARK: - UIView扩展
public extension UIView {
  
  //MARK: 移除所有子视图
  internal func removeAllSubviews() {
    self.subviews.forEach { (subview) in
      subview.removeFromSuperview()
    }
  }
  
  //MARK: 获取视图位置,宽度,高度
  internal var top: CGFloat {
    get {
      return self.frame.origin.y
    }
  }
  
  internal var bottom: CGFloat {
    get {
      return self.frame.origin.y + self.frame.size.height
    }
  }
  
  internal var left: CGFloat {
    get {
      return self.frame.origin.x
    }
  }
  
  internal var right: CGFloat {
    get {
      return self.frame.origin.x + self.frame.size.width
    }
  }
  
  internal var width: CGFloat {
    get {
      return self.frame.size.width
    }
  }
  
  internal var height: CGFloat {
    get {
      return self.frame.size.height
    }
  }
}

