# java Exception 详细介绍

## 一.什么是异常?
异常是指定程序可以处理的错误
在java中有一种类与它对应，即为Exception类.

而程序不能处理的错误，在java中有一种类可以与它对应，
即为Error类
### 1. Exception类与Error类的区别

|参考点|Exception类|Error类|是否相同|
|-------|----------|-------|---------|
|是否可以实例化|是|是|  相同|
|是否可以继承|是|是|相同|
|父类|Throwable|Throwable|相同|
|我们是否可以抛出|可以|可以|相同|
|是否可以被捕获|可以|不可以|不同|
|是否经常使用|经常使用|几乎不使用|不同|

### 2. 关于异常的一些说明
因为Error类不能被捕获，一旦被抛出，所在的线程直接会结束，
所以我们一般使用Exception类及其子类，而不使用Error类。
除了这一点外，这两者几乎完全相同.
所以我们接下来讲的异常都指Exception类

请问:当抛出了Error的时候，程序一定会结束吗?
<<<<<<< HEAD
不一定，当抛出的Error处在main线程中，程序会结束，
但是当处在其他线程中，只有那个线程会被终止
=======
>>>>>>> 39b241b5c949519e54e994a043c34c007276bfcb
## 二.为什么会产生异常?
异常，按照字面的意思，即为程序在运行的时候发生异于平常的
事情。

异常往往发生在逻辑处理过程中，如方法调用时候缺少调用主体，
进行数学计算的时候，数字除以0发生的错误。

在一般情况下不会出现异常，但是当用户数据,物理条件，网络环境与程序进行交互的时候，
异常发生的可能性就大大提高了.

## 三.常见的异常有哪些?
|类型常见类|类型解释|
|-------------|----------|
|java.lang.NullPointerException|空指针异常| 
|java.lang.ArithmeticException |算数异常| 
|java.lang.ArrayIndexOutOfBoundsException|数组越界异常| 
|java.io.IOException  |输入输出异常异常| 
|java.io.FileNotFoundException|文件未找到异常| 


## 五. 异常的两种处理方式

1. 使用 try{}catch{} 捕获
```
public void hello(){
    try{
        //监控异常区域
    }catch(Exception e){
        // 处理
    }
}
```
2. 使用 throws 进行抛出
```
public void hello() throws Exception{

}
```

### 1.使用 try{}catch(){} 捕获
使用这种方式进行处理，如果发生的异常与catch中的某个小括号里的异常能够进行匹配，
那么就会执行，相应位置的内容,代码如下:
```
try{
    Integer a = null;
    System.out.println(a.toString());
}catch (NullPointerException e){
    System.out.println("one");
}catch (ArithmeticException e){
    System.out.println("two");
}
```
因为被监测的区域发生了空指针异常，所以最后程序如下:
```
one
```
question:如果检测区域里发生了两个不同的异常，请问会发生什么?代码如下:
```
try{
    Integer a = null;
    a.toString();
    int b =  2 / 0 ;
}catch (NullPointerException e){
    System.out.println("one");
}catch (ArithmeticException e){
    System.out.println("two");
}
```
answer:程序会输出one然后结束。
异常被捕获了以后，就会跳出监控区域，执行匹配的catch里的内容，然后如果有finally
就会执行finally里的内容,最后执行之外的内容。

所以它不会再回到了监控区域再往下执行
每当执行的try的内容发生异常时，最多捕获1次异常。

**如果try里面或者catch里面直接return，
那么会return一次，还会执行finally内容,如果finally里有return,则再return一次，取最后一次return
且finally里操作的是那个数据的引用,所以如果是基本数据类型，则不受影响。**
可能的确很晕，请参考五.异常的执行顺序
### 2.使用 throw 进行抛出
```
public void hello() throws IOException {
    FileWriter fileWriter = new FileWriter("hello.txt");
}
```
异常发生后，所在的方法块将会把它往上抛，交给它的调用者去处理，它的调用者可以选择继续往上抛出，
也可以选择进行捕获.

如果你使用这种方式去处理异常，那么这个时候被调用的方法将会变成 **编译时异常** ,
编译器将会强迫你对这种异常进行处理(要么继续往上抛出，要么捕获).

如果最后由主线程抛出，那么最后会交给java虚拟机去进行处理
## 五.异常执行的顺序
异常的执行顺序一般为
try->catch->finally

1. 如果没有finally，那么就变为了 try->catch
2. 如果没有发生异常,那么就变为 try->finally

即使一个方法里，try里和finally里都有return，那么也会依次执行，以下有4道典型的题目
```
// first
public static void main(String[] args) {
System.out.println(hello());
}
public static int hello(){
    int a = 0;
    try{
        a += 1;
        return a+8;
    }catch (Exception e){
        a += 2;
        return a+16 ;
    }finally {
        a += 4;
        return a+32;
    }
}
```
first的答案是37，你答对了吗?
```
//second
public static void main(String[] args) {
    System.out.println(hello());
}
public static int hello(){
    int a = 0;
    try{
        a += 1;
        return a+8;
    }catch (Exception e){
        a += 2;
        return a+16 ;
    }finally {
        a += 4;
    }
}
```
second的答案是9,这里finally里的a+=4并没有影响结果
```
// third
public static void main(String[] args) {
    System.out.println(hello());
}
public static StringBuffer hello(){
    StringBuffer stringBuffer = new StringBuffer();
    try{
        stringBuffer.append("1");
        return stringBuffer.append("4");
    }catch (Exception e){
        stringBuffer.append("2");
        return  stringBuffer.append("5");
    }finally {
        stringBuffer.append("3");
        
    }
}
```
取的是最后一次return的结果为143
```
public static void main(String[] args) {
    System.out.println(hello());
}
    
public static StringBuffer hello(){
    StringBuffer stringBuffer = new StringBuffer();
    try{
        stringBuffer.append("1");
        return stringBuffer.append("4");
    }catch (Exception e){
        stringBuffer.append("2");
        return  stringBuffer.append("5");
    }finally {
        stringBuffer.append("3");
        return stringBuffer.append("6");
    }
}
```
结果为1436

## 六.异常在底层是如何实现的?

## 七.使用异常的优点
程序员不可能预测所有可能发生的问题，所以使用异常可以处理一般
普通if...else 无法处理的问题
## 八.使用异常的缺点
过多的异常处理在巨大的系统中会使得代码显得非常混乱
