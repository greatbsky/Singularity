//
//  global.swift
//  Singularity
//
//  Created by Architect bian on 22/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

import UIKit


// MARK: - 屏幕宽度/高度
public let ScreenWidth = UIScreen.main.bounds.width
public let ScreenHeight = UIScreen.main.bounds.height
public let NavBarHeight = CGFloat(64)
// MARK: - 界面适配
// 320为iphont5屏幕宽度，375为iphone6屏幕宽度，414为iphone6+屏幕宽度
public let iphone5ScreenWidth  = CGFloat(320)
public let iphone6ScreenWidth  = CGFloat(375)
public let iphone6pScreenWidth = CGFloat(414)

func delay(_ delay:Double, closure:@escaping ()->()) {
  let when = DispatchTime.now() + delay
  DispatchQueue.main.asyncAfter(deadline: when, execute: closure)
}

struct G {
  public static var window = UIApplication.shared.delegate!.window!!
  public static let db = SQLiteManager.shareInstance
}
