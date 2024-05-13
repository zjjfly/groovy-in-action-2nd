package com.github.zjjfly.gia.ch03

//数字类型的加减乘操作的强制类型转换原则:
//1.如果其中有一个操作数是Float或Double,结果就是Double
assert (1.2f + 3) instanceof Double
//2.否则,如果其中一个操作数是BigDecimal,结果就是BigDecimal
assert (1.2 + 3) instanceof BigDecimal
//3.否则,如果其中一个操作数是BigInteger,结果就是BigInteger
assert (128g - 2) instanceof BigInteger
//4.否则,如果其中一个操作数是Long,结果就是Long
assert (128L * 3) instanceof Long
//5.否则,结果就是Integer
assert (Byte) 1 + (Byte) 2 instanceof Integer

//其他操作的强制类型转换原则:
//1.如果操作溢出的当前的数字类型的范围,不会强制转型,除非是power函数
assert (Integer.MAX_VALUE + 1) == Integer.MIN_VALUE
//2.对于除法,如果其中有一个操作数是Float或Double,结果就是Double,否则结果类型是BigDecimal
assert (1.2f / 0.3) instanceof Double
assert (1.2 / 0.3) instanceof BigDecimal
assert 1.2 / 0.3 == 4
//3.想要进行整除(结果是整数),可以使用显示的转型或intdiv方法
assert ((int) (12 / 5)) == 2
assert 12.intdiv(4) instanceof Integer
//4.shifting方法只能对Integer和Long型的使用,但你可以在自己的类型中实现,通过操作符重载.它不会强制转型
assert 12 << 2 == 48
assert 12L << 2 == 48
assert Integer.MAX_VALUE << 1 instanceof Integer
//5.power会对结果强制转型
assert Integer.MAX_VALUE**2 instanceof BigInteger
assert 2**3.5 instanceof Double
//6.等于方法在比较之前会把操作数转换成更宽泛的类型以便比较
assert 1.0f == 1g

//GDK提供的一些便利方法
assert 1 == (-1).abs()
assert 2 == 2.5.toInteger()
//as也可以用来强制转型
assert 2 == 2.5 as Integer
assert 2 == (int) 2.5
//四舍五入
assert 3 == 2.5f.round()
//指定精度
assert 3.142 == Math.PI.round(3)
//去掉小数点
assert 4 == 4.5f.trunc()
//使用指定精度,去掉多余的精度
assert 2.718 == Math.E.trunc(3)
//String新增的判断是否是合法的数字字符串
assert '2.14'.isNumber()
//String新增的转成数字的方法
assert 5 == '5'.toInteger()
assert 5.1 == '5.1'.toDouble()
//String也可用as转成数字
assert 5 == '5' as Integer
//cast,只能用于单个字符的字符串,会转成对应的ASCII码
assert 53 == (int) '5'
//字符串拼接,和Java一样
assert '6 times' == 6 + ' times'

//i.times{...},相当于for(int n=0;n<i;n++){...}
def store = ''
10.times {
    store += 'x'
}
assert 'x' * 10 == store

//upto,从当前数字递增1直到一个特定的数,类似Scala的to
store = ''
1.upto(5) { number ->
    store += number
}
assert store == '12345'

//downto,从当前数字递减1直到一个特定的数
store = ''
2.downto(-2) { number ->
    store += number + ' '
}
assert store == '2 1 0 -1 -2 '

//step,从当前数字按指定的步长增长,到大于等于指定数字之前停止
store = ''
//不会包括0.5
0.step(0.5, 0.1) { number ->
    store += number + ' '
}
assert store == '0 0.1 0.2 0.3 0.4 '
