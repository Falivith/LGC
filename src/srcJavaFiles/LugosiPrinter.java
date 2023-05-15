import java.io.*;
import java.util.*;

public class LugosiPrinter {
	public LugosiPrinter(LugosiTree tree) {
		System.out.println("#include <stdio.h>");
		System.out.println("#include <stdbool.h>");
		System.out.println("#include <stdlib.h>");
		System.out.println("#include <string.h>");

        for(Func f : tree.funcoes){ 
            printerFunc(f);
        }

        printerMain(tree.main);
    }

    public static void printerMain(Main main){
		System.out.print("int main()"); 
		System.out.println("{ ");

		for(VarDecl vardecl: main.varD){
			printerVarDecl(vardecl);
		}
		printerSeqComandos(main.comandos);
		
		System.out.println("\treturn 0; \n}");
	}

    public static void printerFunc(Func f){
        int cont = 0;
        System.out.print(f.tipo + " ");
        System.out.print(f.id + " (");

        for(ListaArg arg: f.argumentos){
            printerArgs(arg);
            if((f.argumentos).size() > 1 && cont < (f.argumentos).size() - 1){
                System.out.print(", ");
            }
            cont+=1;
        }

        System.out.println("){");

        for(VarDecl v : f.vd){ 
            printerVarDecl(v);
        }

        for(Comando c : f.seqComandos){
            printerCmd(c);
        }
        System.out.println("}");
    }

    public static void printerArgs(ListaArg arg){
        	System.out.print(arg.tipo + " ");
		    System.out.print(arg.id);
    }

    public static void printerVarDecl(VarDecl v){
        System.out.println("\t" + v.tipo +" "+ v.var +"; ");
    }

    public static void printerCmd(Comando c){
        
        if (c instanceof CmdAtribuicao){
			printerCmdAtribuicao(c);
		}
        else if (c instanceof CmdChamadaFuncao){
			printerCmdChamadaFuncao(c);
		}
		else if (c instanceof CmdIf){
			printerCmdIf(c);
		}
		else if (c instanceof CmdWhile){
			printerCmdWhile(c);
		}
		else if (c instanceof CmdDoWhile){
			printerCmdDoWhile(c);
		}
		else if (c instanceof CmdReturn){
			printerCmdReturn(c);
		}
		else if (c instanceof CmdPrint){
			printerCmdPrint(c);
		}
    }

    public static void printerCmdNR(Object e){
        if(e instanceof Exp){
            System.out.print("= ");
            printerExp((Exp) e);
            System.out.println(";");
        }
		else if (e instanceof ArrayList){
			System.out.print(" (");
			int tamanhoExp = ((ArrayList <Exp>)e).size(); 
			int cont = 0;
		
			for (Exp expressao : ((ArrayList <Exp>)e)) {
				printerExp(expressao);
				if (tamanhoExp > 1 && cont < tamanhoExp -1){
					System.out.print(", ");
				}
				cont+= 1;
			}
			System.out.print(")");
			System.out.println(";");		
		}
    }
    
    public static void printerCmdAtribuicao(Comando c){
		System.out.print("\t" + ((CmdAtribuicao) c).var + " ");
		printerCmdNR(((CmdAtribuicao) c).e);
    }

    public static void printerCmdChamadaFuncao(Comando c){
		System.out.print("\t" + ((CmdChamadaFuncao) c).nomeFuncao + " ");
		printerCmdNR(((CmdChamadaFuncao) c).argumentos);
	}

    public static void printerCmdIf(Comando c){
        System.out.print("\tif (");
		printerExp(((CmdIf) c).exp);
		System.out.print(") ");
		System.out.println("{ ");
		printerSeqComandos(((CmdIf) c).then); 
		System.out.print("\t}");
		System.out.println(";");
    }

    public static void printerCmdWhile(Comando c){
        System.out.print("\twhile ");
		System.out.print("(");
		printerExp((((CmdWhile) c).exp));
		System.out.print(") ");
		System.out.println("{ ");
		printerSeqComandos(((CmdWhile) c).comandos); 
		System.out.print("}");
		System.out.println(";");
    }

    public static void printerCmdDoWhile(Comando c){
        System.out.print("\tdo ");
		System.out.println("{ ");
		printerSeqComandos(((CmdDoWhile) c).comandos); 
		System.out.print("\t} ");
		System.out.print("while ");
		System.out.print("(");
		printerExp(((CmdDoWhile) c).exp);
		System.out.print(")");
		System.out.println(";");
    }

    public static void printerCmdReturn(Comando c){
        System.out.print("return ");	
		printerExp(((CmdReturn) c).exp);
		System.out.println(";");
    }

    public static void printerCmdPrint(Comando c){
        System.out.print("\tprintf(\"%d\", ");
		printerExp(((CmdPrint) c).exp);
		System.out.print(")");
		System.out.println(";");
    }

    public static void printerSeqComandos(ArrayList <Comando> comandos){
		for (Comando c : comandos) {
			printerCmd(c);
		}
	}

    public static void printerExp(Exp e){
        if(e instanceof Fator){
            printerFtr(e);
        }
        else{
            printerExpOperador(e);
        }
    }

    public static void printerFtr(Exp e){
        if (e instanceof FtrExpPar){
			printerFtrExpPar(e);
		}
		else if (e instanceof FtrNumLiteral){
			printerFtrNumLiteral(e);
		}
		else if (e instanceof FtrTipo){
			printerFtrTipo(e);
		}
		else if (e instanceof FtrTokenId){
			printerFtrTokenId(e);
		}
    }

    public static void printerFtrExpPar(Exp e){
        System.out.print( ((FtrExpPar) e).id + " ( ");
		
		int tamanhoExp = (((FtrExpPar)e).listExp).size(); 
		int cont = 0;
		
		for (Exp e1 : ((FtrExpPar)e).listExp){
			printerFtr(e1);
			if (tamanhoExp > 1 && cont < tamanhoExp-1){
				System.out.print(", ");
			}
			cont+= 1;
		}

        System.out.print( ")" );
    }

    public static void printerFtrTokenId(Exp e){
        System.out.print( ((FtrTokenId) e).id + " ");
    }

    public static void printerFtrNumLiteral(Exp e){
		System.out.print( ((FtrNumLiteral) e).numLiteral + " ");
	}

    public static void printerExpOperador(Exp e){
        System.out.print("(");
	  	printerExp(((ExpOperador)e).arg1);
	  	System.out.print(((ExpOperador) e).operador + " ");
	  	printerExp(((ExpOperador)e).arg2);	
	  	System.out.print(")");
    }

    public static void printerFtrTipo(Exp e){
		System.out.print(((FtrTipo) e).tipo + " ");
	}
}
