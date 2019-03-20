# dispatchEvent
this is a dispatch event demo.
**android事件分发机制的详解**

# **为什么要使用事件分发？**
##在很多情况下，布局复杂的界面，由于有很多的控件重叠摆放在一起，这就出现了点击事件的重复性，在开发中经常遇到点击某个子控件，父控件的点击事件需要屏蔽掉，这时候就需要用到事件分发机制来协调处理。
# 事件分发机制的分发和消费方向
# 事件分发方向
事件分发方向：DecorView->phoneWindow->Activity->ViewGroup->View
# 事件消费方向
事件消费方向(自向而上)：View->ViewGroup->Activity->PhoneWindow->DecorView;流程图这里我就不画了，因为大家都是有一定经验的人。
# demo目录结构
![目录结构](https://img-blog.csdnimg.cn/20190319182704504.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUxNjEwOQ==,size_16,color_FFFFFF,t_70)
# demo的介绍
项目结构很简单，项目最上层的父类使用的Activity，然后用activity中包含一个ViewGroup的LinearLayout，子控件button做最后的点击事件处理，项目使用起来也比较简单，比如LinearLayout下的有三个事件方dispatchTouchEvent(),onInterceptTouchEvent(),onTouchEvent()三个方法；在手机显示的是设置每个方法的返回值，通过弹出listPopwindow进行返回值的设置，然后需要打开androidstudio日志打印点击事件的方法执行的顺序和哪些方法调用。
# 项目的讲解
在activity这个最上级的窗口中，重写了dispatchTouchEvent()、onTouchEvent()两个方法，这两个方法的返回值，暂时都继承父类的方法，因为如果在activity的dispatchTouchEvent()方法中返回false/true都只会调用一次activity的dispatchTouchEvent方法，无法把事件传递下去了。只会传递到更上级去了。
现在关键还是从LineaLayout控件中的方法讲解![在这里插入图片描述](https://img-blog.csdnimg.cn/20190319192932767.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUxNjEwOQ==,size_16,color_FFFFFF,t_70)
1.当LinearLayout控件中的dispatchTouchEvent()方法中返回参数为“true”的时候，分别执行的是Activity中的dispatchTouchEvent方法，LinearLayout中的dispatchTouchEvent，其它的是重复打印的，所以当ViewGroup的dispatchTouchEvent方法返回值是“true”的时候，意思不是事件继续分发下去，而是自行处理，这和我以前理解的有些出入。
2.当LinearLayout控件中的dispatchTouchEvent方法中返回参数为"false"的时候，如下图所示
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190319193516490.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUxNjEwOQ==,size_16,color_FFFFFF,t_70)
执行的顺序为：Activity的dispatchTouchEvent方法，LineaLayout的dispatchTouchEvent，最后执行activity的onTouchEvent()方法；以此我们可以看出当LinearLayout的dispatchTouchEvent返回值为“false”，不进行分发下去，也就是连onTouchEvent方法也是交给上级处理。
3.当LineaLayout的dispatchTouchEvent方法返回值为super.dispatchTouchEvent(ev)的时候，如下图所示
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190319195141255.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUxNjEwOQ==,size_16,color_FFFFFF,t_70)
从上述截图可以看出分发方法还是从activity的dispatchTouchEvent方法开始执行，再调用LineaLayout的dispatchTouchEvent方法，接着调用onInterceptTouchEvent方法，表示是否拦截当前的点击方法，如果onInterceptTouchEvent返回的是false/super方法就表示点击事件不拦截，分发给button子控件，所以就会调用button的dispatchTouchEvent方法和onTouchEvent方法。
这时候就要考虑button中的两个分发方法的返回值了：
1.如果button中的dispatchTouchEvent方法中返回true,如下图所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190319200602389.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUxNjEwOQ==,size_16,color_FFFFFF,t_70)
走的几个方法如下：activity的dispatchTouchEvent方法，LineaLayout的dispatchTouchEvent方法，LineaLayout的onInterceptTouchEvent方法，最后只会执行button的dispatchTouchEvent()方法，因为button中dispatchTouchEvent返回的参数是true，也就相当于这个事件链中断了，不会再分发下去了，也不会沿上级传递了。
2.如果button中的dispatchTouchEvent方法中返回false,如下图所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190319201108471.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUxNjEwOQ==,size_16,color_FFFFFF,t_70)
前面三个方法还是一样的执行，关键看后面两个方法，先执行的是button的dispatchTouchEvent方法，因为在button的dispatchTouchEvent方法中返回了false，表示当前的事件不分发给当前的button下的onTouchEvent方法了，所以button的onTouchEvent方法就不执行了，当时事件并没有中断，可以往上传递，所以就只能执行button的上一级父类的onTouchEvent方法了，也就是LinearLayout的onTouchEvent方法了。
3.如果button中的dispatchTouchEvent方法中返回super,如下图所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190319201708281.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUxNjEwOQ==,size_16,color_FFFFFF,t_70)
同样执行五个方法，前面三个方法和上面一样，这时候会调用button的dispatchTouchEvent，因为是super的，所以事件默认是继续分发下去，如果button的dispatchTouchEvent是super/true的，默认就是自己消费掉了，不会再向上级传递了。反之button的dispatchTouchEvent是false的，自己不完全消费掉，还是可以往上级传递，调用LinearLayout的onTouchEvent方法，如下图所示：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019031920212369.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjUxNjEwOQ==,size_16,color_FFFFFF,t_70)
项目到这，基本就差不多。很好理解把！好了多了也不说了，想了解详情可以下载下demo,自己测试一下就什么都懂了。
链接地址：[事件分发demo链接地址](https://github.com/ailibin/dispatchEvent)
# 总结
总之，如果一个控件中，不管是Activity还是ViewGroup，view的dispatchTouchEvent方法中返回true，就表示事件在当前控件断掉了，不会向上级传递也不会当前控件消费；一个ViewGroup/View/Activity的dispatchTouchEvent方法如果返回false，意思就是事件不再当前的控件分发下去，就会交给上一级的父类onTouchEvent方法进行处理；一个ViewGroup的onInterceptTouchEvent拦截方法，返回值false/super，都表示不拦截当前要传递的事件，事件往下级传递，最终如果子类的onTouchEvent方法中返回super/true，表示事件处理完毕，整个流程结束，如果是返回false，事件交由上一级处理，如果上一级的onTouchEvent方法中还是返回false,继续交由上级处理，依次类推...
