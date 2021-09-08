package main.groovy.com.github.zjjfly.gia.ch03

/**
 * Created by zjjfly on 2017/2/13.
 */
//Groovy的数字字面量可以调用方法，在Java中是非法的
assert (1 + 2 + 3).toString() == "6"
//引用类型也可以使用运算符，在Java中也是非法的
assert "abc" - "b" == "ac"

//Groovy可以像声明原始类型那样声明变量，但实际上是包装类型。
assert (15 instanceof Integer)
assert (0xffff instanceof Integer)
assert (100_000_000 instanceof Integer)
assert (100L instanceof Long)
assert (1.23f instanceof Float)
assert (1.23d instanceof Double)
assert (123g instanceof BigInteger)
assert (1.23g instanceof BigDecimal)
assert (1.23 instanceof BigDecimal)
assert (1.23e8 instanceof BigDecimal)
