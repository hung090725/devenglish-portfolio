@echo off
setlocal enabledelayedexpansion

echo ==============================================
echo        SOLO QA GATE - ENGLISHPATH AI
echo ==============================================

set REPORT_DIR=build\reports\solo_qa_gate
if not exist "%REPORT_DIR%" mkdir "%REPORT_DIR%"
set REPORT_FILE=%REPORT_DIR%\solo_qa_gate_report.txt

echo Solo QA Gate Report > "%REPORT_FILE%"
echo Timestamp: %date% %time% >> "%REPORT_FILE%"
echo ============================================== >> "%REPORT_FILE%"

echo.
echo [1/7] Running: clean...
call .\gradlew.bat :app:clean
if %ERRORLEVEL% neq 0 goto :FAIL

echo [2/7] Running: assembleDebug...
call .\gradlew.bat :app:assembleDebug
if %ERRORLEVEL% neq 0 goto :FAIL
echo - assembleDebug: PASS >> "%REPORT_FILE%"

echo [3/7] Running: testDebugUnitTest...
call .\gradlew.bat :app:testDebugUnitTest
if %ERRORLEVEL% neq 0 goto :FAIL
echo - testDebugUnitTest: PASS >> "%REPORT_FILE%"

echo [4/7] Running: lintDebug...
call .\gradlew.bat :app:lintDebug
if %ERRORLEVEL% neq 0 goto :FAIL
echo - lintDebug: PASS >> "%REPORT_FILE%"

echo [5/7] Running: compileDebugAndroidTestJavaWithJavac...
call .\gradlew.bat :app:compileDebugAndroidTestJavaWithJavac
if %ERRORLEVEL% neq 0 goto :FAIL
echo - compileDebugAndroidTestJavaWithJavac: PASS >> "%REPORT_FILE%"

echo [6/7] Running: assembleRelease...
call .\gradlew.bat :app:assembleRelease
if %ERRORLEVEL% neq 0 goto :FAIL
echo - assembleRelease: PASS >> "%REPORT_FILE%"

echo [7/7] Running: bundleRelease...
call .\gradlew.bat :app:bundleRelease
if %ERRORLEVEL% neq 0 goto :FAIL
echo - bundleRelease: PASS >> "%REPORT_FILE%"

echo.
echo ==============================================
echo                  SUCCESS
echo ==============================================
echo All 7 steps passed successfully!
echo Status: SUCCESS >> "%REPORT_FILE%"

echo. >> "%REPORT_FILE%"
echo Artifact Paths: >> "%REPORT_FILE%"
echo - Debug APK: app\build\outputs\apk\debug\app-debug.apk >> "%REPORT_FILE%"
echo - Release APK: app\build\outputs\apk\release\app-release.apk >> "%REPORT_FILE%"
echo - Release AAB: app\build\outputs\bundle\release\app-release.aab >> "%REPORT_FILE%"

echo. >> "%REPORT_FILE%"
echo Next Manual Test Steps: >> "%REPORT_FILE%"
echo 1. Install app-release.apk on a real device. >> "%REPORT_FILE%"
echo 2. Open Solo QA Dashboard to run Runtime Self-tests. >> "%REPORT_FILE%"
echo 3. Test Microphone and TTS features manually. >> "%REPORT_FILE%"
echo 4. Check Backup/Restore JSON export validity. >> "%REPORT_FILE%"

echo Report saved to %REPORT_FILE%
goto :EOF

:FAIL
echo.
echo ==============================================
echo                   FAIL
echo ==============================================
echo A step failed. Stopping process.
echo Status: FAIL >> "%REPORT_FILE%"
echo Report saved to %REPORT_FILE%
exit /b 1
