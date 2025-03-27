# aop_tools

学习自《SpringBoot JDK原⽣反序列化链》

通过spring自带的aop，构造以下链子

> tostring() -> 触发object.method()
>
> get('name')-> object.method(param) 
