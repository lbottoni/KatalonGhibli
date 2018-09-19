
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import com.kms.katalon.core.testobject.TestObject

import java.lang.String


def static "lbottoni.utility.test"() {
    (new lbottoni.utility()).test()
}

def static "lbottoni.utility.doubleClickWithJavascript"(
    	TestObject element	) {
    (new lbottoni.utility()).doubleClickWithJavascript(
        	element)
}

def static "lbottoni.utility.isFileDownloaded"(
    	String downloadPath	
     , 	String fileName	) {
    (new lbottoni.utility()).isFileDownloaded(
        	downloadPath
         , 	fileName)
}

def static "lbottoni.utility.deleteFile"(
    	String downloadPath	
     , 	String fileName	) {
    (new lbottoni.utility()).deleteFile(
        	downloadPath
         , 	fileName)
}

def static "lbottoni.utility.objectByXpath"(
    	String name	
     , 	String xpath	) {
    (new lbottoni.utility()).objectByXpath(
        	name
         , 	xpath)
}
