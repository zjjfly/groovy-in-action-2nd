package main.groovy.com.github.zjjfly.gia.ch03

import java.util.regex.Matcher

//groovy中写正则一般使用斜杠字符串
assert "abc" == /abc/
//这种形式的字符串可能会和行内注释或除号混淆,所以可以用括号把它括起来
(/\d/) //one digit

def twister = 'she sells sea shells at the sea shore of seychelles'
//判断字符串是否包含了匹配正则的子字符串
assert twister =~ /s.a/
//=~实际返回的是一个Matcher,但它在groovy中也可以作为boolean类型使用,如何实现这个特性在之后会探索
def finder = (twister =~ /s.a/)
assert finder instanceof Matcher
//判断字符串是否匹配正则,类似String的matches方法
assert twister ==~ /(\w+ \w+)*/
def WORD = /\w+/
matcher = (twister ==~ /($WORD $WORD)*/)
assert matcher instanceof Boolean
assert !(twister ==~ /s.e/)

//replaceAll
def wordsX = twister.replaceAll(WORD, 'x')
assert wordsX == 'x x x x x x x x x x'

//split
def words = twister.split(/ /)
assert words.size() == 10
assert words[0] == 'she'

def myFairStringy = 'The rain in Spain stays mainly in the plain!'
def wordEnding = /\w*ain/
def rhyme = /\b$wordEnding\b/
def found = ''
//使用eachMatch来连理匹配项.如果正则中没有group,这个匹配项的类型是string
myFairStringy.eachMatch(rhyme) { match ->
    found += match + " "
}
assert found == 'rain Spain plain '

//使用Matcher的each方法,和eachMatch一样使用
found = ''
(myFairStringy =~ rhyme).each { match ->
    found += match + " "
}
assert found == 'rain Spain plain '

//Groovy新键入的replaceAll方法,相比Java的,它的第二个参数是一个可以对每个匹配项进行操作的闭包,这样就可以实现对不同的匹配项做不同的替换
def cloze = myFairStringy.replaceAll(rhyme) { it - 'ain' + '___' }
assert cloze == 'The r___ in Sp___ stays mainly in the pl___!'

//Groovy增强了Matcher,你可以把它看成是一个包含了所有匹配项的列表
def matcher = 'a b c' =~ /\S/
assert matcher[0] == 'a'
assert matcher[1..2] == ['b', 'c']
assert matcher.size() == 3
//搭配Groovy的并行赋值特性可以同时为每个匹配项赋值
def (a, b, c) = 'a b c' =~ /\S/
assert a == 'a'
assert b == 'b'
assert c == 'c'

//正则中有group的情况,匹配项是一个string数组
matcher = 'a:1 b:2 c:3' =~ /(\S+):(\S+)/
matcher.each { full, key, value ->//为每个group赋值,这种做法可以用于所有接受一个Matcher参数的闭包
    assert full.size() == 3
    assert key.size() == 1
    assert value.size() == 1
}
