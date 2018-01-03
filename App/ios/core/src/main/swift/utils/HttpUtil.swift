//
//  HttpUtil.swift
//  core
//
//  Created by Architect bian on 3/1/2018.
//  Copyright © 2018 Architect bian. All rights reserved.
//  reference: https://github.com/Alamofire/Alamofire/blob/master/Documentation/Usage.md
//

import Foundation
import Alamofire

open class HttpUtil {
    
    /**
     发送请求
     */
    open static func request(_ url: URLConvertible, method: HTTPMethod = .get, parameters: Parameters? = nil, encoding: ParameterEncoding = URLEncoding.default, headers: HTTPHeaders? = nil) -> DataRequest {
        return Alamofire.request(url, method: method, parameters: parameters, encoding: encoding, headers: headers)
    }
    
    open static func download(_ url: URLConvertible, method: HTTPMethod = .get, parameters: Parameters? = nil, encoding: ParameterEncoding = URLEncoding.default, headers: HTTPHeaders? = nil, to destination: DownloadRequest.DownloadFileDestination? = nil) -> DownloadRequest {
        return Alamofire.download(url, method: method, parameters: parameters, encoding: encoding, headers: headers, to: destination)
    }
    
    open static func upload(_ fileURL: URL, to url: URLConvertible, method: HTTPMethod = .post, headers: HTTPHeaders? = nil) -> UploadRequest {
        return Alamofire.upload(fileURL, to: url, method: method, headers: headers)
    }
    
    open static func upload(multipartFormData: @escaping (MultipartFormData) -> Void, usingThreshold encodingMemoryThreshold: UInt64 = SessionManager.multipartFormDataEncodingMemoryThreshold, to url: URLConvertible, method: HTTPMethod = .post, headers: HTTPHeaders? = nil, encodingCompletion: ((SessionManager.MultipartFormDataEncodingResult) -> Void)?) {
        
        Alamofire.upload(multipartFormData: multipartFormData, to: url, encodingCompletion: encodingCompletion)
    }
}

