package FirstExercicio;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class SistemaAmigo {
	
	protected List<Amigo> amigos = new ArrayList<>();
	protected List<Mensagem> mensagens = new ArrayList<>();
	protected List<Amigo> amigosAindaNaoSorteados = new ArrayList<>();
	protected List<Amigo> amigosJaSorteados = new ArrayList<>();
	protected int _MENSAGENS;
	
	public SistemaAmigo() {
		_MENSAGENS = 1000;
	}
	public SistemaAmigo(int mensagens) {
		_MENSAGENS = mensagens;
	}
	public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException{
		Amigo amigo = new Amigo (nomeAmigo, emailAmigo);
		boolean lock = false;
		if(!amigos.contains(amigo)) {
			amigos.add(amigo);
		}
		else {
			throw new AmigoJaExisteException("Erro, amigo já está cadastrado!!");
		}
	}
	
	public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException{
		for(Amigo a: amigos) {
			if(a.getEmail().equalsIgnoreCase(emailAmigo)) {
				return a;
			}
		}
		throw new AmigoInexistenteException("Amigo Não Encontrado");
	}
	
	public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima) {
		MensagemParaTodos mensagemTodos = new MensagemParaTodos (texto, emailRemetente, ehAnonima);
		mensagens.add(mensagemTodos);
		
	}
	
	public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
		MensagemParaAlguem mensagemAlguem = new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, ehAnonima);
		mensagens.add(mensagemAlguem);
	}
	
	public List<Mensagem> pesquisarMensagensAnonimas(){
		List<Mensagem> mensagensAnonimas = new ArrayList<>();
		for(Mensagem m: mensagens) {
			if(m.ehAnonima()) {
				mensagensAnonimas.add(m);
			}
		}return mensagensAnonimas;
		
	}
	
	public List<Mensagem> pesquisarTodasAsMensagens(){
		return this.mensagens;
	}
	
	public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException{
		boolean setou = false;
		for(Amigo a: amigos) {
			if(a.getEmail().equalsIgnoreCase(emailDaPessoa)) {
				a.setEmailAmigoSorteado(emailAmigoSorteado);
				setou = true;
				break;
			}
		}if(!setou){
			throw new AmigoInexistenteException("Amigo inexistente, verifique o email.");
		}
	}
	
	public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException{
		for(Amigo a: amigos) {
			if(a.getEmail().equalsIgnoreCase(emailDaPessoa)) {
				if(a.getEmailAmigoSorteado() == null){
					throw new AmigoNaoSorteadoException("Amigo ainda não sorteado..");
				}else {
					return a.getEmailAmigoSorteado();
				}
			}
		}throw new AmigoInexistenteException("Amigo não encontrado");
	}
	
	public void sortear() {

		int [] arrayPosicoes = new int [amigos.size()];
		for(int s = 0; s < arrayPosicoes.length; s++) {
			int indicesSorteados = (int) (Math.random()*amigos.size());
			arrayPosicoes[s] = indicesSorteados;
		}
		List <Integer> indicesUltilizados = new ArrayList <>();
		int contador = 0;
		for(Amigo a1: amigos){	
			a1.setEmailAmigoSorteado(amigos.get(arrayPosicoes[contador]).getEmail());

			indicesUltilizados.add(contador);
			contador++;
			continue;	
		}
	}
	public List<Amigo> resultadoSorteio (){
		return amigos;
	}
}
