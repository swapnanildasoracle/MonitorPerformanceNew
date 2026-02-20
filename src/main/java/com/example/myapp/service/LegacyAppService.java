package com.example.myapp.service;

import net.lightbody.bmp.proxy.CaptureType;
import org.springframework.stereotype.Service;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LegacyAppService {

    public String run(String token, String url)  {
//        // 1. Start the Proxy Server with a specific address
//        BrowserMobProxy proxy = new BrowserMobProxyServer();
//        proxy.setTrustAllServers(true); // Prevents hanging on SSL warnings
//        proxy.start(0);
//
//// 2. Map the proxy to 127.0.0.1 explicitly
//        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
//        String proxyAddr = "127.0.0.1:" + proxy.getPort();
//        seleniumProxy.setHttpProxy(proxyAddr);
//        seleniumProxy.setSslProxy(proxyAddr);
//
//// 3. Add arguments to bypass Chrome's startup checks
//        ChromeOptions options = new ChromeOptions();
//        options.setProxy(seleniumProxy);
//        options.addArguments("--ignore-certificate-errors");
//        options.addArguments("--remote-allow-origins=*"); // Fixes Selenium 4 handshake issues
//        options.addArguments("--no-sandbox");             // Helpful for Linux/Containers
//        options.addArguments("--disable-dev-shm-usage");  // Prevents memory hangs
//
//        WebDriver driver = new ChromeDriver(options);
//        String harAsString;
//        try {
//            // 5. Enable HAR Capture Types
//            proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
//
//            // 6. Start recording
//            proxy.newHar("google-search-test");
//
//            // 7. Navigate to the website
//            driver.get(url);
//
//            // 8. Capture and Save HAR
//            Har har = proxy.getHar();
////            File harFile = new File("network_log.har");
//            StringWriter writer = new StringWriter();
//            try {
//                har.writeTo(writer);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            harAsString = writer.toString();
//        } finally {
//            // 9. Cleanup
//            driver.quit();
//            proxy.stop();
//        }
//
//
//        driver.quit();
//        proxy.stop();
//        return "Result : " + url + ":  " + harAsString;

        return "Result : " + url + ":  " + token;

    }
}