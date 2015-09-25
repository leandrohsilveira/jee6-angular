:repetir
SET /p build="Deseja realizar o maven build antes de iniciar o Wildfly? (s= sim, n=nao, x=cancelar): " %=%

IF /i "%build%"=="s" GOTO sim
IF /i "%build%"=="S" GOTO sim
IF /i "%build%"=="n" GOTO nao
IF /i "%build%"=="N" GOTO nao
IF /i "%build%"=="x" GOTO cancelar
IF /i "%build%"=="X" GOTO cancelar
GOTO repetir

:sim
SET /p parametros="Informe os parametros de build: " %=%

CALL "dist\apache\maven\bin\mvn.bat" %parametros%

:nao
CALL "dist\wildfly\bin\standalone.bat"

:cancelar