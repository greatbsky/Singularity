//
//  AppSO.swift
//  Singularity
//
//  Created by Architect bian on 5/1/2018.
//  Copyright © 2018 Facebook. All rights reserved.
//

import Foundation
import Moya

public class AppSO {
  
  static let provider = MoyaProvider<AppAPI>()
  
  static func initial() {
    provider.request(.initial) { result in
      switch result {
      case let .success(response):
        print(String(data: response.data, encoding: .utf8)!)
        print(response.statusCode)
      case .failure(_): break
      }
    }
  }
  
  static func initial(_ version: String) {
    provider.rx.request(AppAPI.initialFor(version: version)).subscribe { event in
      switch event {
      case let .success(response):
        print(String(data: response.data, encoding: .utf8)!)
        print(response.statusCode)
      case let .error(error):
        print(error)
      }
    }
  }
}
