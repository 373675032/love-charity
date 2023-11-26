package com.charity.utils;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 图片上传工具
 * <p>
 * ==========================================================================
 * 郑重说明：本项目免费开源！原创作者为：薛伟同学，严禁私自出售。
 * ==========================================================================
 * B站账号：薛伟同学
 * 微信公众号：薛伟同学
 * 作者博客：http://xuewei.world
 * ==========================================================================
 * 陆陆续续总会收到粉丝的提醒，总会有些人为了赚取利益倒卖我的开源项目。
 * 不乏有粉丝朋友出现钱付过去，那边只把代码发给他就跑路的，最后还是根据线索找到我。。
 * 希望各位朋友擦亮慧眼，谨防上当受骗！
 * ==========================================================================
 *
 * @author <a href="http://xuewei.world/about">XUEW</a>
 */
public class OssUtils {

    private static String FILE_URL;
    private static final String BUCKET_NAME = "su-share";
    private static final String END_POINT = "oss-cn-beijing.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "";
    private static final String ACCESS_KEY_SECRET = "";

    /**
     * 上传文件
     */
    public static String upload(MultipartFile file, String path) throws IOException {
        if (file == null || path == null) {
            return null;
        }
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        if (!ossClient.doesBucketExist(BUCKET_NAME)) {
            ossClient.createBucket(BUCKET_NAME);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        String extension = getFileExtension(file);
        //设置文件路径
        String fileUrl = path + "/" + IdUtil.simpleUUID() + extension;
        FILE_URL = "https://" + BUCKET_NAME + "." + END_POINT + "/" + fileUrl;
        PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, fileUrl, file.getInputStream()));
        //上传文件
        ossClient.setBucketAcl(BUCKET_NAME, CannedAccessControlList.PublicRead);
        return FILE_URL;
    }

    /**
     * 获取文件的扩展名
     */
    public static String getFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * 获得随机的头像
     */
    public static String getRandomImg() {
        String[] imgs = {
                "https://su-share.oss-cn-beijing.aliyuncs.com/1.jpg",
                "https://su-share.oss-cn-beijing.aliyuncs.com/2.jpg",
                "https://su-share.oss-cn-beijing.aliyuncs.com/3.jpg",
                "https://su-share.oss-cn-beijing.aliyuncs.com/4.jpg",
                "https://su-share.oss-cn-beijing.aliyuncs.com/5.jpg",
        };
        return imgs[(int) (Math.random() * 5)];
    }

    /**
     * 获得随机的文章封面
     */
    public static String getRandomFace() {
        String[] imgs = {
                "https://su-share.oss-cn-beijing.aliyuncs.com/1.jpg",
                "https://su-share.oss-cn-beijing.aliyuncs.com/2.jpg",
                "https://su-share.oss-cn-beijing.aliyuncs.com/3.jpg",
                "https://su-share.oss-cn-beijing.aliyuncs.com/4.jpg",
                "https://su-share.oss-cn-beijing.aliyuncs.com/5.jpg",

        };
        return imgs[(int) (Math.random() * 5)];
    }


    public static boolean checkFileSize(Long len, int size, String unit) {
        double fileSize = 0;
        if ("B".equalsIgnoreCase(unit)) {
            fileSize = (double) len;
        } else if ("K".equalsIgnoreCase(unit)) {
            fileSize = (double) len / 1024;
        } else if ("M".equalsIgnoreCase(unit)) {
            fileSize = (double) len / 1048576;
        } else if ("G".equalsIgnoreCase(unit)) {
            fileSize = (double) len / 1073741824;
        }
        return !(fileSize > size);
    }
}
