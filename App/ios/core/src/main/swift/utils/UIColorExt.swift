//
//  UIColorExt.swift
//  core
//
//  Created by Architect bian on 23/12/2017.
//  Copyright © 2017 Architect bian. All rights reserved.
//

import UIKit


// uicolor init simplified
extension UIColor{
    class func rbg(r: CGFloat, g: CGFloat, b: CGFloat) -> UIColor {
        let color = UIColor.init(red: r/255, green: g/255, blue: b/255, alpha: 1)
        return color
    }
}

