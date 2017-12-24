//
//  RCTViewManagerBridge.m
//  Singularity
//
//  Created by Architect bian on 24/12/2017.
//  Copyright © 2017 Facebook. All rights reserved.
//

#import <React/RCTViewManager.h>
#import <MapKit/MapKit.h>

@interface MapViewManager : RCTViewManager
@end

@implementation MapViewManager

RCT_EXPORT_MODULE()

- (UIView *)view
{
  return [[MKMapView alloc] init];
}

RCT_EXPORT_VIEW_PROPERTY(zoomEnabled, BOOL)

@end
