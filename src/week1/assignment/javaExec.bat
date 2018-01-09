@echo off
set PRJ_DIR=D:\user\Stoyan\Projects\eclipse-workspace\[coursera]Algorithms_I\
set CLASS_DIR=%PRJ_DIR%\bin
set LIB_DIR=%PRJ_DIR%\lib
set CLASSPATH=%CLASS_DIR%;%LIB_DIR%\algs4.jar
set ASSIGNMENT=week1.assignment.PercolationStats

java -cp %CLASSPATH% %ASSIGNMENT% %*