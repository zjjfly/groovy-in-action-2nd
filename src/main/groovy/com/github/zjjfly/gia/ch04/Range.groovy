package main.groovy.com.github.zjjfly.gia.ch04

//range使用..定义,这个操作符的优先级较低,所以一般放在括号里
assert (0..10).contains(0)
assert (0..10).contains(5)
assert (0..10).contains(10)
assert !(0..10).contains(-1)
assert !(0..10).contains(11)

//..<也用于声明range,但它不包括右边界
assert (0..<10).contains(9)
assert !(0..<10).contains(10)

//range对应的类型
def a = 0..10
assert a instanceof groovy.lang.Range

//使用明确的构造方法声明Range
a = new IntRange(0, 10)
assert a.contains(5)

//range也可以是浮点类型
assert (0.0..1.0).contains(1.0)
//containsWithinBounds测试是否包含在from和to确定的范围内,不一定要在这个Range中
assert (0.0..1.0).containsWithinBounds(0.5)
assert !(0.0..1.0).contains(0.5)

def today = new Date()
//gdk在Date类型中添加了加减方法,让它可以方便的加减天数
def yesterday = today - 1
//range用于日期
assert (yesterday..today).size() == 2

//range用于string,gdk给String加入了previous和next方法
assert ('a'..'c').contains('b')

//for-in遍历range
def log = ''
for (i in 9..5) {
    log += i
}
assert log == '98765'
//each遍历range
log = ''
(9..<5).each {
    log += it
}
assert log == '9876'

//Range作为一等对象,重写了isCase
//所以Range可以用于switch和grep
assert 5 in 0..10
assert (0..10).isCase(5)
def age = 36
switch (age) {
    case 16..20: insuranceRate = 0.05; break
    case 21..50: insuranceRate = 0.06; break
    case 51..65: insuranceRate = 0.07; break
    default: throw new IllegalArgumentException()
}
assert insuranceRate == 0.06
def ages = [20, 36, 42, 56]
def midage = 21..50
assert ages.grep(midage) == [36, 42]

//一个类要使用range语法,只需满足两点:
//1.它要包含previous()和next()语法
//2.它实现了Comparable接口

class Weekday implements Comparable {
    static final DAYS = [
            'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'
    ]
    private int index = 0

    Weekday(String weekDay) {
        index = DAYS.indexOf(weekDay)
    }

    Weekday next() {
        return new Weekday(DAYS[(index + 1) % DAYS.size()])
    }

    Weekday previous() {
        return new Weekday(DAYS[index - 1])
    }

    int compareTo(Object o) {
        //<=>在Groovy中对应compareTo方法
        return this.index <=> o.index
    }

    String toString() {
        return DAYS[index]
    }
}

def mon = new Weekday('Mon')
def fri = new Weekday('Fri')

def worklog = ''
for (day in mon..fri) {
    worklog += day.toString() + ' '
}
assert worklog == 'Mon Tue Wed Thu Fri '
