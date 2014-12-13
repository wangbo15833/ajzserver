@echo off
SETLOCAL ENABLEDELAYEDEXPANSION
for /f "delims=" %%a in ('dir /b^|findstr "Jdjh"') do (
set name=%%a
set name=!name:Jdjh=Jcjh!
ren "%%a" "!name!"
)
pause