//
//  AppDelegate.swift
//  TestDel
//
//  Created by Architect bian on 14/12/2017.
//  Copyright © 2017 Architect bian. All rights reserved.
//

import UIKit
import core
import UserNotifications

@UIApplicationMain
class AppDelegate: BaseAppDelegate {
  
  let notifyManager = NotifyManager()
  
  override func didFinishLaunching(_ application: UIApplication, _ launchOptions: [UIApplicationLaunchOptionsKey: Any]?) {
//    launchRNWindow(launchOptions)
//    launchNativeWindow(launchOptions)
    launchMainStoryboard()
    setAppearance()
    let center = UNUserNotificationCenter.current()
    center.delegate = self.notifyManager
  }
  
  func launchMainStoryboard() {
    //just do nothing
  }
  
  func launchNativeWindow(_ launchOptions: [UIApplicationLaunchOptionsKey: Any]?) {
    self.window = self.window ?? UIWindow()
    self.window!.rootViewController = MainController(nibName:nil, bundle:nil)
    self.window!.backgroundColor = .white
    self.window!.makeKeyAndVisible()
  }
  
  func launchRNWindow(_ launchOptions: [UIApplicationLaunchOptionsKey: Any]?) {
    let jsCodeLocation = RCTBundleURLProvider.sharedSettings().jsBundleURL(forBundleRoot: "index", fallbackResource: nil)
    let rootView = RCTRootView(bundleURL: jsCodeLocation, moduleName: "Singularity", initialProperties: nil, launchOptions: launchOptions)
    rootView?.appProperties = ["k": "v"]
    self.window = UIWindow(frame: UIScreen.main.bounds)
    let rootVC = UIViewController()
    rootVC.view = rootView
    window?.rootViewController = rootVC
    window?.makeKeyAndVisible()
  }
  
  func setAppearance() {
    UITabBarItem.appearance().setTitleTextAttributes([.font:UIFont(name:"Avenir-Heavy", size:14)!], for:.normal)
    
  }
  
}
