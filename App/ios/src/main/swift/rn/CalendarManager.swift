//
//  CalendarManager.swift
//  Singularity
//
//  Created by Architect bian on 24/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//
import Foundation

// CalendarManager.swift

@objc(CalendarManager)
class CalendarManager: NSObject {
  
  @objc(constantsToExport)
  func constantsToExport() -> [AnyHashable: Any]! {
    return ["someKey": "someValue"]
  }
  
  @objc(timeZone)
  func timeZone() {
    DispatchQueue.main.asyncAfter(deadline: DispatchTime.now() + .seconds(6)) {
      let timeZone = NSCalendar.current.timeZone
      print("timeZone.description:")
      print(timeZone.description)
      Emitter.share?.emit(eventName: Emitter.EventResult, params: timeZone.description)
    }
  }
  
  @objc(timeZone:reject:)
  func timeZone(resolve:@escaping RCTPromiseResolveBlock, reject:RCTPromiseRejectBlock) -> Void {
//    Thread.sleep(forTimeInterval: 6)
    let delay = DispatchTime.now() + .seconds(6)
    DispatchQueue.main.asyncAfter(deadline: delay) {
      let timeZone = NSCalendar.current.timeZone
      print("timeZone.description:")
      print(timeZone.description)
      resolve(timeZone.description);
    }
  }
  
  @objc(timeZone:callback:)
  func timeZone(date: NSNumber, callback:RCTResponseSenderBlock) -> Void {
    
    print("date:" + date.stringValue)
    let date = RCTConvert.nsDate(date)
    print(date?.description ?? "")
    let calendar:Calendar = NSCalendar.current
    let timeZone = calendar.timeZone
    print("timeZone:")
    print(timeZone.description)
    callback([timeZone.description]);
  }
  
  @objc(addEvent:location:date:)
  func addEvent(name: String, location: String, date: NSNumber) -> Void {
    let calendar:Calendar = NSCalendar.current
    let calendarIdentifier = calendar.identifier
    print("calendarIdentifier:")
    print(calendarIdentifier)
    // 获取地区信息
    let localeIdentifier1 = calendar.locale?.identifier
    print("localeIdentifier1:")
    print(localeIdentifier1)
    // 获取时区信息
    let timeZone = calendar.timeZone
    print("timeZone:")
    print(timeZone)
    // 获取每周的第一天从星期几开始
    let firstWeekday = calendar.firstWeekday
    print("firstWeekday:")
    print(firstWeekday)
    // 获取第一周必须包含的最少天数
    let minimumDaysInFirstWeek = calendar.minimumDaysInFirstWeek
    print("minimumDaysInFirstWeek:")
    print(minimumDaysInFirstWeek)
    
    print("name:" + name)
    print("location:" + location)
    print("date:" + date.stringValue)
  }
  
  
}
