package pages;

public class TradeMePage extends BasePage {

    private String makeDropdown = "//select[@name='selectedMake']";
    private String searchButton = "//button[@type='submit']";
    private String resultsLabel = "//h3[@class='tm-search-header-result-count__heading ng-star-inserted']";

    public TradeMePage() {
        super(driver);
    }

    public void navigateToTradeMotor() {
        navigateTo("https://www.trademe.co.nz/a/motors");
    }
   // usamos by text de a clase base, tambien podemos usar por id o valor
   //LE PASAMOS DOS ARGUMENTOS UNO ES EL LOCATOR MAKEDROPDOWN QUE ESTA MAS ARRIBA Y EL MAKE ES EL TEXTO QUE QUEREMOS UBICAR
    public void selectMakeFromDropdown(String make) {
        selectFromDropdownByText(makeDropdown, make);
    }

    public void clickSearch() {
        clickElement(searchButton);
    }
// estamos declarando un entero no debe devolver un entero por eso no es void, el drowdown size esta en archivo base
    public int makeDropdownSize() {
        return dropdownSize(makeDropdown);
    }

    public String resultsAmount() {
        return textFromElement(resultsLabel);
    }
}
