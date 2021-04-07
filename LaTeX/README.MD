# $\LaTeX$ QA

$\LaTeX$ 学习文档：见 [$\LaTeX$ Studio 学习文档](https://www.latexstudio.net/archives/51802.html)

## $\LaTeX$ 用来做什么？

1. 写学术论文
2. 写个人简历（cv/resume）
3. 写 ppt （beamer）
4. 写 notes


## $\LaTeX$ 如何设置中文字体？

1. 怎么查看电脑中有哪些字体？或者说 $\LaTeX$ 如何直接加载自定义的字体？

``` shell
fc-list -f "%{family}\n" :lang=zh
```

有的下载字体，如 冬青黑、苹方，这个是不在 `:lang=zh` 条目下的，可以去掉此条目即可

如何中文乱码，则利用 `chcp` 命令

``` shell
PS C:\Users\username>:chcp 65001
Active code page: 65001
```

3. 有些字体已经下载，但是没有 `fc-list` 显示出来

字体安装设置为 **为所有用户安装**。

2. 如何设置字体？

``` tex
\documentclass!{article}
\usepackage{xeCJK}
\newcommand{\shusong}{\CJKfontspec{FZShuSong-Z01S}}%方正书宋
\newcommand{\heiti}{\CJKfontspec{FZHei-B01S}}%方正黑体
\newcommand{\kaishu}{\CJKfontspec{FZKai-Z03S}}%方正楷体
\newcommand{\xingkai}{\CJKfontspec{STXingkai}}%华文行楷
\begin{document}
\noindent
\xingkai 这是华文行楷\\
\heiti 这是方正黑体\\
\kaishu 这是方正楷体\\
\shusong 这是方正书宋
\end{document}
```

3. 如何字体刷新？

``` shell
fc-cache -fsv ## 3 次即可
```

* 或者用管理员身份运行 3 次 `fc-cache.exe` 文件
* 或者清空 `\texlive\2018\texmf-var\fonts\cache\` 中的文件

## $\LaTeX$ IEEE 参考文献同名作者默认缺省

打开 `IEEEtran.bst` ，找到以下字段，并进行相应修改即可。

``` tex
% #0 turns off the “dashification” of repeated (i.e., identical to those

% of the previous entry) names. The IEEE normally does this.

% #1 enables

FUNCTION {default.is.dash.repeated.names} { #1 }
```

## 编译 bibtex 时报错 “I couldn't open file name `xxxx.aux'.” 对应

1. 使用 `pdflatex` 编译主文件。
2. 生成 `reference.aux` 后用 `bibtex` 编译 `reference.bib` 文件。结果一直控制台提示“I couldn't open file name `amcs.aux'”。
3. 可以对生成的 `.aux` 文件使用 `bibtex` 编译。
4. 使用 `pdflatex` 对主文件编译两次。

该方法在某些场景下有效。

## 数学字体加黑

1. 使用 `\usepackage{amsmath}`，然后使用 `\boldsymbol{\sigma}`，`\mathbf{}` 只对公式中的普通字母有效
2. 使用 `\usepackage{bm}`，`\bm{}` 最好（但是使用 `\bm{}` 在某些情况下会出现问题）


## MathJax Plugin for Github 的渲染问题

在 Github 上中，使用 MathJax Plugin for Github 插件时渲染公式的时候，会出现一些问题：

* 无法渲染大括号 `{}`
* 渲染 $\LaTeX$ 公式的换行 `\\` 时，无法渲染出换行效果
* 无法渲染 `_` 下标
* 无法渲染 `*` 
* 脚注问题：解决方案不够简洁，暂时不解决此类问题

当然还有其他一些渲染问题，这些问题的出现主要是因为 Markdown 语法默认渲染效果与 Mathjax 语法互相冲突的问题。可见 https://segmentfault.com/a/1190000007261752 和 https://zhuanlan.zhihu.com/p/108766968。

1. 无法渲染大括号

```tex
$A=\{1,2,\ldots,n \}$
```

渲染后类似于 $A=1,2,\ldots,n$。

应带使用 $\LaTeX$ 定界符，即 $\lbrace 1,2,\ldots, n\rbrace $ 即可。

2. 无法渲染换行

这是因为 `\\` 会被 Markdown 解析为 `\` 符号。所以首先的解决方案就是修改为 `\\\\`，但是这样在 VSC 中则渲染出错。为此，我们可以使用 `\newline` 代替 `\\` 即可。


3. 无法解析 `_` 符号

在 $\LaTeX$ 中 `_` 表示下标，而在 Markdown 中 `_` 会生成斜体效果。

4. 对于 `*`，使用 `\ast` 符号


### 使用 HTML 块标签解决 display 公式渲染问题

所以在公示的下标符号 `_` 中，**有时** 会被 Markdown 吃掉，表成 HTML 的标记 `<i>` 从而造成 $\LaTeX$ 的数学公式显示不正常。

鉴于 Markdown 和 HTML 的关系，我们可以用 `<div></div>` 块标签来解决 display 公式的渲染问题，但是对于行间公式则不可以。

```
<div>
$$
\alpha^i=\bigg[(\alpha^{i-1})_{n-1},(\alpha^{i-1})_{n-2},\cdots,(\alpha^{i-1})_2,(P(\alpha^{i-1}))\bigg]
$$
</div>
```

这样就可以解决上面的字符冲突问题，而且这个在 VSC 的 Markdown Preview Enhanced 渲染下也是不变的。

## Word 中如何使用 $\LaTeX$ 代码

使用 aurora 插件，最好的插入 $\LaTeX$ 代码的插件，没有之一。

但是 aurora 的已经早已没人维护了，使用起来也会有许多问题。

最后，排版能用 $\LaTeX$ 就不用 Word。

如何使用 aurora 插件参见 http://www.yuerya.me/post/aurora-%7C-latex-in-word 和 https://blog.csdn.net/m0_37907383/article/details/90238946?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242。