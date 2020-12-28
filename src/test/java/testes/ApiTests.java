package testes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class ApiTests {
    private String token;

    @Before
    public void SetUp(){
        RestAssured.baseURI = "http://165.227.93.41";
        RestAssured.basePath = "lojinha";

        //Obtem o token
        token = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        " \"usuariologin\": \"christian.sa\",\n" +
                        " \"usuariosenha\": \"Abc1234\"\n" +
                        "}")
                .when()
                .post("login")
                .then()
                .extract()
                .path("data.token");

        //validar visualmente o retorno
        //System.out.println(token);
    }

    @Test
    public void testBuscandoSomenteUmItemEspecifico() {
        RestAssured
                .given()
                .header("token", token)
                .when()
                .get("produto/9870")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.produtonome", Matchers.equalTo("css prod 1"))
                .body("data.componentes[0].componentenome", Matchers.equalTo("cabos"))
                .body("message", Matchers.equalTo("Detalhando dados do produto"));
    }

    @Test
    public void testBuscandoSomenteUmItemSecundarioEspecifico(){
        RestAssured
                .given()
                    .header("token", token)
                .when()
                    .get("produto/9870/componente")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("data[0].componentenome", Matchers.equalTo("cabos"))
                        .body("data[0].componentequantidade", Matchers.equalTo(3))
                        .body("message", Matchers.equalTo("Listagem de componentes de produto realizada com sucesso"));
    }
}
