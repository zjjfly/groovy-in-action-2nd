package com.github.zjjfly.gia.ch02
/**
 * Created by zjjfly on 2017/2/6.
 */
//Closure闭包
//类似Java8的lambda表达式，但功能更强大,它可以获取调用者的作用域从而让闭包在一个动态的上下文中执行
[1, 2, 3].each { entry -> println entry }
//计算五个人互相碰杯，一共能听到多少次碰杯声
def totalClinks = 0
def partyPeople = 100
1.upto(partyPeople) { guestNumber ->//Groovy给Integer新增的upto方法，可以替代Java的for(int i=x,x<n,x++){...}这样的代码
    clinksWithGuest = guestNumber - 1
    totalClinks += clinksWithGuest//closure可以访问和修改外部变量，这在Java中是很难的
}
assert totalClinks == (partyPeople * (partyPeople - 1)) / 2
