package main.groovy.com.github.zjjfly.gia.ch01
/**
 * Created by zjjfly on 2017/2/3.
 */
def fibo = [1, 1]
def gold = [1, 2]

def isGolden(candidate) {
    def small = 1
    def big = small * candidate
    return isCloseEnough((small + big) / big, big / small)
}

def isCloseEnough(a, b) { return (a - b).abs() < 1.0e-9 }

while (!isGolden(gold[-1])) {
    fibo.add(fibo[-1] + fibo[-2])
    gold.add(fibo[-1] / fibo[-2])
}

def getRepls() {
    [
            "a": 1,
            "b": 2
    ]
}

println "found golden ratio with fibo(${fibo.size - 1}) as"
println fibo[-1] + " / " + fibo[-2] + " = " + gold[-1]
println "_" * 10 + "|" + "_" * (10 * gold[-1])
println(getRepls())
