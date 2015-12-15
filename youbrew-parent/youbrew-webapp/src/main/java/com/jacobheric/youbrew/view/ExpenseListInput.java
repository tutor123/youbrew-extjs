package com.jacobheric.youbrew.view;

import com.jacobheric.youbrew.domain.Expense;

import java.util.List;

/**
 * A thin, dumb wrapper to help jackson deserialize standard icoming extjs store json (which features a root element of "items")
 * @user: jacob
 * @created: 2/18/2011
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
public class ExpenseListInput {
	List<Expense> items;

	public ExpenseListInput() {
	}

	public List<Expense> getItems() {
		return items;
	}

	public void setItems(List<Expense> items) {
		this.items = items;
	}
}
