CREATE schema  IF NOT EXISTS  `cucumber`;

USE `cucumber`;

USE public;

CREATE TABLE IF NOT EXISTS `source` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'id',
  `code` varchar(64) NOT NULL COMMENT '唯一code',
  `name` varchar(128) DEFAULT NULL COMMENT 'source name',
  `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '类型',
  `ext` text NOT NULL,
  `enable_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '启用/禁用',
  `create_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  primary key (`id`)
) ;
