/**
 * Description: Recipe Controller
 * @author Jacob Heric
 * October, 25 2010
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
package com.jacobheric.youbrew.controller;

import com.jacobheric.youbrew.dao.criteria.ExpenseCriteria;
import com.jacobheric.youbrew.domain.Expense;
import com.jacobheric.youbrew.service.contract.IExpenseService;
import com.jacobheric.youbrew.view.DeleteInput;
import com.jacobheric.youbrew.view.ExpenseListInput;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(value="/expense")
public class ExpenseController extends BaseController implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);

	@Autowired
	private IExpenseService expenseService;


	/**
	 * Default constructor
	 */
	public ExpenseController() {
	}

	@RequestMapping(method = {RequestMethod.GET})
	public @ResponseBody ServiceResultWrapper<Expense> SearchRecipes(@ModelAttribute ExpenseCriteria criteria)
	{
		return new ServiceResultWrapper(this.expenseService.search(criteria), true, "Search successful", criteria.getTotal());
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ServiceResultWrapper<List<Expense>> addRecipes(@RequestBody ExpenseListInput expenseListInput)
	{

		List<Expense> expenses = expenseListInput.getItems();
		//
		//some reasonable defaults
		ServiceResultWrapper<List<Expense>> result = new ServiceResultWrapper<List<Expense>>(null, true, "Expense(s) saved successfully", 0);

		//
		//Validate this entity
		this.validateEntities(result, expenses);

		if (!result.getSuccess()){
			return result;
		}

		try {

			//
			//Must send back all data including generated PK for UI grids to synch up
			for (Expense r : expenses){
				expenseService.insert(r);
			}

			result.setItem(expenses);

		}
		catch(Exception e){
			log.error("error", e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
			result.setItem(expenses);
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody ServiceResultWrapper<Void> updateRecipes(@RequestBody ExpenseListInput expenseListInput)
	{

		List<Expense> expenses = expenseListInput.getItems();
		//
		//some reasonable defaults
		ServiceResultWrapper<Void> result = new ServiceResultWrapper<Void>(null, true, "expense(s) updated successfully", 0);

		//
		//Validate this entity
		this.validateEntities(result, expenses);

		if (!result.getSuccess()){
			return result;
		}

		try {

			for (Expense r : expenses){
				//
				//Load the recipe from db
					
				
				expenseService.update(r);
			}

		}
		catch(Exception e){
			log.error("error", e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());			
		}

		return result;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody ServiceResultWrapper<Void> deleteExpenses(@RequestBody DeleteInput delInput)
	{
		//
		//some reasonable defaults
		ServiceResultWrapper<Void> result = new ServiceResultWrapper<Void>(null, true, "expense(s) deleted successfully", 0);

		try {

			for (Long id : delInput.getItems()){
				expenseService.deleteById(id);
			}

		}
		catch(Exception e){
			log.error("error", e);
			result.setSuccess(false);
			result.setMessage(e.getMessage());
		}

		return result;
	}

	/**
	 * TODO:  move to yeast controller
	 * @return list of available yeasts
	 */
	@RequestMapping(value = "/expense/list", method = RequestMethod.GET)
	public @ResponseBody ServiceResultWrapper<Expense> getYeasts()
	{
		List<Expense> expensesall = this.expenseService.findAll();
		return new ServiceResultWrapper(expensesall, true, "", expensesall.size());
	}

}