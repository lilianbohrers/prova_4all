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

public class Desafio02 {
	
	public WebDriver driver;
	private Produtos produto;
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
	public void desafio02() {
		produto = new Produtos(driver);
		produto.abrirPagina();
		produto.filtrarCategoria("Bebidas");
		tiraprint.getScreenshot(driver, "CategoriaBebidas");
		produto.selecionarProdutos("Coca-cola lata");
		tiraprint.getScreenshot(driver, "cocaAdded");
		produto.selecionarProdutos("Fanta uva lata");
		tiraprint.getScreenshot(driver, "fantaAdded");
		produto.selecionarProdutos("Água mineral sem gás");
		tiraprint.getScreenshot(driver, "aguaAdded");
		produto.filtrarCategoria("Todos");
		tiraprint.getScreenshot(driver, "CategoriaTodosD2");
		produto.selecionarProdutos("Rissole médio");
		tiraprint.getScreenshot(driver, "RissoleAdded");
		produto.selecionaCarrinho();
		
		carrinho = new Carrinho(driver);		
		assertTrue(carrinho.carregou());
		tiraprint.getScreenshot(driver, "AcessouCarrinhoD2");
		carrinho.aumentarItens("Rissole médio", 9);
		tiraprint.getScreenshot(driver, "AumentarIntesD2");
		carrinho.diminuirItens("Rissole médio", 5);
		tiraprint.getScreenshot(driver, "DiminuirItensD2");
		carrinho.validarTotal();
		tiraprint.getScreenshot(driver, "TotalCompraD2");
		carrinho.finalizarCompra();
		assertEquals(carrinho.validarMsg(),"Pedido realizado com sucesso!");
		tiraprint.getScreenshot(driver, "PedidoD2Realizado");
		carrinho.fecharMensagem();
	
	}
	
	@After
	public void fecharNavegador() {
		driver.quit();
	}
}
