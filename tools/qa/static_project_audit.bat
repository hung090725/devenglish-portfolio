@echo off
setlocal enabledelayedexpansion
echo Running Static Project Audit...
set REPORT_DIR=build\reports\static_project_audit
if not exist %REPORT_DIR% mkdir %REPORT_DIR%
set REPORT_FILE=%REPORT_DIR%\static_project_audit_report.txt
echo REPORT > %REPORT_FILE%

set FAIL_COUNT=0

:: Check for .jks files
dir /b /s *.jks >nul 2>&1
if %errorlevel% equ 0 (
    echo FAIL: Found .jks file in repository. >> %REPORT_FILE%
    echo FAIL: Found .jks file in repository.
    set /a FAIL_COUNT+=1
)

:: Check for signing.properties
if exist "signing.properties" (
    echo FAIL: Found signing.properties in repository. >> %REPORT_FILE%
    echo FAIL: Found signing.properties in repository.
    set /a FAIL_COUNT+=1
)

if %FAIL_COUNT% equ 0 (
    echo PASS: No failures. >> %REPORT_FILE%
    echo PASS: No failures.
    exit /b 0
) else (
    echo FAIL: Static Project Audit Failed with %FAIL_COUNT% errors.
    exit /b 1
)
