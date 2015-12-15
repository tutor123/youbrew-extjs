/*
 * Description: Hop DAO, implements the hop dao interface & extends the baseDao
 * 
 * @author: jacob heric
 * Date: 1/27/2010
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
package com.jacobheric.youbrew.dao.impl;

import com.jacobheric.youbrew.dao.contract.IExpenseDAO;
import com.jacobheric.youbrew.dao.criteria.ExpenseCriteria;
import com.jacobheric.youbrew.domain.Expense;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("expenseDAO")
public class ExpenseDAOImpl extends BaseDAOImpl<Expense, Long> implements IExpenseDAO {
	@SuppressWarnings("unchecked")
	public List<Expense> search(ExpenseCriteria expenseCriteria) {

		Criteria c = this.getSessionFactory().getCurrentSession().createCriteria(Expense.class);

		//
		//Property restrictions
		if (expenseCriteria.getQuery() != null) {
			Disjunction d = Restrictions.disjunction();

			d.add(Restrictions.like("name", expenseCriteria.getQuery().trim(), MatchMode.ANYWHERE));
			d.add(Restrictions.like("tasteNotes", expenseCriteria.getQuery().trim(), MatchMode.ANYWHERE));
			d.add(Restrictions.like("brewNotes", expenseCriteria.getQuery().trim(), MatchMode.ANYWHERE));

			c.add(d);
		}

		//
		//Determine the total before limiting (useful for paging)
		c.setProjection(Projections.rowCount());
		expenseCriteria.setTotal(((Integer) c.uniqueResult()).intValue());
		c.setProjection(null);
		c.setResultTransformer(Criteria.ROOT_ENTITY);
		

		//
		//Start & limit grid page restrictions
		if (expenseCriteria.getStart() != null){
			c.setFirstResult(expenseCriteria.getStart());
		}

		if (expenseCriteria.getLimit() != null){
			c.setMaxResults(expenseCriteria.getLimit());
		}

		

		return c.list();
	}	


}
