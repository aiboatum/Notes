# Linux 常用命令

01. `cd` : change directory，切换当前目录

``` shell
cd /tmp     # 切换目录
cd ./path   # 切换当前目录下的 path 目录
cd ../path  # 切换上层目录中的 path 目录
cd ../..    # 返回上上级目录

cd ~        # 切换到 home 目录
```

02. `ls` : list

``` shell
ls -l       # 长格式显示
ls -a       # 列出所有文件，包括隐藏文件
ls -d       # 列出目录
ls R        # 递归列出
```

03. `grep` : 进行筛选工作，分析一行的信息，若其中有我们需要的信息，就将该行显示出来

``` shell
grep -c pattern    # 返回含有 pattern 的行数
grep -i pattern    # 忽略大小写
cat some.txt | grep -i pattern
```

04. `find` : 用于查找，非常强大

``` shell
find / -name filename       # 查找文件名位 filename 的文件
```

05. `cp` : copy

``` shell
cp -a file1 file2   
cp file1 file2 file3 dir    # 把文件 file1,file2,file3 复制到 目录 dir 中
cp 源文件 目标位置/目标名称     # 复制并改名
cp -r 复制目录
```

06. `mv` : move

``` shell
mv -f : force，如果目标文件存在，不会询问，直接覆盖
mv -i : 如果目标文件存在，就会询问是否覆盖
mv file1 file2 file3 dir    # 把文件 ... 移动到目录 dir 中
mv file1 file2              # 重命名 file1
```

07. `rm` : remove

``` shell
rm - r 目录名 
        #  rm recursive （递归删除目录，很显然，如果不空的话，将会逐级向下递归，直到最后的子目录（或子文件），然后询问是否删除，然后逐级返回，此时的目录就一级级变为空目录，进而删除）, 
rm -f 文件名或目录名    # force（直接删除，不逐级询问是否删除）
rm -rf 目录名
```

当然，删除空目录可以是 `rmdir` ，不过这个用的很少

08. `mkdir` : make directory

``` shell
mkdir dir1                    # 当前目录下，创建一个目录
mkdir -p  dir1/dir2/file      # 当前目录下，创建多级目录(递归创建)
```

09. `touch` : 创建一个新文件（若文件不存在）或修改文件的时间戳（文件存在）

10. `cat, more, less` : concatenate，将文件内容完全cat到终端显示中；more 查看文件内容，百分比显示

11. `head, tail`

``` shell
tail -f 循环读取：实时显示
tail -n 尾部 n 行内容
head -n 显示前 n 行内容
head 默认显示前 10 行
```
