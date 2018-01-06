//
//  Video.swift
//  Singularity
//
//  Created by Architect bian on 22/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

import Foundation

class Video: NSObject {
  
  var uid : String = ""
  var title : String = ""
  var url : String = ""
  
  override init() {
  }
  
  init(uid: String, title: String, url: String) {
    super.init()
    self.uid = uid
    self.title = title
    self.url = url
  }
  
  init(dict : [String : NSObject]) {
    super.init()
    setValuesForKeys(dict)
  }
  
}
