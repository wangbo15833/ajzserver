@echo off
SETLOCAL ENABLEDELAYEDEXPANSION
for /f "delims=" %%a in ('dir /b^|findstr "Signrule"') do (
set name=%%a
set name=!name:Signrule=Inbox!
ren "%%a" "!name!"
)
pause