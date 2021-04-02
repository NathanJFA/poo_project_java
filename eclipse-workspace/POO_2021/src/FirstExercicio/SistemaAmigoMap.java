package FirstExercicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SistemaAmigoMap {
	protected HashMap<String, Amigo> amigos = new HashMap<>();
	protected int contKeys = 1;
	protected HashMap<Integer, Mensagem> mensagens = new HashMap<>();
	protected HashMap<Integer, Amigo> amigosAindaNaoSorteados = new HashMap<>();
	protected HashMap<Integer, Amigo> amigosJaSorteados = new HashMap<>();
	protected int _MENSAGENS;
	
	public SistemaAmigoMap() {
		_MENSAGENS = 1000;
	}
	public SistemaAmigoMap(int mensagens) {
		_MENSAGENS = mensagens;
	}
	public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException{
		Amigo amigo = new Amigo (nomeAmigo, emailAmigo);
		if(!amigos.containsValue(amigo)) {
			amigos.put(amigo.getEmail(), amigo);
		}
		else {
			throw new AmigoJaExisteException("Erro, amigo já está cadastrado!!");
		}
	}
	
	public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException{
		if(!amigos.containsKey(emailAmigo)) {
			throw new AmigoInexistenteException("Amigo não encontrado no BD");
		}
		return amigos.get(emailAmigo);
	}
	
	public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
		MensagemParaTodos msg = new MensagemParaTodos(texto, emailRemetente, ehAnonima);
		int indice = mensagens.size();
		mensagens.put(indice+1, msg); 
	}
	
	public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
		MensagemParaAlguem msg = new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima);
		int indice = mensagens.size();
		mensagens.put(indice+1, msg);
	}
	
	public HashMap<Integer, Mensagem> pesquisarMensagensAnonimas(){
		HashMap<Integer, Mensagem> msgsAnonimas = new HashMap<>();
		for(int i=1; i<=mensagens.size(); i++) {
			if(mensagens.get(i).ehAnonima()) {
				int indice = mensagens.size();
				msgsAnonimas.put(indice+1, mensagens.get(i));
			}
		}return msgsAnonimas;
	}
	
	public HashMap<Integer, Mensagem> pesquisarTodasAsMensagens(){
		return mensagens;
	}
	
	public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException{
		if(amigos.containsKey(emailDaPessoa)) {
			amigos.get(emailDaPessoa).setEmailAmigoSorteado(emailAmigoSorteado);
		}else {
			throw new AmigoInexistenteException("Amigo Inexistente");
		}
	}
	
	public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException{
		if(!amigos.containsKey(emailDaPessoa)) {
			throw new AmigoInexistenteException("Amigo não encontrado no BD");
		}else{
			if(amigos.get(emailDaPessoa).getEmailAmigoSorteado() != null) {
				return amigos.get(emailDaPessoa).getEmailAmigoSorteado();
			}else {
				throw new AmigoNaoSorteadoException("Amigo ainda não foi sorteado");
			}
		}
	}
	
	public void sortear() {
		
		int [] arrayPosicoes = new int [amigos.size()];
		for(int s = 0; s < arrayPosicoes.length; s++) {
			int indiceSorteados = (int) (Math.random()*amigos.size());
			arrayPosicoes[s] = indiceSorteados;
		}
		List <Integer> indicesUltilizados = new ArrayList <>();
		int contador = 0;
		Set<String> keys = amigos.keySet();
		for(String key: keys) {
			if(amigos.get(key).getEmailAmigoSorteado() != null) {
				amigos.get(key).setEmailAmigoSorteado(amigos.get(arrayPosicoes[contador]).getEmail());
				indicesUltilizados.add(contador);
				contador++;
				continue;	
			}
		}
	}
	
	public HashMap<String,Amigo> resultadoSorteio (){
		return amigos;
	}
}
