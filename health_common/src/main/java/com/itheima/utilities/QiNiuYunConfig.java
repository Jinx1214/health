package com.itheima.utilities;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class QiNiuYunConfig {
    public static UploadManager uploadManager = null;
    public static Configuration cfg = null;

    static {
        cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        uploadManager = new UploadManager(cfg);
    }

    private static final String accessKey = "bW3diTh5sykNecsTJ6qRn6W-fKsiwhGug8jVsgyE";
    private static final String secretKey = "6QJUkdiEPB8JquV-atG_fRGP7mxh9bN7I3U1nvkk";
    private static final String bucket = "itheima-health-jinx";


    public static String uploadPicture(String fileName,byte[] bytes) {

        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        //TODO 将每日上传的图片进行存储
        try {
            byte[] uploadBytes = bytes;
             auth = Auth.create(accessKey, secretKey);
             upToken = auth.uploadToken(bucket);
             Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
             DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
             System.out.println(putRet.key);
             System.out.println(putRet.hash);
             return putRet.key.toString();
        } catch (Exception ex) {
           ex.printStackTrace();
           throw  new RuntimeException();
        }
    }
    public static void deletePicture(String key){
        Auth auth = Auth.create(accessKey, secretKey);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(bucket, key);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
    }
}

