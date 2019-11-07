package com.liudehuang.fastdfs.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Description:
 * @Author: liudehuang
 * @CreateDate: 2019-11-07
 * @UpdateUser: liudehuang
 * @UpdateDate: 2019-11-07
 * @UpdateRemark:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private FastFileStorageClient storageClient;

    @PostMapping("fileUpload")
    public String fileUpload(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
            FastFile.Builder builder = new FastFile.Builder();
            builder.withFile(file.getInputStream(),file.getSize(),suffix);
            FastFile fastFile = builder.build();
            StorePath storePath = storageClient.uploadFile(fastFile);
            String path = storePath.getFullPath();
            return path;

        }
        return null;
    }
}


