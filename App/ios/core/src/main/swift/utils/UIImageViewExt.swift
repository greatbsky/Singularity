//
//  UIImageViewExt.swift
//  core
//
//  Created by Architect bian on 23/12/2017.
//  Copyright © 2017 Architect bian. All rights reserved.
//

import UIKit

extension UIImageView {
    /// 设置圆形图片
    func circleImage() {
        // 建立上下文
        UIGraphicsBeginImageContextWithOptions(self.frame.size, false, 0)
        // 获取当前上下文
        let ctx = UIGraphicsGetCurrentContext()
        // 添加一个圆，并裁剪
        ctx?.addEllipse(in: self.bounds)
        ctx?.clip()
        // 绘制图像
        self.draw(self.bounds)
        // 获取绘制的图像
        let image = UIGraphicsGetImageFromCurrentImageContext()
        // 关闭上下文
        UIGraphicsEndImageContext()
        // 异步执行，防止阻塞主线程
        DispatchQueue.global().async {
            self.image = image
        }
    }
}

