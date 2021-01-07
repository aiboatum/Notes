# LaTeX QA

LaTeX 学习文档：见 [LaTeX Studio 学习文档](https://www.latexstudio.net/archives/51802.html)

## LaTeX 如何设置中文字体？

1. 怎么查看电脑中有哪些字体？或者说 LaTeX 如何直接加载自定义的字体？

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

## LaTeX IEEE 参考文献同名作者默认缺省

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
2. 使用 `\usepackage{bm}`，`\bm{}` 最好