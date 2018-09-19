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
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW

WebUI.comment('verifiche sul container del cluster nella colonna peerserver')

WebUI.waitForElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/lbl_peerlist_clustername'), GlobalVariable.waitShort)

WebUI.verifyElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/lbl_peerlist_clustername'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/btn_peerlist_refresh'))

WebUI.verifyElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/btn_peerlist_pause'))

WebUI.click(findTestObject('GhibliDashboard/Dashboard-PeerServer/btn_peerlist_pause'))

WebUI.waitForElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/btn_peerlist_play'), GlobalVariable.waitShort)

WebUI.verifyElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/btn_peerlist_play'))

WebUI.verifyElementNotPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/btn_peerlist_pause'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/serverpeer_progressbar_reload'), GlobalVariable.waitShort)

WebUI.verifyElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/serverpeer_progressbar_reload'))

//◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
//inizia la verifica sul primo server nella lista dei peer
//◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
WebUI.comment('inizia la verifica sul primo server nella lista dei peer')

WebUI.verifyElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/serverpeer_container'))

//controllo che esista almeno un peer container, con i relativi pulsanti
WebUI.verifyElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/singlepeer_container'))

//exapand
WebUI.verifyElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/serverpeer_first_exapand'))

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/serverpeer_first_exapand'), GlobalVariable.waitShort)

WebUI.comment('fine controllo container primo peer')

//◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
//inizia la verifica sul primo peer
//◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
WebUI.verifyElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/lbl_singlepeer_name'))

WebUI.comment('EXPAND PRIMO PEER')

WebUI.click(findTestObject('GhibliDashboard/Dashboard-PeerServer/serverpeer_first_exapand'))

WebUI.waitForElementVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/peer1_container_servers'), GlobalVariable.waitShort)

WebUI.comment('controllo sul primo server del primo peer')

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/peer1_container_servers'), GlobalVariable.waitShort)

//controllo che il primo server abbia lo stato

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/serverpeer_state'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/serverpeer_icon'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/serverpeer_name'), 1)

WebUI.click(findTestObject('GhibliDashboard/Dashboard-PeerServer/btn_server_start'))

WebUI.waitForElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/msg_container'), GlobalVariable.waitShort)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/msg_clustername'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/msg_peername'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/msg_clustername'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/msg_servername'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/msg_serverstate'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/msg_serverstate_new'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/msg_protocol'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/btn_NO'), 1)

WebUI.verifyElementPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/btn_YES'), 1)

WebUI.delay(2)

WebUI.click(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/btn_NO'))

WebUI.waitForElementNotPresent(findTestObject('GhibliDashboard/Dashboard-PeerServer/messagebox/msg_container'), GlobalVariable.waitShort)

//◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
//inizia la verifica sul primo peer
//◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
WebUI.comment('COLLAPSE PRIMO PEER')

WebUI.click(findTestObject('GhibliDashboard/Dashboard-PeerServer/serverpeer_first_collapse'))

WebUI.waitForElementNotVisible(findTestObject('GhibliDashboard/Dashboard-PeerServer/peer1_container_servers'), GlobalVariable.waitShort)
