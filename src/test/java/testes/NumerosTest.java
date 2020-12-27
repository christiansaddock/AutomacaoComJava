package testes;

import numeros.Numeros;
import org.junit.Assert;
import org.junit.Test;

public class NumerosTest {
    @Test
    public void testValidarSeUmNumeroEUmaUnidade(){
        //Utilizar o metodo EUmaUnidade com valor 9 como unidade
        Numeros numeros = new Numeros();

        boolean eUnidade = numeros.eUmaUnidade(9);

        //Validar que a resposta é verdadeira
        Assert.assertTrue(eUnidade);
    }
}
