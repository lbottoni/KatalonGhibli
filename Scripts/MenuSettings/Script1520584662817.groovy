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
import org.openqa.selenium.By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import java.util.List as List
import java.util.regex.Matcher as Matcher
import com.kms.katalon.core.testobject.ConditionType as ConditionType

//prima di cliccare, devo verificare se non sia gà pressato (ovvero siamo già nella dashboard)
//devo verificare che non abbia la class css "x-btn-pressed"
TestObject btn_dash = findTestObject('GhibliDashboard/btn_monitordashboard')

String css = WebUI.getAttribute(btn_dash, 'class', FailureHandling.STOP_ON_FAILURE)

boolean isPressed = css.contains(GlobalVariable.btn_pressed)

if (isPressed==false) {
    WebUI.click(findTestObject('GhibliDashboard/btn_monitordashboard'))
}

WebUI.verifyElementNotVisible(findTestObject('GhibliDashboard/btn_menu'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('GhibliDashboard/btn_setting'))

WebUI.waitForElementPresent(findTestObject('GhibliDashboard/btn_menu'), GlobalVariable.waitShort)

WebUI.verifyElementVisible(findTestObject('SettingsMenu/kt_settings_menu'), FailureHandling.STOP_ON_FAILURE)

//ora abbiamo il menu tree a sinistra dei setting aperto, verifico che esistano tutte le voci
// //*[contains(@class, 'kt_btn_settings_menu_')]//span[contains(@class,"x-btn-inner x-btn-inner-center")]
//findTestObject('Object Repository/SettingsMenu/Collection Tree Text')
List<WebElement> element = WebUiCommonHelper.findWebElements(findTestObject('SettingsMenu/Collection Tree Text'), GlobalVariable.waitShort)

element.each({ def el ->
        //verifico che siano testi in maiuscolo
        String text = el.getText()
        boolean isUppercase = false
        isUppercase = (text ==~ '[A-Z ]+')
        assert isUppercase == true    		
		})

//verifico che passando su ogni elemento nel menu setting abbia il qtip e questo sia mostrato quando è usato il mouseover sull'elemento
List<WebElement> elementQtip = WebUiCommonHelper.findWebElements(findTestObject('SettingsMenu/Collection Tree'), 10)
elementQtip.each({ def elW ->
	
	String idQtip=elW.getAttribute("id")
	println(">>>>>>>>>>>"+idQtip+">>>>>>>>>>>")
	println("idQtip="+idQtip)
	TestObject ob = new TestObject("Elments_"+idQtip);
	ob.addProperty("xpath", ConditionType.EQUALS, '//a[@id="'+idQtip+'"]//span[contains(@class,"x-btn-inner x-btn-inner-center")]',true)
	println("ob="+ob)
	WebUI.mouseOver(ob);
	WebUI.waitForElementVisible(findTestObject('SettingsMenu/QuickTipText'), GlobalVariable.waitShort)
	String qtipText=WebUI.getText(findTestObject('SettingsMenu/QuickTipText'), FailureHandling.STOP_ON_FAILURE)
	println("qtipText="+qtipText)		
	TestObject obElA = new TestObject("Elments_A_"+idQtip);
	println("obElA="+obElA)
	obElA.addProperty("xpath", ConditionType.EQUALS, '//a[@id="'+idQtip+'"]',true)
	String TextobElA=WebUI.getText(obElA, FailureHandling.STOP_ON_FAILURE)	
	String qtipTextobElA=WebUI.getAttribute(obElA, "data-qtip")
	println("TextobElA="+TextobElA)
	println("qtipTextobElA="+qtipTextobElA)	
	println("<<<<<<<<<<<<<<<<<<<<<<")
	assert qtipText==qtipTextobElA	
	
})
//prelevo l'attributo con il valore del testo del qtip
//String qtip=WebUI.getAttribute(el, css, FailureHandling.STOP_ON_FAILURE)data-qtip
//

