package com.churchmutual.rest.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Kayleen Lim
 */
@ExtendedObjectClassDefinition(category = "cmic")
@Meta.OCD(
	id = "com.churchmutual.rest.configuration.MockPolicyWebServiceConfiguration", localization = "content/Language",
	name = "mock-policy-web-service"
)
public interface MockPolicyWebServiceConfiguration {

	@Meta.AD(deflt = "true", description = "Enable mock for Policy Service method /v1/transactions", required = false)
	public boolean enableMockGetTransaction();

	@Meta.AD(
		deflt = "true", description = "Enable mock for Policy Service method /v1/policy-summaries/on-account",
		required = false
	)
	public boolean enableMockGetPolicyAccountSummariesByAccounts();

	@Meta.AD(
		deflt = "true", description = "Enable mock for Policy Service method /v1/transactions/on-policy",
		required = false
	)
	public boolean enableMockGetTransactionsOnPolicy();

	@Meta.AD(deflt = "true", description = "Enable mock for Policy Service method /v1/policies", required = false)
	public boolean enableMockGetPoliciesByPolicyNumbers();

	@Meta.AD(
		deflt = "true", description = "Enable mock for Policy Service method /v1/policies/on-account", required = false
	)
	public boolean enableMockGetPoliciesOnAccount();

}