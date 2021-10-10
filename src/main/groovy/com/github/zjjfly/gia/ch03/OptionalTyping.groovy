package com.github.zjjfly.gia.ch03

import java.awt.*

/**
 * Created by zjjfly on 2017/2/14.
 */


int i = 1//声明的类型是原始类型，但实际上不是
assert i instanceof Integer

//Groovy编译器会自动在加入类型转换，所以下面的代码在编译的时候不会报错
//Integer n = new Object()

//Groovy强大的自动类型转换
Point topLeft = new Point(0, 0)
Point botRight = [100, 100]//List cast
Point center = [x: 50, y: 50]//Map cast
assert botRight instanceof Point
assert center instanceof Point

Integer n = '1'
println n

def rectangle = new Rectangle()
rectangle.location = [0, 0]//Point
rectangle.size = [width: 100, height: 100]//Dimension

//有两种情况不需要声明类型:
//1.这个变量是一个方法调用的返回结果,并且除了作为参数传递给另一个方法不会做任何事
//2.调用不确定类型的某个方法.比如你想调用doSomething方法,但你不关心调用这个方法的对象的类型

