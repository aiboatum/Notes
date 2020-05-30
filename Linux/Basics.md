# Linux

## 认识Linux

初步认识：
- linux是一个类unix系统。
- linux指的是linux内核，和广义的linux系统

### Linux版本
- linux内核版本：http://www/kernel.org/
- linux发行版：各个厂家基于linux内核定制不同的系统，
    - redhat enterprise（收费，稳定）
    - fedora 免费，不稳定
    - centos 免费，较为稳定，基于redhat enterprise
    - debian 图形化
    - ubantu 图形化


## 搭建linux系统

vmvare+linux发行版即可。

## kernel和Shell

系统kernel是负责控制底层硬件的。

shell可以视为终端terminal（不完全等价），shell就是一个命令解释器。
- 可以视为系统内核和用户之间的交互层，用户可以通过shell使用内核，进而间接进行硬件操作。
- shell通过调用内核提供的接口（就是各种函数），来进行硬件相关的操作，如文件操作，IO操作，进程管理等。
以上两点可以看出为什么称之为shell（壳），这是相对于kernel（核）而言的。
- shell命令分为内部命令和外部命令。
    - 内部命令是shell解释器自带的命令（较少）。
    - 外部命令，如windows下，cmd可以调用python程序，然后python负责解释python命令
- shell是一个脚本语言。

shell有多种：bash，sh，csh（命令行的壳），kde，cde（图形化的壳）。例如：
windows中：内核是DOS（windows 9x）、Windows NT（windows xp），图形化的壳有windows explorer，命令行的壳有cmd（command）、powershell等。

##  Linux常用命令

这里不给出如何使用，只给出一下解释：

ls: list 
cd: change directory
mkdir: make directory
rmdir: remove directory （删除空目录，用的较少）
rm: remove, rm -r:rm recursive （递归删除目录，很显然，如果不空的话，将会逐级向下递归，直到最后的子目录（或子文件），然后询问是否删除，然后逐级返回，此时的目录就一级级变为空目录，进而删除）, rm -rf： force（直接删除，不逐级询问是否删除）
cat: concatenate，将文件内容完全cat到终端显示中。
more: 查看文件内容，百分比显示
head: 从头部开始显示，一般使用head -n
tail: 唯一特殊的是，可以实时刷新文件的内容。即文件内容如果更新了，会实时显示出来，例如显示系统日志文件。


### apt命令
apt: advanced packaging tools，`apt`命令是16年引入的，是`apt-get,apt-cache,apt-config`中最常用命令的集合，即三者的一个子集。

apt只是提供一个检索和下载机制，安装是调用的`dpkg`，apt设置里有一个对应关系，可以检索到相应的网址，得到一个数据软件库，然后从该库中检索对应的软件，以及相应的依赖关系，之后下载这些软件包。之后交给`dpkg`进行安装。

> apt和apt-get：通过`apt`命令，用户可以在同一地方集中得到所有必要的工具。`apt`具有更精简但足够的命令选线，并且参数选项的组织方式更加有效。例如`apt`默认启用，安装或删除程序时可以看到进度条。因此，**可以直接认为`apt`就是`apt-get`的升级版**。

### sudo命令

首先，root用户的权限最高，root用户又称为超级用户，其ID为0。sudo命令之前，如果要执行管理任务，则需要切换到root用户（使用root密码登录），这时就带来了一定的危险（长期处于root用户下，很有可能做出越权操作）和麻烦（需要知道root密码）。

sudo命令可以暂时提升当前用户的权限，临时以root用户的权限执行命令。sudo不需要root用户密码，只需要自身用户的登录密码。sudoers文件确定谁可以使用sudo命令以及可以做什么。即一个服务器的管理员可以是root以及sudoers文件中的所有用户。也就是说，超级用户在sudoers文件（默认存储在该文件）中记录下，可以使用sudo命令的用户、各个用户可以使用的命令（提升权限的命令）、按照其他规则执行、时间限制等信息。
- sudo命令后，该命令的进程以超级用户的权限运行，并且5分钟（默认值）之内不需要再次输入密码。

比如，服务器中只有极少数人掌握root密码，其他需要一下提高权限的命令，以及做什么。将会被添加到相应服务器的sudoers文件中，并且该授权有时间限制。

--- 

Linux命令的执行时发生了什么？（Bash解释命令时做了什么?)

和windows一样，环境变量的概念是相同的。我们先看，bash解释命令时做了什么：
1. 判断用户是否以绝对路径或相对路径的方式输入命令（如输入`/bin/ls`），如果是的话，则直接执行。这里，命令的路径被显示给出，因此可以直接按照该路径检索该命令的位置，然后执行。
2. 检查是否为alias命令。如`alias zeze=ls`，输入`zeze`将会按照`ls`命令等同来处理。
3. Bash解释器判断用户输入的是内部命令还是外部命令，`type command`可以查看是否为内部命令：
        ```c
        [root@... ~] # type python
        python 是 /usr/bin/python //python命令的位置被给出 
        ```
    内部命令直接有Bash负责解释，外部命令则查找该命令的位置，然后由其命令解释器（如python解释器）负责解释执行。
4. 系统在多个路径中查找用户输入命令的位置，定义这些路径的变量叫做**PATH**，用来告知Bash解释器，可能用来存放命令的位置，然后逐个在这些路径中查找。
    ```c
    [root@... ~] # echo $PATH
    /usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/snap/bin
    // 添加路径到PATH中
    [root@... ~] # PATH=$PATH:/root/bin
    ...:/root/bin
    ```

