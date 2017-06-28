package com.fmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 冯晓 on 2017/6/27.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
