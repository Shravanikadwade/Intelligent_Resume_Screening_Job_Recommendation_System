package com.aiResumeApplication.ai_resume_system.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SkillExtractionService {

    // Predefined skills
    private static final List<String> PREDEFINED_SKILLS = Arrays.asList(

            // Programming Languages
            "Java",
            "Python",
            "C",
            "C++",
            "JavaScript",
            "TypeScript",

            // Frontend
            "HTML",
            "CSS",
            "React",
            "Angular",
            "Vue",
            "Bootstrap",
            "Tailwind CSS",

            // Backend
            "Spring Boot",
            "Hibernate",
            "JPA",
            "Node.js",
            "Express.js",

            // Database
            "MySQL",
            "SQL",
            "MongoDB",
            "Oracle",

            // Tools
            "Git",
            "GitHub",
            "Docker",
            "Kubernetes",
            "Postman",

            // Cloud
            "AWS",
            "Azure",
            "GCP",

            // APIs
            "REST API",

            // Concepts
            "Microservices",
            "OOP",
            "DSA",
            "JWT"
    );

    // Skill aliases
    private static final Map<String, String> SKILL_ALIASES = Map.ofEntries(

            Map.entry("springboot", "Spring Boot"),
            Map.entry("spring boot", "Spring Boot"),

            Map.entry("reactjs", "React"),
            Map.entry("react.js", "React"),

            Map.entry("nodejs", "Node.js"),

            Map.entry("js", "JavaScript"),

            Map.entry("mysql database", "MySQL"),

            Map.entry("restful api", "REST API")
    );

    // Normalize text
    private String normalizeText(String text) {

        String normalized = text.toLowerCase();

        for (Map.Entry<String, String> entry : SKILL_ALIASES.entrySet()) {

            normalized = normalized.replace(
                    entry.getKey().toLowerCase(),
                    entry.getValue().toLowerCase()
            );
        }

        return normalized;
    }

    // Extract Skills
    public String extractSkills(String resumeText) {

        String normalizedText = normalizeText(resumeText);

        Set<String> extractedSkills = new LinkedHashSet<>();

        for (String skill : PREDEFINED_SKILLS) {

            Pattern pattern = Pattern.compile(
                    "\\b" + Pattern.quote(skill.toLowerCase()) + "\\b",
                    Pattern.CASE_INSENSITIVE
            );

            Matcher matcher = pattern.matcher(normalizedText);

            if (matcher.find()) {
                extractedSkills.add(skill);
            }
        }

        return String.join(", ", extractedSkills);
    }
}