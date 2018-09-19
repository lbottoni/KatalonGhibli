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
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import java.util.List as List
import java.util.regex.Matcher as Matcher
import com.kms.katalon.core.testobject.ConditionType as ConditionType

//premo il pulsante nel menu 
boolean btn=WebUI.verifyElementPresent(findTestObject('SettingsMenu/kt_btn_settings_menu_spaziocfg'), GlobalVariable.waitShort,FailureHandling.OPTIONAL)
if(btn==true){
WebUI.click(findTestObject('SettingsMenu/kt_btn_settings_menu_spaziocfg'))
}

WebUI.waitForElementPresent(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/kt_view_dataentry_spazio_menu'), GlobalVariable.waitShort)

WebUI.verifyElementPresent(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/kt_view_dataentry_spazio_menu_btn_create'), 
    GlobalVariable.waitShort)

WebUI.verifyElementPresent(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/kt_view_dataentry_spazio_tree'), GlobalVariable.waitShort)

//prelevo il primo nod che è mostrato nel tree di spazio
TestObject firstNode = findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/first_tree_xnode_text')

String FirstNodeText = WebUI.getText(firstNode)

assert FirstNodeText != ''

//clicco sulla prima freccia del primo nodo
WebUI.click(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/first_arrow_xnode'))

//controllo appaia il primo figlio
WebUI.verifyElementPresent(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/first_arrow_xnode_Local Queue Manager'), GlobalVariable.waitShort)

WebUI.verifyElementPresent(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/first_tree_xnode_Local Queue Manager'), GlobalVariable.waitShort)

WebUI.click(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/first_arrow_xnode_Local Queue Manager'), FailureHandling.STOP_ON_FAILURE)

/*
 *XPTAH per scritta dove NNN è la tr
 * //tbody[starts-with(@id,"treeview")]/tr[NNN]//span[contains(@class,"x-tree-node-text")][contains(text(),"Remote Nodes")]
 * XPATH per icone freccia
 * //tbody[starts-with(@id,"treeview")]/tr[NNN]//img[1]
 * 
 * La struttura ad albero per singolo nodo (dopo che tutti i figli sono stati espansi):
 * NODE1NAME (tr[1]) <arrow>
 * 	->Local Queue Manager (tr[2]) <arrow>
 * 	--->LQINFONAME (tr[3]) <arrow>
 * 	----->Queue (tr[4]) <arrow>
 * 	------->Local (tr[5])
 * 	------->Remote (tr[6])
 * 	----->Default Tables (tr[7])
 * 	----->User Class (tr[8])
 * 	----->DQLs (tr[9])
 * 	--->Transport Classes (tr[10])
 * 	--->Remote Nodes (tr[11])
 * ......
 * NODE2NAME (tr[12])
 * 
 * Per eseguire quest test devo prelevare tutti i Nodes esistenti con un xpath //tbody[starts-with(@id,"treeview")]/tr
 */
//List<WebElement> nodes = WebUiCommonHelper.findWebElements(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/Collection xnodes'), GlobalVariable.waitShort)

String xpathText = '//tbody[starts-with(@id,"treeview")]/tr[${TR}]//span[contains(@class,"x-tree-node-text")]';
String xpathArrow = '//tbody[starts-with(@id,"treeview")]/tr[${TR}]//img[1]';
nodes.each({ def node ->
	Integer TR=1;
	String nodeText=node.getText();
	println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ nodeText: "+nodeText;
	
	TestObject tr1 = new TestObject("TR_${TR}");
	
	tr1.addProperty("xpath", ConditionType.EQUALS, xpathText,true)
	
	TestObject arrowtr1 = new TestObject("ARROWTR_${TR}");
	arrowtr1.addProperty("xpath", ConditionType.EQUALS, xpathArrow,true);
	
	println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ tarrowtr1r1: "+tr1;
	println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ arrowtr1: "+tr1;
})





