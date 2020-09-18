package paginas;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Carrinho {

	private WebDriver driver;

	public Carrinho(WebDriver driver) {
		this.driver = driver;
	}

	public void aumentarItens(String nomeProduto, int qtde) {
		for (int i = 0; i < qtde; i++) {
			WebElement product = driver
					.findElement(By.xpath("//*[contains(text(), '" + nomeProduto + "')]//../div[1]/div[2]"));
			product.click();
		}
	}

	public void diminuirItens(String nomeProduto, int qtde) {

		for (int i = 0; i < qtde; i++) {

			WebElement produto = driver
					.findElement(By.xpath("//*[contains(text(), '" + nomeProduto + "')]//../div[1]/div[1]"));
			produto.click();
		}
	}

	public void finalizarCompra() {
		WebElement finalizar = driver.findElement(By.id("finish-checkout-button"));
		finalizar.click();

	}

	public String validarMsg() {
		WebElement mensagem = driver.findElement(By.xpath("//*[contains(text(),'Pedido realizado com sucesso!')]"));
		return mensagem.getText();

	}

	public void fecharMensagem() {
		WebElement fechar = driver.findElement(By.xpath("//*[contains(text(),'Fechar')]"));
		fechar.click();
	}

	public boolean carregou() {
		return driver.findElement(By.xpath("//*[contains(text(),'BELLA GULA')]")).isDisplayed();
	}

	public void validarTotal() {

		int qtdItem = 0;
		double valorItem = 0.0f;
		double valorTotal = 0.0f;
		double valorSubtotal;

		WebElement quantidadeItemWebElement;
		WebElement valorItemWebElement;

		ArrayList<WebElement> listaItens = (ArrayList<WebElement>) driver
				.findElements(By.xpath("//*[@class='sc-csuQGl wfUhd']"));

		for (int i = 0; i < listaItens.size(); i++) {
			quantidadeItemWebElement = driver.findElement(By.id(String.format("product-%d-qtd", i)));
			valorItemWebElement = driver.findElement(By.id(String.format("product-%d-price", i)));

			qtdItem = Integer.parseInt(quantidadeItemWebElement.getText());
			valorItem = Double.parseDouble(valorItemWebElement.getText().replace("R$", "").replace(",", ".").trim());
			valorTotal += qtdItem * valorItem;
		}
		WebElement subtotal = driver.findElement(By.id("subtotal-price"));
		valorSubtotal = Double.parseDouble(subtotal.getText().replace("R$", "").replace(",", ".").trim());
		assertEquals(valorSubtotal + "", valorTotal + "");
	}
}
