package pages;
 
public class PaginaPrincipal extends BasePage {
 
    private String sectionLink = "//nav//a[normalize-space()='%s']";
    private String elegirUnPlanButton = "//a[normalize-space()='Elegir Plan' and @href]";
 //
    public PaginaPrincipal() {
        super(driver);
    }
 
    // Método para navegar a www.freerangetesters.com
    public void navigateToFreeRangeTesters() {
        navigateTo("https://www.freerangetesters.com");
 
    }
 
    public void clickOnSectionNavigationBar(String section) {
        // Reemplaza el marcador de posición en sectionLink con el nombre
        String xpathSection = String.format(sectionLink, section);
        clickElement(xpathSection);
    }

     public void clickOnElegirPlanButton() {
        clickElement(elegirUnPlanButton);
    }
 
}