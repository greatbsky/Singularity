//
//  NSStringExt.swift
//  core
//
//  Created by Architect bian on 22/12/2017.
//  Copyright © 2017 Architect bian. All rights reserved.
//
extension String {
    public var urlEscaped: String {
        return addingPercentEncoding(withAllowedCharacters: .urlHostAllowed)!
    }
    
    public var utf8Encoded: Data {
        return data(using: .utf8)!
    }
    
}

extension NSString {
    /// 计算文本的高度
    open func getTextHeight(width: CGFloat) -> CGFloat {
        let size = CGSize(width: width, height: CGFloat(MAXFLOAT))
        return (self.boundingRect(with: size, options: .usesLineFragmentOrigin, attributes: [.font: UIFont.systemFont(ofSize: 16)], context: nil).size.height)
    }
}

