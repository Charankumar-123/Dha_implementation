package com.ac.dha.utils;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class EclaimHttpResponse {

	private final Map<String, String> eclaimHttpResponse = Map.ofEntries(
			Map.entry("login", "user login name to connect to the DHPO\\eRx Hub"),
			Map.entry("3", "Member has no approved trade drugs, hence Prescription transaction is not returned."),
			Map.entry("2", "No new prior authorization transactions are available for download."),
			Map.entry("1", "E-claim transaction validation succeeded with warnings."),
			Map.entry("0", "Operation is successful."), Map.entry("-1", "Login failed for the user."),
			Map.entry("-2", "E-claim transaction validation is failed with errors."),
			Map.entry("-3",
					"Required input parameter for the web service is empty, or null, or contains invalid value."),
			Map.entry("-4", "Unexpected error occurred."),
			Map.entry("-5", "If difference between date from and date to parameters is longer than 100 days."),
			Map.entry("-6", "The specified file is not found."), Map.entry("-7", "Transaction is not supported."),
			Map.entry("-10", "If no search criteria is found."));

	public String getDescription(String key) {
		return eclaimHttpResponse.getOrDefault(key, "Unknown code");
	}
}
