//
//  VersionUtil.swift
//  core
//
//  Created by Architect bian on 22/12/2017.
//  Copyright © 2017 Architect bian. All rights reserved.
//

open class VersionUtil {
    
    /**
     判断是否是新版本
     */
    open static func isNewVersion() -> Bool {
        
        // 获取当前的版本号
        let currentVersion = Bundle.main.infoDictionary!["CFBundleShortVersionString"] as? String
        
        // 获取到之前的版本号
        let sandboxVersionKey = "sandboxVersionKey"
        let sandboxVersion = UserDefaults.standard.string(forKey: sandboxVersionKey)
        
        // 保存当前版本号
        UserDefaults.standard.set(currentVersion, forKey: sandboxVersionKey)
        UserDefaults.standard.synchronize()
        
        // 当前版本和沙盒版本不一致就是新版本
        return currentVersion != sandboxVersion
    }
}
