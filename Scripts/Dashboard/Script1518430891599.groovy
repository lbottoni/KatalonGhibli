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
import org.openqa.selenium.Keys as Keys

//definisco la matrice di oggetti che devo verificare
//String[] testArray = ["A", "B", "C"]

TestObject[] maps = [
	findTestObject('Object Repository/GhibliDashboard/input_transferStatus'), 
	/*findTestObject('Object Repository/GhibliDashboard/input_sourceQueueManager'),*/
     findTestObject('Object Repository/GhibliDashboard/input_destinationQueueManager'),
	 findTestObject('Object Repository/GhibliDashboard/input_correlationID'),
     findTestObject('Object Repository/GhibliDashboard/btn_workflowdashboard'),
	 findTestObject('Object Repository/GhibliDashboard/btn_workflow'),
     findTestObject('Object Repository/GhibliDashboard/btn_transfersearch'),
	 findTestObject('Object Repository/GhibliDashboard/btn_setting'),
     findTestObject('Object Repository/GhibliDashboard/btn_monitordashboard'), //findTestObject('Object Repository/GhibliDashboard/btn_menu'),
     findTestObject('Object Repository/GhibliDashboard/btn_logout'),
	 findTestObject('Object Repository/GhibliDashboard/btn_clearfilter'),
     findTestObject('Object Repository/GhibliDashboard/btn_applyfilter')]

Number total = 0

Number ok = 0

Number ko = 0

for (def element : maps) {
    String tag = element.findPropertyValue('tag', false)

    String id = element.getObjectId()

    boolean test = false

    assert tag != ''

    println("◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ Element["+total+"] ◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙");
	println(" > id="+id);
	println(" > tag="+tag);
    test = WebUI.waitForElementPresent(element, 1)

    if (test == true) {
        ok++
    } else {
        ko++
    }
    
    assert test == true

    println("◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙");
    println("◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙");
    println("") ;println("") ;
    total++
}

println('END TEST')

println('> TOTALE:' + total)

println('> OK:' + ok)

println('> KO:' + ko)

