package club.huangliang.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class HmallManageWebApplicationTests {

   @Test
    public void contextLoads() throws IOException, MyException {
        //配置fdfs的全局链接地址
        String tracker = HmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();//获得配置文件的路径
        ClientGlobal.init(tracker);
        TrackerClient trackerClient=new TrackerClient();
        //获取一个trackerServer的实例
        TrackerServer trackerServer=trackerClient.getConnection();
        //通过tracker获取一个Storage链接客户端
        StorageClient storageClient=new StorageClient(trackerServer,null);

        String[] uploadInfos=storageClient.upload_file("C:/Users/Lenovo/Desktop/桌面文件/100.jpg","jpg",null);

        String url="http://192.168.206.140";

        for (String uploadInfo : uploadInfos) {
           url = url + "/" + uploadInfo;

        }
       System.out.println(url);



    }

}
