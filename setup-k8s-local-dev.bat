@echo off
setlocal enabledelayedexpansion

:: ========= CONFIGURATION =========
:: Format: service_name:local_port:service_port
set "SERVICES=dept-webapp-service:8101:3001 webapp-service:8100:3000"

:: ========= BEGIN SCRIPT =========
set "HOSTS_FILE=%SystemRoot%\System32\drivers\etc\hosts"
echo Hosts file: %HOSTS_FILE%
echo.

for %%S in (%SERVICES%) do (
    call :parseServiceLine %%S
)
goto :eof

:parseServiceLine
setlocal
set "line=%1"
for /f "tokens=1,2,3 delims=:" %%A in ("%line%") do (
    endlocal & set "SERVICE_NAME=%%A" & set "LOCAL_PORT=%%B" & set "SERVICE_PORT=%%C"
)

:: Restart delayed expansion in parent scope
setlocal enabledelayedexpansion

echo Starting port-forward for !SERVICE_NAME! (local:!LOCAL_PORT! -> svc:!SERVICE_PORT!)...

:: Launch port-forward in separate window
start "Port Forward - !SERVICE_NAME!" cmd /k "kubectl port-forward svc/!SERVICE_NAME! !LOCAL_PORT!:!SERVICE_PORT!"

:: Small wait to stabilize forwarding
timeout /t 2 /nobreak > NUL

:: Check / add hosts entry
findstr /C:"!SERVICE_NAME!" "%HOSTS_FILE%" >nul
if !errorlevel! equ 0 (
    echo Hosts entry already exists for !SERVICE_NAME!
) else (
    echo Adding hosts entry for !SERVICE_NAME! -> 127.0.0.1
    >> "%HOSTS_FILE%" echo 127.0.0.1 !SERVICE_NAME!
)
echo.
exit /b
