//
//  CalendarManagerBridge.m
//  Singularity
//
//  Created by Architect bian on 24/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

// CalendarManagerBridge.m
#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(CalendarManager, NSObject)

RCT_EXTERN_METHOD(addEvent:(NSString *)name location:(NSString *)location date:(nonnull NSNumber *)date)

//RCT_EXTERN_METHOD(timeZone:(nonnull NSNumber *)date callback:(RCTResponseSenderBlock)callback)
//RCT_EXTERN_METHOD(timeZone:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(timeZone)

- (dispatch_queue_t)methodQueue
{
  return dispatch_get_main_queue();
//  return dispatch_queue_create("AsyncQueue", DISPATCH_QUEUE_SERIAL);
}

@end

