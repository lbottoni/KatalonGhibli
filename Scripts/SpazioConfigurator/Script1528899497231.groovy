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

//premo il pulsante nel menu ,
boolean btn=WebUI.verifyElementVisible(findTestObject('SettingsMenu/kt_btn_settings_menu_spaziocfg'), FailureHandling.OPTIONAL)
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


Integer TR=1;
Integer maxNodes=2;
Integer stepper=11;
Integer nowNode;
for(i=0;i<maxNodes;i++){
		
	String xpathText = "//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]";
	String xpathArrow = "//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//img[contains(@class,\"x-tree-expander\")]";
	String NAME="";
	nowNode=(TR==1)?1:++nowNode;
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								ROOT
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//nowNode++;
	NAME="ROOT";
	TestObject elemRoot=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	TestObject arrowRoot=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_ARROW_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//img[contains(@class,\"x-tree-expander\")]");
	String textRoot=WebUI.getText(elemRoot, FailureHandling.STOP_ON_FAILURE);
	assert textRoot!="";
	WebUI.verifyElementVisible(elemRoot, FailureHandling.STOP_ON_FAILURE);
	WebUI.verifyElementVisible(arrowRoot, FailureHandling.STOP_ON_FAILURE);
	
	WebUI.click(arrowRoot);
	WebUI.delay(1);
	//
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								LQM
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	nowNode++;//2
	NAME="LQM";
	TestObject elemLQM=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	String textLQM=WebUI.getText(elemLQM, FailureHandling.STOP_ON_FAILURE);
	assert textLQM=="Local Queue Manager";
	WebUI.verifyElementVisible(elemLQM, FailureHandling.STOP_ON_FAILURE);
	
	//verifiche su pagina dx
	WebUI.click(elemLQM);
	WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/lbl_title'), FailureHandling.STOP_ON_FAILURE);
	String textTitle=WebUI.getText(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/lbl_title'), FailureHandling.STOP_ON_FAILURE)
	assert textTitle == "${textRoot} - Local Queue Managers";
	//check pulsanti ricerca
	WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/btn_apply'), FailureHandling.STOP_ON_FAILURE);
	WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/btn_clear'), FailureHandling.STOP_ON_FAILURE);
	WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/input_searchfield'), FailureHandling.STOP_ON_FAILURE);
	
	//verifico se esiste almeno un row nella griglia
	WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid'), FailureHandling.STOP_ON_FAILURE);
	boolean checkRow=WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_first_row'),FailureHandling.OPTIONAL);
	if(checkRow){
		//almeno una riga esiste
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_first_row_last_cell'), FailureHandling.STOP_ON_FAILURE);
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_eye'), FailureHandling.STOP_ON_FAILURE);
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_pencil'), FailureHandling.STOP_ON_FAILURE);
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_delete'), FailureHandling.STOP_ON_FAILURE);
		
		WebUI.click(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_eye'));
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/input_lqm_name'), FailureHandling.STOP_ON_FAILURE);
		WebUI.click(elemLQM);
		WebUI.delay(1);		
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/lbl_title'), FailureHandling.STOP_ON_FAILURE);
		}
	
	
	TestObject arrowLQM=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_ARROW_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//img[contains(@class,\"x-tree-expander\")]");
	WebUI.verifyElementVisible(arrowLQM, FailureHandling.STOP_ON_FAILURE);
	
	WebUI.click(arrowLQM);
	WebUI.delay(1);
	//
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								CNODE
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	nowNode++;//3
	NAME="CNODE";
	TestObject elemCNODE=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	String textCNODE=WebUI.getText(elemCNODE, FailureHandling.STOP_ON_FAILURE);
	assert textCNODE!="";
	WebUI.verifyElementVisible(elemCNODE, FailureHandling.STOP_ON_FAILURE);
	
	TestObject arrowCNODE=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_ARROW_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//img[contains(@class,\"x-tree-expander\")]");
	WebUI.verifyElementVisible(arrowCNODE, FailureHandling.STOP_ON_FAILURE);
	
	WebUI.click(arrowCNODE);
	WebUI.delay(1);
	//
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								QUEUE
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	nowNode++;//4
	NAME="QUEUE";
	TestObject elemQUEUE=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	String textQUEUE=WebUI.getText(elemQUEUE, FailureHandling.STOP_ON_FAILURE);
	assert textQUEUE=="Queue";
	WebUI.verifyElementVisible(elemQUEUE, FailureHandling.STOP_ON_FAILURE);
	
	TestObject arrowQUEUE=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_ARROW_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//img[contains(@class,\"x-tree-expander\")]");
	WebUI.verifyElementVisible(arrowQUEUE, FailureHandling.STOP_ON_FAILURE);
	
	WebUI.click(arrowQUEUE);
	WebUI.delay(1);
	//tutti i nodi sono aperti
	//
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								QUEUELC
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	nowNode++;//5
	NAME="QUEUELC";
	TestObject elemQUEUELC=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	String textQUEUELC=WebUI.getText(elemQUEUELC, FailureHandling.STOP_ON_FAILURE);
	assert textQUEUELC=="Local";
	WebUI.verifyElementVisible(elemQUEUELC, FailureHandling.STOP_ON_FAILURE);
	//
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								QUEUERM
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	nowNode++;//6
	NAME="QUEUERM";
	TestObject elemQUEUERM=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	String textQUEUERM=WebUI.getText(elemQUEUERM, FailureHandling.STOP_ON_FAILURE);
	assert textQUEUERM=="Remote";
	WebUI.verifyElementVisible(elemQUEUERM, FailureHandling.STOP_ON_FAILURE);
	//
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								DTAB
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	nowNode++;//7
	NAME="DTAB";
	TestObject elemDTAB=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	String textDTAB=WebUI.getText(elemDTAB, FailureHandling.STOP_ON_FAILURE);
	assert textDTAB=="Default Tables";
	WebUI.verifyElementVisible(elemDTAB, FailureHandling.STOP_ON_FAILURE);
	//
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								UC
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	nowNode++;//8
	NAME="UC";
	TestObject elemUC=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	String textUC=WebUI.getText(elemUC, FailureHandling.STOP_ON_FAILURE);
	assert textUC=="User Class";
	WebUI.verifyElementVisible(elemUC, FailureHandling.STOP_ON_FAILURE);
	//
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								DQL
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	nowNode++;//9
	NAME="DQL";
	TestObject elemDQL=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	String textDQL=WebUI.getText(elemDQL, FailureHandling.STOP_ON_FAILURE);
	assert textDQL=="DQLs";
	WebUI.verifyElementVisible(elemDQL, FailureHandling.STOP_ON_FAILURE);
	
	
	//verifiche su pagina dx
	WebUI.click(elemDQL);
	WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/lbl_title'), FailureHandling.STOP_ON_FAILURE);
	String textTitleDQL=WebUI.getText(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/lbl_title'), FailureHandling.STOP_ON_FAILURE)
	assert textTitleDQL == "${textCNODE} - DQLs";
	//check pulsanti ricerca
	WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/btn_apply'), FailureHandling.STOP_ON_FAILURE);
	WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/btn_clear'), FailureHandling.STOP_ON_FAILURE);
	WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/input_searchfield'), FailureHandling.STOP_ON_FAILURE);
	
	//verifico se esiste almeno un row nella griglia
	WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid'), FailureHandling.STOP_ON_FAILURE);
	boolean checkRowDql=WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_first_row'),FailureHandling.OPTIONAL);
	if(checkRowDql){
		//almeno una riga esiste
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_first_row_last_cell'), FailureHandling.STOP_ON_FAILURE);
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_eye'), FailureHandling.STOP_ON_FAILURE);
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_pencil'), FailureHandling.STOP_ON_FAILURE);
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_delete'), FailureHandling.STOP_ON_FAILURE);
		
		WebUI.click(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/grid_actioncell_eye'));
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/input_lqm_name'), FailureHandling.STOP_ON_FAILURE);
		//elementi DQL
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/dql/dql_label_info'), FailureHandling.STOP_ON_FAILURE);
		//controllo testo
		String DQLInfo=WebUI.getText(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/dql/dql_label_info'), FailureHandling.STOP_ON_FAILURE)
		assert DQLInfo=="DQL Queue Info";
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/dql/dql_button_plus'), FailureHandling.STOP_ON_FAILURE);
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/GhibliDataentry-Spazio/dql/dql_grid_magic_actioncolumn'), FailureHandling.STOP_ON_FAILURE);
		
		//ritorno al precedente
		WebUI.click(elemLQM);
		WebUI.delay(1);
		WebUI.verifyElementVisible(findTestObject('Object Repository/SettingsMenu/SpazioConfigurator/DxPage/top/lbl_title'), FailureHandling.STOP_ON_FAILURE);
		}
	
	
	
	
	
	//
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								TCLASS
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	nowNode++;//10
	NAME="TCLASS";
	TestObject elemTCLASS=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	String textTCLASS=WebUI.getText(elemTCLASS, FailureHandling.STOP_ON_FAILURE);
	assert textTCLASS=="Transport Classes";
	WebUI.verifyElementVisible(elemTCLASS, FailureHandling.STOP_ON_FAILURE);
	//
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	//								RNODES
	//	◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙
	nowNode++;//11
	NAME="RNODES";
	TestObject elemRNODES=CustomKeywords.'lbottoni.utility.objectByXpath'("${NAME}_NODE_${TR}","//tbody[starts-with(@id,\"treeview\")]/tr[${nowNode}]//span[contains(@class,\"x-tree-node-text\")]");
	String textRNODES=WebUI.getText(elemRNODES, FailureHandling.STOP_ON_FAILURE);
	assert textRNODES=="Remote Nodes";
	WebUI.verifyElementVisible(elemRNODES, FailureHandling.STOP_ON_FAILURE);
	//
	//incremento al prossimo TR NODO
	TR++;
	
}

/*
 boolean isDeleted = CustomKeywords.'lbottoni.utility.deleteFile'(downloadPath, filename)
nodes.each({ def node ->
	
	String nodeText=node.getText();
	println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ nodeText: "+nodeText;
	
	TestObject tr1 = new TestObject("TR_${TR}");
	
	tr1.addProperty("xpath", ConditionType.EQUALS, xpathText,true)
	
	TestObject arrowtr1 = new TestObject("ARROWTR_${TR}");
	arrowtr1.addProperty("xpath", ConditionType.EQUALS, xpathArrow,true);
	
	println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ tarrowtr1r1: "+tr1;
	println "◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙◙ arrowtr1: "+tr1;
})
*/




