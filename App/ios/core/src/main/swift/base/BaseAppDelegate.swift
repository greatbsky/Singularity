//
//  AppDelegate.swift
//  TestDel
//
//  Created by Architect bian on 14/12/2017.
//  Copyright © 2017 Architect bian. All rights reserved.
//

import UIKit
import Reachability
import UserNotifications
import IQKeyboardManagerSwift

open class BaseAppDelegate: UIResponder, UIApplicationDelegate {
  
  open var window: UIWindow?
  open let reachability = Reachability()!
  
  open func didFinishLaunching(_ application: UIApplication, _ launchOptions: [UIApplicationLaunchOptionsKey: Any]?) {

  }

  open func requestAuthoritation() {
    UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .sound]) { ok, err in
        if err != nil {
            return
        }
        if ok {
        }
    }
  }
  
  open func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
    didFinishLaunching(application, launchOptions)
    setupGlobalData()
    setupKeyBoardManager()
    setupGlobalStyle()
    setupReachability()
    print(VersionUtil.isNewVersion())
    requestAuthoritation() // 安装后第一次打开app
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
        NotificationCenter.default.addObserver(self, selector: #selector(reachabilityChanged), name: .reachabilityChanged, object: reachability)
        do {
            try reachability.startNotifier()
        } catch{
            print("could not start reachability notifier")
        }
    }
    
    @objc fileprivate func reachabilityChanged(note: Notification) {
        let reachability = note.object as! Reachability
        switch reachability.connection {
        case .wifi:
            print("Reachable via WiFi")
        case .cellular:
            print("Reachable via Cellular")
        case .none:
            print("Network not reachable")
            
            let alert = UIAlertController(title: local("no_network"),
                                          message: local("please_check_network"),
                                          preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: local("I_known"), style: .default))
            self.window?.rootViewController?.present(alert, animated: true)
        }
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
        IQKeyboardManager.sharedManager().enable = true
    }
    
    /**
     全局样式
     */
    fileprivate func setupGlobalStyle() {
        UIApplication.shared.isStatusBarHidden = false
//        UIApplication.shared.statusBarStyle = UIStatusBarStyle.lightContent
        UITabBarItem.appearance().setTitleTextAttributes([.font:UIFont(name:"Avenir-Heavy", size:14)!], for:.normal)
    }
    
}
