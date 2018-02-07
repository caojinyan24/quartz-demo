# quartz的页面管理
完成一个新的调度任务只需要参考DemoJob,实现三个抽象方法.
项目启动时,数据库中会自动插入增加的调度任务信息,同时页面提供执行时间和执行参数的修改

>由于只是在这个工程中做了简单尝试,就应用在了公司的项目代码中;后期对公司的项目代码再次做了功能的扩展和优化,但其中只有关键代码维护在了这个工程中,所以这个项目代码只作为参考.

在此基础上稍加补充可以实现的功能:
* 项目启动时,自动注册所有的调度任务信息
* 点击更新,页面可修改任务名称
* 点击更新,页面可修改调度参数,且点击保存后,调度时间会自动生效
* 点击立即执行,可输入json格式的执行参数(页面自动填充当前数据库中保存的执行参数,可手工修改)
* 调度任务列表中,添加按钮,可查看对应调度任务的执行历史及对应执行状态

后期考虑做一个分布式调度平台,参考另一个jobschedule项目;但对于远程调用的部分,有时间要参考下Dubbo的代码,对netty的调用做一个封装.