//
//  ScrollViewManager.m
//  Singularity
//
//  Created by Architect bian on 24/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

#import <React/RCTViewManager.h>
#import "Singularity-Swift.h"

@interface ScrollManager : RCTViewManager
@end

@implementation ScrollManager

RCT_EXPORT_MODULE()

- (UIView *)view
{
  return [[Scroll alloc] init];
}

RCT_EXPORT_VIEW_PROPERTY(bounces, BOOL)
RCT_EXPORT_VIEW_PROPERTY(onClickEvent, RCTBubblingEventBlock)

@end
