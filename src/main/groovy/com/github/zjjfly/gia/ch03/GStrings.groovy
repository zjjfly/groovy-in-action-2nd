package com.github.zjjfly.gia.ch03

import java.time.format.DateTimeFormatter

//GString就是有一些额外能力的String,GString虽然不是String的子类,但Groovy会在你需要的时候转成String
//让一个String变成GString取决于是否有占位符

def me = 'Tarzan'
def you = 'Jane'
def line = "me $me - you $you"
assert line == 'me Tarzan - you Jane'

TimeZone.default = TimeZone.getTimeZone('GMT')
def date = new Date(0).toLocalDateTime()
def dateMap = [y: date.year - 1900, m: date.monthValue, d: date.dayOfYear]
def out = "Year $dateMap.y Month $dateMap.m Day $dateMap.d"
assert out == 'Year 70 Month 1 Day 1'

def tz = TimeZone.getTimeZone('GMT')
def format = DateTimeFormatter.ofPattern('d MMM YYYY HH:mm:SS z').withZone(tz.toZoneId())
out = "Date is ${date.format(format)} !"//有花括号的里面可以放任意的表达式,花括号表示是一个闭包
assert out == 'Date is 1 一月 1970 00:00:00 GMT !'

//GString最适用于模板字符串
def sql = """
SELECT FROM MyTable
  WHERE Year = $dateMap.y
"""
assert sql == """
SELECT FROM MyTable
  WHERE Year = 70
"""
out = "my 0.02\$"
assert out == 'my 0.02$'

//GString对固定部分和动态部分的处理是分开的,看下面的代码
assert line instanceof GString
assert line.strings[0] == 'me '
assert line.strings[1] == ' - you '
assert line.values[0] == 'Tarzan'
assert line.values[1] == 'Jane'
//GString中的占位符是在声明的时候解析的,然后在转成java.lang.String的时候写入,所以对于某些类型可以实现值的解析是lazy的
def x = 10
def s = "${-> x}"
assert s == '10'
x = 11
assert s == '11'
