package com.github.zjjfly.gia.ch02

@groovy.lang.Grab(group = 'org.apache.commons', module = 'commons-lang3', version = '3.5')
import org.apache.commons.lang3.ClassUtils

/**
 * Created by zjjfly on 2017/2/4.
 */
class Outer {
    class Inner {}
}

assert !ClassUtils.isInnerClass(Outer)
assert ClassUtils.isInnerClass(Outer.Inner)
