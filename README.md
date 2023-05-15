# LGC - Lugosi Compiller

Projeto de Compilador para linguagem hipotética "Lugosi"

Primeiro foi feita a análise léxica, depois a análise sintática, e depois a geração de código a partir da árvore sintática extraída do front end do compilador. 

O compilador converte a Linguagem Lugosi para código C.

Para fins de organização, foi adotada a seguinte estratégia para compilação dos arquivos:
- Pra compilar o Lugosi.jj, da pasta "src" dê um "javacc -OUTPUT_DIRECTORY:srcJavaFiles Lugosi.jj"
- Para compilar os arquivos do Parser em conjunto com o gerador de código, vá na pasta "srcJavaFiles" e dê um "javac Lugosi.java -Xlint:unchecked -d classFiles"
- Para executar, entre na pasta "classFiles" e lance "./Lugosi ex.lug" ... ...

O repositório também contém as quatro fases dos exercícios desenvolvidos no começo do semestre, que envolvem a modificação de um compilador simples para expressões matemáticas. 

O código em C será imprimido no console e também sairá em um ".out" na pasta classFiles. 

Desenvolvido em conjunto com André Porto, na disciplina de <strong> Projeto de Compiladores</strong>, primeiro semestre de 2023. 
