@ECHO off

call %~dp0control-derby-server.cmd shutdown -p 51527

@ECHO ON