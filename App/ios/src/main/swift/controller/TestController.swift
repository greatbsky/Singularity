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
import SDWebImage
import RxSwift
import RxCocoa
import SnapKit
import SwiftyJSON
import pop
import IBAnimatable

class TestController : BaseViewController {
  
  @IBOutlet weak var img: UIImageView!
  
  @IBOutlet weak var img2: UIImageView!
  
    @IBOutlet weak var txtNum: UITextField!
    @IBOutlet weak var label: UILabel!
    
    @IBOutlet weak var myview: AnimatableView!
    
    
    override func viewDidLoad() {
      super.viewDidLoad()
      SVProgressHUD.show()
      delay(6) {
        SVProgressHUD.dismiss()
      }
      img2.sd_setImage(with: URL(string: "http://d.hiphotos.baidu.com/image/pic/item/838ba61ea8d3fd1ffa1179293a4e251f95ca5f8d.jpg"), placeholderImage: UIImage(named: "placeholder.jpg"),
                       completed: { (image, error, cache, url) in
                        print(image!)
                        })
      txtNum.rx.text.map(){text -> String in
        print(text!)
        if(text == nil) {
          return ""
        } else {
          return text!
        }}.bind(to: label.rx.text)
      let box = UIView()
      box.backgroundColor = UIColor.blue
      self.view.addSubview(box)
      box.snp.makeConstraints { (make) -> Void in
        make.edges.equalTo(txtNum).inset(UIEdgeInsetsMake(50, 0, -50, 0))
      }
      let jsonString = "{\"k\": \"v\"}"
      if let dataFromString = jsonString.data(using: .utf8, allowLossyConversion: false) {
        var json:JSON!
        do { json = try JSON(data: dataFromString) }
        catch { print("Error JSON: \(error)") }
        print(json["k"].string!)
      }
      
      let animation = POPSpringAnimation(propertyNamed: kPOPLayerScaleXY)
      animation!.toValue = NSValue(cgSize: CGSize(width: 1.1, height: 1.1))
      animation!.springBounciness = 10 //[0-20] 弹力 越大则震动幅度越大
      animation!.springSpeed = 20 //[0-20] 速度 越大则动画结束越快'CGSizeMake' is unavailable
      self.view.backgroundColor = UIColor.red
      self.view.layer.pop_add(animation, forKey: "scaleXY")
      
//      self.myview.animate(.squeezeFade(way: .in, direction: .down))
//        .delay(5)
//        .then(.pop(repeatCount: 1))
//        .delay(5)
//        .then(.shake(repeatCount: 1))
//        .delay(5)
//        .then(.squeeze(way: .in, direction: .down))
//        .delay(5)
//        .then(.wobble(repeatCount: 1))
//        .delay(5)
//        .then(.flip(along: .x))
//        .delay(5)
//        .then(.flip(along: .y))
//        .delay(5)
//        .then(.slideFade(way: .out, direction: .down))
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
          self.img.image = image
        }
    }
  }
  
  
}

