package it;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.galenframework.junit.GalenJUnitTestBase;

public class BlogArticlePageTest extends GalenJUnitTestBase {

	@Override
	public WebDriver createDriver() {
		return new FirefoxDriver();
	}

	private TestDevice device;

	public BlogArticlePageTest(final TestDevice testDevice) {
		super();
		this.device = testDevice;
	}

	public static class TestDevice {

		private final String name;
		private final Dimension screenSize;
		private final List<String> tags;

		public TestDevice(String name, Dimension screenSize, List<String> tags) {
			this.name = name;
			this.screenSize = screenSize;
			this.tags = tags;
		}

		public Dimension getScreenSize() {
			return screenSize;
		}

		protected List<String> getTags() {
			return tags;
		}
	}

	@Parameterized.Parameters
	public static Iterable<Object[]> devices() {
		return Arrays.asList(new Object[][] {
				{ new TestDevice("desktop", new Dimension(1024, 800), Arrays.asList("normal", "desktop")) },
				// { new TestDevice("small-phone", new Dimension(280, 800),
				// Arrays.asList("small-phone", "phone", "mobile")) },
				// { new TestDevice("normal-phone", new Dimension(320, 800),
				// Arrays.asList("normal-phone", "phone", "mobile")) }

		});
	}

	@Test
	public void shouldRenderBlogArticleCorrect() throws Exception {
		load("http://www.hascode.com/2016/05/load-testing-web-applications-with-gatling-and-maven/", 1024, 768);
		checkLayout("/specs/hascodeArticle.spec", Arrays.asList("mobile"));
	}
}
