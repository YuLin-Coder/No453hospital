package com.hos.hospital.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.UUID;

public class StringRandom {

    //生成随机字符
    public static String getRandom() {
        String  su        = UUID.randomUUID().toString();
        return   su.replace("-", "").substring(0, 8);
    }

  
}
