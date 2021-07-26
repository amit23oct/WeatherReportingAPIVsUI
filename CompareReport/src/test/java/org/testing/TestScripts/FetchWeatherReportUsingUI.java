package org.testing.TestScripts;

import java.util.Set;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testing.Base.UI_UrlHit;
import org.testng.annotations.Test;

public class FetchWeatherReportUsingUI extends UI_UrlHit{
	
	public int getHumidityInInt=10;
	public int getTempInInt=20;
	public String getHumidityValue;
	public String getTempValue;
	@Test
	public  void retriveReport() 
	{
		WebElement weatherReportPlace = driver.findElementByXPath(searchBox);
		weatherReportPlace.click();
		weatherReportPlace.sendKeys("Bangalore");
		weatherReportPlace.sendKeys(Keys.ENTER);
			
		WebElement linkMoreDetails = driver.findElementByXPath(moreDet);
		linkMoreDetails.click();
		WebElement iFrame = driver.findElementByXPath(iframeAd);
		driver.switchTo().frame(iFrame);
		WebElement closeAd = driver.findElementByXPath(crossAd);
		closeAd.click();
		driver.switchTo().parentFrame();
		WebElement UIhumidityValue = driver.findElementByXPath(humidityVal);
		 getHumidityValue=UIhumidityValue.getText();
		getHumidityValue=getHumidityValue.replaceAll("%", "");
		getHumidityInInt = Integer.parseInt(getHumidityValue);
		
		WebElement UItempValue = driver.findElementByXPath(tempUI);
		 getTempValue=UItempValue.getText();	
		getTempValue=getTempValue.replaceAll("°C", "");
		getTempInInt = Integer.parseInt(getTempValue);
		System.out.println( "Value of UI  Humididy  "+getHumidityInInt);
		System.out.println("Value of UI Tempreture  "+getTempInInt);
		
		}


}
