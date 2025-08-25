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