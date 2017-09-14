package com.lx.shell.ui

import com.lx.shell.mvp.model.bean.Idea


/**
 * Created by lixu on 2017/8/22.
 */
fun main(args: Array<String>) {
    var name = ""
    val finalName = ""

    //null声明
    var name2:String ? = null

    var finalName2 : String?
    finalName2 = null


    //空判断
    val length = finalName2?.length


    var namep = "$name sa $finalName "

    var nameq = """
    |6
    |7
    |8
    |9
    """.trimMargin()

    var nump = 22

    if(nump in 0..100){

    }

    /**
     * Set不会引用重复的对象，
     */
    //setOf : 只读
    var set:Set<String> = setOf("1","2","3","4","5","1","2","9")
    for(ss in set){
        println(ss)

    }

    //可变
    var setMutable = mutableListOf<String>()
    setMutable.clear()
    setMutable.get(0)

    //是否为空
    setMutable.isEmpty()


    //遍历集合
    setMutable.forEach {
        println(it)
    }




    //三元表达式
    var san = if(1 > 2) "0" else "1"

    var idea = Idea()

    //类判断
    if(san is String){

    }

    var inn:Int = 0
    if(inn in 0..300){

    }

    //多重判断
    var score = 9
    when(score){
        9,10 -> "1"
        in 6..8 -> "2"

        else -> "other"
    }





    //方法定义
    fun dth(){

    }

    //带有返回值的方法
    fun reInt() : Int{
       return 2
    }

    //带有参数的方法
    fun reInt2(num : Int):Int{
        return  num*2
    }

    fun sum(a : Int,b : Int) : Int{
        return a * b
    }

    fun sum2(a : Int,b : Int) = a * b

    //Unit返回无意义的值
    //Unit 返回类型可以省略
    fun sum3(a : String,b : String): Unit{

    }


    /**
     * 基本类型
     * =：赋值，在逻辑运算时也有效；
     *  ==：等于运算，比较的是值，而不是引用；
     *  ===：完全等于运算，不仅比较值，而且还比较引用，只有两者一致才为真。
     */


    var a = 1
    val s1 = "a is $a"

    a = 2
    val s2 = "${s1.replace("is","was")},but now is $a"


    //条件表达式
    fun maxOf(a : Int,b : Int): Int{

        if(a > b){
            return  a
        }else{
            return b
        }

    }


    fun maxOf2(a : Int,b : Int) = if(a > b) a else b



    //使用可空值及null检测
    fun parseInt(str : String):Int?{
        return str.toInt()
    }


    fun printPro(arg1 : String,arg2 : String){
        val x = parseInt(arg1)
        val y = parseInt(arg2)

        if(x != null && y != null){
            println( x * y)
        }else{
            println("is not a number")
        }
    }


    //Any
    //is 运算符检测一个表达式是否某类型的一个实例。 如果一个不可变的局部变量或属性已经判断出为某类型，那么检测后的分支中可以直接当作该类型使用，无需显式转换：
    fun getStringLength(str : Any) : Int?{
        if(str is String){
            return str.length
        }
        return null
    }

    //or

    fun getStringLength2(str : Any) : Int?{
        if(str !is String) return null
        return  str.length
    }



    //甚至
    fun getStringLength3(str : Any) : Int?{
        if(str is String && str.length > 0){
            return str.length
        }
        return null
    }


    /**
     * for循环
     * indices : index的复数
     */
    val forList = listOf("111","222","333")
    for(str in forList.indices){
        println(str)
    }


    /**
     * when表达式
     */
    fun whenSimple(obj : Any): String = when(obj){

        1 -> ""
        "hello" -> ""
        is Long -> "long"
        !is String -> ""
        else -> ""

    }

    /**
     * range区间
     * 使用 in 运算符来检测某个数字是否在指定区间内：
     * list.indices  输出 0..2
     * list.lastIndex   最后一个下标
      */
    val x = 10
    val y = 9

    //区间内
    if(x in y..y+1){
        //区间内
    }


    //区间外
    val list2 = listOf("aaa","bbb","ccc")
    if(-1 in 0..list2.lastIndex){

    }



}