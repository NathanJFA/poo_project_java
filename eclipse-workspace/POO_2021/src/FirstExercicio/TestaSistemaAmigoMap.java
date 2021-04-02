package FirstExercicio;

import java.util.HashMap;
import java.util.Set;

import javax.swing.JOptionPane;

public class TestaSistemaAmigoMap {
	public static void main(String [] args) {
		int quantMaxMensagens = Integer.parseInt(JOptionPane.showInputDialog("Qual número Máximo de mensagens? "));
		//TODO
		SistemaAmigoMap sistema = new SistemaAmigoMap(quantMaxMensagens);		
		boolean sair = false;
		while(!sair) {
			int opcao = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção: "
					+ "\n1. Testar programa."
					+ "\n2. Pesquisar amigo secreto de alguém."
					+ "\n3. Resultado do sorteio"
					+ "\n4. Sair."));
			switch(opcao) {
				case(1):
					int totalAmigos = Integer.parseInt(JOptionPane.showInputDialog("Quantidade máxima de amigos na brincadeira? "));
					for(int a = 0; a < totalAmigos; a++) {
						String nome = JOptionPane.showInputDialog("Nome ("+a+"): ");
						String email = JOptionPane.showInputDialog("email ("+a+"): ");
						try {
							sistema.cadastraAmigo(nome, email);
						} catch (AmigoJaExisteException e) {
							e.printStackTrace();
						}					
					}break;	
					
								
				
				case(2):
					sistema.sortear();
					String amigoSecreto = JOptionPane.showInputDialog("Deseja pesquisar o amigo secreto de quem? ");
				
					try {
						String amigoSecretoDe = sistema.pesquisaAmigoSecretoDe(amigoSecreto);
						JOptionPane.showMessageDialog(null, amigoSecretoDe);
					} catch (AmigoNaoSorteadoException e) {
						e.printStackTrace();
					} catch (AmigoInexistenteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case(3):
					HashMap<String, Amigo> resultSorteio = sistema.resultadoSorteio();
					Set<String> keys = resultSorteio.keySet();
					for(String key: keys){
						System.out.println("O amigo secreto de "+ resultSorteio.get(key) +" é " +resultSorteio.get(key).getEmailAmigoSorteado());
					}
					break;
				case(4):
					sair = true;
					break;
			}
		}
		/*
		//ENVIAR MENSAGEM P TODOS 
		String remetente = JOptionPane.showInputDialog("Email do Remetente?");
		String texto = JOptionPane.showInputDialog("Texto a enviar: ");
		int ehAnonima = Integer.parseInt(JOptionPane.showInputDialog("A mensagem é anonima? Responda com 1 para (SIM) e 0 para (NÃO)"));
		boolean anonima;
		if(ehAnonima == 1) {
			anonima = true;
		}
		else{
			anonima = false;
		}
		sistema.enviarMensagemParaTodos(texto, remetente, anonima);
		*/
	}

}
