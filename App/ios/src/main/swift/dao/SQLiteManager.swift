//
//  SQLiteManager.swift
//  Singularity
//
//  Created by Architect bian on 6/1/2018.
//  Copyright © 2018 Facebook. All rights reserved.
//

import Foundation
import FMDB

class SQLiteManager: NSObject {
  
  //  单例
  static let shareInstance: SQLiteManager = SQLiteManager()
  var queue: FMDatabaseQueue?
  
  override init() {
    super.init()
    openDB(name: DBConf.dbName)
  }
  
  @discardableResult
  func execSQL(_ sqlString : String) -> Bool {
    return execSQL(sqlString, nil)
  }
  
  @discardableResult
  func execSQL(_ sqlString : String, _ values : Array<Any>?) -> Bool {
    queue?.inDatabase({ (db) -> Void in
      try? db.executeUpdate(sqlString, values: values)
    })
    return true
  }
  
  func query(_ sqlString : String) -> FMResultSet? {
    return query(sqlString, nil)
  }
  
  func query(_ sqlString : String, _ values: Array<Any>?) -> FMResultSet? {
    var result: FMResultSet?
    queue?.inDatabase({(db) -> Void in
      result = try? db.executeQuery(sqlString, values: values)
      })
    return result
  }
  
  /// 创建表
  private func creatTable(){
    // 1.编写SQL语句
    let videoTable = "CREATE TABLE IF NOT EXISTS video (" +
      "uid TEXT PRIMARY KEY, " +
      "title TEXT, " +
      "url TEXT, " +
      "creatTime TEXT NOT NULL DEFAULT (datetime('now','localtime'))" +
    ");"
    execSQL(videoTable)
  }
  
  // 打开数据库/数据库的名称
  func openDB(name: String) {
    let path = name.cache()
    queue = FMDatabaseQueue(path: path)
    // 3.创建表
    creatTable()
  }
  
}

// MARK:- 事务相关的操作
extension SQLiteManager {
  
  func beginTransaction() {
    // 1.开启事务的语句
    let beginTransaction = "BEGIN TRANSACTION"
    
    // 2.执行
    execSQL(beginTransaction)
  }
  
  func commitTransaction() {
    // 1.提交事务的语句
    let commitTransaction = "COMMIT TRANSACTION"
    
    // 2.执行
    execSQL(commitTransaction)
  }
  
  func rollbackTransaction() {
    // 1.回滚事务的语句
    let rollbackTransaction = "ROLLBACK TRANSACTION"
    
    // 2.执行
    execSQL(rollbackTransaction)
  }
  
}


