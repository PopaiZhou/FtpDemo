/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */

import client.FtpClientServer;
import server.model.RequestFile;
import util.MD5FileUtil;

import java.io.File;

/**
 * @author zhoujp
 * @version ClientUploadApplication.java, v 0.1 2020-12-31 11:47 zhoujp
 */
public class ClientUploadApplication {
    public static void main(String[] args) {
        int port = 8080;
        try {
            RequestFile echo = new RequestFile();
            String path = System.getProperty("user.dir") + File.separator + "test" + File.separator;
            File file = new File(path + "测试文件.txt");
            String fileName = file.getName();// 文件名
            echo.setFile(file);
            echo.setFileMd5(MD5FileUtil.getFileMD5String(file));
            echo.setFileName(fileName);
            echo.setFileType(getSuffix(fileName));
            echo.setStarPos(0);// 文件开始位置
            new FtpClientServer().connect(port, "127.0.0.1", echo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String getSuffix(String fileName)
    {
        return fileName.substring(fileName.lastIndexOf("."), fileName.length());
    }
}