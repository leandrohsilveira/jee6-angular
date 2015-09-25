SET /p parametros="Informe os parametros para build: " %=%

ECHO "Para executar o build deste projeto, eh necessario JDK 1.8 configurada no JAVA_HOME, que atualmente eh: '%JAVA_HOME%"

:repetir
SET /p trocarJRE="Deseja modificar o diretorio de instalacao da JDK? (s= sim, n=nao, x=cancelar): " %=%

IF /i "%trocarJRE%"=="s" GOTO trocar
IF /i "%trocarJRE%"=="S" GOTO trocar
IF /i "%trocarJRE%"=="n" GOTO manter
IF /i "%trocarJRE%"=="N" GOTO manter
IF /i "%trocarJRE%"=="x" GOTO cancelar
IF /i "%trocarJRE%"=="X" GOTO cancelar
GOTO repetir

:trocar
DIR "C:\Program Files\Java\" /A:D

SET /p jh="Informe a JDK para realizar o Build [C:\Program Files\Java\?]: " %=%

set JAVA_HOME=C:\Program Files\Java\%jh%


:manter
CALL "dist\apache\maven\bin\mvn.bat" %parametros%

:cancelar