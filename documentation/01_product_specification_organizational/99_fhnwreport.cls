%%
%% This is file `fhnwreport.cls',
%% generated with the docstrip utility.
%%
%% The original source files were:
%%
%% fhnwreport.dtx
%%
%% IMPORTANT NOTICE:
%%
%% For the copyright see the source file.
%%
%% Any modified versions of this file must be renamed
%% with new filenames distinct from fhnwreport.cls.
%%
%% For distribution of the original source see the terms
%% for copying and modification in the file fhnwreport.dtx.
%%
%% This generated file may be distributed as long as the
%% original source files, as listed above, are part of the
%% same distribution. (The sources need not necessarily be
%% in the same archive or directory.)
\NeedsTeXFormat{LaTeX2e}[1995/12/01]
\ProvidesClass{99_fhnwreport}[2015/03/07 v2.2 PDF-LaTeX class for FHNW reports]
\LoadClass{article}
\RequirePackage{geometry}
\RequirePackage{calc}
\RequirePackage{times}
\RequirePackage{graphicx}
\geometry{a4paper,twoside=false}
\geometry{width=160mm,height=241mm,top=32mm,outer=25mm}
\geometry{footskip=19mm,headsep=13mm}
\newcommand{\CaptionFont}{\em}
\newcommand{\SecFont}{\sf\bfseries}
\newcommand{\SecNumFont}{\sf\bfseries}
\newcommand{\ChapFont}{\sf\bfseries}
\newcommand{\TitleFont}{\sf\bfseries}
\newcommand{\HeaderFont}{\sf\small}
\newcommand{\BackgroundFont}{\em}
\newcommand{\DTfont@author}{\sf\bfseries}
\newcommand{\DTfont@normal}{\sf}
\newcommand{\DTfont@series}{\sf\bfseries}
\newcommand{\DTfont@title}{\sf\bfseries}
\renewcommand\maketitle{\par
  \begingroup
    \renewcommand\thefootnote{\@fnsymbol\c@footnote}%
    \def\@makefnmark{\rlap{\@textsuperscript{\normalfont\@thefnmark}}}%
    \long\def\@makefntext##1{\parindent 1em\noindent
            \hb@xt@1.8em{%
                \hss\@textsuperscript{\normalfont\@thefnmark}}##1}%
    \if@twocolumn
      \ifnum \col@number=\@ne
        \@maketitle
      \else
        \twocolumn[\@maketitle]%
      \fi
    \else
      \newpage
      \global\@topnum\z@   % Prevents figures from going at top of the title page.
      \@maketitle
    \fi
    \thispagestyle{empty}\@thanks
  \endgroup
  \setcounter{footnote}{0}%
  \global\let\thanks\relax
  \global\let\maketitle\relax
  \global\let\@maketitle\relax
  \global\let\@thanks\@empty
  \global\let\@author\@empty
  \global\let\@date\@empty
  \global\let\@title\@empty
  \global\let\title\relax
  \global\let\author\relax
  \global\let\date\relax
  \global\let\and\relax
}
\def\@maketitle{%
  \newpage
  \null
  \setlength{\unitlength}{1cm}%
  \begin{picture}(0,0)
    \linethickness{0.025mm}
    \put(-0.85,2.2){\mbox{\includegraphics[height=12mm]{99_fhnwlogo.pdf}}}
  \end{picture}
  \par%
  \vskip 2em%
  \begin{center}%
    \let \footnote \thanks
    {\LARGE \@title \par}%
    \vskip 1.5em%
    {\large
      \lineskip .5em%
      \begin{tabular}[t]{c}%
        \@author
      \end{tabular}\par}%
    \vskip 1em%
    {\large \@date}%
  \end{center}%
  \par
  \vskip 1.5em}
\endinput
%%
%% End of file `fhnwreport.cls'.
