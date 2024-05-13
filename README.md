# Work

##### 粗体的为重要,斜体的为了解.

1.*初步了解程序,比如:可以尝试更改所有的关键词'movie'->'elite'*



3.首先确立出数据库的表

4.开始改后端的类代码


# Attention

1.不要改完就提交,运行并登录一下账号:*super*密码:*123*

2.我们的数据库账号与密码都不同,在拉下来更新的代码时注意修改

3.在提交之前保存好修改的代码pull一下，不然有可能把其他人的更新冲掉

4.在merge之前小心一点不然容易让自己的努力功亏一篑

# Log

04082212 latest已更新，增加了sql文件，包含整个数据库,并且把原本来的README.md改为R.md   -Pardo

04082315 latest已更新,添加user类失败,不过保留了进度,并且经过大致测试其他部分可正常运行  -Pardo

04151722 latest已更新,添加user类成功,并且经过大致测试其他部分可正常运行  -Pardo

04151941 latest已更新,添加enterprise类，administrator类成功，还没有测试  -SpShi

04161740 latest已更新,添加elite类成功,并且经过大致测试其他部分可正常运行  -Pardo

04171200 latest已更新,增加管理员的企业和数据库操作接口并debug和更新了数据库sql文件,并且经过大致测试其他部分可正常运行 -Pardo

04221240 latest已更新,main已同步,更新README.md -Pardo

04221240 latest已更新,增加了EliteServlet -Pardo

04222225 latest已更新,增加了CompServlet与AdminServlet,更新sql文件 -Pardo

04231755 latest已更新,增加了elite_add.jsp，需要测试，尤其是性别选择部分 -SpShi

04281225 latest已更新,发生错误,保留elite_add.jsp并回滚,可以进行登录 -Pardo

04291725 latest已更新,修改了Elite*,Comp*,Job*,可以进行登录 -Pardo

04301316 latest已更新,添加了user_add.jsp等,可以进行登录 -Pardo

05061751 latest已更新,修改了servlet相关,可以进行登录 -Pardo

05062147 latest已更新,实现了前端,修改了jobs相关的bug,添加了注册功能,修改了sql文件,可以进行登录 -前端组

05061751 latest已更新,添加了部分保护机制,为后端函数添加了注释,可以进行登录 -Pardo

05131526 latest已更新,修改了sql文件,记得更新数据库 -Pardo

05131611 latest已更新,修改了type冲突问题,应该改完了 -Pardo



## 怎样提交修改后的代码

main存放正确的代码,一个latest存放最新的,一个alter依然是的main代码作为main的备用方案,修改latest后更新readme,其他人在修改代码之前先看看在自己修改代码后有没有人提交代码同步一下

## todo

代码构成主要的代码都在web与src文件夹下

### src

#### action:
表单的处理,也就是与前端最近的部分,里面所有的req.getParameter
(getParameter() 方法是 javax.servlet.ServletRequest 接口的一部分，常用于从 HTTP 请求（如 GET 或 POST）中提取参数)都是从前端获取信息
setAttribute
(这个函数接受两个参数：
name：这是我们要设置的属性的名称，数据类型是String。
value: 这是我们要设置的值，它的数据类型是Object。
它没有返回值，它的任务就是将值存储在指定的属性名中。
例如，我们可以在session中设置一个用户属性的值：
session.setAttribute("user", user);
在这里，"user"是属性的名称，user则是我们要存储的User对象。
后面，我们可以使用getAttribute()方法来获取存储的对象。)
是往前端发送信息(可以这么理解)

#### bean

具体的类的定义,打开看一下就明白了

#### biz

中间实现代码实现把具体的数据库操作组成方法向servlet中提供

#### dao

与数据库的交互具体细节实现(里面写了具体的sql)

#### filter

过滤一些不合法的访问

#### listener

暂时用不到,之前是为了把movie与member的type提取出来做一个下拉框或选择框做的

#### util

support

#### web:

还是那句话见名知意
_add就是注册或者添加新成员的界面
_list是从数据库中读出所有数据属于管理员能看的界面
_details是详细信息显示下面一般跟一个修改或确认按钮
_modify是修改个人信息的界面
_beta不用管这是当时为了区分管理员与非管理员设定的,现在我们直接写新的就行

然后主要是前后端一起写,确实有点慢,而且我觉得你们也大概看懂了代码,

具体的参数取servlet里找
比如comp_add，就是compservlet的add方法下的这些值，要在html里体现
![XQPVAOJ68HWZ)YC@L`%4K)S_tmb](https://github.com/SpShi/Software-Course-Project-sducs2024/assets/162112744/69838775-6cfe-4570-8c94-b5593a7cbc83)


### 现在需要完成的前端
user_details.jsp
elite_modify.jsp
elite_details.jsp
comp_add.jsp
comp_modify.jsp
comp_details.jsp
jobs_add.jsp
jobs_details.jsp
jobs_remove.jsp
jobs_modify.jsp

