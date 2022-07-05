package com.revature.quizzard;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

import javax.servlet.ServletException;

public class App {
    public static void main(String[] args) throws ServletException {

        final String contextPath = "";
        final String docBase = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
        Tomcat server = new Tomcat();
        server.setBaseDir(docBase);
        server.setPort(5000);
        server.addWebapp(contextPath, new File("src/main/webapp").getAbsolutePath());

        try {
            server.start();
            server.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }

}
