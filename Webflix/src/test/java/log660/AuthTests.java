/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package log660;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.regex.Pattern;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * A Basic Spring MVC Test for the Sample Controller"
 * 
 * @author Biju Kunjummen
 * @author Doo-Hwan, Kwak
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class AuthTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testAuthHome() throws Exception {
		this.mockMvc.perform(get("/auth/login")).andExpect(status().isOk());
	}

    @Test
    public void testEmptyEmail() throws Exception {
        this.mockMvc.perform(post("/auth/signin").param("email","").param("password","lapin"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(header().string("location", RegexMatcher.matches("/auth/login")));
    }

    @Test
    public void testNoEmail() throws Exception {
        this.mockMvc.perform(post("/auth/signin").param("password","lapin"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(header().string("location", RegexMatcher.matches("/auth/login")));
    }

    @Test
    public void testEmptyPassword() throws Exception {
        this.mockMvc.perform(post("/auth/signin").param("email","lapin").param("password",""))
                .andExpect(status().isMovedTemporarily())
                .andExpect(header().string("location", RegexMatcher.matches("/auth/login")));
    }

    @Test
    public void testNoPassword() throws Exception {
        this.mockMvc.perform(post("/auth/signin").param("email","lapin"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(header().string("location", RegexMatcher.matches("/auth/login")));
    }

	private static class RegexMatcher extends TypeSafeMatcher<String> {
		private final String regex;

		public RegexMatcher(String regex) {
			this.regex = regex;
		}

		public static org.hamcrest.Matcher<String> matches(String regex) {
			return new RegexMatcher(regex);
		}

		@Override
		public boolean matchesSafely(String item) {
			return Pattern.compile(this.regex).matcher(item).find();
		}

		@Override
		public void describeMismatchSafely(String item, Description mismatchDescription) {
			mismatchDescription.appendText("was \"").appendText(item).appendText("\"");
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("a string that matches regex: ")
					.appendText(this.regex);
		}
	}
}
