import java.io.*;

enum TokenType{ NUM, NUMD, SOMA, SUB, DIV, MULT,APar,FPar, EOF}

class Token{
  char lexema;
  String lexema1;
  TokenType token;

 Token (char l, TokenType t)
 	{ lexema=l;token = t;}

	Token (String l, TokenType t)
	{ lexema1 = l;token = t;}

}

class AnaliseLexica {

	BufferedReader arquivo;

	AnaliseLexica(String a) throws Exception
	{
		
	 	this.arquivo = new BufferedReader(new FileReader(a));
		
	}

	Token getNextToken() throws Exception
	{
		Token token;
		int eof = -1;
		char currchar;
		int currchar1;

		int nextchar1;
		char nextchar;

		do{
			currchar1 =  arquivo.read();
			currchar = (char) currchar1;
		} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');

		if(currchar1 != eof && currchar1 !=10)
		{
			if (currchar >= '0' && currchar <= '9') {

				arquivo.mark(10);

				nextchar1 = arquivo.read();
				nextchar = (char)nextchar1;

				if(nextchar >= '0' && nextchar <= '9'){
					String number = "";
					number += currchar;
					number +=  nextchar;

					arquivo.mark(10);
					nextchar1 = arquivo.read();
					nextchar = (char)nextchar1;

					while (nextchar >= '0' && nextchar <= '9'){

						number += nextchar;
						arquivo.mark(10);
						nextchar1 = arquivo.read();
						nextchar = (char)nextchar1;
					}

					arquivo.reset();
					return (new Token( number, TokenType.NUMD));
				}

				arquivo.reset();
				return (new Token(currchar, TokenType.NUM));
			}
			else
					switch (currchar){
						case '(':
							return (new Token (currchar,TokenType.APar));
						case ')':
							return (new Token (currchar,TokenType.FPar));
						case '+':
							return (new Token (currchar,TokenType.SOMA));
						case '*':
							return (new Token (currchar,TokenType.MULT));
						case '-':
							return (new Token (currchar,TokenType.SUB));
						case '/':
							return (new Token (currchar,TokenType.DIV));
						
						default: throw (new Exception("Caractere inválido: " + ((int) currchar)));
					}
			}

			arquivo.close();
			
		return (new Token(currchar,TokenType.EOF));
		
	}
}
