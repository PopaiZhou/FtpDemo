/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package server.model;

import java.io.File;
import java.io.Serializable;

/**
 * @author zhoujp
 * @version RequestFile.java, v 0.1 2020-12-31 11:21 zhoujp
 */
public class RequestFile implements Serializable {
    private static final long serialVersionUID = -1425307876096494974L;

    /**
     * 文件
     */
    private File file;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 开始位置
     */
    private long starPos;
    /**
     * 文件字节数组
     */
    private byte[] bytes;
    /**
     * 结尾位置
     */
    private int endPos;
    /**
     * 文件的MD5值
     */
    private String fileMd5;
    /**
     * 文件类型
     */
    private String fileType;
    /**
     * 文件总长度
     */
    private long fileSize;

    /**
     * Getter method for property <tt>fileName</tt>.
     *
     * @return property value of fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Setter method for property <tt>fileName</tt>.
     *
     * @param fileName value to be assigned to property fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter method for property <tt>starPos</tt>.
     *
     * @return property value of starPos
     */
    public long getStarPos() {
        return starPos;
    }

    /**
     * Setter method for property <tt>starPos</tt>.
     *
     * @param starPos value to be assigned to property starPos
     */
    public void setStarPos(long starPos) {
        this.starPos = starPos;
    }

    /**
     * Getter method for property <tt>bytes</tt>.
     *
     * @return property value of bytes
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Setter method for property <tt>bytes</tt>.
     *
     * @param bytes value to be assigned to property bytes
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * Getter method for property <tt>endPos</tt>.
     *
     * @return property value of endPos
     */
    public int getEndPos() {
        return endPos;
    }

    /**
     * Setter method for property <tt>endPos</tt>.
     *
     * @param endPos value to be assigned to property endPos
     */
    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }

    /**
     * Getter method for property <tt>fileMd5</tt>.
     *
     * @return property value of fileMd5
     */
    public String getFileMd5() {
        return fileMd5;
    }

    /**
     * Setter method for property <tt>fileMd5</tt>.
     *
     * @param fileMd5 value to be assigned to property fileMd5
     */
    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    /**
     * Getter method for property <tt>fileType</tt>.
     *
     * @return property value of fileType
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Setter method for property <tt>fileType</tt>.
     *
     * @param fileType value to be assigned to property fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Getter method for property <tt>fileSize</tt>.
     *
     * @return property value of fileSize
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * Setter method for property <tt>fileSize</tt>.
     *
     * @param fileSize value to be assigned to property fileSize
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Getter method for property <tt>file</tt>.
     *
     * @return property value of file
     */
    public File getFile() {
        return file;
    }

    /**
     * Setter method for property <tt>file</tt>.
     *
     * @param file value to be assigned to property file
     */
    public void setFile(File file) {
        this.file = file;
    }
}