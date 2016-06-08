/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 *
 * @author Shadow
 */
public class FTPUtil {

    private static FTPClient ftpClient;

    public static void connect() {

    }

    public static void makeDirectory(File dir) {
        if (!dir.isDirectory()) {
            return;
        }
        //log.debug("Mkdir" + dir.getPath());
        try {
            String dirName = "/mydata/MA/" + dir.getPath();
            dirName = dirName.replaceAll("\\\\", "/");
            //log.debug("Upload to dir : " + dirName);
            if (!ftpClient.changeWorkingDirectory(dirName)) {
                System.out.println("Make dir on ftp : " + dirName);
                ftpClient.makeDirectory(dirName);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void uploadFile(File file) {
        if (!file.isFile()) {
            return;
        }
        try {
            String fileName = "/mydata/MA/" + file.getPath();
            fileName = fileName.replaceAll("\\\\", "/");
            FTPFile[] serverFiles = ftpClient.listFiles(fileName);
            for (FTPFile serverFile : serverFiles) {
                if (serverFile.getSize() == file.length()
                        && Math.abs(file.lastModified() - serverFile.getTimestamp().getTimeInMillis()) > 4000) {
                    System.out.println("File existed : " + fileName);
                    UIUtil.setDialogText(file.getName());
                    return;
                }
            }
            System.out.println("Upload file : " + fileName);
            ftpClient.setBufferSize(1024);
            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                ftpClient.storeFile(fileName, bis);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void updateLocalFiles(String currentPath) {
        FileOutputStream fos = null;
        try {
            String remoteFileName = "/mydata/MA/";
            FTPFile[] serverFiles = ftpClient.listFiles(remoteFileName + currentPath);
            for (FTPFile serverFile : serverFiles) {
                if (serverFile.isFile()) {
                    String localFileName = currentPath + serverFile.getName();
                    File f = new File(localFileName);
                    if (f.exists() && f.length() == serverFile.getSize()
                            && Math.abs(f.lastModified() - serverFile.getTimestamp().getTimeInMillis()) < 4000) {
                        System.out.println("Local file existed: " + serverFile.getName());
                        continue;
                    }
                    if (f.exists()) {
                        System.out.println(Math.abs(f.lastModified() - serverFile.getTimestamp().getTimeInMillis()));
                    }
                    
                    UIUtil.setDialogText("正在下载：" + serverFile.getName());
                    ftpClient.setBufferSize(1024);
                    String localDirectory = currentPath;
                    if (localDirectory.isEmpty()) {
                        localDirectory = ".";
                    }
                    File dir = new File(localDirectory);
                    if (!dir.exists() || !dir.isDirectory()) {
                        dir.delete();
                        dir.mkdirs();
                    }
                    fos = new FileOutputStream(localFileName);
                    System.out.println("downloading file : " + serverFile.getName());
                    ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                    boolean returnval = ftpClient.retrieveFile(remoteFileName + currentPath + serverFile.getName(), fos);
                    fos.close();
                    f.setLastModified(serverFile.getTimestamp().getTimeInMillis());
                    System.out.println("downloading successful: " + returnval);
                } else if (serverFile.isDirectory()) {
                    updateLocalFiles(currentPath + serverFile.getName() + "/");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                // Set the file change time to DB's
                //File f = new File(bean.getPath());
                //f.setLastModified(bean.lastModified());
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public static void disconnect() {
        try {
            ftpClient.disconnect();
            System.out.println("Disconnected from ftp server.");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        connect();

        try {
            //log.debug(ftpClient.listHelp());
            String fileName = "/mydata/auto_update";
            fileName = fileName.replaceAll("\\\\", "/");
//            if (!ftpClient.changeWorkingDirectory(dirName)) {
//                log.warn("WARN: dir not exist on ftp, create : " + dirName);
//                ftpClient.makeDirectory(dirName);
//                ftpClient.changeWorkingDirectory(dirName);
//            }
            //log.debug("Upload file : " + fileName);
            //ftpClient.enterLocalPassiveMode();
            //ftpClient.changeWorkingDirectory(fileName);
            FTPFile[] serverFiles = ftpClient.listFiles(fileName);
            System.out.println(serverFiles.length);
            for (FTPFile serverFile : serverFiles) {
                System.out.println(serverFile.getName());
                System.out.println(serverFile.getTimestamp().getTimeInMillis());
            }
            File f = new File("build");
            System.out.println(f.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        disconnect();

    }

    public static boolean checkLocalFiles(String currentPath) {
        try {
            String remoteFileName = "/mydata/MA/";
            FTPFile[] serverFiles = ftpClient.listFiles(remoteFileName + currentPath);
            for (FTPFile serverFile : serverFiles) {
                if (serverFile.isFile()) {
                    String localFileName = currentPath + serverFile.getName();
                    File f = new File(localFileName);
                    if (f.exists() && f.length() != serverFile.getSize()) {
                        System.out.println("Local file existed: " + f.getName());
                        System.out.println("Local size: " + f.length());
                        System.out.println("Remote size: " + serverFile.getSize());
                        System.out.println("Local time: " + f.lastModified());
                        System.out.println("Remote time: " + serverFile.getTimestamp().getTimeInMillis());
                        Thread.sleep(1000L);
                        return true;
                    }
                } else if (serverFile.isDirectory()) {
                    checkLocalFiles(currentPath + serverFile.getName() + "/");
                }
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex);
        }
        return false;
    }
}
