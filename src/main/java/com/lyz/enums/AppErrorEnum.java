package com.lyz.enums;


import com.lyz.util.error.IError;

/**
 * App内部错误码枚举类
 *
 * @author xiaojw
 * @since 2020/1/14
 * 范围 551001-551999
 */
public enum AppErrorEnum implements IError {

    APP_PARAM_ERROR("550002","参数异常"),
    EX_HEADER_IS_NOT_EXIST("551001","请求头信息不存在"),
    EX_FEIGN_EXEC("551002","feign调用出错"),
    EX_GT_SERVICE_EXEC("551003","服务器内部错误，长城后台服务错误"),
    EX_ACCESSTOKEN_EMPTY("551004","accessToken为空"),
    EX_Channel_EMPTY("551005","rs不合法"),
    EX_RSA_DECRYPT_FAIL("551007","rsa解密失败"),
    TERMINAL_BRAND_ENTERPRISEID_FAIL("551008","terminal或brand或enterpriseId不合法"),
    RUNTIME_FAIL("551009","runtime不合法"),
    TEM_FAIL("551010","tem不合法"),
    EX_REFRESHTOKEN_INVALID("551011","refreshToken已经失效"),
    VEHICLES_NOT_EXIST("551012", "车辆不存在"),
    VIN_DECRYPTION_FAILED("551013", "VIN码校验失败"),
    SECURITYTOKEN_FAILURE("551014", "安防免密失效，请重新校验"),
    SECURITY_PASSWORD_ERROR_3("551015", "安防密码格式错误"),
    INSUFFICIENT_AUTHORITY("551016", "渠道不合法，没有限权访问"),
    INSUFFICIENT_AUTHORITY_5("551017", "该账号与此车辆无绑定关系"),
    BLUETOOTH_STATUS_ERROR_1("551018", "蓝牙感应开启时，蓝牙钥匙必须开启"),
    BLUETOOTH_STATUS_ERROR_2("551019", "分享蓝牙和控车必须同时开启一个"),
    BLUETOOTH_STATUS_ERROR_4("551021", "蓝牙钥匙信息不存在"),
    BLUETOOTH_STATUS_ERROR_5("551022", "非车主不能新增蓝牙钥匙"),
    BLUETOOTH_STATUS_ERROR_6("551023", "蓝牙钥匙已经存在，新增失败"),
    BLUETOOTH_STATUS_ERROR_7("551024", "车辆不支持蓝牙"),
    BLUETOOTH_EQUIPMENT_ERROR("551025", "获取蓝牙设备信息失败"),
    MOBILE_ACCOUNT_ERROR("551026", "手机号和账号不匹配"),
    VERIFICATION_INVALID("551027", "手机验证标识已经失效"),
    BLUETOOTH_INVALID("551028", "非车主不能失效蓝牙"),
    PARAM_INVALID("551029", "查询任务列表参数不合法"),
    BLUETOOTH_ONE_MONTH("551030", "蓝牙分享只能在一个月"),
    REMOTECONTROL_ABILITY("551032", "分享车辆远控时，远控能力必填"),
    LONGIN_SIWEI_FAIL("551033", "登录失败获取四维车辆信息"),
    AGREEMENT("551034", "车辆协议不合法"),
    STATIC_TYPE_INVALID("551035", "不合法的StaticType"),
    EX_FOLLOW_MYSELF("551036", "自己就不用关注自己啦！"),
    QR_CODE_FAILURE("551037", "二维码已过期，请重新扫码登录"),
    SHARER_SHARER_ERROR("551038", "获取分享人信息报错"),
    SUBSCRIBE_ERROR("551039", "当前车辆已经存在待执行的空调，请不要重复预约"),
    PC_TOKEN_FAILURE("551040", "未获取到登录状态"),
    POTT_IS_ERROR("551041", "端口号错误"),
    TIME_ERROR("551042", "空调预约时间在当前时间往后二十四小时之内"),


    CAT_TYPE_INEXISTENCE("551043", "该车型暂不支持"),
    MASSAGE_LIST_OR_NULL("551044", "CatalogId和MessageType不能都为空"),
    WEY_BOARD_LIMIT("551046", "每人只能加入两个车友会"),
    BLUETOOTH_SHARE_TIME_ERROR("551045", "蓝牙已经分享，分享时间不可修改"),
    BOARD_STATUS("551047", "该板块已经下线或解散"),
    HAFO_BOARD_ROLE_LIMIT("551048", "副会长最多3个"),
    BOARD_ROLE_PARAM_ERROR("551049", "设置车友会角色参数错误"),
    BOARD_ROLE_EDIT_ERROR("551050", "非会长不能设置用户角色"),
    BOARD_APPLY_ERROR("551051", "非会长不能解散车友会或转让会长"),

    /**
     * 违章
     */
    VIOLATION_MATERIL_MISS("551052", "车行易代办资料缺失，请补充"),
    VIOLATION_ORDER_INVALID("551053","请检查订单号(违章)"),
    VIOLATION_PRODUCT_INVALID("551054","产品不存在请刷新再试"),
    ORDER_CREATE_ERROR("551055","创建仙豆订单失败"),
    VIOLATION_STATE_ERROR("551056","违章已处理或处理中"),
    VIOLATION_CANNOT_PROCESS("551057","该违章信息，暂不支持代办"),
    VIOLATION_VALID("551058","违章数据已过期，请重新查询违章"),
    QUERY_USER_INFO_ERROR("551059", "查询封装用户信息异常"),

    //品牌书架
    PRICE_CALCULATE_ERROR("551060","产品价格错误"),
    SALE_PROCE_TYPE_ERROR("551061","售价类型配置错误"),
    PRICE_STRATEGY_ERROR("551062","售价策略配置错误(价格策略 1-动态获取，2-手动输入)"),
    WEBSITE_NODE_UNEXIST("551063", "品牌书架站点信息不存在"),
    BEAN_ID_NOT_NULL("551064", "用户id不能为空"),
    CATEGORY_IDS_UNEXIST("551065", "书架专辑分类ID信息不存在"),
    ADD_USER_COLLECT_ERROR("551066", "专辑id和收藏类型不能为空"),
    CANCEL_USER_COLLECT_ERROR("551067", "专辑id不能为空"),
    DEVICE_ID_UNEXIST("551068", "设备号不能为空"),
    SEARCH_NOT_NULL("551069", "搜索类型不能为空"),
    WEBSITE_RECOMMEND_UNEXIST("551070", "品牌书架热门推荐入口ID信息不存在"),

    //活动中心
    ACTIVITY_END_ERROR("551080", "活动结束时间不能早于活动开始时间"),
    ACTIVITY_DEADLINE_TIME_ERROR("551081", "活动报名截止时间不能早于当前时间后十分钟"),
    ACTIVITY_START_TIME_ERROR("551082", "活动报名截止时间要早于活动开始时间"),

    USER_IDENTITY_ERROR("551090", "用户是否车主状态有误"),

    //违章订阅
    VIOLATION_SUBSCRIBE_ORDER_DETAIL_ERROR("551101", "此违章提醒订单信息错误（创建订单时请传入正确的vin码）"),
    VIOLATION_SUBSCRIBE_DETAIL_ERROR("551104", "违章提醒信息错误（订阅服务是否有效为null）"),

    PRODUCT_SALETYPE_TYPE_ERROR("551102", "互联服务商品价格策略有误"),
    PRODUCT_SALETYPE_NOT_EXIST("551103", "售价类型错误"),
    VEHICLE_MODEL_CODE_NOT_EXIST("551104", "该vin码没有查到车型信息"),

    //远控
    T4_NOT_SUPPORT("551109","T3/T4不支持多指令"),
    /**
     * 沿用社区code
     */
    PORNOGRAPHIC("710028","内容标题含有敏感信息，请重新编辑"),


    /**
     * 搜索
     */
    SEARCH_ACTIVITY_RIMINFO("551190","搜索查询活动参与人头像信息为空"),
    SEARCH_CARCLUB_ACTIVITY_BASE("551191","搜索查询车友会活动基本信息为空"),
    SEARCH_ACTIVITY_USER("551192","搜索查询车友会活动创建人信息为空"),
    SEARCH_OFFICIAL_ACTIVITY("551193","搜索查询官方活动的基本信息为空"),
    SEARCH_POST("551194","搜索帖子的基本信息为空"),
    SEARCH_THREAD_RELATION("551195","搜索查询帖子关联的专题/话题/板块信息为空"),
    SEARCH_POST_USER("551196","搜索帖子用户的信息为空"),
    /**
     * E代驾
     */
    THIRDPARTY_ERROR("551070","调用第三方接口错误"),
    DETAILS_ERROR("551071","正在进行中的订单不能查询详情"),
    ORDERNO_IS_NULL("551072","订单数据不存在"),
    ORDER_REDUNDANT("551073","正在进行中的订单大于1"),
    ORDER_ALREADY_EXISTS("551074","订单已存在,请勿重复下单"),
    ORDER_PHONENUM_DEVICEID_NULL("551076","手机号和设备号不能为空"),
    /**
     * 取送车
     */
    STORE_IS_NULL("551080","商品信息为空"),
    /**
     * 车百科
     */
    IS_ALL_NULL("551083","场景Id与车辆部件Id与搜索框内容不能同时为空"),

    /**
     * 积分
     */
    VALIDATE_POLICY_UNEXIST("551200","校验策略不存在"),
    POINT_PROJECT_UNEXIST("551202","积分场景不存在"),
    POINT_VALIDATE_FAILED("551203","积分参数错误"),
    COMMUNITY_TASK_POINT_FAILED("551204","社区任务返回积分参数错误"),
    SSOID_UNEXIST("551204","SsoId错误"),
    BRAND_HAVE_NO_THIS_TASK("551206","当前品牌无此任务场景"),
    NOT_SUPPORT_REWARD("551207","当前帖子不支持打赏"),
    /**
     * 搜索
     */
    SUBSCRIPTION_TEST_DRIVE("551211","刚刚已经预约试驾成功！！！勿要重复预约"),
    /**
     * 消息
     */
    LCCTYPE_ERROR("551212","terminal为“GW_APP_Haval”时lccType不能为空"),

    /**
     * 加油
     */
    REFUEL_ORDER_CREATE_ERROR("551400","cpspId不能为空"),
    REFUEL_ORDER_CREATE_ERROR1("551413","gasPlatform不正确"),


    /**
     * 消息
     */
    SUBSCRIPTION_TEST_APPRAISE("551214","预约试驾记录不存在！"),
    SUBSCRIPTION_TEST_DRIVE_MODE("551213","该车型不支持预约试驾"),

    //行程管理
    SCHEDULE_TIME_ERROR("551300", "行程时间错误"),
    DECODE_TIME_ERROR("551301", "行程时间解析错误"),
    SCHEDULE_CLASH_ERROR("551302", "用户行程冲突"),
    SCHEDULE_ERROR("551303", "用户行程处理异常"),
    BRAND_NOT_EXIST("551304", "品牌不存在"),
    BATCH_UPLOAD_ERROR("551305", "行程批量上传异常"),
    SCHEDULE_TIME_BIGGER_NOW("551306", "行程时间错误，开始时间不能小于当前时间"),
    SCHEDULE_TIME_NOT_ALLOW("551307", "行程时间错误，结束时间不能小于开始时间"),

    CONTENT_INFO_ERROR("551307","不允许发布纯数字"),
    //发票
    INVOICE_BANK_ACCOUNT_ERROR("551401","银行账号格式不正确"),
    ORDER_STATUS_ERROR_NO_INVOICE("551402","订单状态或者发票状态不对,不能开发票"),

    BOARD_INFO_ERROR("551410","车友会详情返回结果为空"),
    BOARD_EDIT_USER_ERROR("551411","非会长不能编辑"),

    //消息
    MESSAGE_PUSH_SWITCH_NOT_EXIST("551501","消息开关未配置"),
    MESSAGE_HAVE_NO_THIS_CASE("551502","APP无此消息场景"),

    RC_SUBSCRIBE_ERROR("551104", "当前车辆已经存在同类型待执行的远控，请不要重复预约"),
    RC_SUBSCRIBE_TIME_ERROR("551105", "远控预约时间在当前时间往后二十四小时之内"),
    HEADER_CHANNEL_ERROR("551106", "渠道只能为APP|TX|VIVO|MI"),
    RC_SUBSCRIBE_NOT_EXIT("551107", "撤销预约远控记录不存在"),
    RC_SUBSCRIBE_CANNELED("551108", "预约远控记录已撤销,请勿重复操作"),

    //订单
    ORDER_ID_IS_NOT_NULL("551111", "cp订单号不能为空"),
    ORDER_NO_IS_NOT_NULL("551112", "仙豆订单号不能为空"),
    ORDER_STATUS_EXCEPTION("551113", "订单状态不正确"),
    ORDER_IS_NOT_EXIST("551114", "订单不存在或者订单信息异常"),
    ORDER_CANCEL_LOSE("551115", "订单取消失败"),
    CP_ORDER_IS_NOT_EXIST("551116", "取送车服务订单不存在请稍后或者刷新再试哦"),
    ORDER_IS_NOT_CANCEL("551117", "不能取消订单哦"),

    //免密
    SCENEID_INVALID("5510109","场景sceneid无效或者支付中心没有配置场景"),

    //皮卡查询定制车友会类型错误
    CAR_CLUB_TYPE_ERROR("5510201","查询车友会类型错误"),
    CAR_CLUB_NOT_CONFIG("5510202","查询车友会数据未配置"),
    CAR_CLUB_LEVEL_ERROR("5510203","车友会页面无此层级"),
    TOP_TEN_NOT_CONFIG("5510204","越野场地TOP10数据未配置"),
    PICKUP_SERVICE_SWITCH_NOT_EXIST("5510205","皮卡服务续费开关未配置"),


    CHILD_ID_NOT_NULL("551130", "卡券子id不能为空"),


    POTENTIAL_CUSTOMER_ERROR("551299", "潜客常用车城市保存失败"),

    /**
     * 年度报表
     */
    ANNUAL_REPORT_NOT_FOUND("551300","报表找不到了"),

    /**
     * DTC诊断
     */

    DIAGNOSE_DETAILS_NOT_FIND("551006", "诊断详情未找到"),

    COMMENT_COMPANY_NOT_SUPPORT("551601", "评论暂不支持该企业"),
    USER_CONTINUE_SIGN_INFO_ERROR("551602", "连续签到数据日期对比失败"),
    USER_TASK_QUERY_ERROR("551603", "用户任务查询失败"),

    BOARD_CREAT_INFO_ERROR("551701", "创建条件不满足，需达到1000积分"),
    BOARD_ASSIGNMENT_INFO_ERROR("551702", "对方积分不足1000，无法转让"),
    BOARD_POINTS_VICE_CAP_ROLE_LIMIT("551703", "分队副最多3个"),
    BOARD_CAP_ROLE_EDIT_ERROR("551704", "非队长/分队长不能设置用户角色"),
    BOARD_CAP_APPLY_ERROR("551705", "非队长/分队长不能解散车友会或转让会长"),
    BOARD_VICE_CAP_BOARD_ROLE_LIMIT("551706", "副队长最多3个"),
    BOARD_QUIT_ERROR("551707", "您所删除的成员中包含分队长，其名下有组建的小分队，无法删除"),
    BOARD_CAP_EDIT_ERROR("551708", "非队长/分队长不能编辑车友会"),
    BOARD_USER_JOIN_ERROR("551709", "该成员已加入"),
    BOARD_USER_REFUSE_ERROR("551709", "该成员已被拒绝加入"),
    /**
     * 腾讯云前缀限制报错
     */
    ADDRESS_PREFIXS_IS_INVALID("552003", "地址前缀不合法"),
    APP_VERSION_NOT_NULL("552004", "app版本号不能为空"),
    APP_VERSION_IS_INVALID("552004", "app版本号格式不正确"),
    ;

    /**
     * 错误码
     */
    private String errCode;
    /**
     * 错误信息
     */
    private String errMsg;

    AppErrorEnum(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public String getNamespace() {
        return "";
    }

    @Override
    public String getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errMsg;
    }
}
