package br.com.pedromonteiro.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoryTest {

	@Test
	public void testNewCategory() {
		Assertions.assertNotNull(new Category());
	}
}
