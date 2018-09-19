import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




//WebUI.click("//div[@id='boundlist-1328-listEl']/ul/li[3]")
//WebUI.click("css=#svgbutton-1064-btnIconEl > #Capa_1 > g > path")
//WebUI.click("id=ext-gen1176")
//WebUI.click("//div[@id='boundlist-1328-listEl']/ul/li[6]")

TestObject select_status = findTestObject('Object Repository/GhibliDashboard/transferStatusCLICK')

//definisco gli options
def optionMap = [
	('SUBMITTED') : 'li_SUBMITTED',
	 ('SUSPENDED') : 'li_SUSPENDED',
	  ('RESUMED') : 'li_RESUMED',
	   ('STARTED') : 'li_STARTED',
	    ('STARTED_RETRY') : 'li_STARTED_RETRY',
		 ('FAILED_RETRY') : 'li_FAILED_RETRY']




optionMap.each { optionIndex, optionsValue ->
	WebUI.click(select_status);
	
	TestObject option=findTestObject('Object Repository/GhibliDashboard/transferStatusOptions/'+optionMap[optionIndex]);
	WebUI.waitForElementPresent(option, 1,FailureHandling.STOP_ON_FAILURE);
	
	//seleziono la voce
	WebUI.click(option);
	//WebUI.delay(1)
	
}
//seleziono solo STARTED
WebUI.click(select_status);
WebUI.click(findTestObject('Object Repository/GhibliDashboard/transferStatusOptions/li_STARTED'));

TestObject btn_clear=findTestObject('Object Repository/GhibliDashboard/btn_clearfilter');
TestObject btn_apply=findTestObject('Object Repository/GhibliDashboard/btn_applyfilter');
TestObject input_transferStatus=findTestObject('Object Repository/GhibliDashboard/input_transferStatus');

WebUI.click(btn_clear);



