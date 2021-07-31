package org.testing.TestScripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testing.Base.UI_UrlHit;
import org.testing.TestSteps.HttpMethods;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ComparingReportsAPIVsUI extends UI_UrlHit{

	@Test(enabled = false)
	public void compareValues() throws IOException {
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
		 String getHumidityValue=UIhumidityValue.getText();
		getHumidityValue=getHumidityValue.replaceAll("%", "");
		int getHumidityInIntUI = Integer.parseInt(getHumidityValue);
		
		WebElement UItempValue = driver.findElementByXPath(tempUI);
		String getTempValue=UItempValue.getText();	
		getTempValue=getTempValue.replaceAll("°C", "");
		int getTempInIntUI = Integer.parseInt(getTempValue);
		//System.out.println( "Value of UI  Humididy  "+getHumidityInInt);
		//System.out.println("Value of UI Tempreture  "+getTempInInt);
		
		
		//-------------------------------------------------------------------------------------------
		File restfile = new File("../CompareReport/RestURI_Keys.properties");
		FileInputStream restFis = new FileInputStream(restfile);
		Properties pr = new Properties();
		pr.load(restFis);
		
		HttpMethods httpMeth= new HttpMethods(pr);
		Response weatherReport = httpMeth.getResponse();
		//System.out.println(weatherReport.asString());
	String temperatureAPI=weatherReport.jsonPath().getString("main.temp_min");
	//System.out.println("fetchng temp "+temperatureAPI);
	int tempAPI= (int)Double.parseDouble(temperatureAPI);
	int tempInDegreeAPI =   tempAPI- 273; //Converting the Kelvin Temperatue to Degree Celcus
	
	//Fetching Humidity
	String humidityAPI=weatherReport.jsonPath().getString("main.humidity");
	int humidityInIntAPI= Integer.parseInt(humidityAPI);
  //System.out.println("Temperature after conversion for API to degree Celcius is  "+tempInDegree + "  humidity API is "+humidityInInt);
		
		int varianceInTemperature = tempInDegreeAPI-getTempInIntUI;
		int varianceInHumidity=getHumidityInIntUI-humidityInIntAPI;
		
		if(varianceInTemperature <=2 && varianceInHumidity <=10)
			{
			 System.out.println("Test Case has PASSED");
			}
		else {
					System.out.println("Comparision has FAILED to fullfil the required Criteria");
				}
		}
		
	}

