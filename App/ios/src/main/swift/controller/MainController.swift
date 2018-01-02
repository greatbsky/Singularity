//
//  MainController.swift
//  Singularity
//
//  Created by Architect bian on 22/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

import UIKit
import core

class MainController : BaseViewController {
  
  @IBOutlet weak var logo: UIImageView!
  
  override func viewDidLoad() {
    let v = UIView(frame:CGRect(100,100,50,50))
    v.backgroundColor = .red // small red square
    self.view.addSubview(v) // add it to main view
    self.logo.image = UIImage(named:"nums")
    delay(2) {
      print(self.logo.image!.scale)
    }
  }
  
  
  override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
    print("here") // put breakpoint here so we can pause and examine layout
  }
  
  @IBAction func onTouchButton1(_ sender: UIButton) {
    
  }
  
  @IBAction func onTouchButton2(_ sender: UIButton) {
    
  }
}
