package com.github.zjjfly.gia.ch04

/**
 * @author zjjfly
 */

//map声明
def myMap = [a: 1, b: 2, c: 3]
//空map
def emptyMap = [:]
assert emptyMap.size() == 0
//map默认的类型是LinkedHashMap
assert myMap instanceof LinkedHashMap
//map也可以使用下标操作符来访问和赋值
assert myMap['a'] == 1
//如果想要其他类型的map,可以显示的声明
def explicitMap = new TreeMap()
explicitMap.putAll(myMap)
//其他类型的map也可以使用下标操作符
assert explicitMap['a'] == 1
//展开操作符
def composed = [x: 'y', *: myMap]
assert composed == [x: 'y', a: 1, b: 2, c: 3]
//因为使用string作为key的情况很多,所以groovy中声明key类型是string的map字面量的时候可以省略引号
assert ['a': 1] == [a: 1]
//但如果有一个本地变量,你想要让它作为map的key而不是被当做字符串,可以把它放到一个()里强制让Groovy把它当做表达式
def a = 'x'
assert ['x': 1] == [(a): 1]

//map的常用操作
//访问,有三种方式
//1.下标操作符
assert myMap['b'] == 2
//2.属性
assert myMap.c == 3
//3.get方法,可以传入一个默认值
assert myMap.get('a', 0) == 1
//前面两种方法,如果key不存在,返回null
assert myMap['d'] == null
assert myMap.d == null
//最后的方法,如果不指定默认值,返回的也是null
assert myMap.get('d') == null
//如果指定了默认值,而key不存在,那么在get调用的时候这个key和默认值会被加到map中
assert myMap.get('d', 0) == 0
assert myMap.d == 0

//更新键值对
myMap['d'] = 1
assert myMap.d == 1
myMap.d = 2
assert myMap.d == 2
//如果key中含有特殊字符(如.),要使用属性访问这个key的值,需要把它放在字符串记号中
myMap = ['a.b': 1]
assert myMap.'a.b' == 1
