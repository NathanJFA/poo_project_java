package FirstExercicio;

import java.util.List;

public class TestaSistemaAmigo {
	public static void main(String [] args) {
		Amigo jose = new Amigo ("José", "jose@gmail.com");
		Amigo maria = new Amigo ("Maria", "maria@gmail.com");
		
		
		SistemaAmigo sistema = new SistemaAmigo();
		sistema.amigos.add(jose);
		sistema.amigos.add(maria);
		
		try {
			sistema.configuraAmigoSecretoDe("jose@gmail.com","maria@gmail.com");
		}catch (AmigoInexistenteException e) {
			// TODO
			e.printStackTrace();
		}
		try{
			sistema.configuraAmigoSecretoDe("maria@gmail.com","jose@gmail.com");
		}catch (AmigoInexistenteException e) {
			// TODO
			e.printStackTrace();
		}
		
		String texto = "Mensagem Teste, M > J";
		String emailRemetente = "maria@gmail.com";
		String emailDestinatario = "jose@gmail.com";
		boolean ehAnonima = true;
		sistema.enviarMensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima);
		
		String texto2 = "Mensagem Teste, J > M";
		String emailRemetente2 = "jose@gmail.com";
		String emailDestinatario2 = "maria@gmail.com";
		boolean ehAnonima2 = true;
		sistema.enviarMensagemParaAlguem(texto2, emailRemetente2, emailDestinatario2, ehAnonima2);
		


		try {
			if(sistema.pesquisaAmigoSecretoDe("maria@gmail.com").equalsIgnoreCase("jose@gmail.com")) {
				System.out.println("J é o amigo secreto de M");
			}
		} catch (AmigoNaoSorteadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AmigoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(sistema.pesquisaAmigoSecretoDe("jose@gmail.com").equalsIgnoreCase("maria@gmail.com")) {
				System.out.println("M é o amigo secreto de J");
			}
		} catch (AmigoNaoSorteadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AmigoInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Mensagem> msgsAnonimas = sistema.pesquisarMensagensAnonimas();
		for(Mensagem m: msgsAnonimas) {
			System.out.println(m.getTextoCompletoAExibir());
		}
		try {
			String amigoSecretoDeJose = sistema.pesquisaAmigoSecretoDe("jose@gmail.com");
			System.out.println("Precisa sair maria >>>> " +amigoSecretoDeJose);
		} catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
