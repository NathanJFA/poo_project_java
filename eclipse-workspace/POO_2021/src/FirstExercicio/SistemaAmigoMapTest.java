package FirstExercicio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SistemaAmigoMapTest {

	SistemaAmigoMap sistema;
	
	@BeforeEach
	void setUp()  {
		this.sistema = new SistemaAmigoMap();
	}
	@Test
	void testSistemaAmigo() {
		assertTrue(sistema.pesquisarTodasAsMensagens().isEmpty());
		assertThrows(AmigoInexistenteException.class, ()-> sistema.pesquisaAmigo("nathan@gmail.com"));
	}

	@Test
	void testPesquisaECadastraAmigo() throws AmigoInexistenteException{
		
		try{
			sistema.pesquisaAmigo("nathan@teste.com");
			fail("Deveria falhar pois não existe ainda");
		}catch(AmigoInexistenteException e) {
			//OK
		}
		
		try{
			sistema.cadastraAmigo("nathan", "nathan@teste.com");
			Amigo a = sistema.pesquisaAmigo("nathan@teste.com");
			assertEquals("nathan", a.getNome());
			assertEquals("nathan@teste.com", a.getEmail());
		}catch(AmigoInexistenteException | AmigoJaExisteException e) {
			fail("Não deveria lançar exceção");
		} 	
	}
	
	@Test
	void testEnviarMensagemParaTodos() {
		assertTrue(sistema.pesquisarTodasAsMensagens().isEmpty());
		sistema.enviarMensagemParaTodos("texto", "nathan@teste.com", true);
		HashMap<Integer,Mensagem> mensagensAchadas = sistema.pesquisarTodasAsMensagens();
		assertTrue(mensagensAchadas.size()==1);
		assertTrue(mensagensAchadas.get(1).getEmailRemetente().equals("nathan@teste.com"));
	}

	@Test
	void testEnviarMensagemParaAlguem() {
		assertTrue(sistema.pesquisarTodasAsMensagens().isEmpty());
		sistema.enviarMensagemParaAlguem("texto", "nathan@dcx.ufpb.br", "joseane@teste.com", true);
		HashMap<Integer, Mensagem> mensagensAchadas = sistema.pesquisarTodasAsMensagens();
		assertEquals(1, mensagensAchadas.size());
		assertTrue(mensagensAchadas.get(1) instanceof MensagemParaAlguem);
		assertTrue(mensagensAchadas.get(1).getTexto().equals("texto"));
	}

	@Test
	void testPesquisaMensagensAnonimas() {
		assertTrue(sistema.pesquisarTodasAsMensagens().isEmpty());
		sistema.enviarMensagemParaAlguem("texto 1", "nathan@teste.com", "joseane@teste.com", false);
		assertTrue(sistema.pesquisarMensagensAnonimas().isEmpty());
		sistema.enviarMensagemParaAlguem("texto 2", "nathan@teste.com", "joseane@teste.com", true);
		//assertTrue(sistema.pesquisarMensagensAnonimas().isEmpty());
		assertTrue(sistema.pesquisarMensagensAnonimas().size()==1);
	}

	@Test
	void testPesquisaTodasAsMensagens() {
		assertTrue(sistema.pesquisarTodasAsMensagens().isEmpty());
		sistema.enviarMensagemParaAlguem("texto 1", "nathan@teste.com", "joseane@teste.com", false);
		assertTrue(sistema.pesquisarTodasAsMensagens().size()==1);
		sistema.enviarMensagemParaAlguem("texto 2", "nathan@teste.com", "joseane@teste.com", true);
		assertTrue(sistema.pesquisarTodasAsMensagens().size()==2);
	}

	@Test
	void testPesquisaAmigoEConfiguraAmigoSecretoDe() {
		assertThrows(AmigoInexistenteException.class, 
				()-> sistema.pesquisaAmigoSecretoDe("nathan@teste.com"));
		try {
			sistema.cadastraAmigo("Nathan", "nathan@teste.com");
			sistema.cadastraAmigo("Joselia", "joselia@teste.com");
			sistema.configuraAmigoSecretoDe("nathan@teste.com", "joselia@teste.com");
			sistema.configuraAmigoSecretoDe("joselia@teste.com", "nathan@teste.com");
			assertEquals("joselia@teste.com", sistema.pesquisaAmigoSecretoDe("nathan@teste.com"));
			assertEquals("nathan@teste.com", sistema.pesquisaAmigoSecretoDe("joselia@teste.com"));
		} catch (AmigoInexistenteException | AmigoJaExisteException | AmigoNaoSorteadoException e) {
			fail("Não deveria lançar exceção");
		}
	}
}

