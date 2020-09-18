package testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import paginas.Carrinho;
import paginas.Produtos;
import utils.tiraprint;

public class Desafio01 {

	public WebDriver driver;
	private Produtos produtos;
	private Carrinho carrinho;
	
	@Before
	public void abrirNavegador() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	@Test
	public void comprarDoces() {
		produtos = new Produtos(driver);
		produtos.abrirPagina();
		produtos.filtrarCategoria("Doces");
		tiraprint.getScreenshot(driver, "CategoriaDoces");
		produtos.selecionarProdutos("Brigadeiro");
		tiraprint.getScreenshot(driver, "BrigadeiroAdded");
		produtos.selecionarProdutos("Alfajor de chocolate");
		tiraprint.getScreenshot(driver, "AlfajorAdded");
		produtos.filtrarCategoria("Todos");
		tiraprint.getScreenshot(driver, "CategoriaTodosD1");
		produtos.selecionaCarrinho();
		
		carrinho = new Carrinho(driver);
		assertTrue(carrinho.carregou());
		tiraprint.getScreenshot(driver, "AcessouCarrinhoD1");
		carrinho.aumentarItens("Brigadeiro", 4);
		tiraprint.getScreenshot(driver, "AumentarItensD1");
		carrinho.finalizarCompra();
		assertEquals(carrinho.validarMsg(),"Pedido realizado com sucesso!");
		tiraprint.getScreenshot(driver, "PedidoD1Realizado");
		carrinho.fecharMensagem();
				
	}
	
	@After
	public void fecharNavegador() {
		driver.quit();
	}

}
