package main.java.space.harbour.java.hw6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class WebCrawlerTest {

    private WebCrawler crawler;
    private Queue<URL> list;
    private final int nPages = 20;

    @Before
    public void setUp() throws Exception {
        list = new ConcurrentLinkedQueue<>();
        crawler = new WebCrawler();
    }

    @After
    public void tearDown() throws Exception {
        crawler = null;
        list = null;
    }

    public WebCrawlerTest() {
    }

    @Test
    public void testWebCrawler() {
        try {
            list.add(new URL("http://zmiaikou.com/"));
            crawler.setQueue(list);
            crawler.crawl();
            if (crawler.getExecutor().isTerminated()) {
                assertTrue(crawler.getVisited()
                        .contains("http://www.zmiaikou.com/cv"));
                assertTrue(crawler.getVisited()
                        .contains("http://www.zmiaikou.com/links"));
                assertTrue(crawler.getVisited()
                        .contains("http://www.zmiaikou.com/teaching"));
                assertTrue(crawler.getVisited()
                        .contains("http://www.zmiaikou.com/research"));
                //System.out.println("Crawled!");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSizeOfCrawler() {
        try {
            list.add(new URL("http://zmiaikou.com/"));
            crawler.setQueue(list);
            crawler.crawl();
            if (crawler.getExecutor().isTerminated()) {
                assertEquals(nPages, crawler.getVisited().size());
                //System.out.println("Crawled!");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetContent() {
        try {
            list.add(new URL("http://zmiaikou.com/"));
            crawler.setQueue(list);
            crawler.crawl();
            if (crawler.getExecutor().isTerminated()) {
                assertNotNull(crawler.getContentOfWebPage(list.element()));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
