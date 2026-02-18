package steps;

import org.junit.Assert;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import pages.TradeMePage;
import static io.restassured.RestAssured.given;


import java.util.List;

public class TradeMeSteps {

     private ValidatableResponse json;
     private static RequestSpecification request;
     private Response response;

    TradeMePage trademe = new TradeMePage();

    @Given("I navigate to the TradeMe Motor page")
    public void navigateToTradeMotor() {
        trademe.navigateToTradeMotor();
    }

    @When("I select the car make {string}")
    public void selectMake(String make) {
        trademe.selectMakeFromDropdown(make);
    }
 // EL EXPECTEDAMOUNTRESULTS ES EL VALOR QUE YO LE PASO DEDSDE LA TABLA DE EJMPLO EN EL FEATURE
 // EL ASSER LO QUE ME DICE ES QUE EL EXPECTamountof resulkts sea igual a lo que me traje del treame.results amount
    @Then("I can see it has {string} records in the results")
    public void printAmount(String expectedAmountOfResults) {
        trademe.clickSearch();
        Assert.assertEquals("Showing " + expectedAmountOfResults + " results",trademe.resultsAmount()
        );
    }
 // con este metodoo contamos la cantidad de marcas de carros en el combo box, con esa explresion regular
 //especificamos un entero
 // el make amout es el valor que le paso por el feature y vendria siendo mi expectedAmount
 // el actualaMOUNT ES LO que voy a encontrar en la aplicacion
    @Then("I can verify that the number of car makes is (\\d+)$")
    public void returnAmountOfMakes(int makeAmount) {
        int expectedAmount = makeAmount;
        int actualAmount = trademe.makeDropdownSize();
        Assert.assertEquals(expectedAmount, actualAmount);
    }
// este metodo es cuando debe ser mayor a tanto
    //   @Then("I can verify that the number of car makes is mayor a 50")
    // public void returnAmountOfMakesGreaterThan50() {
    // int actualAmount = trademe.makeDropdownSize();
    // Assert.assertTrue(
    //     "Expected more than 50 items but found " + actualAmount,
    //     actualAmount > 150
    // );
// fin metodos mayor a 50 ,recordar acomodar el feature

//************************************Servicio******************************************** */

     @Given("I send the request to the endpoint")
    public void sendGETRequest() {
        request = given().log().all();
    }
//+++++ QUE EL NUMERO SEA IGUAL A TANTO
    // @Then("^I can see there are (\\d+) car makes$")
    // public void validateAmountOfMakes(int expectedMakeAmount) {
    // response = request.when().get("https://api.trademe.co.nz/v1/Categories/UsedCars.json");

    //     json = response.then().statusCode(200);

    //     List<String> jsonResponse =
    //             response.jsonPath().getList("Subcategories.Name");
    //             Assert.assertEquals("Mismatch on the expected total",expectedMakeAmount,jsonResponse.size()
    //     );
    // }

  @Then("I verify the number of car makes is greater than {int}")
public void validateAmountOfMakesGreaterThan(int minExpectedAmount) {

    response = request.when()
            .get("https://api.trademe.co.nz/v1/Categories/UsedCars.json");

    response.then().statusCode(200);

    List<String> jsonResponse =
            response.jsonPath().getList("Subcategories.Name");

    int actualSize = jsonResponse.size();

    Assert.assertTrue(
            "Expected more than " + minExpectedAmount + 
            " car makes but found " + actualSize,
            actualSize > minExpectedAmount
    );
}


}
    