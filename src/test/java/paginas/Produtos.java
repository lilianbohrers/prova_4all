package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Produtos {

	private WebDriver driver;

	public Produtos(WebDriver driver) {
		this.driver = driver;
	}

	public Produtos abrirPagina() {
		driver.get("https://shopcart-challenge.4all.com/");
		return this;
	}

	public void filtrarCategoria(String nomeCategoria) {
		WebElement campoCategoria = driver.findElement(By.id("open-categories-btn"));
		campoCategoria.click();
		WebElement categoria = driver.findElement(By.xpath("//*[text()='" + nomeCategoria + "']"));
		categoria.click();
	}

	public void selecionarProdutos(String nomeProduto) {
		WebElement produto = driver.findElement(By.xpath("//*[@class='sc-bdVaJa hRjvgd']//*[text()='" + nomeProduto
				+ "']//../../..//button[contains(text(),'Adicionar ao carrinho')]"));
		produto.click();

	}

	public void selecionaCarrinho() {
		WebElement carrinho = driver.findElement(By.id("cart-btn"));
		carrinho.click();
	}

}
