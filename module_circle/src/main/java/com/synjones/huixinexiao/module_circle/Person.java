package com.synjones.huixinexiao.module_circle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class Person implements MultiItemEntity {
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String name;
    public int age;

    @Override
    public int getItemType() {
//        return ExpandableItemAdapter.TYPE_PERSON;
        return 0;
    }
}