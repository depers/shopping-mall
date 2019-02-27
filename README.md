# shopping-mall V1.0
基于SSM框架开发的电商系统，是一个纯后端的项目。这个是我系统学习SSM框架开发的第一个项目。从数据库设计、接口代码编写到测试部署，使我有能力独立开发一个简单的系统。在这里感谢慕课网的Geely老师！

## 项目简介
shopping-mall是一个纯后端电商系统，系统主要包括用户管理模块、分类管理模块、商品模块、购物车模块、收货地址模块、支付模块和订单模块等。为实现前后端分离，该系统只提供前端使用的json数据。
## 接口测试文档
详情请参见本项目[Wiki](https://github.com/depers/shopping-mall/wiki)
## 架构图
![架构图](/document/resource/one.png)

## 技术选型
|技术|说明|官网|
|--|--|--|
|SpringMVC|MVC框架|[https://spring.io/projects/spring-framework](https://spring.io/projects/spring-framework)|
|MyBatis|ORM框架|[http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)|
|MyBatisGenerator|数据层代码生成|[http://www.mybatis.org/generator/index.html](http://www.mybatis.org/generator/index.html)|
|PageHelper|MyBatis物理分页插件|[http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper)|
|Guava|提供本地缓存实现，支持多种缓存过期策略|[https://github.com/google/guava](https://github.com/google/guava)|
|Jackson|Java对象与json对象互转|[https://github.com/FasterXML/jackson](https://github.com/FasterXML/jackson)|
|Hashids|id加密解密|[https://hashids.org/](https://hashids.org/)|
|vsftp|ftp服务器软件|https://security.appspot.com/vsftpd.html|
## 环境搭建

### 开发工具

工具 | 说明 | 官网
----|----|----
IDEA | 开发IDE | https://www.jetbrains.com/idea/download
SwitchHosts| 本地host管理 | https://oldj.github.io/SwitchHosts/
MobaXterm | Linux远程连接工具 | https://mobaxterm.mobatek.net/
Navicat | 数据库连接工具 | http://www.formysql.com/xiazai.html
MindMaster | 思维导图设计工具 | http://www.edrawsoft.cn/mindmaster
ProcessOn | 流程图绘制工具 | https://www.processon.com/


### 开发环境

工具 | 版本号 | 下载
----|----|----
JDK | 1.8 | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
Mysql | 5.6 | https://www.mysql.com/
Nginx | 1.10 | http://nginx.org/en/download.html

## 感谢
[从0开始 独立完成企业级Java电商网站开发（服务端）](https://coding.imooc.com/class/96.html)