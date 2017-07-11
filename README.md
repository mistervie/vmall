# vmall
分布式在线云商平台
开发环境：Eclipse, Maven, Tomcat7
软件架构：Spring，SpringMVC, Mybatis,MySQL, Redis, Solr, HTTPClient 
项目描述：在线云商是一个综合性的B2C平台，类似京东商城、天猫商城。会员可以在商城浏览商品、下订单，以及参加各种活动。商城采用分布式系统架构，子系统之间通过调用服务来实现系统之间的通信，使用HTTP协议传递JSON数据方式实现。使用Redis做系统缓存提高系统性能，并使用Redis实现Session共享。为了保证redis的性能使用redis的集群。搜索功能使用solr做搜索引擎。系统主要包括后台管理系统、前台系统、会员系统、订单系统、搜索系统以及单点登录系统等模块。
