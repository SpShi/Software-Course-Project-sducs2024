## 如何部署环境

1.下载idea,并尝试运行一个helloworld程序

2.下载mysql 5.X版本

3.下载并运行navicat等数据库管理工具，导入movie文件夹下的moviedb.sql

4.下载tomcat（只下载压缩包并解压，不配置环境），将tomcat导入idea

![ZCO86V`QDEZW_FJCQK28J`2](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/22660309-836c-45f1-adbf-18f73e5d0f6c)

![Y}0Z@X C3FFBIHHYX8TT~UX](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/0718fd9f-d8b0-47fd-ad11-12058e930dff)

![FS@D`2_V_0 O$@EW 7NR@IS](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/b7042ab6-3b8c-4e29-9fd4-e5b289e47b0f)

![XL{~ W{O G{JYH}J V{BOP5](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/ce83b5a1-01c7-431e-a845-2e802a6b195a)

![{X~1$Z WE(}9{B 46I%@EIR](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/7d414ccd-410f-4819-b94f-f0d8e767da9f)

![Y1} )M){9R%64NL%B_Q) %1](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/e579954d-6ac5-4875-8d19-03e8ea104062)

![YMNUPRZ`}MBYW5L H B$TY](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/cc02f7cb-746b-475b-92ab-90e96d2f2f70)

![U_(HOZ2XXMAS( VZD %7Q`L](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/133e5839-f291-41db-bbf0-43928e9a324e)

![`4L`3`S_1 M~02CZ~ZA}9I3](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/f3f6877c-c330-4f1e-92ab-0309ca8f963f)

![9PFCR`5X23I3ZEA) %NGVH4](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/e043857d-54b2-439b-a2cb-3e9df0d1d44c)



（看qq群精华消息）

5.尝试movie/src/com/fate/movie/util/DBHelper的main函数的运行

6.尝试运行tomcat，如果出现404，修改movie.iml中tomcat的版本为正确版本，具体可以看下载后导入idea后显示的版本。

![APW7~76I%XKONS0ZN%A(M}7](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/e8224189-197f-4d3b-88a4-d717fc2c0c66)

如果出现了错误,请仔细观察server的output中的 "严重","错误"等关键字.

***
# 代码分析
## lib代码简单介绍:
jar包的库

## src代码简单介绍:

- action:表单的处理

- bean:具体的类的定义

- biz:类的函数的实现

- dao:与数据库的交互

- filter:过滤一些不合法的访问

- util:support

name|effect|ps.
--|--|--
DateHelper|日期处理|日期格式的具体规定
DBHelper|基础测试|可以运行一下主函数测试是否存在错误

- c3p0:与数据库链接的具体参数

## web代码简单介绍:

看名字猜一下就行:smile: *~~(绝对不是因为太多了我不想写)~~*

***

# Work

##### 未带*的为选做.

1.初步了解程序,比如:可以尝试更改所有的关键词'movie'->'elite' (尝试做一下,不要上传)
