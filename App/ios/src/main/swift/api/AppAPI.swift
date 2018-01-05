//
//  AppAPI.swift
//  Singularity
//
//  Created by Architect bian on 5/1/2018.
//  Copyright © 2018 Facebook. All rights reserved.
//

import Foundation
import Moya

enum AppAPI {
  case initial
  case initialFor(version: String)
}

extension AppAPI: TargetType {
  
  var baseURL: URL { return URL(string: APIConf.hostDefault)! }
  
  var path: String {
    switch self {
      case .initial:
        return "api/initialize"
      case .initialFor(let version):
        return "api/\(version)/app/initialdata"
    }
  }
  
  var method: Moya.Method {
    switch self {
      case .initial, .initialFor:
        return .get
//      case .createUser, .updateUser:
//        return .post
    }
  }
  
  var task: Task {
    switch self {
      case .initial, .initialFor: // Send no parameters
        return .requestPlain
//      case let .updateUser(_, firstName, lastName):
//        return .requestParameters(parameters: ["first_name": firstName, "last_name": lastName], encoding: URLEncoding.queryString)
//      case let .createUser(firstName, lastName):
//        return .requestParameters(parameters: ["first_name": firstName, "last_name": lastName], encoding: JSONEncoding.default)
    }
  }
  
  var headers: [String: String]? {
    return ["Content-type": "application/json"]
  }
  
  var sampleData: Data {
    return "".utf8Encoded
  }
  
}
