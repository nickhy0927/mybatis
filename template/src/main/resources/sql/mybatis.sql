-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.53 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 mybatis 的数据库结构
DROP DATABASE IF EXISTS `mybatis`;
CREATE DATABASE IF NOT EXISTS `mybatis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mybatis`;


-- 导出  表 mybatis.sys_base_data 结构
CREATE TABLE IF NOT EXISTS `sys_base_data` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` date DEFAULT NULL COMMENT '新增时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `code` varchar(200) DEFAULT NULL COMMENT '字典编号 ',
  `name` varchar(200) DEFAULT NULL COMMENT '字典名称 ',
  `val` varchar(200) DEFAULT NULL COMMENT '字典值 ',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息备注 ',
  `enable` int(8) DEFAULT NULL COMMENT '是否启用 1 启用  0 停用 ',
  `sys_params_id` varchar(200) DEFAULT NULL COMMENT '系统参数ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基础数据表';

-- 正在导出表  mybatis.sys_base_data 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_base_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_base_data` ENABLE KEYS */;


-- 导出  表 mybatis.sys_base_params 结构
CREATE TABLE IF NOT EXISTS `sys_base_params` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` date DEFAULT NULL COMMENT '新增时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `code` varchar(200) DEFAULT NULL COMMENT '参数编号 ',
  `name` varchar(200) DEFAULT NULL COMMENT '参数名称 ',
  `val` varchar(200) DEFAULT NULL COMMENT '参数值 ',
  `enable` int(8) DEFAULT NULL COMMENT '是否启用 1 启用  0 停用 ',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表';

-- 正在导出表  mybatis.sys_base_params 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_base_params` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_base_params` ENABLE KEYS */;


-- 导出  表 mybatis.sys_menu 结构
CREATE TABLE IF NOT EXISTS `sys_menu` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` date DEFAULT NULL COMMENT '新增时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `code` varchar(200) DEFAULT NULL COMMENT '菜单编号 ',
  `name` varchar(200) DEFAULT NULL COMMENT '菜单名称 ',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单地址 ',
  `alias` varchar(200) DEFAULT NULL COMMENT '菜单别名 ',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '上级菜单 ',
  `enable` int(8) DEFAULT NULL COMMENT '是否启用 1 启用 0 停用 ',
  `shows` int(8) DEFAULT NULL COMMENT '是否显示 1 显示 0 隐藏 ',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- 正在导出表  mybatis.sys_menu 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` (`id`, `create_time`, `update_time`, `status`, `code`, `name`, `url`, `alias`, `menu_id`, `enable`, `shows`, `remark`) VALUES
	('22d94b9912dc4cfdb154f6206bc85993', '2018-08-09', NULL, 1, '75140623', '角色管理', '/platform/role/role-list.do', 'role-mgt', '', 1, 1, '平台角色管理'),
	('2cb45c1e895a4b86a9e15200591f7dfa', '2018-08-09', NULL, 1, '08972463', '角色删除', '/platform/role/role-delete/*.json', 'role-delete', '', 1, 2, '平台角色删除'),
	('4343e169ea874dc8a5aaa1d769c05e79', '2018-08-08', NULL, 1, '41275693', '用户删除', '/platform/user/user-delete/*.json', 'user-delete', '', 1, 2, '删除用户信息'),
	('4c906cd649184e4093ca4c1810f0fd58', '2018-08-07', NULL, 1, '35894610', '系统管理', '#', 'system-mgt', '', 1, 1, '所有的菜单信息'),
	('505c56a20ac14891bf85b5a97636f693', '2018-08-08', NULL, 1, '24965310', '菜单管理', '/platform/menu/menu-list.do', 'menu-mgt', '', 1, 1, '菜单信息管理'),
	('60a9918b459745d4ac5e0df637c71e06', '2018-08-08', NULL, 1, '68174902', '菜单删除', '/platform/menu/menu-delete/*.json', 'menu-delete', '', 1, 2, '删除菜单信息'),
	('66e4ab5ab20d4f55849634e48c72af42', '2018-08-08', NULL, 1, '15849267', '菜单修改', '/platform/menu/menu-edit/*.do', 'menu-edit', '', 1, 2, '修改菜单信息'),
	('8c2b0b67db9c4ff98e08e15356b11dc3', '2018-08-08', NULL, 1, '38016579', '用户修改', '/platform/user/user-edit/*.do', 'user-edit', '', 1, 2, '修改用户信息'),
	('a41d801bfef74697906d037d9589c3c8', '2018-08-09', NULL, 1, '54273810', '角色修改', '/platform/role/role-edit/*.do', 'role-edit', '', 1, 2, '平台角色修改'),
	('a6c6364757ae4b96ba285ba6eb9d1f7a', '2018-08-07', NULL, 1, '30175684', '平台管理', '#', 'platform-mgt', '', 1, 1, '平台管理'),
	('ad9d9b644adf4940b2dca387962346f5', '2018-08-08', NULL, 1, '86253149', '用户管理', '/platform/user/user-list.do', 'user-mgt', '', 1, 1, '平台用户管理'),
	('b82886fe5e9242f99803c8c6f6f46795', '2018-08-07', NULL, 1, '74206135', '菜单新增', '/menu/menu-create.do', 'menu-reate', '1', 1, 1, '新增菜单连接'),
	('c95c3682a8a7490bad8742950d815f8e', '2018-08-09', NULL, 1, '49831627', '角色新增', '/platform/role/role-create.do', 'role-create', '', 1, 1, '平台角色新增'),
	('f655aa788b3c45929fdfcd147b098d94', '2018-08-08', NULL, 1, '47180952', '用户新增', '/platform/user/user-create.do', 'user-create', '', 1, 1, '新增用户');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;


-- 导出  表 mybatis.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` date DEFAULT NULL COMMENT '新增时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `code` varchar(200) DEFAULT NULL COMMENT '角色编号 ',
  `name` varchar(200) DEFAULT NULL COMMENT '角色名称 ',
  `role_id` varchar(200) DEFAULT NULL COMMENT '上级角色 ',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息备注 ',
  `frozen` int(8) DEFAULT NULL COMMENT '是否冻结 1 冻结 0 未冻结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色管理';

-- 正在导出表  mybatis.sys_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;


-- 导出  表 mybatis.sys_role_menu 结构
CREATE TABLE IF NOT EXISTS `sys_role_menu` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` date DEFAULT NULL COMMENT '新增时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `role_id` varchar(200) DEFAULT NULL COMMENT '角色ID ',
  `menu_id` varchar(200) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单管理';

-- 正在导出表  mybatis.sys_role_menu 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;


-- 导出  表 mybatis.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `real_name` varchar(200) DEFAULT NULL COMMENT '真实姓名 ',
  `login_name` varchar(200) DEFAULT NULL COMMENT '登录账号 ',
  `password` varchar(200) DEFAULT NULL COMMENT '登录密码 ',
  `locked` int(8) DEFAULT NULL COMMENT '是否锁定 1 锁定 0 未锁定 ',
  `enable` int(8) DEFAULT NULL COMMENT '是否启用 1 启用 0 停用 ',
  `email` varchar(200) DEFAULT NULL COMMENT '电子邮箱 ',
  `mobile` varchar(200) DEFAULT NULL COMMENT '电话号码 ',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息备注 ',
  `position` varchar(200) DEFAULT NULL COMMENT '用户职位 ',
  `last_login_time` date DEFAULT NULL COMMENT '最后一次登录时间',
  `id` varchar(200) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `status` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理';

-- 正在导出表  mybatis.sys_user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;


-- 导出  表 mybatis.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `status` int(8) DEFAULT NULL COMMENT '有效状态1 有效 0 无效 ',
  `user_id` varchar(200) DEFAULT NULL COMMENT '用户ID ',
  `role_id` varchar(200) DEFAULT NULL COMMENT '角色ID',
  `id` varchar(200) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户菜单管理';

-- 正在导出表  mybatis.sys_user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;


-- 导出  表 mybatis.t_config_database 结构
CREATE TABLE IF NOT EXISTS `t_config_database` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` date DEFAULT NULL COMMENT '新增时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
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

-- 正在导出表  mybatis.t_config_database 的数据：1 rows
/*!40000 ALTER TABLE `t_config_database` DISABLE KEYS */;
INSERT INTO `t_config_database` (`id`, `create_time`, `update_time`, `status`, `ip`, `database_name`, `driver_class_name`, `username`, `password`, `used`, `mysqldump_path`, `params`, `database_type`, `port`) VALUES
	('041eba444ea0454e828b6ae2387760f2', '2018-08-09', NULL, 1, '127.0.0.1', 'mybatis', 'com.mysql.jdbc.Driver', 'root', 'root', 1, 'D:\\phpStudy\\MySQL\\bin', 'useUnicode=true&characterEncoding=utf-8', 1, 3306);
/*!40000 ALTER TABLE `t_config_database` ENABLE KEYS */;


-- 导出  表 mybatis.t_template_table 结构
CREATE TABLE IF NOT EXISTS `t_template_table` (
  `id` varchar(200) NOT NULL COMMENT '主键ID',
  `create_time` date DEFAULT NULL COMMENT '新增时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  `status` int(8) DEFAULT '1' COMMENT '有效状态',
  `path` varchar(200) DEFAULT NULL COMMENT '文件路径 ',
  `entity_name` varchar(200) DEFAULT NULL COMMENT '实体名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库实体对象参数管理';

-- 正在导出表  mybatis.t_template_table 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_template_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_template_table` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
