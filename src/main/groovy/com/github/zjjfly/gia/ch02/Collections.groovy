package com.github.zjjfly.gia.ch02

/**
 * Created by zjjfly on 2017/2/5.
 */
//List
//Groovy的list支持添加和删除元素，在运行时动态修改长度，而且不要求存放的元素的类型统一
//Groovy使用类似方括号声明一个list字面量
def roman = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII']
//可以直接使用下标访问list的成员，就像java数组一样
assert roman[4] == 'IV'
//Groovy的list还支持索引超过目前的边界，这样可以改变list的长度
roman[8] = 'VIII'
assert roman.size() == 9

//Map
//Groovy的map看上去就像键值对的数组，使用冒号分隔键和值
def http = [100: 'CONTINUE', 200: 'OK', 400: 'BAD REQUEST']
//和list一致的访问成员语法
assert http[200] == 'OK'
//和list一致的修改成员语法
http[500] = 'INTERNAL SERVER ERROR'
assert http.size() == 4

//Range
//类似Scala的 x to y
def x = 1..10
assert x.contains(5)
assert !x.contains(15)
assert x.size() == 10
assert x.from == 1//其实数字
assert x.to == 10//结尾数字
assert x.reverse() == 10..1//可以定义顺序颠倒的range
