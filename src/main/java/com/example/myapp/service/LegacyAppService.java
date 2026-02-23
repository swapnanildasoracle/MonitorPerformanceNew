package com.example.myapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.remote.CapabilityType;
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

    public String run(String url, String token)  {
// 1. Start the BrowserMob Proxy
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start(0);
        String harAsString;
        // 2. Get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

        // 3. Configure Chrome to use the proxy
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        options.addArguments("--disable-gpu"); // Often necessary for headless environments
        options.addArguments("--window-size=1920,1080"); // Ensures page elements load properly
        options.addArguments("--no-sandbox"); // Recommended for server/container environments

        options.setCapability(CapabilityType.PROXY, seleniumProxy);
        options.addArguments("--ignore-certificate-errors");


        WebDriver driver = new ChromeDriver(options);
        // Inject the token as a Bearer header
        proxy.addHeader("Authorization", "Bearer " + token);

        try {
            // 4. Enable HAR capture types (headers, cookies, content, etc.)
            proxy.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());

            // 5. Create a new HAR log named "WebsiteTraffic"
            proxy.newHar("WebsiteTraffic");

            // 6. Navigate to the website
            driver.get(url);

            // 7. Retrieve the captured HAR data
            Har har = proxy.getHar();

            // 8. Save the HAR data to a file
            File harFile = new File("network_log.har");
//            har.writeTo(harFile);

            System.out.println("HAR file saved to: " + harFile.getAbsolutePath());
            // Assuming 'har' is your Har object
            StringWriter writer = new StringWriter();
            try {
                har.writeTo(writer); // Writes the HAR JSON to the writer
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            harAsString = writer.toString();

        } finally {
            driver.quit();
            proxy.stop();
        }

//        return "Result : " + url + ":  " + harAsString;

        return "Result : " + url + ":  " + harAsString;

    }
}