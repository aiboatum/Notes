# shell脚本一些特殊符号

## 分号`;`

单行语句中的`;`用来区分代码块，如
```shell
if [ expression_test ] ; then echo "string" ; fi
# 这里第一个分号是then之前的分号，标识if块结束，第二个是标识then块结束。
# echo块不需要使用分号标识
```
等价于
```shell
if [ expression_test ]
then
echo "string"
fi
```

### 连续的分号

`;;`只用在`case`语句中。
格式为：
```shell
case 变量值 in
pattern1)
cmd1
;;
pattern2)
cmd2
;;
*)
默认的cmd
esac
```

## 管道符 `|`

管道符`|`就是将前面命令的输出作为后一个命令的输入。

## 井号 `#`

除了`#!/bin/bash`指明默认使用bash解释器，其余和python一样就是用于注释。



