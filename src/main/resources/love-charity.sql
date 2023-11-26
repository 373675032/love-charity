/*

   <!--
    ==========================================================================
    郑重说明：本项目免费开源！原创作者为：薛伟同学，严禁私自出售。
    ==========================================================================
    B站账号：薛伟同学
    微信公众号：薛伟同学
    作者博客：http://xuewei.world
    ==========================================================================
    陆陆续续总会收到粉丝的提醒，总会有些人为了赚取利益倒卖我的开源项目。
    不乏有粉丝朋友出现钱付过去，那边只把代码发给他就跑路的，最后还是根据线索找到我。。
    希望各位朋友擦亮慧眼，谨防上当受骗！
    ==========================================================================
  -->
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `user_id` int(11) DEFAULT NULL COMMENT '作者用户ID',
  `title` varchar(50) DEFAULT NULL COMMENT '文章标题',
  `content` longtext COMMENT '文章正文',
  `status` int(11) DEFAULT NULL COMMENT '文章状态：发布（1），回收站（2）',
  `read_count` int(11) DEFAULT NULL COMMENT '阅读量',
  `img` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `type` int(11) DEFAULT NULL COMMENT '类型：文章由普通用户发布（1），活动由管理员发表（2）',
  `is_checked` int(11) DEFAULT NULL COMMENT '文章审核，0：未审核，1：审核通过，2：审核不通过',
  `info` varchar(500) DEFAULT '-' COMMENT '审核未通过返回的消息',
  `gmt_create` datetime DEFAULT NULL COMMENT '数据插入时间，即发布时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
BEGIN;
INSERT INTO `article` (`id`, `user_id`, `title`, `content`, `status`, `read_count`, `img`, `type`, `is_checked`, `info`, `gmt_create`, `gmt_modified`) VALUES (45, 14, '环保清洁行动：组织志愿者参与社区、公园或海滩等地的垃圾清理，倡导环境保护意识', '## 背景\n当今社会，环境问题越来越受到人们的关注，尤其是城市化进程不断加快，城市垃圾问题愈发突出。因此，环保清洁行动成为了一项重要的公益活动。\n\n## 目的\n该活动的目的是通过组织志愿者参与社区、公园或海滩等地的垃圾清理，倡导环境保护意识，提高公众环保意识和责任心。同时，也可以促进社区居民之间的交流和沟通，增强社区凝聚力。\n\n## 内容\n环保清洁行动的内容包括组织志愿者清理垃圾、分类回收，宣传环保知识，引导公众养成环保习惯等。在此过程中，还可以开展一些有趣的互动游戏和活动，让参与者更深刻地体验到环保的重要性。\n\n## 意义\n这项公益活动的意义在于，它可以提高公众环保意识和责任心，促进环保行动的落实，保护和改善人类居住环境；同时也可以增强社区凝聚力，促进社区建设的发展。\n\n## 时间与地点\n环保清洁行动的时间和地点可以根据具体情况进行安排，例如可以在周末或假日组织，地点可以选择社区、公园、海滩等需要清理的区域。同时也可以通过社交媒体等平台进行宣传，吸引更多人参与，发挥公益活动的社会效应。', 1, 1, 'https://su-share.oss-cn-beijing.aliyuncs.com/o/6aa8d09ea62f4f459f0725cbc611e07b.jpg', 2, 1, '-', '2023-11-27 00:12:34', '2023-11-27 00:12:34');
INSERT INTO `article` (`id`, `user_id`, `title`, `content`, `status`, `read_count`, `img`, `type`, `is_checked`, `info`, `gmt_create`, `gmt_modified`) VALUES (46, 14, '教育支持计划：提供教育机会和资源支持给贫困地区的儿童，包括奖学金、图书捐赠、义务教学等。', '## 背景\n教育是人类社会发展的重要基石，但在一些贫困地区和偏远地区，很多孩子因为经济原因无法接受良好的教育。因此，教育支持计划成为了一项非常有意义的公益活动。\n\n## 目的\n该活动的目的是通过提供教育机会和资源支持给贫困地区的儿童，包括奖学金、图书捐赠、义务教学等，帮助他们获得良好的教育，改善家庭和整个社区的生活水平。\n\n## 内容\n教育支持计划的内容包括提供资金、物资或志愿者支持，开展义务教学、图书捐赠、奖学金等活动，让更多贫困地区的儿童能够接受良好的教育。\n\n## 意义\n这项公益活动的意义在于，它可以为贫困地区的儿童提供教育资源和机会，帮助他们实现自身价值，扩大社会公平性和公正性，促进整个社区和社会的发展。\n\n## 时间地点\n教育支持计划的时间和地点可以根据具体情况进行安排，例如可以在贫困地区的学校、社区等地点组织，时间可以选择在寒暑假期间或其他空闲时间。同时也可以通过社交媒体等渠道进行宣传，吸引更多的爱心人士参与和支持，实现公益活动的最大效益。', 1, 1, 'https://su-share.oss-cn-beijing.aliyuncs.com/o/dd701ee1cf2a400abe2e5e72c5ae9ab7.jpg', 2, 1, '-', '2023-11-27 00:15:39', '2023-11-27 00:15:39');
INSERT INTO `article` (`id`, `user_id`, `title`, `content`, `status`, `read_count`, `img`, `type`, `is_checked`, `info`, `gmt_create`, `gmt_modified`) VALUES (47, 14, '爱心助老服务：为孤寡老人、失能老人提供陪伴、日常照料和关怀，让他们感受到社会的温暖。', '## 背景\n社会福利活动是指通过组织志愿者为弱势群体提供各种形式的帮助和服务，旨在改善他们生活条件和身心健康，提高他们的生活质量和幸福感。\n\n## 目的\n该活动的目的是为社会弱势群体提供帮助和服务，包括老年人、残疾人、孤寡儿童等人群，提高他们的生活质量和幸福感，同时也倡导社会公益精神和义工文化。\n\n## 内容\n社会福利活动的内容包括定期探访老年人、孤寡儿童等弱势群体，为他们提供日常生活照料和精神慰藉；开展义诊、送医等健康服务；组织文艺演出、康乐活动等增加他们的娱乐生活等。\n\n## 意义\n这项公益活动的意义在于，它可以为社会弱势群体提供帮助和服务，增加他们的生活幸福感和满足感，促进社会团结和和谐发展。\n\n## 时间地点\n社会福利活动的时间和地点可以根据具体情况进行安排，例如可以在老年人、残疾人、孤儿院等地点组织，时间可以选择在周末或其他空闲时间。同时也可以通过社交媒体等平台进行宣传，吸引更多的志愿者参与和支持，实现公益活动的最大效益。', 1, 5, 'https://su-share.oss-cn-beijing.aliyuncs.com/o/f1dd962fcccc4d1584695d59f7f665bd.jpg', 2, 1, '-', '2023-11-27 00:20:16', '2023-11-27 00:20:16');
INSERT INTO `article` (`id`, `user_id`, `title`, `content`, `status`, `read_count`, `img`, `type`, `is_checked`, `info`, `gmt_create`, `gmt_modified`) VALUES (48, 16, '小柴犬找新家', '大家好！我是一只可爱的小柴犬，但目前我是一只流浪狗。虽然我没有一个温暖的家，但我依然保持着积极向上的态度。\n\n我有一身棕色的短毛，一双灵活聪明的眼睛，和一条卷曲的尾巴。虽然我在街头漂泊，但我依然保持着一颗善良和友好的心。\n\n我非常喜欢与人互动，并且对于新事物充满好奇心。我渴望找到一个温暖的家，有一个主人可以给我爱和关怀。我相信，在一个温暖的家庭里，我会成为忠诚的伴侣和家庭成员。\n\n尽管我现在是一只流浪狗，但我仍然坚持积极向上的生活态度。我相信，有一天，我的努力和机遇会让我找到一个真正属于我的家，那时我将会带来无尽的快乐和陪伴。\n\n希望大家能够关注流浪动物的命运，给予我们更多的关爱和帮助。谢谢大家！我期待着未来的美好。\n\n![](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201810%2F16%2F20181016012014_mtxZY.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1703609252&t=3f7da74a4dcf132647a4875fbae5b898)', 1, 3, 'https://su-share.oss-cn-beijing.aliyuncs.com/o/d2f3381bda7d45888cb290562bffc23a.webp', 1, 1, '-', '2023-11-27 00:48:37', '2023-11-27 00:54:04');
INSERT INTO `article` (`id`, `user_id`, `title`, `content`, `status`, `read_count`, `img`, `type`, `is_checked`, `info`, `gmt_create`, `gmt_modified`) VALUES (49, 16, '小柯基找新家', '嗨大家！我是一只可爱的小柯基，虽然现在我是一只流浪狗，但我依然保持着乐观和活力。\n\n我有一身短短的毛发，精灵的眼神和一对短短的小腿，让我看起来特别可爱。尽管我没有一个温暖的家，但我仍然保持着对人类的信任和友善。\n\n作为一只柯基，我非常喜欢与人互动和玩耍。我喜欢追逐球、接住飞盘，还喜欢在户外奔跑和探险。我活泼好动，也很聪明，相信我能成为一个忠诚的伴侣和家庭成员。\n\n我渴望找到一个温暖的家，有一个主人可以给我爱和关怀。我相信，在一个温暖的家庭里，我会过上幸福美满的生活，与主人共同创造美好回忆。\n\n尽管我现在是一只流浪狗，但我依然充满希望和勇气。我坚信，有一天，我的努力和机遇会让我找到真正属于我的家，那时我将带来无尽的快乐和陪伴。\n\n希望大家能够关注流浪动物的处境，给予我们更多的关爱和帮助。谢谢大家！我期待着未来的幸福归宿。\n\n![](https://img2.baidu.com/it/u=1473033799,2225661018&fm=253&fmt=auto&app=138&f=JPEG?w=667&h=500)', 1, 4, 'https://su-share.oss-cn-beijing.aliyuncs.com/o/f3267cafae4440288d9ea36639f9c313.webp', 1, 1, '-', '2023-11-27 00:50:32', '2023-11-27 00:54:00');
INSERT INTO `article` (`id`, `user_id`, `title`, `content`, `status`, `read_count`, `img`, `type`, `is_checked`, `info`, `gmt_create`, `gmt_modified`) VALUES (50, 15, '探访养老院', '最近，我的学校组织了一次特殊的社会实践活动——探访养老院。这是一次让我们感受关爱与责任的活动，也是一次让我们更深刻理解敬老爱老的意义之旅。\n\n当我们踏入养老院的大门时，首先映入眼帘的是一片宁静和温馨。院内整洁明亮，花草绿树扮靓了院子，一群老人们或坐在长椅上晒太阳，或聚在一起唠家常，他们脸上洋溢着对生活的满足和笑容。我心中涌起一股暖流，仿佛感受到了生命的坚韧与包容。\n\n我们和老人们进行了亲切的交流，他们分享了自己的往事和故事，也给我们讲述了年轻时的生活点滴。有的老人因病失去了行动能力，但他们依然用乐观的态度面对生活；有的老人孤身一人，但他们依然保持着对生活的热爱。每一个老人都有自己的故事，每一段故事都深深触动了我们。\n\n在陪伴老人们的过程中，我们不仅学会了尊重和关爱，更懂得了责任与担当。我们给老人们送去了精心准备的礼物，看到他们开心的笑容，我们的心也随之变得欢喜。我们还表演了节目，为老人们带去了快乐和温暖，看到老人们眼中闪烁的光芒，我们深刻领悟到，关爱并不需要华丽的言辞，简单的陪伴和关怀就足以温暖人心。\n\n这次探访养老院的经历让我受益匪浅，也让我更加珍惜现在拥有的一切。我明白了孝顺和关爱不仅仅是口头上的，更需要用行动来诠释。我决心从现在做起，多关心身边的老人，多为他们分担一些生活上的困难，用自己的行动传递关爱与温暖。\n\n通过这次活动，我也更加坚信，尊老爱幼是中华民族的传统美德，也是社会文明进步的基石。我们应该在日常生活中，多关心老人，多为他们创造快乐和舒适的生活环境。只有让老人们感受到社会的温暖，我们的社会才能更加和谐美好。\n\n探访养老院的这次经历，让我获益良多，也让我更加坚定了对于尊老爱幼的信念。我相信，只要我们每个人都用心去关爱身边的老人，那么我们的社会将会更加和谐、更加温暖。愿我们能够用实际行动，传递更多的关爱，让我们的社会更加美好！', 1, 1, 'https://su-share.oss-cn-beijing.aliyuncs.com/o/b00150a50a184546a46d9062d61a074e.jpg', 1, 1, '-', '2023-11-27 00:53:22', '2023-11-27 00:53:55');
COMMIT;

-- ----------------------------
-- Table structure for article_activity
-- ----------------------------
DROP TABLE IF EXISTS `article_activity`;
CREATE TABLE `article_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` int(11) DEFAULT NULL COMMENT '文章的ID',
  `activity_article_id` int(11) DEFAULT NULL COMMENT '活动文章的ID',
  `gmt_create` datetime DEFAULT NULL COMMENT '插入数据的时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_activity
-- ----------------------------
BEGIN;
INSERT INTO `article_activity` (`id`, `article_id`, `activity_article_id`, `gmt_create`, `gmt_modified`) VALUES (2, 50, 47, '2023-11-27 00:53:21', '2023-11-27 00:53:21');
COMMIT;

-- ----------------------------
-- Table structure for certification
-- ----------------------------
DROP TABLE IF EXISTS `certification`;
CREATE TABLE `certification` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `name` varchar(20) DEFAULT NULL COMMENT '机构名称',
  `leader_name` varchar(10) DEFAULT NULL COMMENT '领导者姓名',
  `id_card` varchar(50) DEFAULT NULL COMMENT '领导者身份证号码',
  `type` varchar(50) DEFAULT NULL COMMENT '机构类型',
  `show_name` varchar(50) DEFAULT NULL COMMENT '认证称号（XXX官方账户）',
  `prove_img` varchar(255) DEFAULT NULL COMMENT '单位证明/营业执照图片地址',
  `id_card_img0` varchar(255) DEFAULT NULL COMMENT '领导者身份证照片（反面）',
  `id_card_img1` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '领导者身份证照片（正面）',
  `is_checked` int(11) NOT NULL DEFAULT '0' COMMENT '审核（0），成功（1），失败（2）',
  `info` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '认证未通过返回的消息',
  `gmt_create` datetime DEFAULT NULL COMMENT '插入数据的时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`,`is_checked`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of certification
-- ----------------------------
BEGIN;
INSERT INTO `certification` (`id`, `user_id`, `name`, `leader_name`, `id_card`, `type`, `show_name`, `prove_img`, `id_card_img0`, `id_card_img1`, `is_checked`, `info`, `gmt_create`, `gmt_modified`) VALUES (21, 16, '爱宠找家', '张爱', '130283199910100101', '动物保护', '爱宠找家官方', 'https://su-share.oss-cn-beijing.aliyuncs.com/user/824dea095a1e458080d2e6f6a0ff7cce.jpg', 'https://su-share.oss-cn-beijing.aliyuncs.com/user/a024f1017b40445b979fc8e4839deb59.jpg', 'https://su-share.oss-cn-beijing.aliyuncs.com/user/2d1ab2c879d54b1da57cd4a01411cfc5.jpg', 1, '-', '2023-11-27 00:42:24', '2023-11-27 00:43:02');
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '发表评论用户的ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `img` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `target_id` int(11) DEFAULT NULL COMMENT '目标ID：可以是项目ID 、文章ID',
  `type` int(11) DEFAULT NULL COMMENT '类型：项目（1），文章（2）',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `reply_id` int(11) DEFAULT NULL COMMENT '回复评论的ID',
  `is_first` int(11) DEFAULT '0' COMMENT '是否置顶：置顶（1），不置顶（0）',
  `gmt_create` datetime DEFAULT NULL COMMENT '插入数据的时间，即发表评论的时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` (`id`, `user_id`, `user_name`, `img`, `target_id`, `type`, `content`, `reply_id`, `is_first`, `gmt_create`, `gmt_modified`) VALUES (81, 16, '爱宠找家', 'https://su-share.oss-cn-beijing.aliyuncs.com/1.jpg', 19, 1, '支持支持！', NULL, 0, '2023-11-27 00:44:00', '2023-11-27 00:44:00');
INSERT INTO `comment` (`id`, `user_id`, `user_name`, `img`, `target_id`, `type`, `content`, `reply_id`, `is_first`, `gmt_create`, `gmt_modified`) VALUES (82, 16, '爱宠找家', 'https://su-share.oss-cn-beijing.aliyuncs.com/1.jpg', 18, 1, '支持支持！大家可以关注一下我发的文章，帮助小动物找新家～', NULL, 0, '2023-11-27 00:44:43', '2023-11-27 00:44:43');
INSERT INTO `comment` (`id`, `user_id`, `user_name`, `img`, `target_id`, `type`, `content`, `reply_id`, `is_first`, `gmt_create`, `gmt_modified`) VALUES (83, 16, '爱宠找家', 'https://su-share.oss-cn-beijing.aliyuncs.com/1.jpg', 47, 2, '意义非凡！', NULL, 0, '2023-11-27 00:44:58', '2023-11-27 00:44:58');
INSERT INTO `comment` (`id`, `user_id`, `user_name`, `img`, `target_id`, `type`, `content`, `reply_id`, `is_first`, `gmt_create`, `gmt_modified`) VALUES (84, 14, '管理员', 'https://su-share.oss-cn-beijing.aliyuncs.com/user/c5ea89635d914ffebee1226141e6901d.jpg', 49, 2, '好可爱', NULL, 0, '2023-11-27 00:54:56', '2023-11-27 00:54:56');
INSERT INTO `comment` (`id`, `user_id`, `user_name`, `img`, `target_id`, `type`, `content`, `reply_id`, `is_first`, `gmt_create`, `gmt_modified`) VALUES (85, 14, '管理员', 'https://su-share.oss-cn-beijing.aliyuncs.com/user/c5ea89635d914ffebee1226141e6901d.jpg', 18, 1, '大家一起行动！', 82, 0, '2023-11-27 00:55:12', '2023-11-27 00:55:12');
INSERT INTO `comment` (`id`, `user_id`, `user_name`, `img`, `target_id`, `type`, `content`, `reply_id`, `is_first`, `gmt_create`, `gmt_modified`) VALUES (86, 15, '张三', 'https://su-share.oss-cn-beijing.aliyuncs.com/3.jpg', 18, 1, '666', 82, 0, '2023-11-27 01:01:23', '2023-11-27 01:01:23');
INSERT INTO `comment` (`id`, `user_id`, `user_name`, `img`, `target_id`, `type`, `content`, `reply_id`, `is_first`, `gmt_create`, `gmt_modified`) VALUES (87, 15, '张三', 'https://su-share.oss-cn-beijing.aliyuncs.com/3.jpg', 48, 2, '我要了', NULL, 0, '2023-11-27 01:01:35', '2023-11-27 01:01:35');
INSERT INTO `comment` (`id`, `user_id`, `user_name`, `img`, `target_id`, `type`, `content`, `reply_id`, `is_first`, `gmt_create`, `gmt_modified`) VALUES (88, 15, '张三', 'https://su-share.oss-cn-beijing.aliyuncs.com/3.jpg', 48, 2, '地址是哪里', NULL, 0, '2023-11-27 01:01:42', '2023-11-27 01:01:42');
COMMIT;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '反馈的用户的ID',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `img` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `gmt_create` datetime DEFAULT NULL COMMENT '插入数据的时间，即发表评论的时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of feedback
-- ----------------------------
BEGIN;
INSERT INTO `feedback` (`id`, `user_id`, `user_name`, `img`, `content`, `gmt_create`, `gmt_modified`) VALUES (32, 15, '张三', 'https://su-share.oss-cn-beijing.aliyuncs.com/3.jpg', '建议添加捐款渠道！', '2023-11-27 01:00:43', NULL);
COMMIT;

-- ----------------------------
-- Table structure for link
-- ----------------------------
DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `target_url` varchar(255) DEFAULT NULL COMMENT '链接地址',
  `type` int(11) DEFAULT NULL COMMENT '类型：顶部菜单（1），友情链接（2）',
  `gmt_create` datetime DEFAULT NULL COMMENT '插入数据的时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of link
-- ----------------------------
BEGIN;
INSERT INTO `link` (`id`, `name`, `target_url`, `type`, `gmt_create`, `gmt_modified`) VALUES (35, '淘宝公益', 'https://gongyi.taobao.com/', 2, '2023-11-27 00:56:50', NULL);
INSERT INTO `link` (`id`, `name`, `target_url`, `type`, `gmt_create`, `gmt_modified`) VALUES (36, '京东公益', 'https://gongyi.jd.com/', 2, '2023-11-27 00:57:29', NULL);
INSERT INTO `link` (`id`, `name`, `target_url`, `type`, `gmt_create`, `gmt_modified`) VALUES (37, '中国社会福利基金会', 'http://www.cswf.org.cn/', 1, '2023-11-27 00:57:44', NULL);
INSERT INTO `link` (`id`, `name`, `target_url`, `type`, `gmt_create`, `gmt_modified`) VALUES (38, '基金会中华公益网', 'http://www.gongyi.org/', 1, '2023-11-27 00:58:04', NULL);
INSERT INTO `link` (`id`, `name`, `target_url`, `type`, `gmt_create`, `gmt_modified`) VALUES (39, '腾讯公益', 'https://gongyi.qq.com/', 2, '2023-11-27 00:58:25', NULL);
INSERT INTO `link` (`id`, `name`, `target_url`, `type`, `gmt_create`, `gmt_modified`) VALUES (40, '支付宝蚂蚁公益', 'https://gongyi.alipay.com/', 2, '2023-11-27 00:58:45', NULL);
INSERT INTO `link` (`id`, `name`, `target_url`, `type`, `gmt_create`, `gmt_modified`) VALUES (41, '南都公益基金会', 'https://www.sfn.org.cn/', 2, '2023-11-27 00:59:14', NULL);
INSERT INTO `link` (`id`, `name`, `target_url`, `type`, `gmt_create`, `gmt_modified`) VALUES (42, '泰康在线公益基金会', 'https://gongyi.taikang.com/', 2, '2023-11-27 00:59:32', NULL);
COMMIT;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `receive_user_id` int(11) DEFAULT NULL COMMENT '接收消息的用户ID',
  `sent_user_id` int(20) DEFAULT NULL COMMENT '发送消息的用户ID',
  `type` int(11) DEFAULT NULL COMMENT '类型：项目评论（1）/文章评论（2）/审核  (3)',
  `target_id` int(11) DEFAULT NULL COMMENT '目标ID：公益项目/文章ID',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT NULL COMMENT '消息的内容',
  `is_read` int(11) DEFAULT '0' COMMENT '是否已读：未读（0），已读（1）',
  `gmt_create` datetime DEFAULT NULL COMMENT '插入数据的时间，即发表评论的时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新的时间',
  `sent_user_img` varchar(100) DEFAULT NULL COMMENT '发送消息的用户头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
BEGIN;
INSERT INTO `message` (`id`, `receive_user_id`, `sent_user_id`, `type`, `target_id`, `title`, `content`, `is_read`, `gmt_create`, `gmt_modified`, `sent_user_img`) VALUES (86, 14, 16, 2, 47, '爱心助老服务：为孤寡老人、失能老人提供陪伴、日常照料和关怀，让他们感受到社会的温暖。', '<b>爱宠找家</b> 回复了您关于文章 <b><<爱心助老服务：为孤寡老人、失能老人提供陪伴、日常照料和关怀，让他们感受到社会的温暖。>></b>的评论,内容如下： <b>意义非凡！</b>', 0, '2023-11-27 00:44:58', '2023-11-27 00:44:58', 'https://su-share.oss-cn-beijing.aliyuncs.com/1.jpg');
INSERT INTO `message` (`id`, `receive_user_id`, `sent_user_id`, `type`, `target_id`, `title`, `content`, `is_read`, `gmt_create`, `gmt_modified`, `sent_user_img`) VALUES (87, 15, 14, 2, 50, NULL, '<b>恭喜您,您的文章</b><b><<探访养老院>>已通过审核!</b>', 0, '2023-11-27 00:53:56', '2023-11-27 00:53:55', 'https://su-share.oss-cn-beijing.aliyuncs.com/user/c5ea89635d914ffebee1226141e6901d.jpg');
INSERT INTO `message` (`id`, `receive_user_id`, `sent_user_id`, `type`, `target_id`, `title`, `content`, `is_read`, `gmt_create`, `gmt_modified`, `sent_user_img`) VALUES (88, 16, 14, 2, 49, NULL, '<b>恭喜您,您的文章</b><b><<小柯基找新家>>已通过审核!</b>', 0, '2023-11-27 00:54:01', '2023-11-27 00:54:00', 'https://su-share.oss-cn-beijing.aliyuncs.com/user/c5ea89635d914ffebee1226141e6901d.jpg');
INSERT INTO `message` (`id`, `receive_user_id`, `sent_user_id`, `type`, `target_id`, `title`, `content`, `is_read`, `gmt_create`, `gmt_modified`, `sent_user_img`) VALUES (89, 16, 14, 2, 48, NULL, '<b>恭喜您,您的文章</b><b><<小柴犬找新家>>已通过审核!</b>', 0, '2023-11-27 00:54:04', '2023-11-27 00:54:04', 'https://su-share.oss-cn-beijing.aliyuncs.com/user/c5ea89635d914ffebee1226141e6901d.jpg');
INSERT INTO `message` (`id`, `receive_user_id`, `sent_user_id`, `type`, `target_id`, `title`, `content`, `is_read`, `gmt_create`, `gmt_modified`, `sent_user_img`) VALUES (90, 16, 14, 2, 49, '小柯基找新家', '<b>管理员</b> 回复了您关于文章 <b><<小柯基找新家>></b>的评论,内容如下： <b>好可爱</b>', 0, '2023-11-27 00:54:56', '2023-11-27 00:54:56', 'https://su-share.oss-cn-beijing.aliyuncs.com/user/c5ea89635d914ffebee1226141e6901d.jpg');
INSERT INTO `message` (`id`, `receive_user_id`, `sent_user_id`, `type`, `target_id`, `title`, `content`, `is_read`, `gmt_create`, `gmt_modified`, `sent_user_img`) VALUES (91, 16, 15, 2, 48, '小柴犬找新家', '<b>张三</b> 回复了您关于文章 <b><<小柴犬找新家>></b>的评论,内容如下： <b>我要了</b>', 0, '2023-11-27 01:01:35', '2023-11-27 01:01:35', 'https://su-share.oss-cn-beijing.aliyuncs.com/3.jpg');
INSERT INTO `message` (`id`, `receive_user_id`, `sent_user_id`, `type`, `target_id`, `title`, `content`, `is_read`, `gmt_create`, `gmt_modified`, `sent_user_img`) VALUES (92, 16, 15, 2, 48, '小柴犬找新家', '<b>张三</b> 回复了您关于文章 <b><<小柴犬找新家>></b>的评论,内容如下： <b>地址是哪里</b>', 0, '2023-11-27 01:01:42', '2023-11-27 01:01:42', 'https://su-share.oss-cn-beijing.aliyuncs.com/3.jpg');
COMMIT;

-- ----------------------------
-- Table structure for message_board
-- ----------------------------
DROP TABLE IF EXISTS `message_board`;
CREATE TABLE `message_board` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '邮件地址',
  `content` varchar(500) DEFAULT NULL COMMENT '留言内容',
  `gmt_create` datetime DEFAULT NULL COMMENT '插入数据的时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message_board
-- ----------------------------
BEGIN;
INSERT INTO `message_board` (`id`, `name`, `email`, `content`, `gmt_create`, `gmt_modified`) VALUES (20, '张三', 'zs@qq.com', '当爱心相聚，温暖无处不在。', '2023-11-27 00:03:48', '2023-11-27 00:03:48');
INSERT INTO `message_board` (`id`, `name`, `email`, `content`, `gmt_create`, `gmt_modified`) VALUES (21, '李四', 'ls@qq.com', '守护绿色家园，共筑美丽明天。', '2023-11-27 00:04:10', '2023-11-27 00:04:10');
INSERT INTO `message_board` (`id`, `name`, `email`, `content`, `gmt_create`, `gmt_modified`) VALUES (22, '王五', 'ww@qq.com', '传递微笑，温暖每一天。', '2023-11-27 00:04:32', '2023-11-27 00:04:31');
INSERT INTO `message_board` (`id`, `name`, `email`, `content`, `gmt_create`, `gmt_modified`) VALUES (23, '赵六', 'zl@qq.com', '携手公益，让爱传递到每个角落。', '2023-11-27 00:04:57', '2023-11-27 00:04:57');
COMMIT;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(500) DEFAULT NULL COMMENT '名称',
  `background` varchar(500) DEFAULT NULL COMMENT '背景',
  `target` varchar(500) DEFAULT NULL COMMENT '目的',
  `theme` varchar(500) DEFAULT NULL COMMENT '主题',
  `sponsor` varchar(500) DEFAULT NULL COMMENT '赞助',
  `content` longtext COMMENT '内容',
  `img` varchar(500) DEFAULT NULL COMMENT '公益项目的封面',
  `gmt_create` datetime DEFAULT NULL COMMENT '插入数据的时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '更新的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
BEGIN;
INSERT INTO `project` (`id`, `name`, `background`, `target`, `theme`, `sponsor`, `content`, `img`, `gmt_create`, `gmt_modified`) VALUES (17, '书香助学计划', '随着社会发展，一些贫困地区的孩子由于家庭条件等原因，缺乏良好的阅读资源和环境，导致他们的阅读能力和学业水平无法得到有效提升。', '通过搭建读书辅导、图书捐赠等公益平台，帮助贫困地区的孩子们获得更多的阅读资源和教育支持，提高他们的阅读水平和学业成绩，促进教育公平。', '提倡读书、助力教育、关爱成长', '书香学院、书香书屋、书香四溢公益组织', '该公益项目主要包括以下内容：\n\n1. 图书捐赠：向贫困地区的学校、图书馆等机构捐赠优质图书，丰富当地的阅读资源。\n2. 读书辅导：组织志愿者到贫困地区的学校开展读书辅导活动，引导孩子们培养良好的阅读习惯和方法。\n3. 阅读分享会：举办阅读分享会，邀请作家、教育专家等为孩子们进行讲座，激发他们对阅读的兴趣和热情。', 'https://su-share.oss-cn-beijing.aliyuncs.com/o/a0944e29c6644369991515c43e9b1a7b.jpg', '2023-11-27 00:26:20', '2023-11-27 00:26:20');
INSERT INTO `project` (`id`, `name`, `background`, `target`, `theme`, `sponsor`, `content`, `img`, `gmt_create`, `gmt_modified`) VALUES (18, '守护生命，保护动物', '随着人类的生活方式和经济发展模式的改变，野生动物遭受到了越来越多的威胁，一些濒危物种甚至面临灭绝的危险。同时，一些恶意的非法行为，如偷猎、贩卖野生动物等，也对野生动物造成了不可挽回的伤害。', '通过宣传普及野生动物保护知识，引导公众关注野生动物保护问题，提倡珍爱生命和尊重自然，促进人与自然和谐共生。', '珍爱生命、保护动物、构建和谐', '野生动物基金会、爱救赎、康师傅', '该公益项目主要包括以下内容：\n\n1. 野生动物保护宣传：通过各种渠道，如展览、讲座、网络宣传等，向公众普及野生动物保护知识，提高公众关注度和保护意识。\n2. 野生动物救助：组织专业救援团队，对遭遇意外伤害或受到非法捕猎的野生动物进行救助和治疗，尽可能减少动物伤亡。\n3. 野生动物栖息地保护：对野生动物的栖息地进行保护和修复，提供更好的生存环境，促进野生动物种群的恢复和发展。', 'https://su-share.oss-cn-beijing.aliyuncs.com/o/3204c5fa5f6542bc95694516f337b963.jpg', '2023-11-27 00:30:49', '2023-11-27 00:30:49');
INSERT INTO `project` (`id`, `name`, `background`, `target`, `theme`, `sponsor`, `content`, `img`, `gmt_create`, `gmt_modified`) VALUES (19, '清洁水源，保护未来', '随着人口的增加和工业化的发展，全球范围内的水资源面临着日益严重的污染和短缺问题。水是生命之源，保护水资源对于人类和整个生态系统的可持续发展至关重要。', '通过宣传教育、技术创新和行动实践，提高公众对水资源的认识和保护意识，推动水资源的可持续利用，促进人与自然的和谐发展。', '珍爱水源、保护水资源、可持续发展', '怡宝、百岁山、水资源基金会', '该公益项目主要包括以下内容：\n\n1. 水资源宣传教育：通过举办讲座、展览、宣传活动等形式，向公众普及水资源保护的重要性和方法，引导人们珍惜每一滴水。\n2. 水源保护行动：组织志愿者参与水源地的清洁行动，提倡环保意识，减少污染源的排放，确保水源地的纯净和可持续供应。\n3. 水资源科技创新：鼓励科学家和技术专家进行水资源科技研发，开展高效节水技术的推广和应用，提高水资源利用效率。\n\n![](https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fss2.meipian.me%2Fusers%2F45687928%2F63206c65ab604f398fc859f12ce48d0f.jpg%3Fmeipian-raw%2Fbucket%2Fivwen%2Fkey%2FdXNlcnMvNDU2ODc5MjgvNjMyMDZjNjVhYjYwNGYzOThmYzg1OWYxMmNlNDhkMGYuanBn%2Fsign%2Fcf13787a128f7dda85efe66edbfa4a85.jpg&refer=http%3A%2F%2Fss2.meipian.me&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1703608402&t=f078c4039d560872a5d96aa844028ded)', 'https://su-share.oss-cn-beijing.aliyuncs.com/o/b2e81f68025d4f588e307cbbb7bac5a3.webp', '2023-11-27 00:34:32', '2023-11-27 00:34:32');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(8) DEFAULT '-' COMMENT '姓名',
  `password` varchar(16) DEFAULT '-' COMMENT '密码',
  `email` varchar(30) DEFAULT '-' COMMENT '邮箱地址',
  `phone` char(11) DEFAULT '-' COMMENT '手机号码',
  `address` varchar(50) DEFAULT '-' COMMENT '地址',
  `sex` int(3) DEFAULT '0' COMMENT '性别：女（0）男（1）',
  `birthday` date DEFAULT NULL COMMENT '出生年月',
  `career` varchar(20) DEFAULT '-' COMMENT '职业',
  `certification_id` int(11) DEFAULT NULL COMMENT '认证类型ID',
  `img` varchar(255) DEFAULT 'https://s1.ax1x.com/2020/08/30/dqJ6wd.jpg' COMMENT '头像地址',
  `status` int(11) DEFAULT '0' COMMENT '状态：正常（0），封禁（1）',
  `gmt_create` datetime DEFAULT NULL COMMENT '注册时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '最近更新',
  `role` int(3) NOT NULL DEFAULT '0' COMMENT '角色:普通用户（0）管理员（1）',
  PRIMARY KEY (`id`,`role`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `name`, `password`, `email`, `phone`, `address`, `sex`, `birthday`, `career`, `certification_id`, `img`, `status`, `gmt_create`, `gmt_modified`, `role`) VALUES (14, '管理员', '123456', 'admin@qq.com', '17879540430', '河北省-唐山市-迁安市', 1, '2023-11-26', '-', NULL, 'https://su-share.oss-cn-beijing.aliyuncs.com/user/c5ea89635d914ffebee1226141e6901d.jpg', 0, '2023-11-26 23:47:38', '2023-11-27 00:00:08', 1);
INSERT INTO `user` (`id`, `name`, `password`, `email`, `phone`, `address`, `sex`, `birthday`, `career`, `certification_id`, `img`, `status`, `gmt_create`, `gmt_modified`, `role`) VALUES (15, '张三', '123456', 'zs@qq.com', '17879540430', '河北省-唐山市-迁安市', 1, '2023-11-26', '-', NULL, 'https://su-share.oss-cn-beijing.aliyuncs.com/3.jpg', 0, '2023-11-26 23:47:38', '2023-11-27 00:00:08', 0);
INSERT INTO `user` (`id`, `name`, `password`, `email`, `phone`, `address`, `sex`, `birthday`, `career`, `certification_id`, `img`, `status`, `gmt_create`, `gmt_modified`, `role`) VALUES (16, '爱宠找家', '123456', 'aczj@qq.com', '17879540430', '河北省-唐山市-迁安市', 1, '2023-11-26', '-', 21, 'https://su-share.oss-cn-beijing.aliyuncs.com/1.jpg', 0, '2023-11-26 23:47:38', '2023-11-27 00:43:02', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
