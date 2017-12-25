//
//  MyButton.swift
//  Singularity
//
//  Created by Architect bian on 25/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

import UIKit

@IBDesignable class MyButton : UIButton {
  @IBInspectable var borderWidth : Int {
    set {
      self.layer.borderWidth = CGFloat(newValue)
    }
    get {
      return Int(self.layer.borderWidth)
    }
  }
}
