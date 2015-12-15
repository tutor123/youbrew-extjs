
/**
 * @author: jacob
 * @date: Oct 25, 2010
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
package com.jacobheric.youbrew.service.impl;

import com.jacobheric.youbrew.dao.contract.IExpenseDAO;
import com.jacobheric.youbrew.dao.criteria.ExpenseCriteria;
import com.jacobheric.youbrew.domain.Expense;
import com.jacobheric.youbrew.service.contract.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("expenseService")
public class ExpenseServiceImpl implements IExpenseService {

	@Autowired
	IExpenseDAO expenseDAO;
	
	public Expense insert(Expense expense) {
		return (Expense) this.expenseDAO.insert(expense);
	}

	public Expense update(Expense o) {
		return (Expense) this.expenseDAO.update(o);
	}

	public void delete(Expense o) {
		this.expenseDAO.delete(o);
	}

	public void deleteById(Long id) {
		this.delete(this.findById(id));
	}

	public Expense findById(Long id) {
		 return (Expense) this.expenseDAO.findById(id);
	}

	public List<Expense> findByExample(Expense exampleClass, String[] excludeProperty) {
		return (List<Expense>) this.expenseDAO.findByExample(exampleClass, excludeProperty);
	}

	public List<Expense> findAll() {
		return (List<Expense>) this.expenseDAO.findAll();
	}
	public List<Expense> search(ExpenseCriteria criteria){
		return this.expenseDAO.search(criteria);
	}


}
