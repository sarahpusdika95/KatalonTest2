package mii

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.json.JsonSlurper
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable


class ProductNameComparator {

	@Keyword
	def compareNameProduct(def bodyResponse, String getName) {
		try {
			// Check if bodyResponse is null or empty
			if (!bodyResponse) {
				KeywordUtil.markFailed("Response body is null or empty")
				return
			}

			def parser = new JsonSlurper().parseText(bodyResponse.toString())

			// Safe navigation with null checks
			def content = parser?.content
			def text = content?.text

			String result = null

			if (text) {
				// If text exists, try to parse it as JSON and get name
				try {
					def textParser = new JsonSlurper().parseText(text.toString())
					result = textParser?.name?.toString()
				} catch (Exception e) {
					// If text is not JSON, try to get name directly from main parser
					result = parser?.name?.toString()
				}
			} else {
				// If no content.text, try to get name directly
				result = parser?.name?.toString()
			}

			// Handle comparison with null safety
			if (result && result.equalsIgnoreCase(getName)) {
				KeywordUtil.markPassed("Actual Name Data Product Match Test Data: ${result}")
			} else if (!getName || getName.trim().isEmpty()) {
				KeywordUtil.markPassed("No Data Product expected - Actual name: ${result ?: 'No name found'}")
			} else if (!result) {
				KeywordUtil.markFailed("No 'name' field found in response. Expected: ${getName}")
			} else {
				KeywordUtil.markFailed("Name Data Product mismatch - Expected: '${getName}', Actual: '${result}'")
			}
		} catch (Exception e) {
			KeywordUtil.markFailed("Error comparing product name: ${e.message}")
		}
	}
}

