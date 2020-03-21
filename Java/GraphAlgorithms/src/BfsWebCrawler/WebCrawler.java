package BfsWebCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

    private Queue<URLVertexWrapper> queue;
    private URLVertexWrapper root;
    private Set<String> visited;
//    private static final String URL_REGEX = "%^(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@|\\d{1,3}(?:\\.\\d{1,3}){3}|(?:(?:[a-z\\d\\x{00a1}-\\x{ffff}]+-?)*[a-z\\d\\x{00a1}-\\x{ffff}]+)(?:\\.(?:[a-z\\d\\x{00a1}-\\x{ffff}]+-?)*[a-z\\d\\x{00a1}-\\x{ffff}]+)*(?:\\.[a-z\\x{00a1}-\\x{ffff}]{2,6}))(?::\\d+)?(?:[^\\s]*)?$%iu\n";
    private static final String URL_REGEX = "http://(\\w+\\.)*(\\w+)";

    public WebCrawler(String url) {
        this.root = new URLVertexWrapper(url);
        this.queue = new LinkedList<>();
        this.visited = new HashSet<>();
    }

    public void crawl() throws WebCrawlerException {
        this.queue.add(this.root);
        this.markURLAsVisited(this.root);

        while(!queue.isEmpty()) {
            URLVertexWrapper currentURLWrapper = this.queue.remove();
            String html = extractHtmlFromURL(currentURLWrapper.getUrl());

            String regexp = "http://(\\w+\\.)*(\\w+)";
            /* There i sproblem here ----------------------------------------
            *
            * */
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(html);

            while(matcher.find()) {
                String currentURL = matcher.group();
                URLVertexWrapper current = new URLVertexWrapper(currentURL, currentURLWrapper);

                if (!this.isURLVisited(current)) {
                    this.markURLAsVisited(current);
                    System.out.println("Website URL founded : " + currentURL);
                    queue.add(new URLVertexWrapper(currentURL));
                }
            }
        }
    }

    private URLVertexWrapper findURLByLink(String link, int numberOfMaxSteps) throws WebCrawlerException {
        this.queue.add(this.root);
        this.markURLAsVisited(this.root);
        int currentStep = 0;
        while(!queue.isEmpty() && currentStep < numberOfMaxSteps) {
            URLVertexWrapper currentURLWrapper = this.queue.remove();
            String html = extractHtmlFromURL(currentURLWrapper.getUrl());

            Pattern pattern = Pattern.compile(URL_REGEX);
            Matcher matcher = pattern.matcher(html);

            while(matcher.find()) {
                String currentURL = matcher.group();
                URLVertexWrapper current = new URLVertexWrapper(currentURL, currentURLWrapper);

                if (currentURL.equals(link)) {
                    return current;
                }

                if (!this.isURLVisited(current)) {
                    this.markURLAsVisited(current);
                    System.out.println("Website URL founded : " + currentURL);
                    queue.add(new URLVertexWrapper(currentURL));
                }
            }

            ++currentStep;
        }
        throw new WebCrawlerException("Web Crawler isn't able to find target URL in " + numberOfMaxSteps + " steps !");
    }

    private Stack<URLVertexWrapper> findAncestorsStackTrace(URLVertexWrapper url) {
        Stack<URLVertexWrapper> stack = new Stack<>();
        URLVertexWrapper current = url;
        while (current != null) {
            stack.push(current);
            current = current.getPrevious();
        }

        return stack;
    }

    private List<String> constructPathList(URLVertexWrapper url) {
        List<String> path = new LinkedList<>();
        Stack<URLVertexWrapper> stack = findAncestorsStackTrace(url);
        while(!stack.empty()) {
            path.add(stack.pop().getUrl());
        }

        return path;
    }

    public List<String>  findPathToURL(String url, int numberOfSteps) throws WebCrawlerException {
        URLVertexWrapper wrapper = findURLByLink(url, numberOfSteps);
        return constructPathList(wrapper);
    }

    public void printPathToURL(String url, int numberOfSteps) throws WebCrawlerException {
        List<String> path = findPathToURL(url, numberOfSteps);
        for(int index = 0; index < path.size(); ++index) {
            System.out.println("Link number " + (index + 1) + " : " + path.get(index));
        }
    }

    private boolean isURLVisited(URLVertexWrapper url) {
        return this.visited.contains(url.getUrl());
    }

    private void markURLAsVisited(URLVertexWrapper url) {
        this.visited.add(url.getUrl());
    }

    private String extractHtmlFromURL(String url) throws WebCrawlerException {
        StringBuilder html = new StringBuilder();
        try {
            URL websiteURL = new URL(url);
            InputStreamReader inputStream = new InputStreamReader(websiteURL.openStream());
            BufferedReader reader = new BufferedReader(inputStream);
            String line = "";

            while((line = reader.readLine()) != null) {
                html.append(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new WebCrawlerException("Problem while extracting the HTML from URL : " + url);
        }
        return html.toString();
    }

    public void setRoot(URLVertexWrapper root) {
        this.root = root;
    }
}
