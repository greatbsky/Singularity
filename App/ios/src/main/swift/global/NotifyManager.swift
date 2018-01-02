//
//  MyUserNotificationHelper.swift
//  Singularity
//
//  Created by Architect bian on 2/1/2018.
//  Copyright © 2018 Facebook. All rights reserved.
//

import UserNotifications

class NotifyManager : NSObject {
  
  private var stack:[String:UNMutableNotificationContent] = [:]
  
  private func checkAuthorization() {
    let center = UNUserNotificationCenter.current()
    center.getNotificationSettings {
      settings in
      switch settings.authorizationStatus {
      case .notDetermined:
        self.doAuthorization()
      case .denied:
        print("denied, giving up")
      case .authorized:
        self.createNotification()
      }
    }
  }
  
  private func doAuthorization() {
    let center = UNUserNotificationCenter.current()
    center.requestAuthorization(options: [.alert, .sound]) { ok, err in
      if err != nil {
        return
      }
      if ok {
        self.createNotification()
      }
    }
  }
  
  private func createNotification() {
    for (key, value) in stack {
      let trigger = UNTimeIntervalNotificationTrigger(timeInterval: 10, repeats: false)
      // combine them into a request
      let req = UNNotificationRequest(identifier: key, content: value, trigger: trigger)
      let center = UNUserNotificationCenter.current()
      center.add(req)
    }
  }
  
  
  func notify(_ id: String, _ content: UNMutableNotificationContent) {
    stack.updateValue(content, forKey: id)
    self.checkAuthorization()
  }
}

extension NotifyManager : UNUserNotificationCenterDelegate {

  func userNotificationCenter(_ center: UNUserNotificationCenter,
                              willPresent notification: UNNotification,
                              withCompletionHandler completionHandler: @escaping (UNNotificationPresentationOptions) -> Void) {
    completionHandler([.sound, .alert])
  }
  
  func userNotificationCenter(_ center: UNUserNotificationCenter, didReceive response: UNNotificationResponse, withCompletionHandler completionHandler: @escaping () -> Void) {
    
    let id = response.actionIdentifier // can be default, dismiss, or one of ours
    print("user action was: \(id)")
    let notification = response.notification
    print("user action was: \(notification.request.identifier)")
    completionHandler()
  }
}
