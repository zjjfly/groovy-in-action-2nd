package com.github.zjjfly.gia.ch03

import groovy.transform.Immutable

/**
 * Created by zjjfly on 2017/2/14.
 */
//只要实现相应的方法，类的实例就能使用操作符
@Immutable
class Money {
    int amount
    String currency

    Money plus(Money other) {
        if (null == other) return this
        if (currency != other.currency) {
            throw new IllegalArgumentException("cannot add $other.currency to $currency")
        }
        return new Money(amount + other.amount, currency)
    }

    Money plus(Integer more) {
        return new Money(amount + more, currency)
    }

    Money minus(Money other) {
        if (null == other) return this
        if (currency != other.currency) {
            throw new IllegalArgumentException("cannot minus $other.currency to $currency")
        }
        return new Money(amount - other.amount, currency)
    }

    Money minus(Integer other) {
        return new Money(amount - other, currency)
    }

}

class MyString {
    String value

    boolean isCase(CharSequence ch) {
        value.contains(ch)
    }
}

class Counter {
    Integer maxValue
    private Integer counter = 0

    Iterator iterator() {
        [hasNext: { counter <= maxValue },
         next   : { counter++ }] as Iterator
    }
}

//一个类实现了isCase就可以在switch语句中使用,这点比Java强
def switchMap(val) {
    def result
    switch (val) {
        case [groovy: 'Rocks!', version: '1.7.6']:
            result = "Map contains key '$val'"
            break
        case 60..90:
            result = "Range contains $val"
            break
        case [21, 'test', 9.12]:
            result = "List contains '$val'"
            break
        default:
            result = 'Default'
            break
    }
    result
}


def buck = new Money(100, '$')
assert buck == new Money(100, '$')
assert buck + buck == new Money(200, '$')//Money实现了plus方法，所以可以使用+操作符
assert buck + 1 == new Money(101, '$')
assert buck - buck == new Money(0, '$')
assert buck - 50 == new Money(50, '$')//Money实现了minus方法，所以可以使用-操作符

def myStr = new MyString(value: "jjzi")
assert 'jj' in myStr //重写了isCase,所以可以使用in语法
def counter = new Counter(maxValue: 10)
for (c in counter) {//实现了Iterator iterator()方法,所以可以使用for(x in xs)语法
    println c
}
