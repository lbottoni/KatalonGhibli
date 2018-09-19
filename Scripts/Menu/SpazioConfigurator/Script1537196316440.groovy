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
//custom
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper as WebUiCommonHelper
import java.util.List as List
import java.util.regex.Matcher as Matcher
import com.kms.katalon.core.testobject.ConditionType as ConditionType

def check = false

check = WebUI.verifyElementVisible(findTestObject('SettingsMenu/kt_settings_menu'), FailureHandling.OPTIONAL)

def i = 0

if (check == true) {
    i = 1

    println('Il menu è visibile')

    WebUI.click(findTestObject('SettingsMenu/btn/btn_settings_menu_spaziocfg'))

    WebUI.waitForElementVisible(findTestObject('SettingsMenu/SpazioConfigurator/title_list_queue'), GlobalVariable.waitMedium)
}
    println('Il menu NON è visibile')

	//prelevo il test del menu di Spazio
    List<WebElement> element = WebUiCommonHelper.findWebElements(findTestObject('SettingsMenu/SpazioConfigurator/title_list_queue'), 
        GlobalVariable.waitShort)
	
	String textTitle = element[0].getText()
	println('textTitle=' + textTitle)
	assert textTitle=="Spazio Configurator"

	//contorllo new spazio button
	WebUI.waitForElementPresent(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/kt_view_dataentry_spazio_menu'), GlobalVariable.waitShort)
	
	WebUI.verifyElementPresent(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/kt_view_dataentry_spazio_menu_btn_create'),
		GlobalVariable.waitShort)
	
	WebUI.verifyElementPresent(findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/kt_view_dataentry_spazio_tree'), GlobalVariable.waitShort)
	
	//prelevo il primo nod che è mostrato nel tree di spazio
	TestObject firstNode = findTestObject('SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/first_tree_xnode_text')
	
	String FirstNodeText = WebUI.getText(firstNode)
	println('FirstNodeText=' + FirstNodeText)

	WebUI.comment("estraggo l'elenco dei nodi spazio");
	List<WebElement> spazioNodes = WebUiCommonHelper.findWebElements(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/spaziomenu/Collection xnodes'),
		GlobalVariable.waitShort);


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
	 * NODE2NAME (tr[2])
	 *
	 * Per eseguire quest test devo prelevare tutti i Nodes esistenti con un xpath //tbody[starts-with(@id,"treeview")]/tr
	 */
	
	
	//ogni tr dovrebbe avere 12 tr figlie, quindi il nodo successivo è inizio+11
	Integer maxNodes=1;
	Integer numberSpazioNodes=(spazioNodes.size()<maxNodes)?spazioNodes.size():maxNodes;	
	println "spazioNodes n="+spazioNodes.size();
	println "numberSpazioNodes n="+numberSpazioNodes;
	Integer startNodeTr=1;
	Integer stepNodeTr=11;
	Integer TR=1;
	String xpathText = "//tbody[starts-with(@id,\"treeview\")]/tr[${TR}]//span[contains(@class,\"x-tree-node-text\")]";
	String xpathArrow = "//tbody[starts-with(@id,\"treeview\")]/tr[${TR}]//img[1]";
	for (i = 0; i <numberSpazioNodes; i++) {
		def node=spazioNodes[i];
		
		
		String nodeText=node.getText();
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									ROOT SINGOLO NODO
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//TestObject nodeTr = new TestObject("TR_${TR}");		
		//nodeTr.addProperty("xpath", ConditionType.EQUALS, xpathText,true)
		TestObject nodeTr = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"TR_${TR}",
			xpathText
			);
		Integer numberArrow=TR;//1
		
		
		//IMMAGINE NODO >	
		TestObject arrownodeTr = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWNODETR_${TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//img[contains(@class,\"x-tree-expander\")]"
			);
		WebUI.click(arrownodeTr);WebUI.delay(1);
		//la prossima freccia cliccabile è la Tr attuale + incremnetale++
				
		//ora i nodi sono tutti aperti, devo aprirli per far scorrere gli step, altrimenti
		//l'immagine fraccia su cui si clicca n 2 sarebbe quella del nodo successivo e non 
		//quella delle "Local Queue Manager", questo perchè ext quando si espande il nodo
		//aggiunge TR alla lista come figlie
		//
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									LOCAL QUEUE MANAGER
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		numberArrow++;//2
		//xpathArrow = "//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//img[contains(@class,\"x-tree-expander\")]";
		//TestObject arrowtr_lqm = new TestObject("ARROWTR_LOCAL_QUEUE_MANAGER${TR}");
		//arrowtr_lqm.addProperty("xpath", ConditionType.EQUALS, xpathArrow,true);
		
		TestObject arrowtr_lqm = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWTR_LOCAL_QUEUE_MANAGER${TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//img[contains(@class,\"x-tree-expander\")]"
			);
		
		WebUI.click(arrowtr_lqm);WebUI.delay(1);
		// Controllo il testo sia Local Queue Manager
		TestObject text_lqm = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"TEXT_LOCAL_QUEUE_MANAGER${TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//span[contains(@class,\"x-tree-node-text\")][contains(text(),\"Local Queue Manager\")]"
			);
		//clickc sulla voce local queue manager
		WebUI.verifyElementVisible(text_lqm, FailureHandling.STOP_ON_FAILURE);
		WebUI.click(text_lqm);
		WebUI.delay(1);
		WebUI.waitForElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/view_dataentry_spazio_list'), GlobalVariable.waitMedium);
		WebUI.verifyElementVisible(findTestObject('SettingsMenu/SpazioConfigurator/DxPage/top/lbl_title'), FailureHandling.STOP_ON_FAILURE)
		//controllo il testo a sinistra che deve riportate il nome del node
		//jfsteas - Local Queue Managers
		String TitleText=WebUI.getText(findTestObject('SettingsMenu/SpazioConfigurator/DxPage/top/lbl_title'));
		assert TitleText == nodeText+" - Local Queue Managers"
		//controllo il box con la lente
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/btn_clear'), FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/btn_apply'), FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/input_searchfield'), FailureHandling.STOP_ON_FAILURE)
		String attribute = WebUI.getAttribute(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/input_searchfield'), 'placeholder')
		assert attribute == "Search by name";
		//controllo la presenza di almeno una riga nella tabella
		boolean checkRow = WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_first_row'), FailureHandling.OPTIONAL);
		println "checkRow = (${TR})"+checkRow;
		if(checkRow){
			//ESISTE LA PRIMA RIGA POPOLATA, QUINDI:
			//controllo che esistano i pulsanti e siano visibili
			WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_eye'), FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_pencil'), FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_delete'), FailureHandling.STOP_ON_FAILURE)
		
			WebUI.click(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_eye'));
			WebUI.waitForElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/input_lqm_name'), GlobalVariable.waitMedium, FailureHandling.STOP_ON_FAILURE)
			
			WebUI.click(text_lqm);
			WebUI.delay(1);
			}
		
		//
		//
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									QM - CUSTOM NAME
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		numberArrow++;//3
		TestObject arrowtr_qm = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWTR_QM{TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//img[contains(@class,\"x-tree-expander\")]"
			);
		WebUI.click(arrowtr_qm);WebUI.delay(1);
		//prelevo il nome del QM custom
		
		
		//
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									QUEUE
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		numberArrow++;//4
		TestObject arrowtr_queue = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWTR_QUEUE{TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//img[contains(@class,\"x-tree-expander\")]"
			);
		
		WebUI.click(arrowtr_queue);WebUI.delay(1);
								
		//Ora Ogni FIGLIO del NODES di lavoro nel Tree ha tuttte le foglie espanse
		//quindi il nodo successivo è il prossimo step
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									QUEUE->Local
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		numberArrow++;//5
		TestObject tr_queue_local = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWTR_QUEUE{TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//span[contains(@class,\"x-tree-node-text\")]"
			);
		WebUI.verifyElementVisible(tr_queue_local, FailureHandling.STOP_ON_FAILURE);
		assert WebUI.getText(tr_queue_local)=="Local";
		//
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									QUEUE->Remote
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		numberArrow++;//6
		TestObject tr_queue_remote = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWTR_QUEUE{TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//span[contains(@class,\"x-tree-node-text\")]"
			);
		WebUI.verifyElementVisible(tr_queue_remote, FailureHandling.STOP_ON_FAILURE);
		
		assert WebUI.getText(tr_queue_remote)=="Remote";
		//
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									Default Tables
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		numberArrow++;//7
		TestObject tr_dt = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWTR_DTABLE{TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//span[contains(@class,\"x-tree-node-text\")]"
			);
		WebUI.verifyElementVisible(tr_dt, FailureHandling.STOP_ON_FAILURE);
		
		assert WebUI.getText(tr_dt)=="Default Tables";
		
		//
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									User Class
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		numberArrow++;//8
		TestObject tr_userc = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWTR_USERCLASS{TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//span[contains(@class,\"x-tree-node-text\")]"
			);
		WebUI.verifyElementVisible(tr_userc, FailureHandling.STOP_ON_FAILURE);
		
		assert WebUI.getText(tr_userc)=="User Class";
		
		//
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									DQLs
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		numberArrow++;//9
		TestObject tr_dql = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWTR_DQL{TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//span[contains(@class,\"x-tree-node-text\")]"
			);
		WebUI.verifyElementVisible(tr_dql, FailureHandling.STOP_ON_FAILURE);
		
		assert WebUI.getText(tr_dql)=="DQLs";
		
		//eseguo click su DQL
		
		TestObject text_dql= CustomKeywords.'lbottoni.utility.objectByXpath'(
			"TEXT_DQL${TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//span[contains(@class,\"x-tree-node-text\")][contains(text(),\"DQLs\")]"
			);
		
		WebUI.click(text_dql);
		WebUI.delay(1);
		//controllo la presenza di almeno una riga nella tabella
		boolean checkRowDQL = WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_first_row'), FailureHandling.OPTIONAL);
		println "checkRowDQL = (${TR})"+checkRowDQL;
		if(checkRowDQL){
			//ESISTE LA PRIMA RIGA POPOLATA, QUINDI:
			//controllo che esistano i pulsanti e siano visibili
			WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_eye'), FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_pencil'), FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_delete'), FailureHandling.STOP_ON_FAILURE)
		
			WebUI.click(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_eye'));
			//WebUI.waitForElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/input_lqm_name'), GlobalVariable.waitMedium, FailureHandling.STOP_ON_FAILURE)
			
			WebUI.click(text_lqm);
			WebUI.delay(1);
			}
		
		
		
		//
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									Transport Classes
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		numberArrow++;//10
		TestObject tr_tclass = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWTR_TCLASS{TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//span[contains(@class,\"x-tree-node-text\")]"
			);
		WebUI.verifyElementVisible(tr_tclass, FailureHandling.STOP_ON_FAILURE);
		
		assert WebUI.getText(tr_tclass)=="Transport Classes";
		
		//
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		//									Remote Nodes
		//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
		numberArrow++;//11
		TestObject tr_rnode = CustomKeywords.'lbottoni.utility.objectByXpath'(
			"ARROWTR_RNODE{TR}",
			"//tbody[starts-with(@id,\"treeview\")]/tr[${numberArrow}]//span[contains(@class,\"x-tree-node-text\")]"
			);
		WebUI.verifyElementVisible(tr_rnode, FailureHandling.STOP_ON_FAILURE);
		
		assert WebUI.getText(tr_rnode)=="Remote Nodes";
		
		
		
		println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ TR NODE : "+TR+" ◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙";
		println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ nodeText: "+nodeText;
		println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ nodeTr: "+nodeTr;
		println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ xpathArrow="+xpathArrow;
		println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ arrownodeTr: "+arrownodeTr;
		println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ numberArrow: "+numberArrow;
		
		TR+=stepNodeTr;
		numberArrow+=stepNodeTr;
		println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ next TR: "+TR;
		println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ next numberArrow: "+numberArrow;
		println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙";
		
		
		
		//aggiunta contatore prossimo Node
		
		
		

	}