package lbottoni

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI


import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions as Actions
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.ConditionType as ConditionType


public class utility {
	public WebDriver driver=null;

	@Keyword
	def test(){
		println "hello world..............";
	}
	/**
	 *  esegue un doppio click crossbrowser
	 *  funziona solo se non si riusa la session
	 *  
	 * @param TestObject Katalon element
	 * @return
	 */
	@Keyword
	def doubleClickWithJavascript(TestObject element) {
		String script = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('dblclick', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('ondblclick');}";

		WebElement webel = WebUiCommonHelper.findWebElement(element,30);

		return WebUI.executeJavaScript(script, Arrays.asList(webel))
	}
	/**
	 * verifica che nel path sia presente il file
	 * 
	 * @param downloadPath
	 * @param fileName
	 * @return
	 */
	@Keyword
	def boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false
		println ">>>>>>>>>>>>>>>>>> INIZIO ISDOWNLOAD <<<<<<<<<<<<<<<<<<<<<<<"
		println "downloadPath="+downloadPath
		println "fileName="+fileName
		File dir = new File(downloadPath)
		println "dir "+dir
		File[] dir_contents = dir.listFiles()
		if(dir_contents!=null){
			println "dir_contents "+dir_contents
			println('Total Files Available in the folder are :  ' + dir_contents.length)

			for (int i = 0; i < dir_contents.length; i++) {
				println('File Name at '+i+' is : ' + dir_contents[i].getName())

				if (dir_contents[i].getName().equals(fileName)) {
					println ">>>>>>>>>>>> file trovato "
					flag = true
				}
			}
		}

		println ">>>>>>>>>>>> flag è "+flag
		return flag
	}
	/**
	 * elimina un file 
	 */
	@Keyword
	def boolean deleteFile(String downloadPath, String fileName){
		//println ">>>>>>>>>>>>>>>>>> INIZIO DELETEFILE <<<<<<<<<<<<<<<<<<<<<<<"
		boolean isFilePresent=false
		boolean out=false;
		isFilePresent=isFileDownloaded(downloadPath, fileName);
		//println ">>>>>>>>>>>> deleteFile = "+isFilePresent;
		if(isFilePresent==false){return false;}
		//println ">>>>>>>>>>>> deleteFile SONO USCITO??? = 1"
		//il file esiste , quindi provvedo a eliminarloù
		File dir = new File(downloadPath)
		//println ">>>>>>>>>>>> deleteFile SONO USCITO??? = 2"
		File[] dir_contents = dir.listFiles()
		//println ">>>>>>>>>>>> deleteFile SONO USCITO??? = "+dir_contents;
		for (int i = 0; i < dir_contents.length; i++) {
			//println('File Name at '+i+' is : ' + dir_contents[i].getName())
			if (dir_contents[i].getName().equals(fileName)) {
				//println('File Name at '+i+' is : ' + dir_contents[i].getName())
				//println "find file in index "+i+" in "+downloadPath+"/"+fileName;
				def del= dir_contents[i].delete();
				//println ">>>>>>>>>>DELETE="+del;
				out=true;
			}
		}
		//println ">>>>>>>>>>>> out è "+out
		return out;

	}

	public WebDriver getDriver(){
		if(this.driver==null){
			WebDriver driver = DriverFactory.getWebDriver();
			// this.driver=driver;
		}

		return driver;
	}
	@Keyword
	public TestObject objectByXpath(String name,String xpath){


		TestObject element = new TestObject(name);
		element.addProperty("xpath", ConditionType.EQUALS, xpath,true);
		return element;
	}
}
