package com.example.student;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClassServiceClient {
	private final RestTemplate restTemplate;

    public ClassServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getClassName(Integer classId) {
        String url = "http://localhost:8070/api/v1/classes/" + classId;
        ClassDTO classObj = restTemplate.getForObject(url, ClassDTO.class);
        return classObj != null ? classObj.getName() : "Unknown";
    }
}
