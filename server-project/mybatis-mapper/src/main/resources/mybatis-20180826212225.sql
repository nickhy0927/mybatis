-- MySQL dump 10.13  Distrib 5.5.53, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: mybatis
-- ------------------------------------------------------
-- Server version	5.5.53

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_base_data`
--

DROP TABLE IF EXISTS `sys_base_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_base_data` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `code` varchar(200) DEFAULT NULL COMMENT '字典编号 ',
  `name` varchar(200) DEFAULT NULL COMMENT '字典名称 ',
  `val` varchar(200) DEFAULT NULL COMMENT '字典值 ',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息备注 ',
  `enable` int(8) DEFAULT NULL COMMENT '是否启用 1 启用  0 停用 ',
  `sys_params_id` varchar(200) DEFAULT NULL COMMENT '系统参数ID',
  `base_data_id` varchar(200) DEFAULT NULL,
  `sort` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_base_data`
--

LOCK TABLES `sys_base_data` WRITE;
/*!40000 ALTER TABLE `sys_base_data` DISABLE KEYS */;
INSERT INTO `sys_base_data` VALUES ('0a508667c749419c9ce082ee9925629a','2018-08-25 00:00:00','2018-08-25 13:35:51',1,'53607281','星期四','Thursday','星期四',1,NULL,'1e7706d3c8db4aa2ad2ce0b07fa30b78',3),('1e7706d3c8db4aa2ad2ce0b07fa30b78','2018-08-24 00:00:00',NULL,1,'01863924','星期','WEEK','有效星期',1,NULL,'',NULL),('2166504206d6441bbc4347f0ddea846e','2018-08-25 00:00:00',NULL,1,'70842915','星期日','Sunday','星期日',1,NULL,'1e7706d3c8db4aa2ad2ce0b07fa30b78',7),('64d0ccf97cff471785e86c58b3235076','2018-08-25 00:00:00','2018-08-25 00:00:00',1,'35942671','星期一','Monday','星期一',1,NULL,'1e7706d3c8db4aa2ad2ce0b07fa30b78',1),('7150c1aba5f94164b3688fe8db4c8ebd','2018-08-25 00:00:00',NULL,1,'53607281','星期三','Wednesday','星期三',1,NULL,'1e7706d3c8db4aa2ad2ce0b07fa30b78',3),('972e46fb67984b16b9453cb3ab16efb6','2018-08-25 00:00:00',NULL,1,'08964215','星期五','Friday','星期五',1,NULL,'1e7706d3c8db4aa2ad2ce0b07fa30b78',5),('f61636aec56a41f990433f38e51294c8','2018-08-25 00:00:00','2018-08-25 00:00:00',1,'65421873','星期二','Tuesday','星期二',1,NULL,'1e7706d3c8db4aa2ad2ce0b07fa30b78',2),('fc879a9d6cd04adc80f1629cb551dee5','2018-08-25 03:00:00',NULL,1,'06148723','星期六','Saturday','星期六',1,NULL,'1e7706d3c8db4aa2ad2ce0b07fa30b78',6);
/*!40000 ALTER TABLE `sys_base_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_base_params`
--

DROP TABLE IF EXISTS `sys_base_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_base_params` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `code` varchar(200) DEFAULT NULL COMMENT '参数编号 ',
  `name` varchar(200) DEFAULT NULL COMMENT '参数名称 ',
  `val` varchar(200) DEFAULT NULL COMMENT '参数值 ',
  `enable` int(8) DEFAULT NULL COMMENT '是否启用 1 启用  0 停用 ',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_base_params`
--

LOCK TABLES `sys_base_params` WRITE;
/*!40000 ALTER TABLE `sys_base_params` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_base_params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_exception_log`
--

DROP TABLE IF EXISTS `sys_exception_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_exception_log` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `exception` varchar(4000) DEFAULT NULL COMMENT '异常信息 ',
  `exception_type` int(1) DEFAULT NULL COMMENT '异常信息类型 ',
  `method_name` varchar(1) DEFAULT NULL COMMENT '方法名称',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_exception_log`
--

LOCK TABLES `sys_exception_log` WRITE;
/*!40000 ALTER TABLE `sys_exception_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_exception_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_icon`
--

DROP TABLE IF EXISTS `sys_icon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_icon` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `name` varchar(200) DEFAULT NULL COMMENT '图标名称 ',
  `attr` varchar(200) DEFAULT NULL COMMENT '图标属性 ',
  `enable` int(8) DEFAULT NULL COMMENT '是否启用： 1 启用 0 停用',
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='图标管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_icon`
--

LOCK TABLES `sys_icon` WRITE;
/*!40000 ALTER TABLE `sys_icon` DISABLE KEYS */;
INSERT INTO `sys_icon` VALUES ('4073923e4ef24c1eb930cac74e67dccf','2018-08-22 00:00:00',NULL,1,'资讯管理','<i class=\"Hui-iconfont\">&#xe616;</i>',NULL,NULL),('78142a3d9fd5442e96ad1574c6355f39','2018-08-22 00:00:00',NULL,1,'图片管理','<i class=\"Hui-iconfont\">&#xe613;</i>',1,'图片管理'),('513f090eca7d43468eb12d67366d4b2f','2018-08-22 00:00:00',NULL,1,'产品管理','<i class=\"Hui-iconfont\">&#xe620;</i>',1,'产品管理'),('5232434558b245f1a3dcd9a5de656d94','2018-08-22 00:00:00',NULL,1,'评论管理','<i class=\"Hui-iconfont\">&#xe622;</i>',1,'评论管理'),('42b33a7ac9cb4b55a00c971b0cf2e854','2018-08-22 00:00:00',NULL,1,'会员管理','<i class=\"Hui-iconfont\">&#xe60d;</i>',1,'会员管理'),('18697a8b970348219e521d9a881b025e','2018-08-22 00:00:00',NULL,1,'管理员管理','<i class=\"Hui-iconfont\">&#xe62d;</i>',1,'管理员管理'),('008d9b5a13584dd9a0bc631d418788ee','2018-08-22 00:00:00',NULL,1,'系统统计','<i class=\"Hui-iconfont\">&#xe61a;</i>',1,'系统统计'),('e18f046ada81458db4d018e14995bac0','2018-08-22 00:00:00',NULL,1,'系统管理','<i class=\"Hui-iconfont\">&#xe62e;</i>',1,'系统管理'),('4584af4acc634449a1e16a96d6e7a5f1','2018-08-22 00:00:00',NULL,1,'返回顶部','<i class=\"Hui-iconfont\">&#xe684;</i>',1,'返回顶部'),('3922bf764e1147a68ae99109507a08b5','2018-08-22 00:00:00',NULL,1,'列表','<i class=\"Hui-iconfont\">&#xe667;</i>',1,'列表'),('036936f57b9c4463a82700f6d0f52d4a','2018-08-22 00:00:00',NULL,1,'剪切','<i class=\"Hui-iconfont\">&#xe64e;</i>',1,'剪切'),('e5a6f443d3fc45ca8d07511616c7cb2b','2018-08-22 00:00:00',NULL,1,'搜索2','<i class=\"Hui-iconfont\">&#xe665;</i>',1,'搜索2'),('f5ea276a05d54e22ab046eb859b2a44c','2018-08-22 00:00:00',NULL,1,'保存','<i class=\"Hui-iconfont\">&#xe632;</i>',1,'保存'),('b4179b1f9e5b4cfeb3010dd116a99ade','2018-08-22 00:00:00',NULL,1,'撤销','<i class=\"Hui-iconfont\">&#xe66b;</i>',1,'撤销'),('aec9d27a339b470ebd648d46122d8443','2018-08-22 00:00:00',NULL,1,'下载','<i class=\"Hui-iconfont\">&#xe640;</i>',1,'下载'),('720a11de705b4e9ba1c79308ea17928e','2018-08-22 00:00:00',NULL,1,'添加','<i class=\"Hui-iconfont\">&#xe604;</i>',1,'添加'),('0e5908093ef5457c9132d26e3dc9d18a','2018-08-22 00:00:00',NULL,1,'发布','<i class=\"Hui-iconfont\">&#xe603;</i>',1,'发布'),('90edc8e26601472296b2d792b7f9f17c','2018-08-22 00:00:00',NULL,1,'数据统计','<i class=\"Hui-iconfont\">&#xe61e;</i>',1,'数据统计'),('89bc1076308f4a4ab91e76458ae60883','2018-08-22 00:00:00',NULL,1,'消息','<i class=\"Hui-iconfont\">&#xe68a;</i>',1,'消息');
/*!40000 ALTER TABLE `sys_icon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `code` varchar(200) DEFAULT NULL COMMENT '菜单编号 ',
  `name` varchar(200) DEFAULT NULL COMMENT '菜单名称 ',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单地址 ',
  `alias` varchar(200) DEFAULT NULL COMMENT '菜单别名 ',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '上级菜单 ',
  `enable` int(8) DEFAULT NULL COMMENT '是否启用 1 启用 0 停用 ',
  `shows` int(8) DEFAULT NULL COMMENT '是否显示 1 显示 0 隐藏 ',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息备注',
  `sort` int(8) DEFAULT NULL COMMENT '排序',
  `show_index` int(8) DEFAULT NULL COMMENT '是否首页显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES ('1edbeda9e2db45368c9cd404f18a769e','2018-08-24 00:00:00',NULL,1,'73810465','基础数据删除','/platform/basedata/base-data-delete/*.json','basedata-delete','cf019630f4d94ed7ad7662d765590b50',1,2,'基础数据删除',3,2),('22d94b9912dc4cfdb154f6206bc85993','2018-08-09 00:00:00','2018-08-21 00:00:00',1,'75140623','角色管理','/platform/role/role-list.do','role-mgt','a6c6364757ae4b96ba285ba6eb9d1f7a',1,1,'平台角色管理',2,NULL),('2cb45c1e895a4b86a9e15200591f7dfa','2018-08-09 00:00:00','2018-08-21 00:00:00',1,'08972463','角色删除','/platform/role/role-delete/*.json','role-delete','22d94b9912dc4cfdb154f6206bc85993',1,2,'平台角色删除',22,NULL),('389a8e9abf764287b8de622065c8961b','2018-08-24 00:00:00',NULL,1,'19245073','系统参数','/platform/baseparams/base-params-list.do','base-params-mgt','a6c6364757ae4b96ba285ba6eb9d1f7a',1,1,'系统参数',6,1),('3b202097fa4c4959aec71d5f638cb495','2018-08-25 18:47:48','2018-08-26 16:58:38',1,'41567920','数据库管理','/config/database/database-list.do','database-mgt','4c906cd649184e4093ca4c1810f0fd58',1,1,'数据库管理',1,1),('4343e169ea874dc8a5aaa1d769c05e79','2018-08-08 00:00:00','2018-08-21 00:00:00',1,'41275693','用户删除','/platform/user/user-delete/*.json','user-delete','ad9d9b644adf4940b2dca387962346f5',1,2,'删除用户信息',12,NULL),('4b15236390b240a3897b13cfe9676e74','2018-08-24 00:00:00',NULL,1,'03487561','系统参数删除','/platform/baseparams/base-params-delete/*.json','base-params-delete','389a8e9abf764287b8de622065c8961b',1,2,'系统参数删除',3,2),('4c906cd649184e4093ca4c1810f0fd58','2018-08-07 00:00:00','2018-08-21 00:00:00',1,'35894610','系统管理','#','system-mgt','bede9c2eb189422f83f18087a73eee73',1,1,'所有的菜单信息',2,NULL),('505c56a20ac14891bf85b5a97636f693','2018-08-08 00:00:00','2018-08-21 00:00:00',1,'24965310','菜单管理','/platform/menu/menu-list.do','menu-mgt','a6c6364757ae4b96ba285ba6eb9d1f7a',1,1,'菜单信息管理',3,NULL),('56c11d4fcd8044adb796ef952c35ed05','2018-08-24 00:00:00','2018-08-24 00:00:00',1,'32109845','图标删除','/platform/icon/icon-delete/*.json','icon-delete','92c449a4b7cc44ee8bf5ef4b1637284a',1,2,'图标删除',4,2),('60a9918b459745d4ac5e0df637c71e06','2018-08-08 00:00:00','2018-08-21 00:00:00',1,'68174902','菜单删除','/platform/menu/menu-delete/*.json','menu-delete','505c56a20ac14891bf85b5a97636f693',1,2,'删除菜单信息',32,NULL),('66e4ab5ab20d4f55849634e48c72af42','2018-08-08 00:00:00','2018-08-21 00:00:00',1,'15849267','菜单修改','/platform/menu/menu-edit/*.do','menu-edit','505c56a20ac14891bf85b5a97636f693',1,2,'修改菜单信息',33,NULL),('67cd9f72a73c4a408a1fceec5af6efd2','2018-08-22 00:00:00',NULL,1,'32180647','新闻发布系统','#','news-mgt','',1,1,'新闻发布系统',3,1),('711e490629bf4c888327347991bf9991','2018-08-24 00:00:00',NULL,1,'96732154','基础数据新增','/platform/basedata/base-data-create.do','basedata-create','cf019630f4d94ed7ad7662d765590b50',1,2,'基础数据新增',1,2),('7b499de6928f414381abd53d21044f0a','2018-08-22 00:00:00','2018-08-24 00:00:00',1,'39602785','图标修改','/platform/icon/icon-edit/*.do','icon-edit','92c449a4b7cc44ee8bf5ef4b1637284a',1,2,'图标修改',3,2),('8c2b0b67db9c4ff98e08e15356b11dc3','2018-08-08 00:00:00','2018-08-26 17:20:57',1,'38016579','用户修改','/platform/user/user-edit/*.do','user-edit','ad9d9b644adf4940b2dca387962346f5',1,2,'修改用户信息',13,1),('8e0f18ff098a4293a5f715adbb952a3a','2018-08-24 00:00:00',NULL,1,'43912586','系统参数修改','/platform/baseparams/base-params-edit/*.do','base-params-edit','389a8e9abf764287b8de622065c8961b',1,2,'系统参数修改',2,2),('92c449a4b7cc44ee8bf5ef4b1637284a','2018-08-22 00:00:00','2018-08-22 00:00:00',1,'78049316','图标管理','/platform/icon/icon-list.do','icon-mgt','a6c6364757ae4b96ba285ba6eb9d1f7a',1,1,'图标管理',4,1),('9a43a1d98db14d5fac3e376f95d7d205','2018-08-24 00:00:00','2018-08-24 00:00:00',1,'27459031','基础数据修改','/platform/basedata/base-data-edit/*.do','basedata-edit','cf019630f4d94ed7ad7662d765590b50',1,2,'基础数据修改',2,1),('9d162849de3b4c9b8d3ce4ae9764185d','2018-08-21 00:00:00',NULL,1,'97168324','流程管理系统','#','workflow-mgt','',1,1,'流程管理系统',2,NULL),('a41d801bfef74697906d037d9589c3c8','2018-08-09 00:00:00','2018-08-21 00:00:00',1,'54273810','角色修改','/platform/role/role-edit/*.do','role-edit','22d94b9912dc4cfdb154f6206bc85993',1,2,'平台角色修改',23,NULL),('a6c6364757ae4b96ba285ba6eb9d1f7a','2018-08-07 00:00:00','2018-08-21 00:00:00',1,'30175684','平台管理','#','platform-mgt','bede9c2eb189422f83f18087a73eee73',1,1,'平台管理',1,NULL),('a7c0fefa8bd14f2b97a053b4c5e99abb','2018-08-25 23:17:48','2018-08-26 18:14:07',1,'14732689','异常日志管理','/config/exceptionlog/exception-log-list.do','exception-mgt','4c906cd649184e4093ca4c1810f0fd58',1,1,'异常管理',3,1),('ad9d9b644adf4940b2dca387962346f5','2018-08-08 00:00:00','2018-08-21 00:00:00',1,'86253149','用户管理','/platform/user/user-list.do','user-mgt','a6c6364757ae4b96ba285ba6eb9d1f7a',1,1,'平台用户管理',1,NULL),('b7270eaae90a4aafb6eb5ba3975929b0','2018-08-25 23:37:14','2018-08-26 18:30:18',1,'82406719','操作日志管理','/config/optlog/opt-log-list.do','opt-log-mgt','4c906cd649184e4093ca4c1810f0fd58',1,1,'操作日志',2,1),('b82886fe5e9242f99803c8c6f6f46795','2018-08-07 00:00:00','2018-08-26 16:46:53',1,'74206135','菜单新增','/menu/menu-create.do','menu-create','505c56a20ac14891bf85b5a97636f693',1,1,'新增菜单连接1',31,1),('bede9c2eb189422f83f18087a73eee73','2018-08-21 00:00:00',NULL,1,'46839071','系统管理平台','#','platform-mgt','',1,1,'系统管理平台',1,NULL),('c95c3682a8a7490bad8742950d815f8e','2018-08-09 00:00:00','2018-08-24 00:00:00',1,'49831627','角色新增','/platform/role/role-create.do','role-create','22d94b9912dc4cfdb154f6206bc85993',1,2,'平台角色新增',21,2),('cb02f31709d94bdb89d3940316e73374','2018-08-22 00:00:00','2018-08-24 00:00:00',1,'48123509','图标新增','/platform/icon/icon-create.do','icon-create','92c449a4b7cc44ee8bf5ef4b1637284a',1,2,'图标新增',1,2),('cf019630f4d94ed7ad7662d765590b50','2018-08-24 00:00:00',NULL,1,'71245908','基础数据','/platform/basedata/base-data-list.do','basedata-mgt','a6c6364757ae4b96ba285ba6eb9d1f7a',1,1,'基础数据',5,1),('ea6d3b04f4384236a816f49bf4a30185','2018-08-24 00:00:00',NULL,1,'08671934','系统参数新增','/platform/baseparams/base-params-create.do','base-params-create','389a8e9abf764287b8de622065c8961b',1,2,'系统参数新增',1,2),('f655aa788b3c45929fdfcd147b098d94','2018-08-08 00:00:00','2018-08-24 00:00:00',1,'47180952','用户新增','/platform/user/user-create.do','user-create','ad9d9b644adf4940b2dca387962346f5',1,2,'新增用户',11,2);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_opt_log`
--

DROP TABLE IF EXISTS `sys_opt_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_opt_log` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `username` varchar(200) DEFAULT NULL COMMENT '操作人名字 ',
  `user_id` varchar(200) DEFAULT NULL COMMENT '操作人ID ',
  `message` varchar(500) DEFAULT NULL COMMENT '操作信息 ',
  `method` varchar(500) DEFAULT NULL COMMENT '操作信息 ',
  `clazz` varchar(100) DEFAULT NULL COMMENT '操作类名称 ',
  `opt_type` int(1) DEFAULT NULL COMMENT '操作类型',
  `data` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_opt_log`
--

LOCK TABLES `sys_opt_log` WRITE;
/*!40000 ALTER TABLE `sys_opt_log` DISABLE KEYS */;
INSERT INTO `sys_opt_log` VALUES ('31ad321e0aac47e6afd4191cf8363323','2018-08-26 16:46:53',NULL,1,NULL,NULL,'修改菜单信息','menuupdate','com.mybatis.platform.menu.controller.MenuController.menuupdate',2,NULL),('e9dd6116bcf7476fbdd4d5d60c468a59','2018-08-26 13:39:19',NULL,1,NULL,NULL,'获取菜单列表信息','menuList','com.mybatis.platform.menu.controller.MenuController.menuList',NULL,NULL),('7f1b8f76892b4e9cb7785dc0e8ebe574','2018-08-26 16:49:07',NULL,1,NULL,NULL,'修改菜单信息','menuupdate','com.mybatis.platform.menu.controller.MenuController.menuupdate',2,NULL),('85e8aa2394764a1badd215911fa2df5d','2018-08-26 16:58:38',NULL,1,NULL,NULL,'修改菜单信息','menuupdate','com.mybatis.platform.menu.controller.MenuController.menuupdate',2,NULL),('02bd57682e8e45f49eb9aeb3b1430ad5','2018-08-26 17:19:13',NULL,1,NULL,NULL,'修改菜单信息','menuupdate','com.mybatis.platform.menu.controller.MenuController.menuupdate',2,NULL),('af9c5f5148e84d089510ac67e9bcc47f','2018-08-26 17:20:57',NULL,1,NULL,NULL,'修改菜单信息','menuupdate','com.mybatis.platform.menu.controller.MenuController.menuupdate',2,NULL),('5003946513124f35b2225617ea6d17ce','2018-08-26 18:14:07',NULL,1,NULL,NULL,'修改菜单信息','menuupdate','com.mybatis.platform.menu.controller.MenuController.menuupdate',2,NULL),('580656ffd9764162b1e48d9bc67cbceb','2018-08-26 18:14:27',NULL,1,NULL,NULL,'修改菜单信息','menuupdate','com.mybatis.platform.menu.controller.MenuController.menuupdate',2,NULL),('d7bacfcf80b34e4b91a2aa32c4e00e49','2018-08-26 18:28:52',NULL,1,NULL,NULL,'修改菜单信息','menuupdate','com.mybatis.platform.menu.controller.MenuController.menuupdate',2,NULL),('1a2bb6cf6b804bdbbc45bbf3313ee3fb','2018-08-26 18:30:18',NULL,1,NULL,NULL,'修改菜单信息','menuupdate','com.mybatis.platform.menu.controller.MenuController.menuupdate',2,'{\"showIndex\":\"1\",\"code\":\"82406719\",\"menuName\":\"系统管理\",\"remark\":\"操作日志\",\"sort\":\"2\",\"url\":\"/config/optlog/opt-log-list.do\",\"shows\":\"1\",\"enable\":\"1\",\"name\":\"操作日志管理\",\"alias\":\"opt-log-mgt\",\"menuId\":\"4c906cd649184e4093ca4c1810f0fd58\",\"id\":\"b7270eaae90a4aafb6eb5ba3975929b0\"}'),('3264b3e5eccd4217a5de3acdc2bf273b','2018-08-26 18:41:09',NULL,1,NULL,NULL,'新增用户信息','userSave','com.mybatis.platform.user.controller.UserController.userSave',1,'{\"mobile\":\"15527509784\",\"remark\":\"杨紫是一个比较不错的演员\",\"realName\":\"杨紫\",\"password\":\"q1w2e3\",\"enable\":\"1\",\"loginName\":\"yangzi\",\"position\":\"演员\",\"locked\":\"1\",\"email\":\"yuanhuangd@isoftstone.com\"}'),('af614943fa614543a8a110d22dd6e935','2018-08-26 18:42:11',NULL,1,NULL,NULL,'修改用户信息','userupdate','com.mybatis.platform.user.controller.UserController.userupdate',2,'{\"mobile\":\"13297989280\",\"remark\":\"杨紫是一个比较不错的演员\",\"realName\":\"杨紫\",\"enable\":\"1\",\"id\":\"7f374f76794540bf8bd9227e264e1dd8\",\"position\":\"演员\",\"locked\":\"1\",\"email\":\"h_y_12@163.com\"}'),('47c0d7edac0347da98d1008f1d91804b','2018-08-26 20:43:03',NULL,1,NULL,NULL,'删除用户信息','userDelete','com.mybatis.platform.user.controller.UserController.userDelete',3,NULL),('4a32c0bcda3a46e798459e03b746fbf4','2018-08-26 20:50:46',NULL,1,NULL,NULL,'删除用户信息','userDelete','com.mybatis.platform.user.controller.UserController.userDelete',3,NULL);
/*!40000 ALTER TABLE `sys_opt_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `code` varchar(200) DEFAULT NULL COMMENT '角色编号 ',
  `name` varchar(200) DEFAULT NULL COMMENT '角色名称 ',
  `role_id` varchar(200) DEFAULT NULL COMMENT '上级角色 ',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息备注 ',
  `frozen` int(8) DEFAULT NULL COMMENT '是否冻结 1 冻结 0 未冻结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('5cdb5a08a6e84407bf45e1dc59309c16','2018-08-24 00:00:00','2018-08-24 00:00:00',1,'78049316','系统管理员','1','系统管理员',0),('e0dfd3338a1c4471a62b1840d7ad04af','2018-08-25 00:00:00','2018-08-25 18:30:59',1,'10857624','平台管理员','5cdb5a08a6e84407bf45e1dc59309c16','平台管理员管理平台操作',1);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `role_id` varchar(200) DEFAULT NULL COMMENT '角色ID ',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(8) DEFAULT NULL,
  `real_name` varchar(200) DEFAULT NULL COMMENT '真实姓名 ',
  `login_name` varchar(200) DEFAULT NULL COMMENT '登录账号 ',
  `password` varchar(200) DEFAULT NULL COMMENT '登录密码 ',
  `locked` int(8) DEFAULT NULL COMMENT '是否锁定 1 锁定 0 未锁定 ',
  `enable` int(8) DEFAULT NULL COMMENT '是否启用 1 启用 0 停用 ',
  `email` varchar(200) DEFAULT NULL COMMENT '电子邮箱 ',
  `mobile` varchar(200) DEFAULT NULL COMMENT '电话号码 ',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息备注 ',
  `position` varchar(200) DEFAULT NULL COMMENT '用户职位 ',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('803806fda4a948e4be756775016954c9','2018-08-22 00:00:00',NULL,1,'黄园','yuanhuangd','q1w2e3',0,1,'yuanhuangd@isoftstone.com','15527509784','领导岗位','董事长',NULL),('d2fada212899498eb60f3c192f842e9a','2018-08-24 00:00:00','2018-08-24 00:00:00',1,'赵丽颖','zhaoliying','E10ADC3949BA59ABBE56E057F20F883E',1,1,'yuanhuangd@isoftstone.com','15527509784','是一名出色的演员','演员',NULL),('7f374f76794540bf8bd9227e264e1dd8','2018-08-26 18:41:10','2018-08-26 18:42:11',1,'杨紫','yangzi','7CBB3252BA6B7E9C422FAC5334D22054',1,1,'h_y_12@163.com','13297989280','杨紫是一个比较不错的演员','演员',NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `status` int(8) DEFAULT NULL COMMENT '有效状态1 有效 0 无效 ',
  `user_id` varchar(200) DEFAULT NULL COMMENT '用户ID ',
  `role_id` varchar(200) DEFAULT NULL COMMENT '角色ID',
  `id` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户菜单管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_config_database`
--

DROP TABLE IF EXISTS `t_config_database`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_config_database` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `ip` varchar(200) DEFAULT NULL COMMENT '数据库连接ip ',
  `database_name` varchar(200) DEFAULT NULL COMMENT '数据库名称 ',
  `driver_class_name` varchar(200) DEFAULT NULL COMMENT '数据库驱动名称 ',
  `username` varchar(200) DEFAULT NULL COMMENT '数据库用户名 ',
  `password` varchar(200) DEFAULT NULL COMMENT '数据库密码 ',
  `used` int(11) DEFAULT NULL COMMENT '是否启用 1 启用 2停用 ',
  `mysqldump_path` varchar(200) DEFAULT NULL COMMENT 'mysqldump命令目录 ',
  `params` varchar(200) DEFAULT NULL COMMENT '连接数据库参数 ',
  `database_type` int(8) DEFAULT NULL COMMENT '数据库类型1 mysql 2 oracle ',
  `port` int(8) DEFAULT NULL COMMENT '连接数据库端口',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='数据库管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_config_database`
--

LOCK TABLES `t_config_database` WRITE;
/*!40000 ALTER TABLE `t_config_database` DISABLE KEYS */;
INSERT INTO `t_config_database` VALUES ('041eba444ea0454e828b6ae2387760f2','2018-08-09 00:00:00',NULL,1,'127.0.0.1','mybatis','com.mysql.jdbc.Driver','root','root',1,'D:\\phpStudy\\MySQL\\bin','useUnicode=true&characterEncoding=utf-8',1,3306);
/*!40000 ALTER TABLE `t_config_database` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_template_table`
--

DROP TABLE IF EXISTS `t_template_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_template_table` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` datetime DEFAULT NULL COMMENT '新增时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `path` varchar(200) DEFAULT NULL COMMENT '文件路径 ',
  `entity_name` varchar(200) DEFAULT NULL COMMENT '实体名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库实体对象参数管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_template_table`
--

LOCK TABLES `t_template_table` WRITE;
/*!40000 ALTER TABLE `t_template_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_template_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-26 21:22:26
