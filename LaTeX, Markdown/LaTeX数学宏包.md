# $\LaTeX$ 数学宏包

* [algorithm2e](http://www.ctan.org/tex-archive/macros/latex/contrib/algorithm2e/algorithm2e.pdf)
    
用于 LaTeX2e 中的算法排版。

* [algorithms](http://www.ctan.org/tex-archive/macros/latex/contrib/algorithms/algorithms.pdf)

定义了 algorithmic 和 algorithm 两个环境和一组命令，可用于排版算法。

* [amsbsy](http://www.ctan.org/tex-archive/macros/latex/required/amslatex/math/amsbsy.dtx)

定义了数学中的黑体显示效果，即 `\boldsymbold` 和 `\pmb`，效果分别如下
1. $\boldsymbol{abc}$
2. $\pmb{abc}$

* [amscls](http://www.ctan.org/tex-archive/macros/latex/required/amslatex/classes/instr-l.pdf)
   
定义了 amsart、amsbook 和 amsproc 三种数学源文件类型，包括了美国数学学会所有的出版物样式。提供了可独立使用的定理宏包 [amsthm](https://www.ctan.org/pkg/amsthm)。

* [ANSFonts](http://www.ctan.org/tex-archive/fonts/amsfonts/pdfdoc/amsfndoc.pdf)
    
包含 amsfonts、amssymb、eufrak 和 eucal 四个宏包。

eucal 可更改 $\LaTeX$ 的 `\mathcal` 字体。

eufrack 设置了哥特字体。

* [amsfonts](http://www.ctan.org/tex-archive/fonts/amsfonts/pdfdoc/amsfndoc.pdf)

定义了如下字体

<div>
$$
\begin{array}{lcccccc}
\hline
\text{default} & \verb|\mathbf| & \verb|\mathsf| & \verb|\mathit| & \verb|\mathcal| & \verb|\mathbb| & \verb|\mathfrak|\\
X & \mathbf{X} & \mathsf{X} & \mathit{X} & \mathcal{X} & \mathbb{X} &\mathfrak{X}\\
\hline
\end{array}
$$
</div>

* [amsmath](http://www.ctan.org/tex-archive/macros/latex/required/amslatex/math/amsldoc.pdf)

定义了各种显示多行公式的环境和一系列排版数学公式的命令，如 `\cfrac` 排版连分数

* bm

黑体数学渲染，但是可能会和某些宏包冲突。

* [amsthm](https://www.ctan.org/pkg/amsthm)

排版定理环境，可以自定义环境
```tex
\newtheorem{定理环境名}{标题}[计数器名]
```

* [mathptmx](http://www.ctan.org/tex-archive/macros/latex/required/psnfss/psnfss2e.pdf)

在标准 $\LaTeX$ 中，默认的字体族为计算机现代罗马字体;在数学环境中，大写希腊字母为直立体，小写希腊字母为倾斜体。

加载该宏包可将系统默认的字体族改为 Adobe Times，并将文稿中的数学字符转成虚拟 mathptmx 字体。它只有一个 slantedGreek 选项，使用此选项，数学模式中的大写希腊字母也成为倾斜体。如需变为直立体，比如 `\Gamma` 改为 `\upGamma` 即可，而小写希腊字母则不行。它没有粗体数学字符，`\boldmath` 命令无效，也不推荐使用 `bm` 宏包，可以用命令 `\mathbf` 获得粗体数学字符。

该宏包是 psnfss 宏包套件之一，它将 times 和 mathptm 两个宏包的功能合为一体。

* [theorem](http://www.tug.org/tex-archive/macros/latex/required/tools/theorem.pdf)

$\LaTeX$ 工具宏包套件之一，可以定义不同的定理环境。

