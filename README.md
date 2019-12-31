#spring-boot

# shiro
http://www.ityouknow.com/springboot/2017/06/26/springboot-shiro.html

# 自动装配(starter-demo)
1. SpringBoot在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值
2. 将这些值作为自动配置类导入容器，自动配置类就生效，帮我们进行自动配置工作；
3. 以前我们需要自己配置的东西，自动配置类都帮我们解决了
4. 整个J2EE的整体解决方案和自动配置都在springboot-autoconfigure的jar包中；
5. 它将所有需要导入的组件以全类名的方式返回，这些组件就会被添加到容器中 ；
6. 它会给容器中导入非常多的自动配置类 （xxxAutoConfiguration）,就是给容器中导入这个场景需要的所有组件，并配置好这些组件 ；
7. 有了自动配置类，免去了我们手动编写配置注入功能组件等的工作；
