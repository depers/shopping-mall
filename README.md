# shopping-mall
基于SSM框架开发的电商系统，是一个纯后端的项目。他是继[shopping-mall v1.0](https://github.com/depers/shopping-mall/tree/v1.0)之后的第二个版本——基于Tomcat集群与Redis分布式的电商系统，对v1.0版本进行了架构演进。

## 项目简介
shopping-mall是一个纯后端电商系统，基于SSM+Tomcat集群+Redis分布式实现。系统主要包括用户管理模块、分类管理模块、商品模块、购物车模块、收货地址模块、支付模块和订单模块等。为实现前后端分离，该系统只提供前端使用的json数据。
## 接口测试文档
项目演示地址：[http://mall.bravedawn.cn/](http://mall.bravedawn.cn/)    
详情请参见本项目[Wiki](https://github.com/depers/shopping-mall/wiki)
## 架构图
![架构图](/document/resource/one.png)
## 关键技术点
* 集群环境下session共享问题

* 定时任务分布式调度问题

* 本地guava cache迁移问题

## 技术选型
|技术|说明|官网|
|--|--|--|
|SpringMVC|MVC框架|[https://spring.io/projects/spring-framework](https://spring.io/projects/spring-framework)|
|Spring Session|用于管理用户会话信息|[https://spring.io/projects/spring-session](https://spring.io/projects/spring-session)|
|MyBatis|ORM框架|[http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html)|
|MyBatisGenerator|数据层代码生成|[http://www.mybatis.org/generator/index.html](http://www.mybatis.org/generator/index.html)|
|PageHelper|MyBatis物理分页插件|[http://git.oschina.net/free/Mybatis_PageHelper](http://git.oschina.net/free/Mybatis_PageHelper)|
|Redis|分布式缓存|[https://redis.io/](https://redis.io/)|
|Redisson|提供分布式锁|[https://redisson.org/](https://redisson.org/)|
|Guava|提供本地缓存实现，支持多种缓存过期策略|[https://github.com/google/guava](https://github.com/google/guava)|
|Jackson|Java对象与json对象互转|[https://github.com/FasterXML/jackson](https://github.com/FasterXML/jackson)|
|Logback|简化对象封装工具|[https://github.com/rzwitserloot/lombok](https://github.com/rzwitserloot/lombok)|
|Hashids|id加密解密|[https://hashids.org/](https://hashids.org/)|
|vsftp|ftp服务器软件|https://security.appspot.com/vsftpd.html|
## 环境搭建

### 开发工具

工具 | 说明 | 官网
----|----|----
IDEA | 开发IDE | https://www.jetbrains.com/idea/download
RedisDesktop | redis客户端连接工具 | https://redisdesktop.com/download
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
Redis | 3.2 | https://redis.io/download
Tomcat|8.5| http://tomcat.apache.org/
Nginx | 1.10 | http://nginx.org/en/download.html

## 感谢
[Java企业级电商项目架构演进之路Tomcat集群与Redis分布式](https://coding.imooc.com/class/162.html?mc_marking=4655172e1a62839eea65105dbf244230&mc_channel=sjkctjpc)