//
//  ScrollView.swift
//  Singularity
//
//  Created by Architect bian on 24/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

import UIKit


@objc(Scroll)
class Scroll: UIScrollView {
  
  @objc
  var onClickEvent: RCTBubblingEventBlock?
  
  @objc
  public override init(frame: CGRect) {
    super.init(frame: frame)
    let label = UILabel(frame:CGRect(x:10, y:20, width:300, height:300))
    label.text = "hi world!00000000"
    self.addSubview(label)
    self.isUserInteractionEnabled = true
    self.isScrollEnabled = true
    self.bounces = true
    self.contentSize = self.subviews[0].bounds.size
//    self.contentSize = CGSize(1000, 1000)
    if #available(iOS 11.0, *) {
      self.contentInsetAdjustmentBehavior = .always
    } else {
      // Fallback on earlier versions
    }
//    self.delegate = self as! UIScrollViewDelegate
//    let tapGesture = UITapGestureRecognizer(target: self, action: #selector(tapGestureAction))
//    tapGesture.numberOfTapsRequired = 1
//    self.addGestureRecognizer(tapGesture)
  }

  required init?(coder aDecoder: NSCoder) {
    super.init(coder: aDecoder)
  }
  
  
}
extension Scroll{
  override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
    let location:CGPoint! = touches.first?.location(in: self)//点击时记录点击位置
    print("touchesBegan")
    print(onClickEvent ?? "")
    if self.onClickEvent != nil {
      self.onClickEvent!([
        "x": location.x,
        "y": location.y
        ])
    }
  }
    
//  @objc fileprivate func tapGestureAction(sender:UITapGestureRecognizer){
//    print("onClickEvent")
//    print(onClickEvent ?? "")
//
//  }
}

