import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

class Compilador{

	public static void main(String[]args)
	{	
		ArvoreSintatica arv=null;
	
		try{

			AnaliseLexica al = new AnaliseLexica(args[0]);
			Parser as = new Parser(al);
		
			arv = as.parseProg();
		
			
			CodeGen backend = new CodeGen();
			String codigo = backend.geraCodigo(arv);
			System.out.println(codigo);

			BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
			PrintWriter gravar = new PrintWriter(writer);
			gravar.println(codigo);
			gravar.close();
			writer.close();

		}catch(Exception e)
		{			
			System.out.println("Erro de compilação:\n" + e);
		}



	}
}
