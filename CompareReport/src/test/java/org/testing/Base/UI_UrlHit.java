package org.testing.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class UI_UrlHit {
	public ChromeDriver driver;
	public Properties pr;
	public ChromeOptions options;
	public String searchBox;
	public String moreDet;
	public String iframeAd;
	public String crossAd;
	public String tempUI;
	public String humidityVal;
	@BeforeMethod
	public void browserLaunchUrlHit() throws IOException
	{
		System.setProperty("webdriver.chrome.driver", "../CompareReport/chromedriver.exe");
	
		ChromeOptions options =new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();  //maximize the current url window
		driver.get("https://www.accuweather.com/");
		
		File file  = new File("../CompareReport/Elements.properties");
		FileInputStream fi = new FileInputStream(file);
		Properties pr  = new Properties();
		pr.load(fi);
		searchBox= pr.getProperty("textBox");
		moreDet= pr.getProperty("moreDetails");
		iframeAd=pr.getProperty("iframeElement");
		crossAd=pr.getProperty("closeAd");
		tempUI=pr.getProperty("tempUI");
		humidityVal=pr.getProperty("humidity");
	}
	
	@AfterMethod
	public void closeBrowser() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.close();  
	}
	
}
