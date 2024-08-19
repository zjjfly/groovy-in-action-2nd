package com.github.zjjfly.gia.ch01

@groovy.lang.Grab(group = 'org.codehaus.gpars', module = 'gpars', version = '1.2.1')
import groovy.xml.XmlSlurper
import groovyx.gpars.GParsPool

/**
 * Created by zjjfly on 2017/2/3.
 */
//默认导入java.util包
def date = new Date()
println date

//闭包,文件处理
def num = 0
//默认导入java.io包
new File("data.txt").eachLine {
    line ->
        num++
        println "$num: $line"
}

//List字面量
//和java的数组很像，但实际上是List
def classes = [String, List, File]
for (clazz in classes) {//好用的for循环
    println clazz.package.name//更简单的获取属性值
}
//上面的代码的简化版本，可以避免使用for循环
println([String, List, File]*.package*.name)//*号在这里其实可以省略

//处理Xml
def customers = new XmlSlurper().parse(new File("customers.xml"))
for (customer in customers.corporate.customer) {
    println "${customer.@name} works for ${customer.@company}"
}

def urls = ['https://www.groovy-lang.org']*.toURL()
println GParsPool.withPool {
    urls.collectParallel { URL url ->
        url.text.findAll(~/[Gg]roovy/).size()
    }
}
