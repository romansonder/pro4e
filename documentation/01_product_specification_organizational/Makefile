NAME	= product_specification
SRCS	= 00_fhnwreport.tex
# Attention: make clean removes all ${JOB}.* files
JOB	= zzz

build: ${SRCS}

	@-pdflatex -jobname ${JOB} -synctex=1 -interaction=nonstopmode ${SRCS}
	@-bibtex ${JOB}.aux
	@-pdflatex -jobname ${JOB} -synctex=1 -interaction=nonstopmode ${SRCS}
	@-pdflatex -jobname ${JOB} -synctex=1 -interaction=nonstopmode ${SRCS}
	@-mv ${JOB}.pdf ${NAME}.pdf
	@echo ""
	@echo "################################################################################"
	@echo "Warnings"
	@echo "--------------------------------------------------------------------------------"
	@-grep -A0 ".*Warning.*" ${JOB}.log | sed 's/Warning/Warning/'
	@-grep -A0 ".*Overfull.*" ${JOB}.log | sed 's/Overfull/Overfull/'
	@-grep -A0 ".*Underfull.*" ${JOB}.log | sed 's/Underfull/Underfull/'
	@echo ""
	@echo "################################################################################"
	@echo "Errors"
	@echo "--------------------------------------------------------------------------------"
	@-grep -A0 -E "(\!|^l\.[0-9].*)" ${JOB}.log | sed 's/l\./Line /'
	@echo "################################################################################"

complete: build clean tar

clean:
	rm -rf ${JOB}.* *.tar.gz

allclean:
	rm -rf ${JOB}.* ${NAME}.pdf *.tar.gz *.aux *.lof *.log *.lot *.out *.toc *.bbl *.blg *.synctex.gz *${NAME}.pdf

tar:
	tar --exclude='${JOB}.*' --exclude='*~' --exclude=*.tar.gz -cvzf ${NAME}.tar.gz *
