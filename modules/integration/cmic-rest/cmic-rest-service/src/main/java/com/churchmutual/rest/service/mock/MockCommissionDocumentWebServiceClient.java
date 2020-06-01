package com.churchmutual.rest.service.mock;

import com.churchmutual.rest.model.CMICCommissionDocument;
import com.churchmutual.rest.model.CMICFile;
import com.churchmutual.rest.service.MockResponseReaderUtil;

import com.liferay.portal.kernel.json.JSONDeserializer;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kayleen Lim
 */
@Component(immediate = true, service = MockCommissionDocumentWebServiceClient.class)
public class MockCommissionDocumentWebServiceClient {

	public List<CMICFile> downloadDocuments(String[] ids, boolean includeBytes) {
		String fileName = _COMMISSION_DOCUMENT_WEB_SERVICE_DIR + "downloadDocuments.json";

		String fileContent = MockResponseReaderUtil.readFile(fileName);

		List<CMICFile> allFiles = new ArrayList<>();

		try {
			JSONObject file = _jsonFactory.createJSONObject(fileContent);

			Iterator iterator = file.keys();

			while (iterator.hasNext()) {
				String fileId = String.valueOf(iterator.next());

				String fileArray = file.getString(fileId);

				JSONDeserializer<CMICFile[]> jsonDeserializer = _jsonFactory.createJSONDeserializer();

				CMICFile[] files = jsonDeserializer.deserialize(fileArray, CMICFile[].class);

				Collections.addAll(allFiles, files);
			}
		}
		catch (JSONException jsone) {
			if (_log.isErrorEnabled()) {
				_log.error("Could not create JSONObject: " + jsone.getMessage(), jsone);
			}
		}

		return allFiles;
	}

	public List<CMICCommissionDocument> searchDocuments(
		String agentNumber, String divisionNumber, String documentType, String maximumStatementDate,
		String minimumStatementDate) {

		String fileName = _COMMISSION_DOCUMENT_WEB_SERVICE_DIR + "searchDocuments.json";

		String fileContent = MockResponseReaderUtil.readFile(fileName);

		JSONDeserializer<CMICCommissionDocument[]> jsonDeserializer = _jsonFactory.createJSONDeserializer();

		CMICCommissionDocument[] commissionDocuments = jsonDeserializer.deserialize(
			fileContent, CMICCommissionDocument[].class);

		return ListUtil.fromArray(commissionDocuments);
	}

	private static final String _COMMISSION_DOCUMENT_WEB_SERVICE_DIR = "commission-document-web-service/";

	private static final Log _log = LogFactoryUtil.getLog(MockCommissionDocumentWebServiceClient.class);

	@Reference
	private JSONFactory _jsonFactory;

}