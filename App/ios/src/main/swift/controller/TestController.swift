//
//  TestController.swift
//  Singularity
//
//  Created by Architect bian on 3/1/2018.
//  Copyright © 2018 Facebook. All rights reserved.


import UIKit
import core
import UserNotifications
import Alamofire
import SVProgressHUD

class TestController : BaseViewController {
  
  @IBOutlet weak var img: UIImageView!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    SVProgressHUD.show()
    delay(6) {
      SVProgressHUD.dismiss()
    }
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
  
  
  @IBAction func request(_ sender: UIButton) {
    let parameters:Dictionary = ["key":"93c921ea8b0348af8e8e7a6a273c41bd"]
    HttpUtil.request("\(APIConf.hostDefault)user/u123456", parameters: parameters)
      .responseString { response in
        print("Response String: \(String(describing: response.result.value))")
      }
      .responseJSON {
        response in
        print("result==\(response.result)")   // 返回结果，是否成功
        print("Response: \(String(describing: response.response))") // http url response
        if let json = response.result.value {
          print("JSON: \(json)") // serialized json response
        }
        
        if let data = response.data, let utf8Text = String(data: data, encoding: .utf8) {
          print("Data: \(utf8Text)") // original server data as UTF8 string
        }
    }
  }
  
  @IBAction func download(_ sender: UIButton) {
    let destination = DownloadRequest.suggestedDownloadDestination(for: .documentDirectory)
    print("destination: \(destination)")
    HttpUtil.download("https://httpbin.org/image/png", to: destination)
      .downloadProgress { progress in
        print("Download Progress: \(progress.fractionCompleted)")
      }
      .responseData { response in
        print(response)
        
        if response.error == nil, let imagePath = response.destinationURL?.path {
          let image = UIImage(contentsOfFile: imagePath)
          DispatchQueue.main.sync {
            self.img.image = image
          }
        }
    }
  }
  
  
}

