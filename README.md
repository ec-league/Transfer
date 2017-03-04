# Transfer
该项目主要是来源于携程的Hackathon的项目

[![Build Status](https://travis-ci.org/ec-league/Transfer.svg?branch=master)](https://travis-ci.org/ec-league/Transfer)
[![codecov](https://codecov.io/gh/ec-league/Transfer/branch/master/graph/badge.svg)](https://codecov.io/gh/ec-league/Transfer)

## Context

提升.NET转Java的研发效率

本项目主要是为了将C#代码自动转成对应的Java代码

主要包括以下几个方面

1. `var`关键字的处理，Java中不支持`var`关键字的，需要自动识别对应的新建对象的类型
2. 静态类的处理，将其自动转成`Spring Bean`
3. 对应`out`,`ref`,`this`等关键字的处理

## Solution

项目包括几个方面：

* `*.csproj`文件的解析，C#和Java的不同在于，在对类的加载上，需要解析`*.csproj`文件来解析对应的`*.cs`文件
* 目前C#代码中的任务并非通过`main()`函数启动，而是通过容器启动，所需需要对于`*.csproj`文件中的项目依赖进行解析
* 对于静态类，可以自动生成`Spring Bean`，自动抽象接口
