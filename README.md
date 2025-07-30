# MZS_SpringAI

MZS_SpringAI is a RESTful web application built using **Spring Boot** that integrates **OpenAI's GPT models** through **Spring AI**. This project showcases how to leverage generative AI capabilities in a clean, scalable, and enterprise-grade Java application.

## 🔍 Features

- Integrates with OpenAI's `gpt-3.5-turbo` model
- Utilizes Spring AI's abstraction for cleaner interaction with LLMs
- RESTful architecture
- Supports API key management via environment variables
- Lightweight and easy to deploy

## 🚀 Technologies Used

- Java 21
- Spring Boot 3.5.4
- Spring AI 1.0.0
- Maven
- OpenAI API
- Thymeleaf (for templating)
- H2 Database (for local persistence)

## ⚙️ Prerequisites

- JDK 21
- Maven 3.6+
- OpenAI API Key

## 🔧 Configuration

Set your OpenAI API key using environment variables (preferred for security):

```bash
export OPENAI_API_KEY=your_api_key_here
spring.application.name=MZS_SpringAI
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-3.5-turbo
