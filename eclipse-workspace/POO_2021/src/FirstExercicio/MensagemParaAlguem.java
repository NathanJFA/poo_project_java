package FirstExercicio;

public class MensagemParaAlguem extends Mensagem{
	
	private String emailDestinatario;
	
	public MensagemParaAlguem(String texto,String emailRemetente, String emailDestinatario, boolean anonima) {
		super(texto, emailRemetente, anonima);
		this.emailDestinatario = emailDestinatario;
	}
	public String getEmailDestinatario() {
		return emailDestinatario;
	}
	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}
	public String getTextoCompletoAExibir() {
		if(anonima) {
			return "Mensagem anônima para "+emailDestinatario+". Texto da Mensagem: "+texto;
		}
		else {
			return "Mensagem de "+emailRemetente+" para "+emailDestinatario+". Texto da Mensagem: "+texto;
		}
		
	}
	

}
