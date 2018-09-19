import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
//add
import com.kms.katalon.core.testobject.ConditionType as ConditionType

//label 2 cifre
//span[contains(@class,"kt_lbl_short_counter_SB")]
//span[contains(@class,"kt_lbl_long_counter_SB")]


["SB","ST","SR","SU","RS","FR"].each{
	//per ogni stato creo un Test object con il suo corrispettivo xpath
	
	println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙ ${it} ◙◙◙◙◙◙◙◙◙◙◙◙◙◙";
	//################### SHORT ###############################
	String xp_short="//span[contains(@class,'kt_lbl_short_counter_${it}')]";
	
	WebUI.comment("controllo SHORT counter label")
	TestObject countershort = new TestObject("countershort${it}");
	countershort.addProperty("xpath", ConditionType.EQUALS, xp_short,true)	
	println "countershort="+countershort;
	
	//per ognuno controllo che esista la label e sia visibile
	WebUI.verifyElementPresent(countershort, GlobalVariable.waitShort, FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyElementVisible(countershort, FailureHandling.STOP_ON_FAILURE)
	
	//################### LONG ###############################
	WebUI.comment("controllo LONG counter label")
	String xp_long="//span[contains(@class,'kt_lbl_long_counter_${it}')]";

	TestObject counterlong = new TestObject("countershort${it}");
	counterlong.addProperty("xpath", ConditionType.EQUALS, xp_long,true)
	println "countershort="+counterlong;
	
	//per ognuno controllo che esista la label e sia visibile
	WebUI.verifyElementPresent(counterlong, GlobalVariable.waitShort, FailureHandling.STOP_ON_FAILURE)
	WebUI.verifyElementVisible(counterlong, FailureHandling.STOP_ON_FAILURE)
	
	//controllo che sia presnete il testo
	String txt= WebUI.getText(counterlong)
	assert txt!="";
	println "txt="+txt;
}

