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
import org.junit.After as After
import org.openqa.selenium.Keys as Keys


TestObject input_login = findTestObject('Object Repository/GhibliLoginPage/input_username')

TestObject input_pwd = findTestObject('Object Repository/GhibliLoginPage/input_password')

WebUI.verifyElementPresent(input_login, 1, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(input_pwd, 1, FailureHandling.STOP_ON_FAILURE)

WebUI.setText(input_login, GlobalVariable.username)

WebUI.setText(input_pwd, GlobalVariable.fakepassword)

WebUI.delay(2)

boolean text1 = WebUI.verifyElementNotVisible(findTestObject('Object Repository/GhibliLoginPage/div_Incorrect Username or Pass'))

assert text1 == true

//WebUI.sendKeys(findTestObject('Object Repository/GhibliLoginPage/input_password'), Keys.chord(Keys.ENTER))
WebUI.doubleClick(findTestObject('Object Repository/GhibliLoginPage/LoginButton'))

WebUI.delay(2)

WebUI.waitForElementPresent(findTestObject('Object Repository/GhibliLoginPage/div_Incorrect Username or Pass'), GlobalVariable.waitShort)

boolean text2 = WebUI.verifyTextPresent('Incorrect Username or Password', false)

assert text2 == true

WebUI.focus(input_login)

WebUI.setText(input_login, GlobalVariable.username)

WebUI.delay(1)
WebUI.focus(input_pwd)
WebUI.setText(input_pwd, GlobalVariable.password)

WebUI.delay(1)
//WebUI.doubleClick(findTestObject('GhibliLoginPage/LoginButton'))
WebUI.sendKeys(findTestObject('Object Repository/GhibliLoginPage/input_password'), Keys.chord(Keys.ENTER))



