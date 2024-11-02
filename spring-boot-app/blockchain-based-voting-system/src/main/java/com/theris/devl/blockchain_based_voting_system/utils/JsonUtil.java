package com.theris.devl.blockchain_based_voting_system.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

	private static final ObjectMapper objectMapper;

	// Static block for initialization
	static {
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}

	private JsonUtil() {

	}

	public static void writeToJsonFile(List<?> data, String jsonPath) throws IOException {
		LOGGER.debug("Execute writeJson() in JsonUtil");
		FileWriter fileWriter = new FileWriter(jsonPath);
		objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, data);
		fileWriter.close();
		LOGGER.debug("Exit writeJson() in JsonUtil");
	}

	public static <T> List<T> readJson(String jsonPath, Class<T> clazz) throws IOException {
		LOGGER.debug("Execute readJson() in JsonUtil");
		List<T> data = objectMapper.readValue(new File(jsonPath),
				objectMapper.getTypeFactory().constructCollectionType(List.class, clazz)); // Load blockchain from JSON
																							// file
		LOGGER.debug("Exit readJson() in JsonUtil");
		return data;
	}
}
