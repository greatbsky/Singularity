//
//  AppDelegate.swift
//  TestDel
//
//  Created by Architect bian on 14/12/2017.
//  Copyright © 2017 Architect bian. All rights reserved.
//

import UIKit

open class BaseAppDelegate: UIResponder, UIApplicationDelegate {
  
  open var window: UIWindow?
  
  open func didFinishLaunching(_ application: UIApplication, _ launchOptions: [UIApplicationLaunchOptionsKey: Any]?) {

  }
  
  open func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
    didFinishLaunching(application, launchOptions);
    setupGlobalData();
    setupKeyBoardManager();
    setupGlobalStyle();
    setupReachability();
    print(VersionUtil.isNewVersion())
    return true
  }
  
  open func applicationWillResignActive(_ application: UIApplication) {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
  }
  
  open func applicationDidEnterBackground(_ application: UIApplication) {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
  }
  
  open func applicationWillEnterForeground(_ application: UIApplication) {
    // Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
  }
  
  open func applicationDidBecomeActive(_ application: UIApplication) {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
  }
  
  open func applicationWillTerminate(_ application: UIApplication) {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
  }
  
}

extension BaseAppDelegate {

    /**
     配置网络检测
     */
    fileprivate func setupReachability() {
        // 发出网络改变通知
        
    }
    
    /**
     配置全局数据
     */
    fileprivate func setupGlobalData() {
        
    }
    
    /**
     配置键盘管理者
     */
    fileprivate func setupKeyBoardManager() {
//        IQKeyboardManager.sharedManager().enable = true
    }
    
    /**
     全局样式
     */
    fileprivate func setupGlobalStyle() {
        UIApplication.shared.isStatusBarHidden = false
//        UIApplication.shared.statusBarStyle = UIStatusBarStyle.lightContent
    }
    
}
