package testes;

import numeros.Numeros;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//Utilizar o metodo eUmNumeroRealPositivo com valores que representem unidades e validar retorno
public class NumerosTest {
    private Numeros numeros;

    @Before
    public void SetUp(){
        numeros = new Numeros();
    }

    @Test
    public void testValidarSeUmNumeroRealPositivoComZero() {
        testValidarSeUmNumeroRealPositivo(000);
    }

    @Test
    public void testValidarSeUmNumeroRealPositivo() {
        testValidarSeUmNumeroRealPositivo(1);
    }

    @Test
    public void testValidarSeUmNumeroRealPositivoMaximoInt() {
        testValidarSeUmNumeroRealPositivo(999999999);
    }

    private void testValidarSeUmNumeroRealPositivo(int numero) {
        Assert.assertTrue(numeros.eUmNumeroRealPositivo(numero));
    }

    @Test
    public void testValidarSeUmNumeroRealPositivoComValorNegativo() {
        Assert.assertFalse(numeros.eUmNumeroRealPositivo(-1));
    }

}
