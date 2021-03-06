package com.fmall.service.impl;

import com.fmall.service.IFileService;
import com.fmall.util.FTPUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * Created by 冯晓 on 2017/6/27.
 */
@Service("iFileServiceImpl")
@Slf4j
public class IFileServiceImpl implements IFileService {

    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        //获取扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        //新文件名
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        //打印日志
        log.info("开始上传文件，上传的文件名是{},上传的路径是{},新文件名是{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //将targetFile上传到FTP服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //上传完成后，删除upload下面的文件
            targetFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常", e);
            return null;
        }

        return targetFile.getName();

    }
}
