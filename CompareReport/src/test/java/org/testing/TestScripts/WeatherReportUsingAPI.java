package org.testing.TestScripts;

import org.testing.TestSteps.HttpMethods;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class WeatherReportUsingAPI {
	
	public int humidityInInt;
	public int tempInDegree;
	public String humidityAPI;
	public String temperatureAPI;
	@Test
	public void weatherReportByAPI() throws IOException
	{
		File restfile = new File("../CompareReport/RestURI_Keys.properties");
		FileInputStream restFis = new FileInputStream(restfile);
		Properties pr = new Properties();
		pr.load(restFis);
		
		HttpMethods httpMeth= new HttpMethods(pr);
		Response weatherReport = httpMeth.getResponse();
		System.out.println(weatherReport.asString());
	 temperatureAPI=weatherReport.jsonPath().getString("main.temp_min");
	System.out.println("fetchng temp "+temperatureAPI);
	int tempAPI= (int)Double.parseDouble(temperatureAPI);
	tempInDegree =   tempAPI- 273; //Converting the Kelvin Temperatue to Degree Celcus
	
	//Fetching Humidity
	 humidityAPI=weatherReport.jsonPath().getString("main.humidity");
	humidityInInt= Integer.parseInt(humidityAPI);
  System.out.println("Temperature after conversion for API to degree Celcius is  "+tempInDegree + "  humidity API is "+humidityInInt);
		
	}
	
}
