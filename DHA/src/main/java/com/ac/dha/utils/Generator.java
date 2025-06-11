package com.ac.dha.utils;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Generator {
	private static final Random RANDOM = new Random();

	public String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public String currentTimeStamp() {
		return DateTimeFormatter.ISO_INSTANT.format(Instant.now().truncatedTo(ChronoUnit.MILLIS));
	}

	public int generateERxReferenceNo() {
		return 10000000 + RANDOM.nextInt(90000000);
	}
}