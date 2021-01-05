/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package server.model;

import java.io.Serializable;

/**
 * @author zhoujp
 * @version RecvieMessage.java, v 0.1 2020-12-31 12:01 zhoujp
 */
public class RecvieMessage implements Serializable {

    private static final long serialVersionUID = 6200390330718630934L;

    private short msgType;

    private String data;

    public short getMsgType() {
        return msgType;
    }

    public void setMsgType(short msgType) {
        this.msgType = msgType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}