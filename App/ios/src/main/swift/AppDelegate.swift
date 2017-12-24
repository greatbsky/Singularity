//
//  AppDelegate.swift
//  TestDel
//
//  Created by Architect bian on 14/12/2017.
//  Copyright © 2017 Architect bian. All rights reserved.
//

import UIKit
import core

@UIApplicationMain
class AppDelegate: BaseAppDelegate {
  
  override func didFinishLaunching(_ application: UIApplication, _ launchOptions: [UIApplicationLaunchOptionsKey: Any]?) {
    let jsCodeLocation = RCTBundleURLProvider.sharedSettings().jsBundleURL(forBundleRoot: "index", fallbackResource: nil)
    let rootView = RCTRootView(bundleURL: jsCodeLocation, moduleName: "Singularity", initialProperties: nil, launchOptions: launchOptions)
    rootView?.appProperties = ["k": "v"]
    self.window = UIWindow(frame: UIScreen.main.bounds)
    let rootVC = UIViewController()
    rootVC.view = rootView
    window?.rootViewController = rootVC
    window?.makeKeyAndVisible()
  }
  
}
