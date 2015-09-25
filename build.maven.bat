set /p parametros="Informe os parametros para build: " %=%

set /p jh="Informe a JDK para realizar o Build [C:/Program Files/Java/?]: " %=%

echo "Exibindo diretorio C:/Program Files/Java/" 


call dir "C:/Program Files/Java/" /A:D

set JAVA_HOME="C:\Program Files\Java\%jh%"

echo %JAVA_HOME%

call dist\apache\maven\bin\mvn.bat %parametros%