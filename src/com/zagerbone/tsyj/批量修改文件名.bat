@echo off
SETLOCAL ENABLEDELAYEDEXPANSION
for /f "delims=" %%a in ('dir /b^|findstr "Signrule"') do (
set name=%%a
set name=!name:Signrule=Tsyj!
ren "%%a" "!name!"
)
pause