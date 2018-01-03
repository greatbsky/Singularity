//
//  BaseGlobal.swift
//  core
//
//  Created by Architect bian on 3/1/2018.
//  Copyright © 2018 Architect bian. All rights reserved.
//

import Foundation

public func local(_ key: String) -> String {
    return NSLocalizedString(key , comment: key)
}
