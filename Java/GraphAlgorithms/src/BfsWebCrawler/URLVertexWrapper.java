package BfsWebCrawler;

public class URLVertexWrapper {
    private String url;
    private URLVertexWrapper previous;

    URLVertexWrapper(String url) {
        this.url = url;
    }

    URLVertexWrapper(String url, URLVertexWrapper previous) {
        this.url = url;
        this.previous = previous;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public URLVertexWrapper getPrevious() {
        return previous;
    }

    public void setPrevious(URLVertexWrapper previous) {
        this.previous = previous;
    }

    @Override
    public int hashCode() {
        return this.url.hashCode();
    }
}
