@echo off
setlocal ENABLEEXTENSIONS

:: Set variables
set IMAGE_NAME=helloworldtest
set CONTAINER_NAME=helloworld-container
set PORT=8080

echo.
echo === Step 1: Building the Spring Boot app using Gradle ===
call .\gradlew clean build

if %errorlevel% neq 0 (
    echo Gradle build failed!
    pause
    exit /b %errorlevel%
)

echo.
echo === Step 2: Building Docker image ===
docker build -t %IMAGE_NAME% .

if %errorlevel% neq 0 (
    echo Docker image build failed!
    pause
    exit /b %errorlevel%
)

echo.
echo === Step 3: Stopping and removing existing container if it exists ===
docker stop %CONTAINER_NAME% >nul 2>&1
docker rm %CONTAINER_NAME% >nul 2>&1

echo.
echo === Step 4: Running Docker container in foreground (Ctrl+C to stop) ===
docker run --rm --name %CONTAINER_NAME% -p %PORT%:8080 %IMAGE_NAME%

if %errorlevel% neq 0 (
    echo Docker container failed to start!
    pause
    exit /b %errorlevel%
)

echo.
echo ✅ App is running at: http://localhost:%PORT%
echo Press any key to stop the container and exit...
pause >nul

echo.
echo 🔁 Stopping container %CONTAINER_NAME%...
docker stop %CONTAINER_NAME% >nul
docker rm %CONTAINER_NAME% >nul
echo ✅ Container stopped and removed.

endlocal