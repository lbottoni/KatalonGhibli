package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object waitShort
     
    /**
     * <p></p>
     */
    public static Object waitMedium
     
    /**
     * <p></p>
     */
    public static Object waitLong
     
    /**
     * <p></p>
     */
    public static Object username
     
    /**
     * <p></p>
     */
    public static Object password
     
    /**
     * <p></p>
     */
    public static Object fakepassword
     
    /**
     * <p></p>
     */
    public static Object btn_pressed
     
    /**
     * <p></p>
     */
    public static Object url
     

    static {
        def allVariables = [:]        
        allVariables.put('default', ['waitShort' : 5, 'waitMedium' : 30, 'waitLong' : 120, 'username' : 'luca.bottoni', 'password' : 'Primeur', 'fakepassword' : 'ghibliXXX', 'btn_pressed' : 'x-btn-pressed', 'url' : 'http://ghibli-legacy.localhost'])
        allVariables.put('c17-Intesa-198', allVariables['default'] + ['waitShort' : 5, 'waitMedium' : 30, 'waitLong' : 120, 'username' : 'luca.bottoni', 'password' : 'Primeur', 'fakepassword' : 'ghibliXXX', 'btn_pressed' : 'x-btn-pressed', 'url' : 'http://192.168.180.198/SPENT'])
        
        String profileName = RunConfiguration.getExecutionProfile()
        
        def selectedVariables = allVariables[profileName]
        waitShort = selectedVariables['waitShort']
        waitMedium = selectedVariables['waitMedium']
        waitLong = selectedVariables['waitLong']
        username = selectedVariables['username']
        password = selectedVariables['password']
        fakepassword = selectedVariables['fakepassword']
        btn_pressed = selectedVariables['btn_pressed']
        url = selectedVariables['url']
        
    }
}
