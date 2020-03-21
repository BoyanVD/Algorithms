package BfsWebCrawler;

public class ApplicationDemo {
    public static void main(String[] args) throws WebCrawlerException {
        WebCrawler crawler = new WebCrawler("https://www.bbc.com");
        crawler.crawl();
        crawler.printPathToURL("http://support.chartbeat.com", 25);
    }
}
