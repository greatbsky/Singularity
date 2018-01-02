//
//  MainController.swift
//  Singularity
//
//  Created by Architect bian on 27/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//


import UIKit
import core
import UserNotifications

class HomeController : BaseViewController, UIToolbarDelegate {
  
  @IBOutlet weak var toolbar: UIToolbar!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    self.toolbar.delegate = self
  }
  
  @IBAction func doItem(_ sender: Any) {
    
  }
  
  @IBAction func doNotify(_ sender: UIButton) {
    let content = UNMutableNotificationContent()
    content.title = "您有新的咖啡" // title now always appears
    // content.subtitle = "whatever" // new
    content.body = "到喝咖啡时间了~~~"
    content.sound = UNNotificationSound.default()
    
    // new iOS 10 feature: attachments! AIFF, JPEG, or MPEG
    let url = Bundle.main.url(forResource: "cup", withExtension: "jpg")!
    
    if let att = try? UNNotificationAttachment(identifier: "cup", url: url, options:nil) {
      content.attachments = [att]
    }
    (UIApplication.shared.delegate as! AppDelegate).notifyManager.notify(String("asdfeww"), content)
  }
  
}
