/*
 * Description: Hop service interface contract. 
 * 
 * @author: jacob
 * @created: 1/27/2010
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
package com.jacobheric.youbrew.service.contract;

import java.util.List;

import com.jacobheric.youbrew.dao.criteria.ExpenseCriteria;
import com.jacobheric.youbrew.domain.Expense;

@SuppressWarnings("unused")
public interface IExpenseService {
	public Expense insert(Expense expense);

	public Expense update(Expense expense);

	public void delete(Expense expense);

	public void deleteById(Long id);

	public Expense findById(Long id);

	public List<Expense> findByExample(Expense exampleClass, String[] excludeProperty);

	public List<Expense> findAll();

	/**
	 * @param criteria - the recipe criteria object
	 * @return - list of found recipes
	 */
	public List<Expense> search(ExpenseCriteria criteria);
}
