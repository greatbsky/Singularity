//
//  StringExt.swift
//  core
//
//  Created by Architect bian on 6/1/2018.
//  Copyright © 2018 Architect bian. All rights reserved.
//
import UIKit
import Foundation
import CommonCrypto

extension String {
    public var urlEscaped: String {
        return addingPercentEncoding(withAllowedCharacters: .urlHostAllowed)!
    }
    
    public var utf8Encoded: Data {
        return data(using: .utf8)!
    }
    
}

// MARK: - 沙盒路径
public extension String {
    /// 沙盒路径之document
    public func document() -> String {
        let documentPath = NSSearchPathForDirectoriesInDomains(FileManager.SearchPathDirectory.documentDirectory, FileManager.SearchPathDomainMask.userDomainMask, true).last!
        return (documentPath as NSString).appendingPathComponent((self as NSString).pathComponents.last!)
    }
    
    /// 沙盒路径之cachePath
    func cache() -> String {
        let cachePath = NSSearchPathForDirectoriesInDomains(FileManager.SearchPathDirectory.cachesDirectory, FileManager.SearchPathDomainMask.userDomainMask, true).last!
        return (cachePath as NSString).appendingPathComponent((self as NSString).pathComponents.last!)
    }
    
    /// 沙盒路径之temp
    func temp() -> String {
        let tempPath = NSTemporaryDirectory()
        return (tempPath as NSString).appendingPathComponent((self as NSString).pathComponents.last!)
    }
}

// MARK: - 和基本数据类型转换等
public extension String {
    func toFloat() -> Float? {
        if let number = NumberFormatter().number(from: self) {
            return number.floatValue
        }
        return nil
    }
    
    func toInt() -> Int? {
        if let number = NumberFormatter().number(from: self) {
            return number.intValue
        }
        return nil
    }
    
    func toDouble(locale: Locale = Locale.current) -> Double? {
        let nf = NumberFormatter()
        nf.locale = locale as Locale!
        if let number = nf.number(from: self) {
            return number.doubleValue
        }
        return nil
    }
    
    func toBool() -> Bool? {
        let trimmed = self.trim().lowercased()
        if trimmed == "true" || trimmed == "false" {
            return (trimmed as NSString).boolValue
        }
        return nil
    }
    
    func toDate(format: String = "yyyy-MM-dd") -> NSDate? {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = format
        return dateFormatter.date(from: self) as NSDate?
    }
    
    func toDateTime(format: String = "yyyy-MM-dd HH:mm:ss") -> NSDate? {
        return toDate(format: format)
    }
}


// MARK: - 通过扩展来简化一下,截取字符串
public extension String {
    var length: Int { return self.count }
    
    subscript (range: Range<Int>) -> String {
        get {
            let startIndex = self.index(self.startIndex, offsetBy: range.lowerBound)
            let endIndex = self.index(self.startIndex, offsetBy: range.upperBound)
            return String(self[Range(startIndex..<endIndex)])
        } set {
            let startIndex = self.index(self.startIndex, offsetBy: range.lowerBound)
            let endIndex = self.index(self.startIndex, offsetBy: range.upperBound)
            let strRange = Range(startIndex..<endIndex)
            self.replaceSubrange(strRange, with: newValue)
        }
    }
    
    func subString(from: Int) -> String {
        let end = self.length
        return self[from..<end]
    }
    func subString(from: Int, length: Int) -> String {
        let end = from + length
        return self[from..<end]
    }
    func subString(from:Int, to:Int) ->String {
        return self[from..<to]
    }
    
    func replace(_ string: String, with withString: String) -> String {
        return replacingOccurrences(of: string, with: withString)
    }
    
    func truncate(_ length: Int, suffix: String = "...") -> String {
        //        return self.length > length
        //            ? substring(to: self.index(startIndex, offsetBy: length)) + suffix
        //            : self
        return self.length > length
            ? prefix(upTo: self.index(startIndex, offsetBy: length)) + suffix
            : self
    }
    
    func split(_ delimiter: String) -> [String] {
        let components = self.components(separatedBy: delimiter)
        return components != [""] ? components : []
    }
    
    func trim() -> String {
        return trimmingCharacters(in: CharacterSet.whitespaces)
    }
}
// MARK: - 判断手机号  隐藏手机中间四位  正则匹配用户身份证号15或18位  正则RegexKitLite框架
extension String {
    
    // 手机号码添加空格
    //    func phoneNumberFormatter(number: String, length: Int) -> String {
    //        var str = number
    //        switch length {
    //        case 3, 8:
    //            str += " "
    //        default:
    //            break
    //        }
    //        return str
    //    }
    
    // 利用正则表达式判断是否是手机号码
    func checkTelNumber() -> Bool {
        let pattern = "^((13[0-9])|(147)|(15[0-3,5-9])|(18[0,0-9])|(17[0-3,5-9]))\\d{8}$"
        let pred = NSPredicate(format: "SELF MATCHES %@", pattern)
        return pred.evaluate(with: self)
    }
    
    // 正则匹配用户身份证号15或18位
    func validateIdentityCard(identityCard: String) -> Bool {
        let pattern = "(^[0-9]{15}$)|([0-9]{17}([0-9]|[0-9a-zA-Z])$)"
        let pred = NSPredicate(format: "SELF MATCHES %@", pattern)
        return pred.evaluate(with: identityCard)
    }
    
    // 隐藏手机敏感信息
    mutating func phoneNumberhideMid() {
        let startIndex = self.index("".startIndex, offsetBy: 4)
        let endIndex = self.index("".startIndex, offsetBy: 7)
        self.replaceSubrange(startIndex...endIndex, with: "****")
    }
    
    // 隐藏敏感信息
    mutating func numberHideMidWithOtherChar(form: Int, to: Int,char: String) {
        // 判断
        var form = form;   var to = to
        if form < 0 || form > self.length{
            form = 0
        }
        if to > self.length {
            to = self.length
        }
        var star = ""
        for _ in form...to {
            star.append(char)
        }
        
        let startIndex = self.index("".startIndex, offsetBy: form)
        let endIndex = self.index("".startIndex, offsetBy: to)
        self.replaceSubrange(startIndex...endIndex, with: star)
    }
}

// MARK: - 汉字转拼音
extension String {
    func transform(chinese: String) -> String{
        
        //将NSString装换成NSMutableString
        let pinyin = NSMutableString(string: chinese) as CFMutableString
        //将汉字转换为拼音(带音标)
        CFStringTransform(pinyin, nil, kCFStringTransformMandarinLatin, false)
        
        //去掉拼音的音标
        CFStringTransform(pinyin, nil, kCFStringTransformStripCombiningMarks, false)
        
        //返回最近结果
        return pinyin as String
    }
}

// MARK: - MD5 加密
extension String {
    var md5: String{
        let str = self.cString(using: String.Encoding.utf8)
        let strLen = CC_LONG(self.lengthOfBytes(using: String.Encoding.utf8))
        let digestLen = Int(CC_MD5_DIGEST_LENGTH)
        let result = UnsafeMutablePointer<CUnsignedChar>.allocate(capacity: digestLen);

        CC_MD5(str!, strLen, result);

        let hash = NSMutableString();
        for i in 0 ..< digestLen {
            hash.appendFormat("%02x", result[i]);
        }
        result.deinitialize(count: Int(strLen))

        return String(format: hash as String)
    }
}

