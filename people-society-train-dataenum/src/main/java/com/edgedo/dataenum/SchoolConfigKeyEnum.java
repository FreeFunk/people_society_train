package com.edgedo.dataenum;

/**
 *@Author:ZhaoSiDa
 *@Description: 学校配置的公共key
 *@DateTime: 2020/7/9 15:48
 */
public enum SchoolConfigKeyEnum {
    /**
     * 人脸识别间隔分钟 默认为 10
     */
    FACE_MATCH_MINUTE,
    /**
     * 开班申请状态（1：自动通过0：后台审核）
     */
    CLASS_APPLY_STATE,
    /**
     * 实名认证开关（0：停用1：启用）
     */
    REAL_NAME_STATE,
    /**
     * @Author WangZhen
     * @Description 进入学习页面是否需要人脸重新识别0 不需要，1需要
     * @Date 2020/7/9 16:07
     **/
    IN_STUDY_NEED_FACE,
    /**
     * @Author WangZhen
     * @Description 配置大于0小于100的整数 允许学习下一课的练习分数 如未设置可取0%
     * @Date 2020/7/9 16:07
     **/
    PASS_PRACTISE_RIGHT_RATE,
    /**
     * @Author WangZhen
     * @Description 是否需要学习完课程才能答题 0 不用，1需要  默认是不用
     * @Date 2020/7/9 16:07
     **/
    NODE_LEARN_DONE_PRACTISE_CAN,

    /**
     * @Author QiuTianZhu
     * @Description: 章节人脸数
     * @Date 2020/8/7 16:31
     **/
    NODE_FACE_QUALIFIED_NUM;

}
