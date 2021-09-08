package main.groovy.com.github.zjjfly.gia.ch02

import groovy.transform.Immutable

/**
 * 使用注解
 * Created by zjjfly on 2017/2/4.
 */
//@Immutable会让编译器执行一个AST(抽象语法树)转换，生成如getter,constructor,hashCode,equals等帮助函数
//这一点和Scala的case class很像
@Immutable
class FixedBook {
    String title
}

FixedBook gina = new FixedBook('Groovy in Action')
FixedBook regina = new FixedBook(title: 'Groovy in Action')//另一种调用构造函数的方法，传名参数
assert gina.title == 'Groovy in Action'
assert gina == regina//一个标准的equals方法会被自动生成
try {
    gina.title = 'Oops!'
    assert false, "should not reach here"
} catch (ReadOnlyPropertyException expected) {
    println "Expected Error:'$expected.message'"
}
