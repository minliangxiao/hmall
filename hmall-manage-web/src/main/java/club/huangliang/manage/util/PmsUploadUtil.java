package club.huangliang.manage.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUploadUtil {
    public static String uploadImage(MultipartFile multipartFile) {
        String imgUrl="http://192.168.206.140";
        //配置fdfs的全局链接地址
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();//获得配置文件的路径
        try {
            ClientGlobal.init(tracker);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        TrackerClient trackerClient=new TrackerClient();
        //获取一个trackerServer的实例
        TrackerServer trackerServer= null;
        try {
            trackerServer = trackerClient.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过tracker获取一个Storage链接客户端
        StorageClient storageClient=new StorageClient(trackerServer,null);


        try {
            byte[] bytes=multipartFile.getBytes();//获取上传的二进制对象

            //获取文件后缀名
            String  originalFilename=multipartFile.getOriginalFilename();//获取到文件的原始名字
            int i=originalFilename.lastIndexOf(".");//获取到文件名最后一个点的位置
            String extName=originalFilename.substring(i+1);//截取文件后缀


            String[] uploadInfos = storageClient.upload_file(bytes,extName,null);

            for (String uploadInfo : uploadInfos) {
                imgUrl +=  "/" + uploadInfo;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imgUrl;
    }
}
