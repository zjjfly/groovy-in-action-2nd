package com.github.zjjfly.gia.ch03

//Groovy有两种字符串:简单字符串和GString,GString支持在字符串中插入动态值

//Groovy的几种字符串字面量
'hello jjzi'//使用单引号的字符串,和Java的字符串字面量相似
def name = "jjzi"//使用双引号的字符串,如果其中不存在未逃逸的$,和上面一种是等价的.
println "hello \$$name".class//存在未逃逸的$,会看成是GString
//三引号的字符串是支持多行的字符串,类似Scala的.
//它的实际类型的确定标准和双引号的字符串是一样的
"""第一行
第二行
第三行"""
//斜杠字符串,这种字符串的转义符只需要一个反斜杠,对于正则表达式特别有用
(/x(\d+)y/)
//但它有两个限制:1.要表示反斜杠\和$需要使用unicode字符来表示;2.最后的一个字符不能是反斜杠
//第一个缺点的解决办法:
/${'\\'}u${1 + 1}/
GString.EMPTY + '\\' + /u${1 + 1}/

/\u005Cu${1 + 1}/

//$反斜杠字符串,它把斜杠字符串的问题基本都解决了,对于$和反斜杠,可以使用$来作为转义符
println($/$$\ /$)//$\

//Groovy里的转义符和Java的基本相同,但它多了一个$的转义在非单引号字符串中
println "\$"

//Groovy要表示char需要显示的指定类型
char a = 'x'
Character b = 'x'
//如果要把String强制转型为Character,有两种方法,但它们只取字符串的第一个字符转成Character
'xs' as char
'xyz'.toCharacter()
