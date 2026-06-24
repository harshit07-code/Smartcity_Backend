package com.cityComplaint.demo.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.cityComplaint.demo.Entity.Department;
import com.cityComplaint.demo.dto.GeminiResponse;
import com.cityComplaint.demo.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

@Service
public class GeminiService {
	@Value("${GROQ_API_KEY}")
	private String groqApiKey;


	@Autowired
	private DepartmentRepository departmentRepository;

	// It is a helper method of the below method it will extract only
	// summarizedDescription and predictedDepartments
	// from the Large JSON response of Gemini API


	// Below method will return the summarizedDescription(AI generated) of your
	// originalDescription

	public GeminiResponse askGemini(String complaintText) throws Exception {


		List<Department> departments = departmentRepository.findAll();

		String departmentNames = departments.stream()

				.map(Department::getDeptName)

				.collect(Collectors.joining(", "));

//		String prompt = """
//				Analyze this complaint.
//
//				Available Departments:
//				%s
//
//				Complaint:
//				%s
//
//				Return ONLY valid JSON:
//
//				{
//				  "summary":"",
//				  "department":""
//				}
//				""".formatted(
//
//				departmentNames,
//
//				complaintText);

		String prompt = """
Analyze this complaint.

Available Departments:
%s

Complaint:
%s

Rules:
1. Summary must be maximum 10-15 words.
2. Summary must be one sentence only.
3. Do not explain details.
4. Select only one department from the available departments.
5. Return ONLY valid JSON.
6.Return department ONLY as one of these exact codes: ROAD, WATER, GARBAGE, ELECTRICITY, MEDICAL, GENERAL.

Example:
				  {
				    "summary":"Large pothole causing accidents near Whitefield signal",
				    "department":"ROAD"
				  }

""".formatted(departmentNames, complaintText);
		RestTemplate restTemplate = new RestTemplate();

//		HttpHeaders headers = new HttpHeaders();
//
//		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);

		headers.setBearerAuth(groqApiKey);

//		Map<String, Object> body = Map.of(
//				"model", "phi3:mini",
//				"prompt", prompt,
//				"stream", false
//		);

		Map<String, Object> body = Map.of(
				"model", "llama-3.1-8b-instant",
				"messages", List.of(
						Map.of(
								"role", "user",
								"content", prompt
						)
				),
				"temperature", 0.2
		);


		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

//		String url = "http://localhost:11434/api/generate";
		String url = "https://api.groq.com/openai/v1/chat/completions";
		String fullResponse = "";

		try {
			ResponseEntity<String> response = restTemplate.exchange(

					url,

					HttpMethod.POST,

					entity,

					String.class);

			fullResponse = response.getBody();
			System.out.println("Gemini Raw Response:");
			System.out.println("Original Complaint = " + complaintText);

			System.out.println("Gemini Response = ");

			System.out.println(fullResponse);
		} catch (HttpClientErrorException e) {

			System.out.println("Gemini Error: " + e.getResponseBodyAsString());


			GeminiResponse errorResponse = new GeminiResponse();
			errorResponse.setSummary("Unable to process complaint");
			errorResponse.setDepartment("GENERAL");
			return errorResponse;
		}


		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(fullResponse);

//		String aiText = root.get("response").asText();
		String aiText =
				root.get("choices")
						.get(0)
						.get("message")
						.get("content")
						.asText();

		System.out.println("AI TEXT = ");
		System.out.println(aiText);

// Remove markdown if present
		aiText = aiText.replace("```json", "")
				.replace("```", "")
				.trim();

		System.out.println("CLEANED JSON = ");
		System.out.println(aiText);

		GeminiResponse geminiResponse =
				mapper.readValue(aiText, GeminiResponse.class);

// 🔥 NORMALIZE department code here
		String dept = geminiResponse.getDepartment();

		if (dept != null) {
			dept = dept.toUpperCase()
					.replace(" DEPARTMENT", "")
					.replace(" ", "");
			geminiResponse.setDepartment(dept);
		}

		return geminiResponse;
	}
}
