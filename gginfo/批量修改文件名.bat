@echo off
SETLOCAL ENABLEDELAYEDEXPANSION
for /f "delims=" %%a in ('dir /b^|findstr "tongz"') do (
set name=%%a
set name=!name:tongz=gginfo!
ren "%%a" "!name!"
)
pause