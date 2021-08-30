package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ArticlesPage extends Page {

    /**
     * FindBy
     */
    @FindBy(css = "#main > div.product-search-header > div.breadcrumb-container.breadcrumb-top > div > ul > li:last-child")
    private WebElement lastNavigationListItem;

    @FindBy(css = "#search-result-items > ul > li.grid-tile > wainclude")
    private List<WebElement> notShownArticles;

    @FindBy(css = "#search-result-items > ul > li.grid-tile > div.product-tile")
    private List<WebElement> shownArticles;

    @FindBy(css = "#primary > div > div > div.search-result-container > div.search-result-options > div > div > span > span.paging-product-dyn-size")
    private WebElement numberOfArticlesThatShouldBeShown;

    public boolean thereAreNoShownArticles() {
        waitForLoadingPage();
        return this.notShownArticles.size() != 0
                && this.shownArticles.size() != Integer.parseInt(this.numberOfArticlesThatShouldBeShown.getText());
    }

    public boolean isTheRightPage(String collectionSelected) {
        return this.lastNavigationListItem.getText().equals(collectionSelected);
    }

}
