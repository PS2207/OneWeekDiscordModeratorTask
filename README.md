# Video Streaming App:-
Video streaming means playing a video directly over the internet without downloading the entire file first.
Instead of saving the whole video to your device, small chunks of data are sent continuously from the server to users browser/app.
Your player (YouTube, Netflix, VLC, etc.) decodes these chunks in real time so you can start watching immediately.

# ⚙️ How It Works (Simple Flow)
A video file is stored on a server.
When you click play, the video is split into small packets/chunks.
These chunks are sent to your device over the internet.
The video player buffers and plays the chunks in sequence.

# 👉 Example:
When you watch a YouTube video, it doesn’t download the full 1GB file to your laptop. Instead, it streams small parts (2–10 seconds of video at a time) so playback is smooth.

1️⃣ The video must exist in full on the server
Imagine 1000 users watching at different times (some start at 0:00, some at 10:00).
The server needs the whole video stored so it can serve any part instantly.
Without the full file, you couldn’t skip forward, rewind, or choose a resolution.

2️⃣ But streaming doesn’t send the whole video at once
Sending the entire file would be the same as downloading → huge delay, lots of wasted bandwidth if you don’t watch till the end.
Instead, the server sends small chunks (like 2–10 seconds of video).
This allows:
Quick start (play in 1–2 seconds, not after 1 GB download).
Adaptive streaming (switch between 480p and 1080p depending on your internet speed).
Efficiency (if you leave after 5 mins, the server never wastes data sending the rest).

⚖️ Analogy
Server = Big Library → has the whole book (full video).
Streaming = Reading few pages at a time → you don’t need the whole book copied, just the part you’re reading.
Downloading = Photocopying the entire book → takes time and space, but then you own the copy.

✅ So:
    the whole video must be stored on the server → so users can request any part.
    the whole video is not sent to your device at once → only the needed chunks are streamed.
# ----------------------------------------------------------------------------------------------------------------
# Day 1 – Moderator Task Progress
📌 Project: Spring Boot Video Streaming App

✅ Tasks Completed Today:-
# 1.Project Setup:
Created a new Spring Boot maven project for video streaming using spring initializer added some required dependecies(dev tool, spring web, data jpa, mysql connector, lombok).

# 2.Configured the folder structure (entities, services, repositories, controllers).

# 3.Entity Creation:
Implemented the Video entity with required fields (title, description, path, metadata).

# 4.Service Layer:
Defined VideoService interface.
Implemented initial logic in VideoServiceImpl for saving videos and handling metadata.
Added file storage functionality: saving uploaded videos to a folder on the system.

# 5.Repository Layer:
Created VideoRepository extending JpaRepository for database operations.

# 6.Controller Layer (Initial Setup):
Set up VideoController with endpoints for video upload and retrieval.

# 7.API Testing with Postman:
Successfully tested the video upload API using Postman.
Verified that videos are stored in the folder with metadata saved in the system.
# ******************************************************************************************************

# 🚀 Day 2 Moderator Task Progress (Improvements over Day 1)

# 1.Added Global Exception Handling
Introduced GlobalExceptionHandler for consistent error responses.
Created custom exception: ResourceNotFoundException.
Improved API Response Structure
Replaced CustomMessage with a more flexible ApiResponse.

# 2.CORS Configuration
Added CorsConfig to allow cross-origin requests (for frontend integration).
Controller Enhancements
Expanded VideoController with proper CRUD endpoints: (upload, update, fetch, delete).
Service Implementation
Added real logic inside VideoServiceImpl instead of returning null.

# ******************************************************************************************************
# 🚀 Day 3 Moderator Task Progress (Improvements over Day 2)

# 1.Implemented Range-based streaming API with chunked video delivery.
Why this is important?
Improved efficiency: clients now fetch only needed video parts, not the whole file.
This is how YouTube, Netflix and other streaming apps work.
Browser <video> element now requests video in chunks instead of full file.
Saves bandwidth and loads videos faster.

# 2.Added Static Resource:
Added Static Resource Page (video.html) to test video streaming directly from browser.

# 3.ApiResponse with Builder Pattern

# **********************************************************************************************

# 🚀 Day 4 Moderator Task :-

🤖 CREATING a AI Chat Spring Boot Project:
# 📝 1. Project Overview:

This project is a Spring Boot-based AI chat application that integrates with Hugging Face large language models (LLMs), specifically HuggingFaceH4/zephyr-7b-beta. It allows users to send prompts or conversation messages via a REST API and receive AI-generated responses in real-time.

The main goal of the project is to provide a lightweight, self-contained backend service for AI chat functionalities that can be easily integrated into web, mobile, or desktop applications.

# ⚙️ 2. How It Works (High-Level)

*User sends a request to the Spring Boot API.
*The backend service (AIChatService) formats the request and sends it to the Hugging Face endpoint using the API token.
*Hugging Face LLM processes the input and returns a response.
*Spring Boot API parses the response and sends it back to the client as JSON.
*This architecture ensures real-time AI responses without storing any conversation data on the backend, keeping it lightweight and secure.

# 📂 3. Project Structure
com.ai.chat
 ├── Day4ChatUsingAiSpringBootApplication.java   # Main Spring Boot application
 ├── controller
 │    └── AIController.java   # REST endpoint
 └── service
      └── AIChatService.java  # Calls Hugging Face API

# 📡 4 .API Usage Test in Postman:
Endpoint:
POST http://localhost:8081/api/ai/chat
Headers:
Content-Type: application/json
Request Body (JSON)
{
  "prompt": "Tell me a fun fact about space."
}


#-------------------------------------------------------------

You should now get a real response back:
{
  "model": "HuggingFaceH4/zephyr-7b-beta",
  "messages": [
    {"role": "user", "content": "Hello, how are you?"}
  ]
}

# *************************************************************************************************
Day5 – OpenAI Chat with Spring Boot
📌 Project Overview
This project demonstrates how to integrate OpenAI’s GPT model into a Spring Boot application using the Spring AI
framework.
With this application, you can: 
Send text prompts (questions, instructions, etc.) to OpenAI.
Receive AI-generated responses via REST API.
Securely store your API key in a .env file.

The goal of this project is to show how to build an AI-powered chat backend that can be extended into chatbots, assistants, or intelligent applications.

# ⚙️ Tech Stack
Spring Boot 3.x
Spring AI (OpenAI Starter)
Maven
Java 17+
OpenAI API
Dotenv (for secure key management)

# 🏗️ Project Structure
Day5-OpenAIChat/
│── src/main/java/com/chat/ai/
│   ├── controller/ChatController.java   # REST endpoint
│   ├── service/ChatService.java         # Service to call OpenAI
│── src/main/resources/
│   ├── application.properties           # Config
│── .env                                 # API key (ignored by git)
│── pom.xml                              # Maven dependencies

-------------------------------------------------------------------------
# 🔑 Setup Instructions
1️⃣ Clone the project
git clone https://github.com/your-username/Day5-OpenAIChat.git
cd Day5-OpenAIChat

2️⃣ Add dependencies In pom.xml:

	<dependency>
			<groupId>me.paulschwarz</groupId>
			<artifactId>spring-dotenv</artifactId>
			<version>3.0.0</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.ai</groupId>
			<artifactId>spring-ai-openai-spring-boot-starter</artifactId>
			<version>1.0.0-M6</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>


Run:
mvn clean install

3️⃣ Create .env file
At the root of the project (same level as pom.xml), create .env:
OPENAI_API_KEY=sk-your-secret-key


# ⚠️ Do not commit .env to GitHub. It must remain private.

4️⃣ Configure application.properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-4o-mini

5️⃣ Run the application

In Eclipse/IntelliJ or via command line:
mvn spring-boot:run

# 🚀 Usage
Once running, the REST API is available at:
Endpoint:
GET http://localhost:8081/api/v1/chat?inputText=what is sprinboot?


# Response Example:
spring boot is a Java framework......

# 📚 How It Works
ChatController receives the user’s input via REST.
ChatService wraps the input into a Prompt and sends it to OpenAI using Spring AI.
OpenAI generates a text response.
The response is returned as plain text to the client.

# 🔮 Future Enhancements
Add conversation history (multi-turn chat).
Support streaming responses.
Add frontend (React/Angular) for a chat UI.
Deploy to cloud (Render, AWS, etc.).


# *************************************************************************************
Day 6 – Spring Security with JWT Authentication
# 📌 Project Overview
This project demonstrates how to secure a Spring Boot application using Spring Security and JWT (JSON Web Token). It includes authentication, authorization, and role-based access control, along with sample APIs for User and Patient entities.

# 🚀 Features Implemented Today

✅ Configured Spring Security with JWT-based authentication
✅ Created User and Patient entities with repositories
✅ Implemented User Registration & Login APIs (AuthController)
✅ Added JWT Token Generation & Validation using AuthUtil
✅ Built a JwtAuthFilter for validating tokens in incoming requests
✅ Created PatientController APIs (secured endpoints)
✅ Role-based security setup in WebSecurityConfig

# 📂 Project Structure
src/main/java/com/springsecurity/jwtauth/
│── Day6SpringsecurityJwtApplication.java   # Main class
│── config/AppConfig.java                   # Bean configurations
│── controller/AuthController.java          # Login & Signup APIs
│── controller/PatientController.java       # Patient APIs
│── dto/                                    # DTOs for request/response
│── entity/User.java                        # User entity
│── entity/Patient.java                     # Patient entity
│── repo/UserRepo.java                      # User repository
│── repo/PatientRepo.java                   # Patient repository
│── security/AuthService.java               # Authentication service
│── security/AuthUtil.java                  # JWT utility class
│── security/CustomUserDetailsService.java  # User details for Spring Security
│── security/JwtAuthFilter.java             # JWT request filter
│── security/WebSecurityConfig.java         # Security configuration

# 🔑 API Endpoints
Authentication (/api/auth)
POST /signup → Register new user
POST /login → Authenticate user & return JWT token
Patients (/api/patients) (secured, requires JWT)


# ⚙️ Tech Stack

Java 17+
Spring Boot (Web, Security, JPA)
JWT for authentication
MySQL / H2 (configurable via application.properties)

# ▶️ How to Run
Clone the repository
git clone <repo-url>
cd day6-springsecurity-jwt
Configure database in application.properties
Run the Spring Boot app
mvn spring-boot:run
Test APIs with Postman