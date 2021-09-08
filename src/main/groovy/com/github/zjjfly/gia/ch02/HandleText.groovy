package main.groovy.com.github.zjjfly.gia.ch02

/**
 * Created by zjjfly on 2017/2/4.
 */
//GString
def nick = 'ReGina'
def book = 'Groovy in Action, 2nd ed.'
assert "$nick is $book" == 'ReGina is Groovy in Action, 2nd ed.'

//正则
assert '12345' =~ /\d+/
assert 'xxxxx' == '12345'.replaceAll(/\d/, 'x')

//Groovy的数字都是对象，而不是原始类型
def x = 1
def y = 2
assert x + y == 3
assert x.plus(y) == 3
assert x instanceof Integer
