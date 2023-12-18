# Mars Shop 网上购物商城项目

## 个人信息
- **学号:** 202130441474
- **姓名:** 潘子健
- **班级:** 计科1班

## 项目链接
- **网站首页地址:** [Mars Shop](http://8.134.185.150:8080/MarsShop)
- **网站后台管理地址:** [管理后台](http://8.134.185.150:8080/MarsShop/admin/login)

### 测试账户
- **会员账户：**
  - 账户名：111111（6个1）
  - 密码：11111111（8个1）
- **管理员账户：**
  - 账户名：admin
  - 密码：123456

## 开发环境配置
- **操作系统:** Windows 10
- **数据库:** MySQL 8.0.28
- **JDK:** JDK 8
- **Tomcat:** Tomcat 9
- **IDE:** IntelliJ IDEA 2021.3.2
- **Maven:** 3.6.0

## 部署环境配置
- **操作系统:** Ubuntu 20.04
- **数据库:** MySQL 8.0.35
- **JDK:** JDK 8
- **Tomcat:** Tomcat 9

## 技术选型
### 后端
- Servlet
- Filter
- Listener
- Jdbc
- Jsp
- Jstl
- EL
- commos-fileupload

### 前端
- Bootstrap
- Jquery
- Ajax

## 功能模块
### 1. 后台管理模块
- 管理员登录和注销
- 商品类别管理
- 具体商品管理
- 会员信息管理
- 订单汇总管理
- 邮件功能管理

### 2. 会员信息管理模块
- 会员注册和登录
- 会员购物车
- 会员结算
- 会员订单

### 3. 前台商品展示模块
- 商品列表
- 商品详情

## 项目目录结构
```plaintext
.
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── marsshop
    │   │           ├── controller
    │   │           ├── dao
    │   │           │   └── impl
    │   │           ├── domain
    │   │           ├── filter
    │   │           ├── listener
    │   │           ├── service
    │   │           │   └── Impl
    │   │           └── util
    │   └── webapp
    │       ├── account
    │       ├── admin
    │       │   └── comms
    │       ├── comms
    │       ├── resources
    │       │   ├── bootstrap
    │       │   │   ├── css
    │       │   │   ├── fonts
    │       │   │   └── js
    │       │   ├── images
    │       │   │   └── shop1
    │       │   ├── jQuery-Knob
    │       │   │   └── js
    │       │   └── styles
    │       │       ├── admin
    │       │       └── common
    │       └── upload
    └── test
        └── java
            └── com
                └── marsshop
                    └── test
```