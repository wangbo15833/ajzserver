@echo off
SETLOCAL ENABLEDELAYEDEXPANSION
for /f "delims=" %%a in ('dir /b^|findstr "Tongz"') do (
set name=%%a
set name=!name:Tongz=Txinfo!
ren "%%a" "!name!"
)
pause