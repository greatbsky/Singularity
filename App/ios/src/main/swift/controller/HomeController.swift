//
//  MainController.swift
//  Singularity
//
//  Created by Architect bian on 27/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//


import UIKit
import core

class HomeController : BaseViewController, UIToolbarDelegate {
  
  @IBOutlet weak var toolbar: UIToolbar!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    self.toolbar.delegate = self
  }
  
  @IBAction func doItem(_ sender: Any) {
    
  }
  
}
