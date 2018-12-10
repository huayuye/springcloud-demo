package com.bingdeng.sprincloud.eureka.producer.utils;

import jcifs.smb.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: Fran
 * @Date: 2018/12/5
 * @Desc:
 **/
public class SMBFlieUtils {

    /**
     *Read the SMB file to local，读取smb文件到本地
     * @param username username smbUsername
     * @param password pwd smbPwd
     * @param smbSourceFileUrl  smbFile path:smb服务器上文件，example: smb://127.0.0.1/share/xxxx.txt
     * @param destFileUrl localFile path:本机文件路径
     * @return
     */
    public static boolean readFile(String username,String password,String smbSourceFileUrl, String destFileUrl){
        try{
            return readFile(username,password,smbSourceFileUrl,new FileOutputStream(destFileUrl));
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     *Read the SMB file to local，读取smb文件到目标文件
     * @param username smbUsername
     * @param password smbPwd
     * @param smbSourceFileUrl mbFile path:smb服务器上文件，example: smb://127.0.0.1/share/xxxx.txt
     * @param outputStream file OutPutStream:目标文件流
     * @return
     */
    public static boolean readFile(String username,String password,String smbSourceFileUrl, OutputStream outputStream){
        BufferedOutputStream out = null;
        SmbFileInputStream in = null;
        try{
            //此方式不支持有特俗字符的密码
            //SmbFile smbFile = new SmbFile("smb://username:pwd@192.xxx.xxx.xxx/share/xxxx.txt");
            //由于密码中有特殊字符，因此需要使用NtlmPasswordAuthentication,先登录验证
            //局域网共享文件，读文件
            SmbFile smbFile = new SmbFile(smbSourceFileUrl,getNtlmPasswordAuthentication(null, username, password));
            // 通过 smbFile.isDirectory();isFile()可以判断smbFile是文件还是文件夹
            // 得到文件的大小
            int length = smbFile.getContentLength();
            int len = -1;
            byte buffer[] = new byte[2048];
            // 建立smb文件输入流
            in = new SmbFileInputStream(smbFile);
            //写出到本地
            out = new BufferedOutputStream(outputStream);
            while ((len=in.read(buffer)) != -1)
            {
                out.write(buffer,0,len);
            }
            return true;
        }catch (SmbAuthException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * Upload local files to SMB server，上传本地文件到smb服务器
     * @param username smbUsername
     * @param password smbPwd
     * @param sourceFileUrl localFile path:需上传的本地文件路径
     * @param smbDestFileUrl smbFile path:需写入的smb 服务器文件路径 example: smb://127.0.0.1/share/xxxx.txt
     * @return
     */
    public static boolean writeFile(String username,String password,String sourceFileUrl, String smbDestFileUrl){
        try {
          return  writeFile(username,password,new FileInputStream(sourceFileUrl),smbDestFileUrl);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Upload net files to SMB server，上传net文件到smb服务器
     * @param username smbUsername
     * @param password smbPwd
     * @param sourceFileUrl netFile path:需上传的网络文件路径
     * @param smbDestFileUrl smbFile path:需写入的smb 服务器文件路径 example: smb://127.0.0.1/share/xxxx.txt
     * @return
     */
    public static boolean writeNetFile(String username,String password,String sourceFileUrl, String smbDestFileUrl){
        try {
            URL url = new URL(sourceFileUrl);
            URLConnection urlConnection = url.openConnection();
            return  writeFile(username,password,urlConnection.getInputStream(),smbDestFileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *Upload local files to SMB server，上传文件到smb服务器
     * @param username smbUsername
     * @param password smbPwd
     * @param inputStream uploadFile InputStream, 需上传的文件流
     * @param smbDestFileUrl smbFile path:需写入的smb 服务器文件路径 example: smb://127.0.0.1/share/xxxx.txt
     * @return
     */
    public static boolean writeFile(String username,String password,InputStream inputStream, String smbDestFileUrl){
        SmbFileOutputStream out = null;
        BufferedInputStream in = null;
        try{

            //此方式不支持有特俗字符的密码
            //SmbFile smbFileOut = new SmbFile("smb://username:pwd@192.xxx.xxx.xxxx/share/xxxx.txt");
            //局域网共享文件，写文件，写入远程服务器的文件，没有则新建
            SmbFile smbFileOut = new SmbFile(smbDestFileUrl,getNtlmPasswordAuthentication(null, username, password));
            // 通过 smbFile.isDirectory();isFile()可以判断smbFile是文件还是文件夹
            if(!smbFileOut.exists()){
                smbFileOut.createNewFile();
            }
            //创建smb文件的输出流
            out = new SmbFileOutputStream(smbFileOut);
            //上传本地文件到smb服务器
            in = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[2048];
            int len = -1;
            while((len=in.read(buffer))!=-1){
                //将内容写入到smb服务上的文件
                out.write(buffer,0,len);
            }
            return true;
        }catch (SmbAuthException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    /**
     * 创建smb登录校验
     * @param smbIP example:127.0.0.1
     * @param username
     * @param password
     * @return
     */
    public static NtlmPasswordAuthentication getNtlmPasswordAuthentication(String smbIP,String username,String password){
        return new NtlmPasswordAuthentication(smbIP, username, password);
    }

}
