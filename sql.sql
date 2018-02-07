CREATE TABLE `job` (
  `id` bigint(24) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
  `job_name` varchar(64) NOT NULL DEFAULT '' COMMENT '任务名',
  `bean_name` varchar(32) NOT NULL DEFAULT '' COMMENT '类名',
  `method_name` varchar(32) NOT NULL DEFAULT '' COMMENT '方法名',
  `cron_param` varchar(32) NOT NULL DEFAULT '' COMMENT '参数',
  `execute_param` varchar(32) NOT NULL DEFAULT '' COMMENT '参数',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否可用:0-废弃,1-可用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

CREATE TABLE `execute_history` (
  `id` bigint(24) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `job_id` bigint(24) unsigned NOT NULL DEFAULT '0' COMMENT '任务ID',
  `status` tinyint(2) NOT NULL COMMENT '执行状态:0-初始,1-执行中,2-成功,3-失败',
  `schedule_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度方式:1-定时调度,2-手工执行',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `IDX_JOB_ID` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录表';