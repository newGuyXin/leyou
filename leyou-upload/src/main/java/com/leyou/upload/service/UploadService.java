package com.leyou.upload.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    private static final List<String> CONTENT_TYPE = Arrays.asList("image/jpeg", "image/jpg","image/png");

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    /**
     * @类描述: 图标上传
     * @创建人: axin
     * @日期: 2021/11/19
     */
    public String UploadBrandImage(MultipartFile file) {
        //检验文件类型
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        if (!CONTENT_TYPE.contains(contentType)) {
            LOGGER.info("文件类型不合法：" + originalFilename);
            return null;
        }
        try {
            //校验文件类容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                LOGGER.info("文件类容不合法：" + originalFilename);
                return null;
            }
            //上传文件
            file.transferTo(new File("D:\\technology\\" + originalFilename));
            //返回url
            return "http://image.leyou.com/" + originalFilename;
        } catch (IOException e) {
            LOGGER.info("系统异常："+originalFilename);
            e.printStackTrace();
            return null;
        }
    }
}
