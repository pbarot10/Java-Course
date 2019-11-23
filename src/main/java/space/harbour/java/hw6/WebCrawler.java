package main.java.space.harbour.java.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebCrawler implements Runnable {

    private Queue<URL> toVisit;
    private Set<URL> alreadyVisited;
    private ExecutorService executorService;
    private final int nThreads = 10;

    private final String urlRegex = "((http[s]?):((//)|(\\\\\\\\))+"
            + "[\\\\w\\\\d:#@%/;$()~_?\\\\+-=\\\\\\\\\\\\.&]*)";
    private final Pattern pattern = Pattern
            .compile(urlRegex, Pattern.CASE_INSENSITIVE);

    public WebCrawler() {
        toVisit = new ConcurrentLinkedQueue<>();
        alreadyVisited = new HashSet<>();
        executorService = Executors.newFixedThreadPool(nThreads);
    }

    @Override
    public void run() {
        // Remove one element from the queue
        while (!toVisit.isEmpty()) {
            URL url = toVisit.poll();
            synchronized (alreadyVisited) {
                alreadyVisited.add(url);
            }

            String content = getContentOfWebPage(url);
            final Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                String uri = content
                        .substring(matcher.start(0), matcher.end(0));
                try {
                    synchronized (alreadyVisited) {
                        if (!alreadyVisited.contains(url)) {
                            toVisit.add(new URL(uri));
                        }
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getContentOfWebPage(final URL url) {
        final StringBuilder content = new StringBuilder();

        try (InputStream is = url.openConnection().getInputStream();
             InputStreamReader in = new
                     InputStreamReader(is, "UTF-8");
             BufferedReader br = new BufferedReader(in); ) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                content.append(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Failed to retrieve content of "
                    + url.toString());
            e.printStackTrace();
        }

        return content.toString();
    }

    public Set<URL> getVisited() {
        synchronized (alreadyVisited) {
            return alreadyVisited;
        }
    }

    public void setQueue(final Queue<URL> url) {
        toVisit = url;
    }

    public void crawl() {
        for (int i = 0; i < nThreads; i++) {
            executorService.submit(this);
        }
        executorService.shutdown();
    }

    public ExecutorService getExecutor() {
        return executorService;
    }
}
