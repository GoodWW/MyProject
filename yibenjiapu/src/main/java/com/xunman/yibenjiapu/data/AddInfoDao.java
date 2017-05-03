package com.xunman.yibenjiapu.data;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.xunman.yibenjiapu.utils.HttpImplStringTest;

import java.util.HashMap;

/**
 * 项目名： MyProject
 * 创建者： xxxxx
 * 创建时间：  2017/4/7 0007 15:23
 * 包名：com.xunman.yibenjiapu.data
 * 文件名： ${name}
 * 描述：  TODO
 */

public class AddInfoDao {
    private Handler handler;
    private AddInfo addInfo;
    private String URL = "AddInfo.xml";

    public AddInfoDao(Handler handler, AddInfo addInfo) {
        this.handler = handler;
        this.addInfo = addInfo;
    }

    public void HttpOnline() {
        new HttpImplStringTest(JSON.parseObject(JSON.toJSONString(addInfo), HashMap.class), URL, handler, "POST").start();
    }

}
