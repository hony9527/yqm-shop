CREATE TABLE SPRING_SESSION
(
    PRIMARY_ID            CHAR(36) NOT NULL,
    SESSION_ID            CHAR(36) NOT NULL,
    CREATION_TIME         BIGINT   NOT NULL,
    LAST_ACCESS_TIME      BIGINT   NOT NULL,
    MAX_INACTIVE_INTERVAL INT      NOT NULL,
    EXPIRY_TIME           BIGINT   NOT NULL,
    PRINCIPAL_NAME        VARCHAR(100),
    CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE
UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE
INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE
INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES
(
    SESSION_PRIMARY_ID CHAR(36)     NOT NULL,
    ATTRIBUTE_NAME     VARCHAR(200) NOT NULL,
    ATTRIBUTE_BYTES    BLOB         NOT NULL,
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION (PRIMARY_ID) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`
(
    `token_id`          varchar(255) DEFAULT NULL COMMENT '加密的access_token的值',
    `token`             longblob COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
    `authentication_id` varchar(255) DEFAULT NULL COMMENT '加密过的username,client_id,scope',
    `user_name`         varchar(255) DEFAULT NULL COMMENT '登录的用户名',
    `client_id`         varchar(255) DEFAULT NULL COMMENT '客户端ID',
    `authentication`    longblob COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据',
    `refresh_token`     varchar(255) DEFAULT NULL COMMENT '加密的refresh_token的值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '访问令牌';

DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals`
(
    `userId`         varchar(255) DEFAULT NULL COMMENT '登录的用户名',
    `clientId`       varchar(255) DEFAULT NULL COMMENT '客户端ID',
    `scope`          varchar(255) DEFAULT NULL COMMENT '申请的权限范围',
    `status`         varchar(10)  DEFAULT NULL COMMENT '状态（Approve或Deny）',
    `expiresAt`      datetime     DEFAULT NULL COMMENT '过期时间',
    `lastModifiedAt` datetime     DEFAULT NULL COMMENT '最终修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '授权记录';

DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(255) NOT NULL COMMENT '客户端ID',
    `resource_ids`            varchar(255) DEFAULT NULL COMMENT '资源ID集合,多个资源时用逗号(,)分隔',
    `client_secret`           varchar(255) DEFAULT NULL COMMENT '客户端密匙',
    `scope`                   varchar(255) DEFAULT NULL COMMENT '客户端申请的权限范围',
    `authorized_grant_types`  varchar(255) DEFAULT NULL COMMENT '客户端支持的grant_type',
    `web_server_redirect_uri` varchar(255) DEFAULT NULL COMMENT '重定向URI',
    `authorities`             varchar(255) DEFAULT NULL COMMENT '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔',
    `access_token_validity`   int(11) DEFAULT NULL COMMENT '访问令牌有效时间值(单位:秒)',
    `refresh_token_validity`  int(11) DEFAULT NULL COMMENT '更新令牌有效时间值(单位:秒)',
    `additional_information`  varchar(255) DEFAULT NULL COMMENT '预留字段',
    `autoapprove`             varchar(255) DEFAULT NULL COMMENT '用户是否自动Approval操作'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '客户端用来记录token信息';
INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`,
                                    `web_server_redirect_uri`, `authorities`, `access_token_validity`,
                                    `refresh_token_validity`, `additional_information`, `autoapprove`)
VALUES ('7d4fa629-785a-42b2-8d14-8d1c38417880', NULL, '$2a$10$mihA6JP/5ITvWPlVTNSvjuvkxZBk4KKvFI9vMn2aJJNzgfBFmmZ7.',
        'all', 'refresh_token,password', NULL, NULL, 7200, 9000, NULL, 'true');


DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`
(
    `token_id`          varchar(255) DEFAULT NULL COMMENT '加密的access_token值',
    `token`             longblob COMMENT 'OAuth2AccessToken.java对象序列化后的二进制数据',
    `authentication_id` varchar(255) DEFAULT NULL COMMENT '加密过的username,client_id,scope',
    `user_name`         varchar(255) DEFAULT NULL COMMENT '登录的用户名',
    `client_id`         varchar(255) DEFAULT NULL COMMENT '客户端ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '客户端信息';

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`
(
    `code`           varchar(255) DEFAULT NULL COMMENT '授权码(未加密)',
    `authentication` varbinary(255) DEFAULT NULL COMMENT 'AuthorizationRequestHolder.java对象序列化后的二进制数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '授权码';

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`
(
    `token_id`       varchar(255) DEFAULT NULL COMMENT '加密过的refresh_token的值',
    `token`          longblob COMMENT 'OAuth2RefreshToken.java对象序列化后的二进制数据 ',
    `authentication` longblob COMMENT 'OAuth2Authentication.java对象序列化后的二进制数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT="更新令牌";



DROP TABLE IF EXISTS tp_user;
CREATE TABLE tp_user
(
    id                       VARCHAR(32)  NOT NULL COMMENT '编号;编号',
    user_name                VARCHAR(255) NOT NULL COMMENT '用户姓名',
    sex                      INT                   DEFAULT 1 COMMENT '性别;0 未知 1 男 2 女',
    phone                    VARCHAR(255) NOT NULL COMMENT '手机',
    email                    VARCHAR(255) NOT NULL COMMENT '邮箱',
    address                  VARCHAR(255) NOT NULL COMMENT '地址;详细地址',
    postal_code              VARCHAR(255) COMMENT '邮编',
    account                  VARCHAR(255) COMMENT '账号',
    password                 VARCHAR(255) COMMENT '密码',
    province_id              VARCHAR(255) NOT NULL COMMENT '省',
    city_id                  VARCHAR(255) NOT NULL COMMENT '市',
    area_id                  VARCHAR(255) NOT NULL COMMENT '区',
    enterprise_certification INT COMMENT '企业认证;0未认证 1 认证 2 认证审核中',
    status                   VARCHAR(255) COMMENT '状态;effective 有效 failure 失效 delete 删除',
    created_by               VARCHAR(32)  NOT NULL COMMENT '创建人',
    created_time             DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_by               VARCHAR(32)  NOT NULL COMMENT '更新人',
    updated_time             DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    `avatar`                 varchar(1024)         DEFAULT NULL COMMENT '头像',
    PRIMARY KEY (id)
) COMMENT = '用户表';
insert into `tp_user` (`id`, `user_name`, `sex`, `phone`, `email`, `address`, `postal_code`, `account`, `password`,
                       `province_id`, `city_id`, `area_id`, `enterprise_certification`, `status`, `created_by`,
                       `created_time`, `updated_by`, `updated_time`, `avatar`)
values ('1', 'admin', '1', '15871301059', '907147608@qq.com', '湖北省', NULL, '15871301059',
        '$2a$10$Kes4JuGAcDtef6kqqEq2w.8bsFdG6lD204uApJkjvd7XMPSQvtcO6', '1', '1', '1', '0', 'effective', '1',
        '2021-10-17 03:49:38', '1', '2021-10-17 03:49:38',
        'https://ts1.cn.mm.bing.net/th/id/R-C.8c372fd892b3bd371eb3a1df8bd7fc88?rik=4KxekfOQD28FKA&riu=http%3a%2f%2fwww.desktx.com%2fd%2ffile%2fwallpaper%2fscenery%2f20170303%2fdfe53a7300794009a029131a062836d5.jpg&ehk=6ayU5y%2fwtGnzhu7g%2bJimm2REgEbHGczl9Mkbg3I1%2b5I%3d&risl=&pid=ImgRaw&r=0');



DROP TABLE IF EXISTS yqm_store_product;
CREATE TABLE yqm_store_product
(
    id                  VARCHAR(32)  NOT NULL COMMENT '编号',
    created_by          VARCHAR(32)  NOT NULL COMMENT '创建人',
    created_time        DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_by          VARCHAR(32)  NOT NULL COMMENT '更新人',
    updated_time        DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    product_name        VARCHAR(255) NOT NULL COMMENT '商品名称',
    product_img         VARCHAR(255) NOT NULL COMMENT '商品图片',
    product_banner      text         NOT NULL COMMENT '轮播图',
    subtitle            VARCHAR(900) COMMENT '副标题',
    brand_id            VARCHAR(32) COMMENT '品牌id',
    classify_id         VARCHAR(255) NOT NULL COMMENT '商品分类id',
    introduce           VARCHAR(900) NOT NULL COMMENT '介绍',
    freight_template_id VARCHAR(255) NOT NULL COMMENT '运费模板id',
    article_number      VARCHAR(255) NOT NULL COMMENT '货号',
    price               VARCHAR(255) COMMENT '价格',
    market_price        VARCHAR(255) COMMENT '市场价',
    inventory_warning   INT COMMENT '总库存预警值',
    measuring_unit      VARCHAR(255) COMMENT '计量单位',
    weight_num          VARCHAR(255) COMMENT '重量;单位克',
    is_shelves          VARCHAR(255)          DEFAULT 'not_shelves' COMMENT '是否上架;shelves 上架 not_shelves 下架',
    recommended         VARCHAR(255) COMMENT '推荐',
    service_guarantee   VARCHAR(900) COMMENT '服务保证',
    details_title       VARCHAR(255) COMMENT '详情页标题',
    details_describe    VARCHAR(255) COMMENT '详情页描述',
    note                VARCHAR(900) COMMENT '商品备注',
    specifications      VARCHAR(900) COMMENT '规格汇总',
    parameter           VARCHAR(255) COMMENT '商品参数;json数据',
    audit               INT                   DEFAULT -1 COMMENT '审核;-1 待审核 0 不同意 1 同意',
    audit_message       VARCHAR(900) COMMENT '审核完后信息',
    `status`            VARCHAR(255) NOT NULL DEFAULT 'success' COMMENT '状态;delete 删除 success 有效',
    PRIMARY KEY (id)
) COMMENT = '商品表';



DROP TABLE IF EXISTS yqm_classification;
CREATE TABLE yqm_classification
(
    id            VARCHAR(32)  NOT NULL COMMENT '编号',
    created_by    VARCHAR(32) COMMENT '创建人',
    created_time  DATETIME              DEFAULT now() COMMENT '创建时间',
    updated_by    VARCHAR(32) COMMENT '更新人',
    updated_time  DATETIME              DEFAULT now() COMMENT '更新时间',
    classify_name VARCHAR(255) NOT NULL COMMENT '商品分类',
    pids          VARCHAR(255) COMMENT '父编号',
    level         INT                   DEFAULT 1 COMMENT '等级',
    `status`      VARCHAR(255) NOT NULL DEFAULT 'success' COMMENT '状态;delete 删除 success 有效',
    PRIMARY KEY (id)
) COMMENT = '商品分类';



DROP TABLE IF EXISTS yqm_store_sku;
CREATE TABLE yqm_store_sku
(
    id                VARCHAR(32) NOT NULL COMMENT '编号',
    created_by        VARCHAR(32) COMMENT '创建人',
    created_time      DATETIME             DEFAULT now() COMMENT '创建时间',
    updated_by        VARCHAR(32) COMMENT '更新人',
    updated_time      DATETIME             DEFAULT now() COMMENT '更新时间',
    sku_name          VARCHAR(255) COMMENT '名称',
    sku_value         VARCHAR(255) COMMENT '值',
    sale_price        VARCHAR(255) COMMENT '销售价',
    inventory         INT                  DEFAULT 0 COMMENT '库存',
    inventory_warning VARCHAR(255) COMMENT '库存预警值;0 没有',
    sku_num           VARCHAR(255) COMMENT 'sku编号',
    `sort`            INT         NOT NULL DEFAULT 1 COMMENT '排序',
    img               VARCHAR(255) COMMENT '图片',
    product_id        VARCHAR(32) NOT NULL COMMENT '商品id',
    `status`          VARCHAR(255)         DEFAULT 'success' COMMENT '状态;delete 删除 success 有效',
    PRIMARY KEY (id)
) COMMENT = '商品sku';



DROP TABLE IF EXISTS yqm_store_evaluation;
CREATE TABLE yqm_store_evaluation
(
    id           VARCHAR(32)  NOT NULL COMMENT '编号',
    created_by   VARCHAR(32) COMMENT '创建人',
    created_time DATETIME              DEFAULT now() COMMENT '创建时间',
    updated_by   VARCHAR(32) COMMENT '更新人',
    updated_time DATETIME              DEFAULT now() COMMENT '更新时间',
    `sort`       INT          NOT NULL DEFAULT 1 COMMENT '排序',
    `status`     VARCHAR(255)          DEFAULT 'success' COMMENT '状态;delete 删除 success 有效',
    product_id   VARCHAR(32)  NOT NULL COMMENT '商品id',
    sku_id       VARCHAR(32)  NOT NULL COMMENT 'sku编号',
    `score`      VARCHAR(255) COMMENT '分数',
    ip           VARCHAR(255) COMMENT 'IP地址',
    is_show      VARCHAR(255) NOT NULL DEFAULT 'show' COMMENT '是否显示;show 显示 not_show 不显示',
    content      text COMMENT '内容',
    user_id      VARCHAR(32)  NOT NULL COMMENT '用户id',
    reply_id     VARCHAR(32) COMMENT '评论id',
    PRIMARY KEY (id)
) COMMENT = '商品评价';


DROP TABLE IF EXISTS yqm_brand;
CREATE TABLE yqm_brand
(
    id                     VARCHAR(32)  NOT NULL COMMENT '编号',
    created_by             VARCHAR(32) COMMENT '创建人',
    created_time           DATETIME              DEFAULT now() COMMENT '创建时间',
    updated_by             VARCHAR(32) COMMENT '更新人',
    updated_time           DATETIME              DEFAULT now() COMMENT '更新时间',
    `sort`                 INT          NOT NULL DEFAULT 1 COMMENT '排序',
    `status`               VARCHAR(255)          DEFAULT 'success' COMMENT '状态;delete 删除 success 有效',
    brand_name             VARCHAR(255) NOT NULL COMMENT '品牌名称',
    logo                   VARCHAR(255) NOT NULL COMMENT '品牌logo',
    max_log                VARCHAR(255) COMMENT '品牌专区大图',
    introduce              text COMMENT '介绍',
    is_show                VARCHAR(255)          DEFAULT 'show' COMMENT '是否显示;show 显示 not_show 不显示',
    is_brand_manufacturers VARCHAR(255)          DEFAULT 0 COMMENT '是否品牌制造商;0 不是 1是',
    PRIMARY KEY (id)
) COMMENT = '品牌';



DROP TABLE IF EXISTS yqm_photoalbum;
CREATE TABLE yqm_photoalbum
(
    id              VARCHAR(32)  NOT NULL COMMENT '编号',
    created_by      VARCHAR(32) COMMENT '创建人',
    created_time    DATETIME              DEFAULT now() COMMENT '创建时间',
    updated_by      VARCHAR(32) COMMENT '更新人',
    updated_time    DATETIME              DEFAULT now() COMMENT '更新时间',
    `sort`          INT          NOT NULL DEFAULT 1 COMMENT '排序',
    `status`        VARCHAR(255)          DEFAULT 'success' COMMENT '状态;delete 删除 success 有效',
    photoalbum_name VARCHAR(255) NOT NULL COMMENT '相册名称',
    img             VARCHAR(255) COMMENT '封面',
    introduce       VARCHAR(900) COMMENT '介绍',
    img_count       INT COMMENT '图片总数',
    PRIMARY KEY (id)
) COMMENT = '相册';


DROP TABLE IF EXISTS yqm_photoalbum_img;
CREATE TABLE yqm_photoalbum_img
(
    id            VARCHAR(32) NOT NULL COMMENT '编号',
    created_by    VARCHAR(32) COMMENT '创建人',
    created_time  DATETIME             DEFAULT now() COMMENT '创建时间',
    updated_by    VARCHAR(32) COMMENT '更新人',
    updated_time  DATETIME             DEFAULT now() COMMENT '更新时间',
    `sort`        INT         NOT NULL DEFAULT 1 COMMENT '排序',
    `status`      VARCHAR(255)         DEFAULT 'success' COMMENT '状态;delete 删除 success 有效',
    photoalbum_id VARCHAR(32) COMMENT '相册id',
    img           VARCHAR(255) COMMENT '图片',
    introduce     VARCHAR(255) COMMENT '介绍',
    PRIMARY KEY (id)
) COMMENT = '相册图片';



















