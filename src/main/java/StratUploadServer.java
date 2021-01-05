/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */

import server.ftp.FtpUploadServer;

/**
 * @author zhoujp
 * @version StratUploadServer.java, v 0.1 2020-12-31 11:33 zhoujp
 */
public class StratUploadServer {
    public static void main(String[] args) {
        try {
            new FtpUploadServer(8080).startup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}