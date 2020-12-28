package testes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebUITests {

    public WebDriver navegador;

    @Before
    public void SetUp(){
        //Pré-condições
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ChristianSaddock-FRE\\Estudos\\AutomacaoComJava\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.get("http://165.227.93.41/lojinha-web/");

        //Login
        navegador.findElement(By.id("usuario")).sendKeys("christian.sa");
        navegador.findElement(By.id("senha")).sendKeys("Abc1234");
        navegador.findElement(By.className("btn")).click();
    }

    @Test
    public void testValidarDadosDeUmProduto(){
        navegador.findElement(By.linkText("css prod 1")).click();

        Assert.assertTrue(navegador.getCurrentUrl().contains("produto/editar/9870"));

        String nome = navegador.findElement(By.id("produtonome")).getAttribute("value");
        Assert.assertEquals("css prod 1", nome);

        String valor = navegador.findElement(By.id("produtovalor")).getAttribute("value");
        Assert.assertEquals("975,99", valor);

        String subItemNome = navegador.findElement(By.id("listaComponentes")).findElement(By.cssSelector(".title")).getText();
        Assert.assertEquals("Lojinha cabos", subItemNome);

        String subItemQtde = navegador.findElement(By.id("listaComponentes")).findElement(By.tagName("p")).getText();
        Assert.assertEquals("3 unidades", subItemQtde);
    }

    @Test
    public void testValidarCadastroDeProduto(){
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        navegador.findElement(By.id("produtonome")).sendKeys("prod novo");
        navegador.findElement(By.id("produtovalor")).sendKeys("1.500,52");
        navegador.findElement(By.id("produtocores")).sendKeys("azul, marrom");
        navegador.findElements(By.cssSelector(".btn")).get(0).click();
        /*navegador.findElements(By.className("btn"))
                .stream()
                .filter(btn -> btn.getAttribute("action") == "Salvar")
                .findFirst().get().click();*/

        String msg = navegador.findElement(By.className("toast")).getText();
        Assert.assertEquals("Produto adicionado com sucesso", msg);
    }

    @After
    public void FinalizaTests(){
        navegador.quit();
    }
}
