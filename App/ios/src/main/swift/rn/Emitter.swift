//
//  Events.swift
//  Singularity
//
//  Created by Architect bian on 24/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

import Foundation

private var instance:Emitter? = nil

@objc(Emitter)
class Emitter: RCTEventEmitter {
  
  class var share: Emitter? {
    return instance
  }

  class var EventResult: String {
      return "EventResult"
  }
  
  @objc(supportedEvents)
  override func supportedEvents() -> [String]! {
    instance = self
    return [Emitter.EventResult]
  }
  
  func emit(eventName:String, params:Any) {
    sendEvent(withName: eventName, body: params)
  }
}
