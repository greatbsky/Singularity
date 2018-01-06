//
//  VideoDao.swift
//  Singularity
//
//  Created by Architect bian on 6/1/2018.
//  Copyright © 2018 Facebook. All rights reserved.
//

import Foundation

extension Video {
  
  func insert() -> Bool {
    // 1.拼接插入语句
    let sql = "INSERT INTO video (uid, title, url) values('\(uid)','\(title)', '\(url)');"
    // 2.执行sql语句
    return G.db.execSQL(sql)
  }
  
  func getOne() {
    // 1.拼接插入语句
    let sql = "select uid, title, url from video where uid = '\(uid)';"
    if let rs = G.db.query(sql) {
      while rs.next() {
        self.uid = rs.string(forColumn: "uid")!
        self.title = rs.string(forColumn: "title")!
        self.url = rs.string(forColumn: "url")!
      }
    }
  }
}
