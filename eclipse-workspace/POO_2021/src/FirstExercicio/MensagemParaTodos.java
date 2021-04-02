package FirstExercicio;

public class MensagemParaTodos extends Mensagem{
	
	public MensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
		super(texto, emailRemetente, anonima);
	}
	public String getTextoCompletoAExibir() {
		if(anonima) {
			return "Mensagem para todos, Texto: "+ texto;
		}
		else {
			return"Mensagem de "+emailRemetente+" para todos, Texto:" + texto;
		}
	}

}
