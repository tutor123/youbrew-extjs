package com.jacobheric.youbrew.service;

import com.jacobheric.AbstractContextBaseTest;
import com.jacobheric.youbrew.dao.criteria.ExpenseCriteria;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author: jacob
 * @created: Oct 25, 2010
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   see <http://www.gnu.org/licenses/>.
 */
public class ExpenseServiceTest extends AbstractContextBaseTest {
	private Logger log = LoggerFactory.getLogger(ExpenseServiceTest.class);

	/*
	@Autowired
	private IRecipeService recipeService;



	@Test
	public void testFindRecipByName() throws Exception{
		RecipeCriteria rc = new RecipeCriteria();
		rc.setQuery("ale");
		List<Recipe> recipes = this.recipeService.search(rc);

		assertTrue("No recipes found", recipes.size() > 0);

		for (Recipe recipe : recipes)
			System.out.println(recipe.getName());
	}
	*/

}
