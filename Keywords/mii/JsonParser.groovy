package mii

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable


import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import groovy.json.JsonSlurper
import com.kms.katalon.core.testdata.InternalData

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.RequestObject as RequestObject

class JsonParser {

	@Keyword
	def getRootID(def response) {
		def esbContext = response.getHeaderField("X-ESB-CONTEXT")
		def parser = new JsonSlurper().parseText(esbContext)
		def result = parser.ROOTID
		GlobalVariable.rootID = result
		return result;
	}

	@Keyword
	def getJournalSeq(def response){
		def esbContext = response.getHeaderField("X-ESB-CONTEXT")
		def parser = new JsonSlurper().parseText(esbContext)
		def result = parser.JOURNAL_SEQ
		//GlobalVariable.journalSeq = result
		//return result;
		if(result != null){
			KeywordUtil.markPassed("JOURNAL_SEQ = " + result)
		}else if(result == ""){
			KeywordUtil.markPassed("JOURNAL_SEQ = Not Found")
		}else{
			KeywordUtil.markPassed("JOURNAL_SEQ = Not Found")
		}
	}

	@Keyword
	def getTellerId(def response) {
		def esbContext = response.getHeaderField("X-ESB-CONTEXT")
		def parser = new JsonSlurper().parseText(esbContext)
		def result = parser.TELLER_ID
		//GlobalVariable.tellerID = result
		//return result;
		if(result != null){
			KeywordUtil.markPassed("TELLER_ID = " + result)
		}else if(result == ""){
			KeywordUtil.markPassed("TELLER_ID = Not Found")
		}else{
			KeywordUtil.markPassed("TELLER_ID = Not Found")
		}
	}

	@Keyword
	def getResponseBodyValue(def bodyResponse, String field) {
		def parser = new JsonSlurper().parseText(bodyResponse)
		def result = parser."RESPONSE"."${field}"
		return result;
	}

	@Keyword
	def compareResponseCode(def bodyResponse, String responseCode){

		def parser = new JsonSlurper().parseText(bodyResponse)
		def result = parser."RESPONSE"."RESPONSE_CODE"
		if(result == responseCode){
			KeywordUtil.markPassed("Actual Response Code Match Test Data Response Code")
		}else if(responseCode == ""){
			KeywordUtil.markFailed("No Data Response Code")
		}else{
			KeywordUtil.markFailed("Response Code: " +result)
		}
	}

	@Keyword
	def compareResponseMessage(def bodyResponse, String responseMessage){

		def parser = new JsonSlurper().parseText(bodyResponse)
		def result = parser."RESPONSE"."RESPONSE_MESSAGE"
		if(result.equalsIgnoreCase(responseMessage)){
			KeywordUtil.markPassed("Actual Response Message Match Test Data Response Message")
		}else if(responseMessage == ""){
			KeywordUtil.markPassed("No Data Response Message")
		}else{
			KeywordUtil.markFailed("ResponseMessage: "+result)
		}
	}

	@Keyword
	def compareNameProduct(def bodyResponse, String getName){

		def parser = new JsonSlurper().parseText(bodyResponse)
		def result = parser."content"."text"."name"
		if(result.equalsIgnoreCase(getName)){
			KeywordUtil.markPassed("Actual Name Data Product Match Test Data")
		}else if(getName == ""){
			KeywordUtil.markPassed("No Data Product")
		}else{
			KeywordUtil.markFailed("Name Data Product: "+result)
		}
	}
	@Keyword
	def compareErrorCode(def bodyResponse, String errorCode){

		def parser = new JsonSlurper().parseText(bodyResponse)
		def result = parser."RESPONSE"."ERROR_CODE"
		if(result == errorCode){
			KeywordUtil.markPassed("Actual Error Code Match Test Data Error Code")
		}else if(errorCode == ""){
			KeywordUtil.markPassed("No Data Error Code")
		}else{
			KeywordUtil.markFailed("Error Code: "+result)
		}
	}


	@Keyword
	def compareCardStatus(def bodyResponse, String status){

		def parser = new JsonSlurper().parseText(bodyResponse)
		def result = parser."RESPONSE"."STATUS"
		if(result == status){
			KeywordUtil.markPassed("Actual Status Match Test Data Status")
		}else if(status == ""){
			KeywordUtil.markPassed("No Data")
		}else{
			KeywordUtil.markFailed("Actual Status Does not Match Test Data Status")
		}
	}
}