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

	private DeviceSetup device;

	public BlogArticlePageTest(final DeviceSetup deviceSetup) {
		super();
		this.device = deviceSetup;
	}

	public static class DeviceSetup {
		private final Dimension screenSize;
		private final List<String> tags;

		public DeviceSetup(Dimension screenSize, String... tags) {
			this.screenSize = screenSize;
			this.tags = Arrays.asList(tags);
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
		return Arrays.asList(new Object[][] { { new DeviceSetup(new Dimension(1024, 800), "normal", "desktop") },
				{ new DeviceSetup(new Dimension(280, 800), "small-phone", "phone", "mobile") },
				{ new DeviceSetup(new Dimension(320, 800), "normal-phone", "phone", "mobile") }

		});
	}

	@Test
	public void shouldRenderBlogArticleCorrect() throws Exception {
		load("http://www.hascode.com/2016/05/load-testing-web-applications-with-gatling-and-maven/",
				device.getScreenSize().getWidth(), device.getScreenSize().getHeight());
		checkLayout("/specs/hascodeArticle.spec", device.getTags());
	}
}
